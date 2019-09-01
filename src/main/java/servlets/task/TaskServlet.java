package servlets.task;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Task;
import service.ServiceException;
import service.TaskService;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TaskService taskService;

	public void init() throws ServletException {
		taskService = new TaskService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("tasks", taskService.findAll());
			request.getRequestDispatcher("/listTask.jsp").forward(request, response);
		} catch (ServiceException e) {
			throw new ServletException("Cannot find all tasks", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Task task = new Task();
		task.setName(request.getParameter("name"));

		try {
			taskService.create(task);
			response.sendRedirect(request.getContextPath() + "/tasks");
		} catch (ServiceException e) {
			throw new ServletException("Cannot create task", e);
		}
	}
}