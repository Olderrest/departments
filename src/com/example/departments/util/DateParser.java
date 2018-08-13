package com.example.departments.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    public static Date parseDate(String value) {
        String datePattern = "yyyy-MM-dd";
        DateFormat format = new SimpleDateFormat(datePattern);
        Date date = null;
        try {
            date = format.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
