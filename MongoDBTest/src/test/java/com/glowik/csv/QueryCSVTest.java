package com.glowik.csv;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.DecimalFormat;

import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 * // start server on PIM % set-location C:\mongodb22\mongodb-win32-x86_64-2008plus-2.2.2\bin %
 * .\mongod -–rest -–dbpath node1 -–port 41000 -–smallfiles -–oplogSize 50
 * 
 * @date March 3, 2013
 * @author amglowik
 * 
 */
public class QueryCSVTest {
    private String decimalFormatString = "0.0000";

    @Test
    public void queryTest() throws IOException {
        // Connect to MongoDB
        MongoClient mongoClient = new MongoClient("localhost", 41000);

        // Get the MongoDB database
        DB db = mongoClient.getDB("mathematical");

        QueryCSV q = new QueryCSV(db);

        // The action starts here.
        // q.getCommonLog(new Float(0));

        String expectedResult = "0.0000";
        String actualResult = q.getCommonLog(new Float(0));
        assertEquals("Looking for common logarithm.", expectedResult, actualResult);

        // Test Java mathematical functions and formatting.
        expectedResult = "1.5877";
        double myNumber = 38.7;
        double d = Math.log10(myNumber);
        DecimalFormat df = new DecimalFormat(this.decimalFormatString);
        assertEquals("Looking for common logarithm.", expectedResult, df.format(d));

        expectedResult = "0.0000";
        myNumber = 1;
        d = Math.log10(myNumber);
        df = new DecimalFormat(this.decimalFormatString);
        assertEquals("Looking for common logarithm.", expectedResult, df.format(d));

        expectedResult = "12.9542";
        Double myBigNumber = new Double("9000000000000"); // 9E12
        d = Math.log10(myBigNumber);
        df = new DecimalFormat(this.decimalFormatString);
        assertEquals("Looking for common logarithm.", expectedResult, df.format(d));

        // Test MongoDB Mathematical Table lookup.
        expectedResult = "1.1367";
        actualResult = q.getCommonLog(new Float(13.7));
        assertEquals("Looking for common logarithm.", expectedResult, actualResult);

        // Test MongoDB Mathematical Table lookup.
        expectedResult = "2.0719";
        actualResult = q.getCommonLog(new Float(118.0));
        assertEquals("Looking for common logarithm.", expectedResult, actualResult);

        // Test MongoDB Mathematical Table lookup.
        expectedResult = "2.5809";
        actualResult = q.getCommonLog(new Float(381.0));
        assertEquals("Looking for common logarithm.", expectedResult, actualResult);

        // Compare with Java API
        String myJavaNumber = "15.90";
        myBigNumber = new Double(myJavaNumber);
        d = Math.log10(myBigNumber);
        df = new DecimalFormat(this.decimalFormatString);
        actualResult = q.getCommonLog(new Float(myJavaNumber));
        assertEquals("Looking for common logarithm.", df.format(d), actualResult);

    }

}
