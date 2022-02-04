package com.fstg.taxeboisson.domaine.utils;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    public LocalDate dateToLocaleDate(Date date){
    	
        LocalDate localDate =  date.toInstant()
        	      .atZone(ZoneId.systemDefault())
        	      .toLocalDate();
        System.out.println(localDate+" locate date");
        return localDate;
    }
    public LocalDate stringToLocaleDate(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
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
            default : return null;
        }
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
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
            default : return null;
        }
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
    public long getDaysOfNextTwoMounths(int numTrim,int year){
    	
        LocalDate dateLeft = getLocaleDateWithMounth((3*numTrim),year);
        LocalDate dateRight;
        if(numTrim == 4) {
        	 dateRight = getLocaleDateWithMounth(2,year+1);
        }else {
        	 dateRight = getLocaleDateWithMounth((3*numTrim+2),year);
        }
        
        System.out.println(dateLeft+" **aaaah** "+dateRight);
        
		return getDaysBetween(dateLeft,dateRight);
    }
    public long getDaysBetween(LocalDate dateLeft,LocalDate dateRight){
    	
    	long days = ChronoUnit.DAYS.between(dateLeft, dateRight);
        return Math.abs(days);
    }
    public long getDays(LocalDate date){
        long days = 0;
        days = date.lengthOfMonth();
        return days;
    }
    public boolean leftGreaterThanRight(LocalDate dateLeft,LocalDate dateRight){
        return dateLeft.isAfter(dateRight);
    }

    public boolean leftLessThanRight(LocalDate dateLeft,LocalDate dateRight){
        return dateLeft.isBefore(dateRight);
    }
}
