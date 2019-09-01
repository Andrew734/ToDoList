package service;

import java.util.List;

import dao.DAOException;
import dao.TaskDao;
import dao.jdbc.TaskDaoJdbc;
import domain.Task;

public class TaskService {
	private TaskDao taskDao = new TaskDaoJdbc();

	public Task findOne(int id) throws ServiceException {
		Task task;
		try {
			task = taskDao.findOne(id);
		} catch (DAOException e) {
			throw new ServiceException("Cannot find task", e);
		}
		return task;
	}

	public List<Task> findAll() throws ServiceException {
		List<Task> tasks;
		try {
			tasks = taskDao.findAll();
		} catch (DAOException e) {
			throw new ServiceException("Cannot find all tasks", e);
		}
		return tasks;
	}

	public Task create(Task task) throws ServiceException {

		try {
			task = taskDao.create(task);
		} catch (DAOException e) {
			throw new ServiceException("Cannot create task", e);
		}
		return task;
	}

	public Task update(Task task) throws ServiceException {
		try {
			task = taskDao.update(task);
		} catch (DAOException e) {
			throw new ServiceException("Cannot update task", e);
		}
		return task;
	}

	public void delete(int id) throws ServiceException {
		try {
			taskDao.delete(id);
		} catch (DAOException e) {
			throw new ServiceException("Cannot delete task", e);
		}
	}
}
