package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import modules.community.Student;
import modules.database.DataSource;

public class NewStudentController implements Initializable {

	@FXML
	private ToggleGroup genderToggle;

	@FXML
	private RadioButton rb_male;

	@FXML
	private RadioButton rb_female;

	@FXML
	private RadioButton rb_gay;

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtName;

	@FXML
	private ComboBox<String> cbb_year;

	@FXML
	private ComboBox<String> cbb_major;

	private static int id = 10;
	String gender;
	String major;
	String year;
	

	@FXML
	void ProcessGender(ActionEvent event) {
		if (rb_male.isSelected())
			gender = "Male";
		if (rb_female.isSelected())
			gender = "Female";
		if (rb_gay.isSelected())
			gender = "Gay";
	}

	@FXML
	void processFinish(ActionEvent event) {
		Student student = new Student();
		student.setId(id);
		student.setName(txtName.getText());
		student.setMajor(cbb_major.getSelectionModel().getSelectedItem());
		student.setYear(cbb_year.getSelectionModel().getSelectedItem());
		student.setGender(gender);
		MainController.getStudentsList().add(student);
		Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		stage.hide();
		MainController.getNumberBean().setNumber(MainController.getNumberBean().getNumber() + 0.05);
		id++;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> year = FXCollections.observableArrayList("First Year", "Second Year", "Third Year",
				"Final Year");
		cbb_year.setItems(year);

		ObservableList<String> major = FXCollections.observableArrayList("English", "Philosophy", "Mathematics",
				"Myanmar");
		cbb_major.setItems(major);
		txtId.setText(String.valueOf(id));
	}

}
