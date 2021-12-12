package com.fstg.taxeboisson.domaine.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    public LocalDate dateToLocaleDate(Date date){
        LocalDate localDate = LocalDate.parse(date.toString(), DateTimeFormatter.ISO_LOCAL_DATE);
        return localDate;
    }
    public LocalDate stringToLocaleDate(String date){
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        return localDate;
    }
    public LocalDate getLocaleDateWithNumTrim(int numTrim,int year){
        String date = "";
        switch (numTrim){
            case 1 :
                date = "31-03-"+year;
                break;
            case 2 :
                date = "30-06-"+year;
                break;
            case 3 :
                date = "30-09-"+year;
                break;
            case 4 :
                date = "31-12-"+year;
                break;
        }
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        return localDate;
    }
    public LocalDate getLocaleDateWithMounth(int mounth,int year){
        String date = "";
        switch (mounth){
            case 1 :
                date = "31-01-"+year;
                break;
            case 2 :
                date = "28-02-"+year;
                break;
            case 3 :
                date = "31-03-"+year;
                break;
            case 4 :
                date = "30-04-"+year;
                break;
            case 5 :
                date = "31-05-"+year;
                break;
            case 6 :
                date = "30-06-"+year;
                break;
            case 7 :
                date = "31-07-"+year;
                break;
            case 8 :
                date = "31-08-"+year;
                break;
            case 9 :
                date = "30-09-"+year;
                break;
            case 10 :
                date = "31-10-"+year;
                break;
            case 11 :
                date = "30-11-"+year;
                break;
            case 12 :
                date = "31-12-"+year;
                break;
        }
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        return localDate;
    }
    public long getDaysOfNextTwoMounths(int numTrim,int year){
        LocalDate dateLeft = stringToLocaleDate("01-"+(3*numTrim+1)+"-"+year);
        LocalDate dateRight = getLocaleDateWithMounth((3*numTrim+2),year);
        long days = getDaysBetween(dateLeft,dateRight);
        return days;
    }
    public long getDaysBetween(LocalDate dateLeft,LocalDate dateRight){
        Duration duration = Duration.between(dateLeft, dateRight);
        long days = duration.toDays();
        return days;
    }
    public long getDays(LocalDate date){
        long days = 0;
        days = date.lengthOfMonth();
        return days;
    }
    public boolean leftGreaterThanRight(LocalDate dateLeft,LocalDate dateRight){
        boolean result = dateLeft.isAfter(dateRight);
        return result;
    }

    public boolean leftLessThanRight(LocalDate dateLeft,LocalDate dateRight){
        boolean result = dateLeft.isBefore(dateRight);
        return result;
    }
}
