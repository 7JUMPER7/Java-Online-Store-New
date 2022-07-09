package com.example.onlinestorenew.utils;

import java.util.UUID;

public class Helpers {
    public static String generateUUIDName(String filename) {
        if(!filename.equals("")) {
            String extension = filename.split("[.]")[1];
            String newName = UUID.randomUUID().toString().replace("-", "") + "." + extension;
            return newName;
        }
        return filename;
    }
}
