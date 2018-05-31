package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * The persistent class for the specialty database table.
 * 
 */
@Entity
@NamedQuery(name = "Specialty.findAll", query = "SELECT s FROM Specialty s")
public class Specialty implements Serializable, IEntity {
	private static final long serialVersionUID = 1L;
	@Transient
	private final String tableName = "Specialty";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String code;

	private String name;

	public Specialty(String name, String code, Faculty faculty) {
		super();
		this.code = code;
		this.name = name;
		this.faculty = faculty;
	}

	// bi-directional many-to-one association to Group
	@OneToMany(mappedBy = "specialty")
	private List<Group> groups;

	// bi-directional many-to-one association to Faculty
	@ManyToOne
	@JoinColumn(name = "facultyID")
	private Faculty faculty;

	// bi-directional many-to-one association to Subject
	@OneToMany(mappedBy = "specialty")
	private List<Subject> subjects;

	public Specialty() {
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public Group addGroup(Group group) {
		getGroups().add(group);
		group.setSpecialty(this);

		return group;
	}

	public Group removeGroup(Group group) {
		getGroups().remove(group);
		group.setSpecialty(null);

		return group;
	}

	public Faculty getFaculty() {
		return this.faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public List<Subject> getSubjects() {
		return this.subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public Subject addSubject(Subject subject) {
		getSubjects().add(subject);
		subject.setSpecialty(this);

		return subject;
	}

	public Subject removeSubject(Subject subject) {
		getSubjects().remove(subject);
		subject.setSpecialty(null);

		return subject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Specialty other = (Specialty) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.code + "-" + this.name;
	}

}