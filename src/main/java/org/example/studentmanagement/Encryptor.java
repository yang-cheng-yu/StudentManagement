// -------------------------------------------------------
// Final Project
// Written by: Cheng Yu Yang 2363504
// For “Programming 2” Section 1 – Fall 2024
// --------------------------------------------------------

/*
NOTE: This is code from a previous project I adapted for this case.
 */

package org.example.studentmanagement;

import java.io.*;
import java.util.ArrayList;

public class Encryptor implements FileHandler {

    private final static char key = 'x';

    // Encrypt with XOR
    public static String xorEncrypt(String input) {

        char[] inputChars = input.toCharArray();
        char[] encryptedChars = new char[input.length()];

        for (int i = 0; i < input.length(); i++) {
            encryptedChars[i] = (char) (inputChars[i] ^ key);
        }

        return new String(encryptedChars);
    }

    // Decrypt with XOR (same)
    public static String xorDecrypt(String input) {
        return xorEncrypt(input); // XOR encryption is symmetrical
    }

    @Override
    public void saveToFile(File file, ArrayList<Student> students) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Student s : students) {
                String data = s.getName() + "," + s.getGender() + "," + s.getGpa();

                String encryptedData = xorEncrypt(data);

                bw.write(encryptedData);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Student> loadFromFile(File file) {
        ArrayList<Student> out = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String decryptedLine = xorDecrypt(line);
                String[] attributes = decryptedLine.split(",");
                if (attributes.length == 3) {
                    String name = attributes[0];
                    char gender = attributes[1].toUpperCase().charAt(0);
                    if (gender != 'M' && gender != 'F') {
                        return null;
                    }
                    double gpa = Double.parseDouble(attributes[2]);
                    out.add(new Student(name, gender, gpa));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return out;
    }
}
