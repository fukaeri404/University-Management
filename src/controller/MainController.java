package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modules.community.Student;
import modules.community.Teacher;
import modules.database.DataSource;
import javafx.scene.input.MouseEvent;

public class MainController implements Initializable {

	@FXML
	private ComboBox<String> cbbYear;

	@FXML
	private ComboBox<String> cbbMajor;

	@FXML
	private ComboBox<?> cbbCourse;
	
	@FXML
	private ListView<Student> lvStudent;

	@FXML
	private ListView<Teacher> lv_teacher;

	@FXML
	private VBox vb_student;

	@FXML
	private VBox vb_teacher;

	@FXML
	private Button btn_delete;
	
	private static final DataSource data = new DataSource();
	private static final ObservableList<Student> students_list = data.getAllStudents(); // store final student datas
	
	private String selectedYear ="";
	private String selectedMajor ="";

	@FXML
	void processAddStudent(ActionEvent event) throws IOException {
		GridPane root = FXMLLoader.load(getClass().getResource("/view/NewStudentUI.fxml"));
//		Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	void processDeleteStudent(ActionEvent event) {
		Student selectedStudent = lvStudent.getSelectionModel().getSelectedItem();
		if (lvStudent.getItems().size() == 1) {
			btn_delete.setDisable(true);
		}
		students_list.remove(selectedStudent);
		lvStudent.getItems().remove(selectedStudent);
	}

	@FXML
	void processStudent(ActionEvent event) {
		vb_student.setVisible(true);
		lvStudent.setItems(students_list);
	}

	@FXML
	void processYear(ActionEvent event) {
		selectedYear = cbbYear.getSelectionModel().getSelectedItem();
		ObservableList<Student> tempList = FXCollections.observableArrayList();
		if(!selectedMajor.isEmpty()) {
			for (Student student : students_list) {
				if (student.getYear().equals(selectedYear)&&student.getMajor().equals(selectedMajor))
					tempList.add(student);
			}
		}else {
			for (Student student : students_list) {
				if (student.getYear().equals(selectedYear))
					tempList.add(student);
			}
		}
		lvStudent.setItems(tempList);
		
	}

	@FXML
	void processMajor(ActionEvent event) {
		selectedMajor = cbbMajor.getSelectionModel().getSelectedItem();
		ObservableList<Student> tempList = FXCollections.observableArrayList();
		if(!selectedYear.isEmpty()) {
			for (Student student : students_list) {
				if (student.getMajor().equals(selectedMajor)&&student.getYear().equals(selectedYear))
					tempList.add(student);
			}
		}else {
			for (Student student : students_list) {
				if (student.getMajor().equals(selectedMajor))
					tempList.add(student);
			}
		}
		lvStudent.setItems(tempList);
		
	}
	
	@FXML
	public void processShowStudents(ActionEvent event) {
		lvStudent.setItems(students_list);
		System.out.println("0");
	}

	@FXML
	public void mc_lvStudent(MouseEvent event) {
		Student selectedStudent = lvStudent.getSelectionModel().getSelectedItem();
		if (selectedStudent != null) {
			btn_delete.setDisable(false);
		} else {
			btn_delete.setDisable(true);
		}
	}

	/**
	 * Teachers part
	 * 
	 * @param event
	 */

	@FXML
	public void processDeleteTeacher(ActionEvent event) {
	}

	@FXML
	public void processAddTeacher(ActionEvent event) {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> year = FXCollections.observableArrayList("First Year", "Second Year", "Third Year",
				"Final Year");
		cbbYear.getItems().addAll(year);
		ObservableList<String> major = FXCollections.observableArrayList("English", "Philosophy", "Mathematics",
				"Myanmar");
		cbbMajor.setItems(major);
		btn_delete.setDisable(true);

	}

}
