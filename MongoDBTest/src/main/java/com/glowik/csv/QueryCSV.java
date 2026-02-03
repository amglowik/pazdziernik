package com.glowik.csv;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class QueryCSV {
    static Logger logger = Logger.getLogger(QueryCSV.class);
    private DB db = null;
    private String decimalFormatString = "0.0000";

    public QueryCSV(DB db) {
        this.db = db;
    }

    public String getCommonLog(Float number) {
        String result = "0.0000";
        String characteristic = "0";
        String mantissa = "0000";
        String decimal = getDecimal(number);

        // Get the characteristic.
        // TODO: Get the characteristic.
        characteristic = getCharacteristic(number);

        // Get the mantissa.
        String rowString = decimal.substring(0, 2);
        String colString = decimal.substring(2);
        logger.debug("decimal number: " + decimal);
        logger.debug("decimal number row: " + rowString);
        logger.debug("decimal number col: " + colString);
        DBCollection coll = db.getCollection("commonLogMantissa");
        // BasicDBObject query = new BasicDBObject("row", "13").append("col", "7");
        BasicDBObject query = new BasicDBObject("row", rowString).append("col", colString);
        DBCursor cursor = coll.find(query);

        try {
            while (cursor.hasNext()) {
                mantissa = cursor.next().get("mantissa").toString();
                logger.debug("result: " + mantissa);
            }
        } finally {
            cursor.close();
        }

        result = characteristic + "." + mantissa;
        return result;
    }

    private String getCharacteristic(Float number) {
        String result = getFormattedNumer(number);
        int characteristic = 0;

        // Find the standard position. the standard position is the position
        // immediately to the right of the first non-zero digit.
        int j = 0;
        // Find character of first non-zero
        char[] rep = result.toCharArray();
        for (j = 0; j < rep.length; ++j) {
            if (rep[j] == '0')
                break;

            if (rep[j] != '.' && rep[j] != '0')
                break;
        }

        // Count from this position to the decimal.
        if (rep[j] != '0') {
            characteristic = -1;
            for (int k = j; k < rep.length; ++k) {
                if (rep[k] != '.') {
                    ++characteristic;
                } else {
                    break;
                }

            }
        }

        logger.debug("characterictic of " + result + " first non-zero: " + j);

        return new Integer(characteristic).toString();
    }

    /**
     * From a float like "13.7", get a string "137"
     * 
     * @param number
     * @return
     */
    private String getDecimal(Float number) {
        String result = getFormattedNumer(number);
        result = result.replaceAll("[.]", "");
        Pattern p = Pattern.compile("(\\d){3}");
        Matcher m = p.matcher(result);

        if (m.find()) {
            result = m.group(0);
        }
        return result;
    }

    private String getFormattedNumer(Float number) {
        String result = "0.0000";
        DecimalFormat df = new DecimalFormat(this.decimalFormatString);

        result = df.format(number);
        return result;
    }
}
