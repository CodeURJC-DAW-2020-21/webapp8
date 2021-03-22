package com.practicaweb.practicadaw.auxClasses;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class auxiliar {

    private static final Path IMAGES_FOLDER = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/profileImages");

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

    public static String getPathImage (String fileName){
        return "profileImages/" + fileName;
    }
}