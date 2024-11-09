// -------------------------------------------------------
// Final Project
// Written by: Cheng Yu Yang 2363504
// For “Programming 2” Section 1 – Fall 2024
// --------------------------------------------------------

package org.example;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainGUI extends Application {

    @Override
    public void init() throws Exception {
        Student.nextID = 1;
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}