package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import dao.DAOException;
import dao.DbConnector;
import dao.TaskDao;
import domain.Task;

public class TaskDaoJdbc implements TaskDao {
	private DbConnector connector = new DbConnector();

	@Override
	public Task create(Task task) throws DAOException {
		String sql = "insert into task (name, date) values (?, '2017-05-16 11:30:00')";
		
		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, task.getName());
			statement.executeUpdate();

			try (ResultSet resultSet = statement.getGeneratedKeys()) {

				if (resultSet.next()) {
					task = new Task(resultSet.getString("name"));
					task.setId(resultSet.getInt("id"));
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Cannot create task", e);
		}
		return task;
	}

	@Override
	public Task update(Task task) throws DAOException {
		String sql = "update task set name = ?, date = ? where id = ?";

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, task.getName());
			GregorianCalendar endDate = task.getDate();
            statement.setTimestamp(2, new Timestamp(endDate.getTimeInMillis()));
			statement.setInt(3, task.getId());
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("Cannot update task", e);
		}
		return task;
	}

	@Override
	public void delete(int id) throws DAOException {
		String sql = "delete from task where id = ?";

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, id);
			statement.execute();

		} catch (SQLException e) {
			throw new DAOException("Cannot delete task with id" + id, e);
		}
	}

	@Override
	public Task findOne(int id) throws DAOException {
		String sql = "select * from task where id = ?";
		Task task = null;

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {

				if (resultSet.next()) {
					task = new Task(resultSet.getString("name"));
					task.setId(resultSet.getInt("id"));
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Cannot find one task with id" + id, e);
		}
		return task;
	}

	@Override
	public List<Task> findAll() throws DAOException {
		String sql = "select * from task";
		List<Task> taskList = new ArrayList<Task>();
		Task task = null;

		try (Connection connection = connector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				task = new Task(resultSet.getString("name"));
				task.setId(resultSet.getInt("id"));
				taskList.add(task);
			}
		} catch (SQLException e) {
			throw new DAOException("Cannot find all tasks", e);
		}
		return taskList;
	}
}
