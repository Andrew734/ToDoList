package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.DAOException;
import dao.DbConnector;
import dao.ProjectDao;
import domain.Project;
import domain.Task;

import dao.TimestampConverter;

public class ProjectDaoJdbc implements ProjectDao {
	private DbConnector connector = new DbConnector();
	
	@Override
	public Project create(Project project) throws DAOException {
		String sql = "insert into projects (name) values (?)";

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, project.getName());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys()) {

				if (resultSet.next()) {
					project = new Project(resultSet.getString("name"));
					project.setId(resultSet.getInt("id"));
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Cannot create project", e);
		}
		return project;
	}

	@Override
	public void addTask(int projectId, int taskId) throws DAOException {
		if (taskId == 0 || projectId == 0) {
			throw new IllegalArgumentException("The argument cannot be zero");
		}
		String sql = "insert into projects_tasks (project_id, task_id) values (?, ?)";

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, projectId);
			statement.setInt(2, taskId);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("Cannot add the task with Id " + taskId
					+ " to the project with Id" + projectId, e);
		}
	}

	@Override
	public void deleteTask(int projectId, int taskId) throws DAOException {
		if (taskId == 0 || projectId == 0) {
			throw new IllegalArgumentException("The argument cannot be zero");
		}
		String sql = "delete from projects_tasks where project_id = ? and task_id = ? ";

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, projectId);
			statement.setInt(2, taskId);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("Cannot delete the task with Id " + taskId
					+ " from the project with Id" + projectId, e);
		}
	}

	@Override
	public Project update(Project project) throws DAOException {
		String sql = "update projects set name = ? where id = ?";

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, project.getName());
			statement.setInt(2, project.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("Cannot update project", e);
		}
		return project;
	}

	@Override
	public void delete(int id) throws DAOException {
		String sql = "delete from projects where id = ?";

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, id);
			statement.execute();

		} catch (SQLException e) {
			throw new DAOException("Cannot delete project with id" + id, e);
		}
	}

	@Override
	public Project findOne(int id) throws DAOException {
		String sql = "SELECT * FROM projects WHERE projects.id = ? ";
		Project project = null;

		ProjectDaoJdbc projectDaoJdbc = new ProjectDaoJdbc();

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {

				if (resultSet.next()) {
					project = new Project(resultSet.getString("name"));
					project.setId(resultSet.getInt("id"));

					for (Task task : projectDaoJdbc.findProjectTasks(project.getId())) {
						project.addTask(task);
					}
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Cannot find one project with id" + id, e);
		}
		return project;
	}

	@Override
	public List<Task> findProjectTasks(int projectId) throws DAOException {
		String sql = "SELECT * FROM task JOIN projects_tasks"
				+ " ON ((projects_tasks.task_id = task.id) AND (projects_tasks.project_id = ?))";

		List<Task> tasks = new ArrayList<Task>();
		Task task;

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, projectId);

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
				    GregorianCalendar endCalendar;
				    
					task = new Task(resultSet.getString("name"));
					task.setId(resultSet.getInt("id"));
					
					endCalendar = TimestampConverter.toGregorianCalendar(resultSet.getTimestamp("date"));
					task.setDate(endCalendar);
					
					tasks.add(task);
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Cannot find tasks of the project with id" + projectId, e);
		}
		return tasks;
	}

	@Override
	public List<Project> findAll() throws DAOException {
		String sql = "SELECT * FROM projects";
		List<Project> projectList = new ArrayList<Project>();
		Project project = null;

		ProjectDaoJdbc projectDaoJdbc = new ProjectDaoJdbc();

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				project = new Project(resultSet.getString("name"));
				project.setId(resultSet.getInt("id"));

				for (Task task : projectDaoJdbc.findProjectTasks(project.getId())) {
					project.addTask(task);
				}

				projectList.add(project);
			}

		} catch (SQLException e) {
			throw new DAOException("Cannot find all projects", e);
		}
		return projectList;
	}

	@Override
	public Set<Task> findNoProjectTasks() throws DAOException {
		String sql = "SELECT * FROM task "
				+ " WHERE task.id <> ALL (SELECT task_id FROM projects_tasks) ";

		Set<Task> tasks = new HashSet<>();
		Task task;

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					task = new Task(resultSet.getString("name"));
					task.setId(resultSet.getInt("id"));
					tasks.add(task);
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Cannot find tasks with no project", e);
		}
		return tasks;
	}

}
