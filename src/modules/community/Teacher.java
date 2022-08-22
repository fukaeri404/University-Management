package modules.community;

import java.util.List;

public class Teacher {
	private String name;
	private List<Course> courseList;

	public Teacher(String name, List<Course> courseList) {
		super();
		this.name = name;
		this.courseList = courseList;
	}

}
