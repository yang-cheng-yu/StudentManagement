// -------------------------------------------------------
// Final Project
// Written by: Cheng Yu Yang 2363504
// For “Programming 2” Section 1 – Fall 2024
// --------------------------------------------------------

package org.example;

public class Student extends Person{

    private int id;
    private double gpa;
    public static int nextID = 1;

    public Student () {
        id = nextID++;
    }

    public Student (String name, double gpa) {
        id = nextID++;
        this.name = name;
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
}
