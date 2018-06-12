package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * The persistent class for the subject database table.
 * 
 */
@Entity
@NamedQuery(name = "Subject.findAll", query = "SELECT s FROM Subject s")
public class Subject implements Serializable, IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int course;

	@Transient
	private final String tableName = "Subject";

	public Subject(String name, int course, Specialty specialty) {
		super();
		this.course = course;
		this.specialty = specialty;
	}

	// bi-directional many-to-one association to Mark
	@OneToMany(mappedBy = "subject")
	private List<Mark> marks;

	// bi-directional many-to-one association to Specialty
	@ManyToOne
	@JoinColumn(name = "specialtyID")
	private Specialty specialty;

	// bi-directional many-to-many association to Teacher
	@ManyToMany(mappedBy = "subjects")
	private List<Teacher> teachers;

	public Subject() {
	}

	public String getTableName() {
		return tableName;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourse() {
		return this.course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public List<Mark> getMarks() {
		return this.marks;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}

	public Mark addMark(Mark mark) {
		getMarks().add(mark);
		mark.setSubject(this);

		return mark;
	}

	public Mark removeMark(Mark mark) {
		getMarks().remove(mark);
		mark.setSubject(null);

		return mark;
	}

	public Specialty getSpecialty() {
		return this.specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public List<Teacher> getTeachers() {
		return this.teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + course;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((specialty == null) ? 0 : specialty.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		if (course != other.course)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (specialty == null) {
			if (other.specialty != null)
				return false;
		} else if (!specialty.equals(other.specialty))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getName();
	}

}