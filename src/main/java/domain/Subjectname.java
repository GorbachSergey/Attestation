package domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * The persistent class for the subjectname database table.
 * 
 */
@Entity
@NamedQuery(name = "Subjectname.findAll", query = "SELECT s FROM Subjectname s")
public class Subjectname implements Serializable, IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public Subjectname(String fullName, String shortName) {
		super();
		this.fullName = fullName;
		this.shortName = shortName;
	}

	private String fullName;

	private String shortName;

	// bi-directional many-to-one association to Subject
	@OneToMany(mappedBy = "subjectname")
	private List<Subject> subjects;

	public Subjectname() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public List<Subject> getSubjects() {
		return this.subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public Subject addSubject(Subject subject) {
		getSubjects().add(subject);
		subject.setSubjectname(this);

		return subject;
	}

	public Subject removeSubject(Subject subject) {
		getSubjects().remove(subject);
		subject.setSubjectname(null);

		return subject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
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
		Subjectname other = (Subjectname) obj;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
			return false;
		if (shortName == null) {
			if (other.shortName != null)
				return false;
		} else if (!shortName.equals(other.shortName))
			return false;
		return true;
	}

}