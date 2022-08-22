package modules.community;

import java.util.List;

public class Course {
	private String name;
	private List<Student> studentList;

	public Course(String name, List<Student> studentList) {
		super();
		this.name = name;
		this.studentList = studentList;
	}

}
