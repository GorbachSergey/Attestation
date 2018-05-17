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

/**
 * The persistent class for the faculty database table.
 * 
 */
@Entity
@NamedQuery(name = "Faculty.findAll", query = "SELECT f FROM Faculty f ORDER BY f.id")
public class Faculty implements Serializable, IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	public Faculty(String name, Institut institut) {
		super();
		this.name = name;
		this.institut = institut;
	}

	// bi-directional many-to-one association to Institut
	@ManyToOne
	@JoinColumn(name = "institutID")
	private Institut institut;

	// bi-directional many-to-one association to Kafedra
	@OneToMany(mappedBy = "faculty")
	private List<Kafedra> kafedras;

	// bi-directional many-to-one association to Specialty
	@OneToMany(mappedBy = "faculty")
	private List<Specialty> specialties;

	public Faculty() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Institut getInstitut() {
		return this.institut;
	}

	public void setInstitut(Institut institut) {
		this.institut = institut;
	}

	public List<Kafedra> getKafedras() {
		return this.kafedras;
	}

	public void setKafedras(List<Kafedra> kafedras) {
		this.kafedras = kafedras;
	}

	public Kafedra addKafedra(Kafedra kafedra) {
		getKafedras().add(kafedra);
		kafedra.setFaculty(this);

		return kafedra;
	}

	public Kafedra removeKafedra(Kafedra kafedra) {
		getKafedras().remove(kafedra);
		kafedra.setFaculty(null);

		return kafedra;
	}

	public List<Specialty> getSpecialties() {
		return this.specialties;
	}

	public void setSpecialties(List<Specialty> specialties) {
		this.specialties = specialties;
	}

	public Specialty addSpecialty(Specialty specialty) {
		getSpecialties().add(specialty);
		specialty.setFaculty(this);

		return specialty;
	}

	public Specialty removeSpecialty(Specialty specialty) {
		getSpecialties().remove(specialty);
		specialty.setFaculty(null);

		return specialty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Faculty other = (Faculty) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}