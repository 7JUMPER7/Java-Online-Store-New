package com.example.onlinestorenew.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtil {
    public static final String folderPath =  "src/main/resources/static/public/images/";
    public static final Path filePath = Paths.get(folderPath);

    public static boolean uploadFiles(MultipartFile[] files) {
        try {
            createDirIfNotExist();

            List<String> fileNames = new ArrayList<>();

            Arrays.asList(files).stream().forEach(file -> {
                byte[] bytes = new byte[0];
                try {
                    bytes = file.getBytes();
                    Files.write(Paths.get(folderPath + file.getOriginalFilename()), bytes);
                    fileNames.add(file.getOriginalFilename());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });

            System.out.println("Files uploaded successfully: " + fileNames);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean uploadFile(MultipartFile file, String newName) {
        try {
            createDirIfNotExist();

            byte[] bytes = new byte[0];
            try {
                bytes = file.getBytes();
                Files.write(Paths.get(folderPath + newName), bytes);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Files uploaded successfully: " + newName);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean deleteFile(String filename) {
        try {
            return Files.deleteIfExists(Paths.get(folderPath + filename));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private static void createDirIfNotExist() {
        File directory = new File(filePath.toString());
        if (! directory.exists()){
            directory.mkdir();
            System.out.println("CREATED DIR");
        }
    }
}
