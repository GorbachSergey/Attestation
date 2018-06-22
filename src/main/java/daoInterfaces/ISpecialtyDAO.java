package daoInterfaces;

import java.util.List;

import domain.Specialty;

public interface ISpecialtyDAO extends IGenericDAO<Specialty> {

	List<Specialty> getSpecialtiesByFacultyId(int id);

}
