package service;

import java.util.List;
import java.util.Set;

import dao.DAOException;
import dao.ProjectDao;
import dao.jdbc.ProjectDaoJdbc;
import domain.Project;
import domain.Task;

public class ProjectService {
	private ProjectDao projectDao = new ProjectDaoJdbc();

	public Project findOne(int id) throws ServiceException {
		Project project;
		try {
			project = projectDao.findOne(id);
		} catch (DAOException e) {
			throw new ServiceException("Cannot find project", e);
		}
		return project;
	}

	public List<Project> findAll() throws ServiceException {
		List<Project> projects;
		try {
			projects = projectDao.findAll();
		} catch (DAOException e) {
			throw new ServiceException("Cannot find all projects", e);
		}
		return projects;
	}

	public Project create(Project project) throws ServiceException {
		try {
			project = projectDao.create(project);
		} catch (DAOException e) {
		throw new ServiceException("Cannot create project", e);
		}
		return project;
	}

	public void addTask(int projectId, int taskId) throws ServiceException {
		try {
			projectDao.addTask(projectId, taskId);
		} catch (DAOException e) {
			throw new ServiceException("Cannot add the task to the project", e);
		}
	}

	public Project update(Project project) throws ServiceException {
		try {
			project = projectDao.update(project);
		} catch (DAOException e) {
			throw new ServiceException("Cannot update project", e);
		}
		return project;
	}

	public void delete(int id) throws ServiceException {
		try {
			projectDao.delete(id);
		} catch (DAOException e) {
			throw new ServiceException("Cannot delete project", e);
		}
	}

	public void deleteTask(int projectId, int taskId) throws ServiceException {
		try {
			projectDao.deleteTask(projectId, taskId);
		} catch (DAOException e) {
			throw new ServiceException("Cannot delete task", e);
		}
	}

	public Set<Task> findNoProjectTasks() throws ServiceException {
		Set<Task> tasks;
		try {
			tasks = projectDao.findNoProjectTasks();
		} catch (DAOException e) {
			throw new ServiceException("Cannot find tasks with no project", e);
		}
		return tasks;
	}

}
