// -------------------------------------------------------
// Final Project
// Written by: Cheng Yu Yang 2363504
// For “Programming 2” Section 1 – Fall 2024
// --------------------------------------------------------

/*
NOTE: This is code from a previous project I adapted for this case.
 */

package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Encryptor implements FileHandler {

    private final static String key = "x";

    // Encrypt with XOR
    public static String xorEncrypt(String input) {

        char[] keyChars = key.toCharArray();
        char[] inputChars = input.toCharArray();
        char[] encryptedChars = new char[input.length()];

        for (int i = 0; i < input.length(); i++) {
            encryptedChars[i] = (char) (inputChars[i] ^ keyChars[i % keyChars.length]);
        }

        return new String(encryptedChars);
    }

    // Decrypt with XOR (same)
    public static String xorDecrypt(String input) {
        return xorEncrypt(input); // XOR encryption is symmetrical
    }

    @Override
    public void saveToFile(String fileName, ArrayList<Student> students) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Student s : students) {
                String data = s.getName() + "," + s.getGpa();

                String encryptedData = xorEncrypt(data);

                bw.write(encryptedData);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Student> loadFromFile(String fileName) {
        ArrayList<Student> out = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String decryptedLine = xorDecrypt(line);
                String[] attributes = decryptedLine.split(",");
                if (attributes.length == 2) {
                    String name = attributes[0];
                    double gpa = Double.parseDouble(attributes[1]);
                    out.add(new Student(name, gpa));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return out;
    }
}
