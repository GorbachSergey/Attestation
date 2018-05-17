package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the mark database table.
 * 
 */
@Entity
@NamedQuery(name = "Mark.findAll", query = "SELECT m FROM Mark m")
public class Mark implements Serializable, IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int countOfPass;

	private int mark;

	public Mark(int countOfPass, int mark, Subject subject, Student student) {
		super();
		this.countOfPass = countOfPass;
		this.mark = mark;
		this.subject = subject;
		this.student = student;
	}

	// bi-directional many-to-one association to Subject
	@ManyToOne
	@JoinColumn(name = "subjectID")
	private Subject subject;

	// bi-directional many-to-one association to Student
	@ManyToOne
	@JoinColumn(name = "studentID")
	private Student student;

	public Mark() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCountOfPass() {
		return this.countOfPass;
	}

	public void setCountOfPass(int countOfPass) {
		this.countOfPass = countOfPass;
	}

	public int getMark() {
		return this.mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mark;
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
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
		Mark other = (Mark) obj;
		if (mark != other.mark)
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}

}