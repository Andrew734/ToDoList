package domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProjectTest {
	static Task task1, task2, task3;
	List<Task> tasks;
	Project project;

	@BeforeClass
	public static void setUpProject() {
		task1 = new Task("Eat");
		task2 = new Task("Learn");
		task3 = new Task("Fishing");
	}

	@Before
	public void setUpDataproject() {
		project = new Project();
		tasks = new ArrayList<Task>();
	}

	@Test
	public void addtaskShouldAddOnetask() {
		tasks.add(task1);
		project.addTask(task1);
		assertEquals(tasks, project.getTasks());
	}

	@Test
	public void addTaskShouldAddSeveralTasks() {
		tasks.add(task1);
		tasks.add(task2);
		tasks.add(task3);
		project.addTask(task1);
		project.addTask(task2);
		project.addTask(task3);
		assertEquals(tasks, project.getTasks());
	}

	@Test(expected = IllegalArgumentException.class)
	public void addtTaskShouldThrowExceptionForNull() {
		project.addTask(null);
	}

	@Test
	public void removeTaskShouldRemoveOneTask() {
		tasks.add(task2);
		project.addTask(task1);
		project.addTask(task2);
		project.removeTask(task1);
		assertEquals(tasks, project.getTasks());
	}
}
