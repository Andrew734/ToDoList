package dao;

import java.util.List;
import java.util.Set;

import domain.Project;
import domain.Task;

public interface ProjectDao extends CrudDao<Project> {

	void addTask(int projectId, int taskId) throws DAOException;

	void deleteTask(int projectId, int taskId) throws DAOException;

	List<Task> findProjectTasks(int projectId) throws DAOException;

	Set<Task> findNoProjectTasks() throws DAOException;

}
