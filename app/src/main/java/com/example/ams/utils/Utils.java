package com.example.ams.utils;

/**
 * Project Name : AMS
 *
 * @author VE00YM572
 * @company YMSLI
 * @date 15-1-2024
 * Copyright (c) 2024, Yamaha Motor Solutions (INDIA) Pvt Ltd.
 * Revision History
 * ----------------------------------------------------------
 * Modified By          Modified On
 * VE00YM572            15-1-2024
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Utils
{

    public static String generateId() {
        StringBuilder idBuilder = new StringBuilder("VE00");

        // Generate 5 random characters or digits
        Random random = new Random();
        String characters = Constants.ID_DICTIONARY;

        for (int i = 0; i < 5; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            idBuilder.append(randomChar);
        }

        return idBuilder.toString();
    }


    public static String generateAssetId(String text)
    {
        StringBuilder idBuilder = new StringBuilder(text);

        // Generate 7 random characters or digits
        Random random = new Random();
        String characters = Constants.ID_DICTIONARY;

        for (int i = 0; i < 7; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            idBuilder.append(randomChar);
        }

        return idBuilder.toString();
    }

    public static String getCurrentDateTime() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Define the desired date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the current date and time using the formatter
        String formattedDateTime = now.format(formatter);

        return formattedDateTime;
    }


}
