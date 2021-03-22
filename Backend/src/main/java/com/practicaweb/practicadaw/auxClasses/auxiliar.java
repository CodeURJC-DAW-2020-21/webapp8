package com.practicaweb.practicadaw.auxClasses;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class auxiliar {

    public static java.util.Date getActualDate(){
        try{
            Calendar auxDate = new GregorianCalendar();
            DateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date actualDate = ft.parse("2014-10-25 20:00:00");
            return actualDate;
        }
        catch (ParseException exc){
            return null;
        }
    }
}
