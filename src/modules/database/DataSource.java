package modules.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modules.community.Student;
import modules.community.Teacher;

public class DataSource {
//	private static final Student STUDENT_INSTANCE = new Student();
	private static final ObservableList<Student> students_list = FXCollections.observableArrayList();
	private static final ObservableList<Teacher> teachers_list = FXCollections.observableArrayList();

	public ObservableList<Student> getAllStudents() {
		students_list.add(new Student(1, "MgZaw", "Male", "English", "First Year"));
		students_list.add(new Student(2, "MaSabal", "Female", "English", "First Year"));
		students_list.add(new Student(3, "MaMya", "Female", "Philosophy", "Final Year"));
		students_list.add(new Student(4, "MgKyaw", "Male", "Mathematics", "Second Year"));
		students_list.add(new Student(5, "MgKhway", "Male", "Myanmar", "Third Year"));
		students_list.add(new Student(6, "MaSwe", "Female", "Philosophy", "Third Year"));
		students_list.add(new Student(7, "MgTun", "Male", "English", "Final Year"));
		students_list.add(new Student(8, "MaThet", "Female", "Mathematics", "Second Year"));
		students_list.add(new Student(9, "MaNi", "Female", "English", "Second Year"));
		return students_list;
	}

	public  ObservableList<Student> getStudents_list() {
		return students_list;
	}

	public ObservableList<Teacher> getAllTeachers() {
		teachers_list.add(new Teacher("DawMoePwint", "Mathematics", "Professor", divideMajor("Mathematics")));
		teachers_list.add(new Teacher("DawKyatPinSein", "English", "Teacher", divideMajor("English")));
		teachers_list.add(new Teacher("UKazunYwat", "Philosophy", "Teacher", divideMajor("Philosophy")));
		teachers_list.add(new Teacher("DawKyatChoChin", "Myanmar", "Teacher", divideMajor("Myanmar")));
		return teachers_list;
	}
	
	
	public static ObservableList<Student> divideMajor(String major){
		ObservableList<Student> studentsMajor = FXCollections.observableArrayList();
		for (Student student : students_list) {
			if(student.getMajor().equals(major))studentsMajor.add(student);
		}
		return studentsMajor;
	}

	public static ObservableList<Teacher> getTeachersList() {
		return teachers_list;
	}
	
	

}
