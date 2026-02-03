package com.glowik.csv;

import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import au.com.bytecode.opencsv.CSVReader;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class ImportCSV {
    static Logger logger = Logger.getLogger(ImportCSV.class);
    private FileReader fr = null;
    private DB db = null;

    public ImportCSV(FileReader fr, DB db) {
        this.fr = fr;
        this.db = db;
    }

    public void run() throws IOException {
        String[] nextLine = null;
        CSVReader cvsReader = new CSVReader(this.fr);
        while ((nextLine = cvsReader.readNext()) != null) {

            // Make sure the line starts with an integer.
            try {
                // The header line will start with the integer "-1" so we can exclude this.
                if (Integer.parseInt(nextLine[0]) != -1) {
                    createRecord(nextLine);
                    /*
                     * StringBuffer sb = new StringBuffer(); for (String nl : nextLine) {
                     * sb.append(nl); sb.append(" "); } logger.debug(sb);
                     */
                }
            } catch (NumberFormatException e) {
                logger.debug("not a number");
            }
        }

        // Close the underlying reader.
        if (cvsReader != null) {
            cvsReader.close();
        }
    }

    private void createRecord(String[] line) {
        // This is debug stuff.
        StringBuffer sb = new StringBuffer();
        for (String nl : line) {
            sb.append(nl);
            sb.append(" ");
        }
        logger.debug("Creating: " + sb);

        // Insert records into db collection.
        for (int j = 1; j < line.length; j++) {
            BasicDBObject doc = new BasicDBObject("row", line[0]).append("col",
                    Integer.toString(j - 1)).append("mantissa", line[j]);
            // TODO: Collection name can be parameterized or the collection, coll, itself can
            // be paramererized.
            DBCollection coll = db.getCollection("commonLogMantissa");
            coll.insert(doc);
        }

    }

    public static void main(String[] args) {
        logger.debug("Hello ImportCSV!");

    }

}
