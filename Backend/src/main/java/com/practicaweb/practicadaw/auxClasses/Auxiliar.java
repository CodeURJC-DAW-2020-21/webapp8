package com.practicaweb.practicadaw.auxClasses;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Auxiliar {

    private static final Path IMAGES_FOLDER = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/profileImages");

    public static String getPathImage (String fileName){
        return "profileImages/" + fileName;
    }
}