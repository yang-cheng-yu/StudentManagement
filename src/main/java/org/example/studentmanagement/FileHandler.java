// -------------------------------------------------------
// Final Project
// Written by: Cheng Yu Yang 2363504
// For “Programming 2” Section 1 – Fall 2024
// --------------------------------------------------------

package org.example.studentmanagement;

import java.io.File;
import java.util.ArrayList;

public interface FileHandler {
    void saveToFile(File file, ArrayList<Student> students);
    ArrayList<Student> loadFromFile(File file);
}
