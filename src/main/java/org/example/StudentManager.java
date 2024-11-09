// -------------------------------------------------------
// Final Project
// Written by: Cheng Yu Yang 2363504
// For “Programming 2” Section 1 – Fall 2024
// --------------------------------------------------------

package org.example;

import java.util.ArrayList;

public class StudentManager {

    private ArrayList<Student> students;

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int id) {
        students.remove(id);
    }

    public Student searchStudentById(int id) {
        try {
            return students.get(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
