package utils;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class FileManager {

    private static final String FILE_NAME = "port_data.txt";

    public static void writeData(String data) {

        try (FileWriter writer = new FileWriter(FILE_NAME)) {

            writer.write(data);

            System.out.println("Data saved successfully.");

        } catch (IOException e) {

            System.out.println("Error writing data: " + e.getMessage());
        }
    }

    public static void readData() {

        try (FileReader reader = new FileReader(FILE_NAME);
             BufferedReader bufferedReader = new BufferedReader(reader)) {

            String line;

            System.out.println("Port Data:");

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {

            System.out.println("Error reading data: " + e.getMessage());
        }
    }
}