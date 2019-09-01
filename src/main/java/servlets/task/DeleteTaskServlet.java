package servlets.task;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import service.ServiceException;
import service.TaskService;

@WebServlet("/task/delete")
public class DeleteTaskServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private TaskService taskService;

	public void init() throws ServletException {
		taskService = new TaskService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int taskId = Integer.parseInt(request.getParameter("id"));

		try {
			taskService.delete(taskId);
			response.sendRedirect(request.getContextPath() + "/tasks");
		} catch (ServiceException e) {
			throw new ServletException("Cannot delete task", e);
		}
	}
}