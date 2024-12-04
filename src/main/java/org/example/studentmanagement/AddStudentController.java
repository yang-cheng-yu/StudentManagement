package org.example.studentmanagement;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddStudentController implements Initializable {

    @FXML
    private HBox topBar;

    @FXML
    private VBox bottomBar;

    @FXML
    private Button close;

    @FXML
    private ComboBox<Character> genderBox;

    @FXML
    private TextField gpaBox;

    @FXML
    private VBox mainPane;

    @FXML
    private Button minimize;

    @FXML
    private TextField nameBox;

    @FXML
    private Label nextId;

    @FXML
    private Label status;

    @FXML
    private Button submitButton;

    private MainController controller;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nextId.setText(String.valueOf(Student.getNextID()));
        genderBox.setItems(FXCollections.observableArrayList('M', 'F'));

        topBar.setBackground(new Background(new BackgroundFill(Palette.LIGHT_PINK, null, null)));
        mainPane.setBackground(new Background(new BackgroundFill(Palette.LIGHT_GRAY, null, null)));
        bottomBar.setBackground(new Background(new BackgroundFill(Palette.GRAY, null, null)));

        close.setBackground(new Background(new BackgroundFill(Palette.PINK, null, null)));
        minimize.setBackground(new Background(new BackgroundFill(Palette.PINK, null, null)));

        submitButton.setBackground(new Background(new BackgroundFill(Palette.PINK, null, null)));
    }

    @FXML
    void drag(MouseEvent event) {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setX(event.getScreenX());
        stage.setY(event.getScreenY());
    }

    @FXML
    void exit(MouseEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        controller.enableAddStudent();
        stage.close();
    }

    @FXML
    void min(MouseEvent event) {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void submit(MouseEvent event) {

        try {
            String name = nameBox.getText();

            if (genderBox.getValue() == null) {
                throw new GenderNotSetException();
            }

            char gender = genderBox.getValue();

            double gpa = Double.parseDouble(gpaBox.getText().replace(',', '.').replaceAll(" ", ""));

            if (gpa < 0 || gpa > 100) {
                throw new InvalidGPAException();
            }

            Student s = new Student(name, gender, gpa);
            MainController.studentList.add(s);
            controller.updateTable();

            nameBox.clear();
            genderBox.setValue(null);
            gpaBox.clear();

        } catch (GenderNotSetException | InvalidGPAException e) {
            status.setTextFill(Color.RED);
            status.setText(e.getMessage());
        } catch (NumberFormatException e) {
            status.setTextFill(Color.RED);
            status.setText("Error: GPA has to be a number");
        } catch (Exception e) {
            status.setTextFill(Color.RED);
            status.setText("Error: Something went wrong...");
        }
    }

    public void setController(MainController controller) {
        this.controller = controller;
    }
}
