package org.example;

import java.util.ArrayList;

public interface FileHandler {
    void saveToFile(String fileName, ArrayList<Student> students);
    void loadFromFile(String fileName);
}
