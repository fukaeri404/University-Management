package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import modules.community.Student;

public class StudentsOfTeacherController implements Initializable {

	@FXML
	private ListView<Student> lv_studentList;

	@FXML
	private TextField tf_studentName;
	
	private ObservableList<Student> sList; 

	@FXML public void kr_tfStudentName(KeyEvent event) {
		if (!tf_studentName.getText().isEmpty()) {
			ObservableList<Student> sList = FXCollections.observableArrayList();
			for (Student student : this.sList) {
				if (student.getName().contains(tf_studentName.getText()))
					sList.add(student);
			}
			lv_studentList.setItems(sList);
		} else {
			tf_studentName.setPromptText("Enter the name of student in here...");
			lv_studentList.setItems(MainController.getSelectedTeacher().getStudentList());
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lv_studentList.setItems(MainController.getSelectedTeacher().getStudentList());
		this.sList = lv_studentList.getItems();
	}

	

}
