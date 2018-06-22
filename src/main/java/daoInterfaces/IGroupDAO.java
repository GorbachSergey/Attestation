package daoInterfaces;

import java.util.List;

import domain.Group;

public interface IGroupDAO extends IGenericDAO<Group> {
	public List<Group> getGroupsBySpecialtyAndCourse(int specId, int id);

}
