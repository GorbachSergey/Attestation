package daoInterfaces;

import java.util.List;

import domain.Faculty;

public interface IFacultyDAO extends IGenericDAO<Faculty> {

	List<Faculty> getFacultiesByInstitutId(int id);

}
