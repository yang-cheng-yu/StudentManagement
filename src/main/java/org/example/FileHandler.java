// -------------------------------------------------------
// Final Project
// Written by: Cheng Yu Yang 2363504
// For “Programming 2” Section 1 – Fall 2024
// --------------------------------------------------------

package org.example;

import java.util.ArrayList;

public interface FileHandler {
    void saveToFile(String fileName, ArrayList<Student> students);
    ArrayList<Student> loadFromFile(String fileName);
}
