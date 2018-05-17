package dao;

import daoInterfaces.IGroupDAO;
import domain.Group;

public class GroupDAO extends GenericDAO<Group> implements IGroupDAO {
	public GroupDAO(Class<Group> cls) {
		super(cls);
	}

}
