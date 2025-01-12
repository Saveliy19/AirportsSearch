package com.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InputFileReader {

    private InputFileReader() {}

    public static List<String> readFile(String filePath) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line.trim());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }
}
