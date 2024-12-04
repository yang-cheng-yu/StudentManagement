// -------------------------------------------------------
// Final Project
// Written by: Cheng Yu Yang 2363504
// For “Programming 2” Section 1 – Fall 2024
// --------------------------------------------------------

package org.example.studentmanagement;

public class Student extends Person{

    private int id;
    private double gpa;
    private static int nextID = 1;

    public Student () {
        id = nextID++;
    }

    public Student (String name, char gender, double gpa) {
        id = nextID++;
        this.name = name;
        this.gender = gender;
        this.gpa = gpa;
    }

    @Override
    String getDescription() {
        return "Name: " + name + "\nID: " + id + "\nGPA: " + gpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public static int getNextID() {
        return nextID;
    }

    public static void setNextID(int nextID) {
        Student.nextID = nextID;
    }
}
