package com.example.coursehubmanager.database;

import androidx.room.TypeConverter;


import java.util.Date;


public class DateConverter {
    @TypeConverter
    public static Long fromDate(Date date) {
        if (date != null) {
            return date.getTime();
        }
        return null;
    }
    @TypeConverter
    public static Date toDate(Long milliseconds) {
        if (milliseconds != null) {
            return new Date(milliseconds);
        }
        return null;
    }
}
