package servlets.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProjectService;
import service.ServiceException;

@WebServlet("/project/deleteTask")
public class DeleteProjectTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectService projectService;

	public void init() throws ServletException {
		projectService = new ProjectService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int taskId = Integer.parseInt(request.getParameter("taskId"));
			int projectId = Integer.parseInt(request.getParameter("projectId"));
			projectService.deleteTask(projectId, taskId);
			response.sendRedirect(request.getContextPath() + "/projects" );
		} catch (ServiceException e) {
			throw new ServletException("Cannot delete task", e);
		}
	}
}
