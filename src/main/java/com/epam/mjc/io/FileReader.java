package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;


public class FileReader {

    public Profile getDataFromFile(File file) {
        StringBuilder data = new StringBuilder();
        try (FileInputStream fr = new FileInputStream(file)) {
            int c;
            while ((c = fr.read()) != -1) {
                data.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<String, String> parsedData = new HashMap<>();

        String[] lines = data.toString().split("\n");
        for (String line : lines) {
            String[] parts = line.split(": ", 2);
            parsedData.put(parts[0].trim(), parts[1].trim());
        }

        Profile profile = new Profile(
                parsedData.get("Name"),
                Integer.parseInt(parsedData.get("Age")),
                parsedData.get("Email"),
                Long.parseLong(parsedData.get("Phone"))
        );

        System.out.println(profile);

        return profile;

    }
}
