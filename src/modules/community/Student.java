package modules.community;

public class Student {
	private int id;
	private String name;
	private String gender;
	private String major;
	private String year;

	

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(int id, String name, String gender, String major, String year) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.major = major;
		this.year = year;
	}

	public String getYear() {
		return year;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", gender=" + gender + ", major=" + major + ", year=" + year
				+ "]";
	}

}
