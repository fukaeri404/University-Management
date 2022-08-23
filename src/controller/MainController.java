package controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modules.community.Student;
import modules.community.Teacher;
import modules.database.DataSource;
import modules.database.NumberBean;
import javafx.scene.input.MouseEvent;

public class MainController implements Initializable {

	@FXML
	private ComboBox<String> cbbYear;

	@FXML
	private ComboBox<String> cbbMajor;

	@FXML
	private ComboBox<String> cbbDepartment;

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

	@FXML
	private Button btn_add;

	@FXML
	private Label lbl_time;

	@FXML
	private Button btnShowStudents;

	@FXML
	private ProgressIndicator pgin;
	
	@FXML
	private Label lbl_enrol;

	static DataSource data = new DataSource();
	private static ObservableList<Student> students_list = DataSource.getAllStudents(); // store final student datas
	private static ObservableList<Teacher> teachers_list = DataSource.getAllTeachers();
	private volatile boolean stop = false;
	private static final NumberBean numberBean = new NumberBean();

	private String selectedYear = "";
	private String selectedMajor = "";
	private static Teacher selectedTeacher;
	private int availStudents = 11;


	Alert a = new Alert(AlertType.NONE);

	@FXML
	void processAddStudent(ActionEvent event) throws IOException {
		if (numberBean.getNumber() < 1.0) {
			GridPane root = FXMLLoader.load(getClass().getResource("/view/NewStudentUI.fxml"));
//			Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} else {
			a.setAlertType(AlertType.INFORMATION);
			a.setContentText("Full Number");
			a.show();
		}
		availStudents-=1;
		lbl_enrol.setText("Available Students : "+availStudents );
	}

	@FXML
	void processDeleteStudent(ActionEvent event) {
		Student selectedStudent = lvStudent.getSelectionModel().getSelectedItem();
		if (lvStudent.getItems().size() == 1) {
			btn_delete.setDisable(true);
		}
		students_list.remove(selectedStudent);
		numberBean.setNumber(numberBean.getNumber() - 0.05);
		availStudents+=1;
		lbl_enrol.setText("Available Students : "+availStudents );
	}

	@FXML
	void processStudent(ActionEvent event) {
		if (!vb_student.isVisible())
			vb_student.setVisible(true);
		if (vb_teacher.isVisible())
			vb_teacher.setVisible(false);
		lvStudent.setItems(students_list);
		stop = true;
	}

	@FXML
	void processYear(ActionEvent event) {
		selectedYear = cbbYear.getSelectionModel().getSelectedItem();
		ObservableList<Student> tempList = FXCollections.observableArrayList();
		if (!selectedMajor.isEmpty()) {
			for (Student student : students_list) {
				if (student.getYear().equals(selectedYear) && student.getMajor().equals(selectedMajor))
					tempList.add(student);
			}
		} else {
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
		if (!selectedYear.isEmpty()) {
			for (Student student : students_list) {
				if (student.getMajor().equals(selectedMajor) && student.getYear().equals(selectedYear))
					tempList.add(student);
			}
		} else {
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
	public void processTeacher(ActionEvent event) {
		if (vb_student.isVisible())
			vb_student.setVisible(false);
		if (!vb_teacher.isVisible())
			vb_teacher.setVisible(true);
		teachers_list.clear();
//		teachers_list = DataSource.getAllTeachers();
		lv_teacher.setItems(teachers_list);
		stop = true;

	}

	@FXML
	public void processDepartment(ActionEvent event) {
		String selectedDepartment = cbbDepartment.getSelectionModel().getSelectedItem();
		ObservableList<Teacher> tempList = FXCollections.observableArrayList();

		for (Teacher teacher : teachers_list) {
			if (teacher.getDepartment().equals(selectedDepartment))
				tempList.add(teacher);
		}

		lv_teacher.setItems(tempList);
	}

	@FXML
	public void processShowItsStudents(ActionEvent event) throws IOException {
		VBox root = (VBox) FXMLLoader.load(getClass().getResource("/view/StudentsOfTeacherUI.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void mc_lvTeacher(MouseEvent event) {
		selectedTeacher = lv_teacher.getSelectionModel().getSelectedItem();
		if (selectedTeacher != null) {
			btnShowStudents.setDisable(false);
		} else {
			btnShowStudents.setDisable(true);
		}
	}

	/**
	 * other blah blah methods
	 */
	private void timeNow() {
		Thread thread = new Thread(() -> {
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
			while (!stop) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					System.out.println(e);
				}
				final String timeNow = sdf.format(new Date());
				Platform.runLater(() -> {
					lbl_time.setText(timeNow);
				});
			}
		});
		thread.start();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> year = FXCollections.observableArrayList("First Year", "Second Year", "Third Year",
				"Final Year");
		cbbYear.getItems().addAll(year);
		ObservableList<String> major = FXCollections.observableArrayList("English", "Philosophy", "Mathematics",
				"Myanmar");
		cbbMajor.setItems(major);
		cbbDepartment.setItems(major);
		btn_delete.setDisable(true);
		btnShowStudents.setDisable(true);
		timeNow();
		lbl_enrol.setText("Available Students : 11");
		pgin.setProgress((double) students_list.size() / 20);
		numberBean.setNumber((double) students_list.size() / 20);

		numberBean.numberProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
				pgin.progressProperty().bind(numberBean.numberProperty());
				
//				lbl_enrol.setText(String.valueOf(Integer.parseInt(newValue.toString())));
			}

		});

		pgin.progressProperty().addListener((ov, oldValue, newValue) -> {
			Text text = (Text) pgin.lookup(".percentage");
			if (text != null && text.getText().equals("Done")) {
				text.setText("Full");
				pgin.setPrefWidth(text.getLayoutBounds().getWidth());
			}
		});

	}

	public static Teacher getSelectedTeacher() {
		return selectedTeacher;
	}

	public static NumberBean getNumberBean() {
		return numberBean;
	}

	public static ObservableList<Student> getStudentsList() {
		return students_list;
	}

}
