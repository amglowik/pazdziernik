package com.glowik.csv;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class ImportCVSTest {

    @Test
    public void importTest() throws IOException {
        // Connect to MongoDB
        MongoClient mongoClient = new MongoClient("localhost", 41000);

        // Get the MongoDB database
        mongoClient.dropDatabase("mathematical");
        DB db = mongoClient.getDB("mathematical");

        // Get the CSV file.
        FileReader fr = new FileReader(new File(this.getClass()
                .getResource("/LogMantissaTable.csv").getFile()));
        ImportCSV c = new ImportCSV(fr, db);

        // The action starts here.
        c.run();

        for (String s : mongoClient.getDatabaseNames()) {
            System.out.println(s);
        }

        // Cleanup
        if (mongoClient != null) {
            mongoClient.close();
        }

    }
}
