package dao;

import java.util.List;

interface CrudDao<T> {

	T create(T arg) throws DAOException;

	T update(T arg) throws DAOException;

	void delete(int id) throws DAOException;

	T findOne(int id) throws DAOException;

	List<T> findAll() throws DAOException;

}
