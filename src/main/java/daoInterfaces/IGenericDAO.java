package daoInterfaces;

import java.util.List;

import domain.IEntity;

public interface IGenericDAO<T extends IEntity> {

	void save(T item);

	void update(T item);

	List<T> getAll();

	void delete(T item);

	T getById(int id);

	void closeSession();

	void openSession();

}
