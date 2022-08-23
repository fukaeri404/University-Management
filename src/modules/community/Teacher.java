package modules.community;



import javafx.collections.ObservableList;

public class Teacher {
	private String name;
	private String position;
	private String department;
	private ObservableList<Student> studentList;

	public Teacher(String name, String department,String position,ObservableList<Student> studentList) {
		super();
		this.name = name;
		this.department = department;
		this.position = position;
		this.studentList = studentList;
	}
	
	

	public ObservableList<Student> getStudentList() {
		return studentList;
	}

	


	public String getDepartment() {
		return department;
	}



	@Override
	public String toString() {
		return "Teacher [name=" + name + ", position=" + position + ", department=" + department + "]";
	}
}
