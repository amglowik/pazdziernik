package com.glowik.sample.client;

import java.net.UnknownHostException;
import java.util.Set;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * My first MongoDbClient.
 * 
 * February 18, 2013
 * 
 * Use MongoDB replica set example from IPD 351 AS#3 as data source. Use 3 nodes: %set-location
 * C:\mongodb22\mongodb-win32-x86_64-2008plus-2.2.2\bin ; %\mongod –-rest –-replSet
 * MatthewReplica –-dbpath node1 –-port 40000 –-smallfiles –-oplogSize 50
 * 
 * %set-location C:\mongodb22\mongodb-win32-x86_64-2008plus-2.2.2\bin ;%.\mongod –-rest
 * –-replSet MatthewReplica –-dbpath node2 –-port 40001 –-smallfiles –-oplogSize 50
 * 
 * %set-location C:\mongodb22\mongodb-win32-x86_64-2008plus-2.2.2\bin ; %.\mongod -–rest
 * –-replSet MatthewReplica –-dbpath node3 –-port 40002 –-smallfiles –-oplogSize 50
 */
public class MongoTestClient {
    static Logger logger = Logger.getLogger(MongoTestClient.class);
    private MongoClient mongoClient = null;
    private DB db = null;

    public void init() throws UnknownHostException {

        // MongoClient mongoClient = new MongoClient();
        // or
        // MongoClient mongoClient = new MongoClient("localhost");
        // or
        this.mongoClient = new MongoClient("localhost", 40001);
        logger.debug("Client made");
        // or, to connect to a replica set, supply a seed list of members
        /*
         * MongoClient mongoClient = new MongoClient(Arrays.asList(new
         * ServerAddress("localhost", 27017), new ServerAddress("localhost", 27018), new
         * ServerAddress("localhost", 27019)));
         */

        // Use replica set example from IPD AS#3.
        String database = "social";
        this.db = mongoClient.getDB(database);
        logger.debug("Connected to database:" + database);

    }

    public void cleanup() {
        if (this.mongoClient != null) {
            this.mongoClient.close();
        }
    }

    public void getCollectionNames() {
        if (this.db != null) {
            Set<String> colls = this.db.getCollectionNames();

            for (String s : colls) {
                logger.debug("Collection: " + s);
            }
        } else {
            logger.debug("No collections found. ");
        }

    }

    public void getDatabaseNames() {
        for (String s : this.mongoClient.getDatabaseNames()) {
            logger.debug("Database: " + s);
        }

    }

    public void manipulateCollection(String collectionName) {
        DBCollection coll = this.db.getCollection(collectionName);
        logger.debug("Collection " + collectionName + " has " + coll.count() + " documents");

        DBObject myDoc = coll.findOne();
        logger.debug("The first document is " + myDoc);

        DBCursor cursor = coll.find();
        try {
            int i = 1;
            while (cursor.hasNext()) {
                logger.debug("Document + " + i++ + " is: " + cursor.next());
            }
        } finally {
            cursor.close();
        }

    }

    public static void main(String[] args) {
        logger.debug("Hello MongoDb!");
        MongoTestClient c = new MongoTestClient();
        try {
            c.init();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        c.getDatabaseNames();
        c.getCollectionNames();
        c.manipulateCollection("friends");
        c.cleanup();
    }
}
