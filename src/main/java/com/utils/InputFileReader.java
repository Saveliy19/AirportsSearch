package com.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InputFileReader {

    private InputFileReader() {}

    public static ArrayList<String> readFile(String filePath) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine().trim());
            }
        }
        return lines;
    }
}
