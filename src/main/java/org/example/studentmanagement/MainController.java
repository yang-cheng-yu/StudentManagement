// -------------------------------------------------------
// Final Project
// Written by: Cheng Yu Yang 2363504
// For “Programming 2” Section 1 – Fall 2024
// --------------------------------------------------------

package org.example.studentmanagement;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainController implements Initializable {

    FileChooser fileChooser = new FileChooser();

    @FXML
    private Button minimize;

    @FXML
    private Button close;

    @FXML
    private TextField idSearch;

    @FXML
    private Label status1;

    @FXML
    private Button addStudent;

    @FXML
    private TableView<Student> table;

    @FXML
    private TableColumn<Student, Integer> idcol;

    @FXML
    private TableColumn<Student, String> namecol;

    @FXML
    private TableColumn<Student, Character> gendercol;

    @FXML
    private TableColumn<Student, Double> gpacol;

    @FXML
    private Label name;

    @FXML
    private Label gender;

    @FXML
    private Label gpa;

    @FXML
    private Button remove;

    @FXML
    private Label path;

    @FXML
    private HBox topBar;

    @FXML
    private VBox mainPane;

    @FXML
    private VBox sidePane;

    @FXML
    private HBox bottomBar;

    @FXML
    private Button exitButton;

    File currentFile;
    Student currentStudent;
    Encryptor encryptor = new Encryptor();

    public static ObservableList<Student> studentList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        topBar.setBackground(new Background(new BackgroundFill(Palette.LIGHT_PINK, null, null)));
        mainPane.setBackground(new Background(new BackgroundFill(Palette.LIGHT_GRAY, null, null)));
        sidePane.setBackground(new Background(new BackgroundFill(Palette.LIGHT_GRAY, null, null)));
        bottomBar.setBackground(new Background(new BackgroundFill(Palette.GRAY, null, null)));

        close.setBackground(new Background(new BackgroundFill(Palette.PINK, null, null)));
        minimize.setBackground(new Background(new BackgroundFill(Palette.PINK, null, null)));
        exitButton.setBackground(new Background(new BackgroundFill(Palette.PINK, null, null)));

        idcol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        namecol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        gendercol.setCellValueFactory(new PropertyValueFactory<Student, Character>("gender"));
        gpacol.setCellValueFactory(new PropertyValueFactory<Student, Double>("gpa"));

        currentFile = null;
        currentStudent = null;
        fileChooser.setInitialDirectory(new File("C:\\"));

        remove.setVisible(false);

        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                searchBind(newValue.getId());
            }
        });
    }

    @FXML
    void drag(MouseEvent event) {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setX(event.getScreenX());
        stage.setY(event.getScreenY());
    }

    @FXML
    void addStudentGUI(MouseEvent event) {
        try {
            Stage stage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addStudent.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Add Student");
            stage.setScene(scene);
            stage.show();

            AddStudentController controller = fxmlLoader.getController();
            controller.setController(this);

            addStudent.setDisable(true);

        } catch (Exception e) {
            System.out.println("Something went wrong...");
            addStudent.setDisable(false);
        }
    }

    @FXML
    void chooseFile(MouseEvent event) {
        try {
            currentFile = fileChooser.showOpenDialog(new Stage());
            Scanner scanner = new Scanner(currentFile);
            path.setText(currentFile.getPath());
        } catch (FileNotFoundException e) {
            path.setText("Error: No file found");
        }
    }

    @FXML
    void save(MouseEvent event) {
        if (currentFile != null) {
            ArrayList<Student> list = new ArrayList<>(studentList);
            encryptor.saveToFile(currentFile, list);
            status1.setTextFill(Color.GREEN);
            status1.setText("Saved successfully");
        } else {
            status1.setTextFill(Color.RED);
            status1.setText("Error: Something went wrong...");
        }
    }

    @FXML
    void load(MouseEvent event) {
        if (currentFile != null) {
            ArrayList<Student> arrayList = encryptor.loadFromFile(currentFile);

            if (arrayList == null) {
                status1.setTextFill(Color.RED);
                status1.setText("Error: Something went wrong...");
                return;
            }

            studentList = FXCollections.observableArrayList(arrayList);

            int maxId = 0;
            for (Student s : studentList) {
                int id = s.getId();
                if (id > maxId) {
                    maxId = id;
                }
            }
            Student.setNextID(maxId);

            updateTable();

            status1.setTextFill(Color.GREEN);
            status1.setText("Loaded successfully");
        } else {
            status1.setTextFill(Color.RED);
            status1.setText("Error: Something went wrong...");
        }
    }

    @FXML
    void min(MouseEvent event) {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void removeStudent(MouseEvent event) {
        try {
            studentList.remove(currentStudent);
            currentStudent = null;
            updateStudent();
            status1.setTextFill(Color.GREEN);
            status1.setText("Student removed successfully");
        } catch (Exception e) {
            status1.setTextFill(Color.RED);
            status1.setText("Error: Could not remove student. Something went wrong...");
        }
    }

    @FXML
    void search(MouseEvent event) {
        try {
            searchBind(Integer.parseInt(idSearch.getText()));
        } catch (NumberFormatException e) {
            status1.setTextFill(Color.RED);
            status1.setText("Error: ID must be a number");
        }
    }

    void searchBind(int id) {
        boolean found = false;
        for (Student s : studentList) {
            if (s.getId() == id) {
                currentStudent = s;
                found = true;
                break;
            }
        }

        if (!found) {
            currentStudent = null;
            updateStudent();

            status1.setTextFill(Color.RED);
            status1.setText("Error: Student not found");
            return;
        }
        updateStudent();
        status1.setText("");
    }

    @FXML
    void exit(MouseEvent event) {
        boolean answer = confirmation();
        if (answer) {
            Platform.exit();
        }
    }

    void updateTable() {
        table.setItems(studentList);
    }

    void updateStudent() {
        if (currentStudent == null) {
            name.setText("");
            gender.setText("");
            gpa.setText("");
            remove.setVisible(false);
        } else {
            name.setText(currentStudent.getName());
            gender.setText("" + currentStudent.getGender());
            gpa.setText(String.valueOf(currentStudent.getGpa()));
            remove.setVisible(true);
        }
    }

    private boolean confirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit confirmation");
        alert.setHeaderText("Are you sure you want quit Student Management?");
        alert.setContentText("Click 'Ok' to close or 'Cancel' to cancel.");

        alert.showAndWait();

        return alert.getResult().getText().equals("OK");
    }

    public void enableAddStudent() {
        addStudent.setDisable(false);
    }
}
