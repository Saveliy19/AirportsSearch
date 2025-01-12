package com.utils;

import com.utils.interfaces.IFileReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InputFileReader implements IFileReader {

    public InputFileReader() {}

    public List<String> readFile(String filePath) {
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
