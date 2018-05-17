package dao;

import daoInterfaces.IKafedraDAO;
import domain.Kafedra;

public class KafedraDAO extends GenericDAO<Kafedra> implements IKafedraDAO {

	public KafedraDAO(Class<Kafedra> cls) {
		super(cls);
	}

}
