package dao;

import daoInterfaces.IMarkDAO;
import domain.Mark;

public class MarkDAO extends GenericDAO<Mark> implements IMarkDAO {

	public MarkDAO(Class<Mark> cls) {
		super(cls);
	}
}
