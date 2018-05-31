package domain;

import java.util.ArrayList;
import java.util.List;

public class Course {
	private final String tableName = "Course";
	private int id;
	private String name;

	public Course(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public static List<Course> getCources() {
		List<Course> lst = new ArrayList<>();
		for (int i = 1; i <= 4; i++) {
			Course c = new Course(i, String.valueOf(i) + " курс");
			lst.add(c);
		}
		return lst;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
