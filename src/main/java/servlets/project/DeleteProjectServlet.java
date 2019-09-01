package servlets.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProjectService;
import service.ServiceException;

@WebServlet("/deleteProject")
public class DeleteProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectService projectService;

	public void init() throws ServletException {
		projectService = new ProjectService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int projectId = Integer.parseInt(request.getParameter("id"));

		try {
			projectService.delete(projectId);
			response.sendRedirect(request.getContextPath() + "/projects");
		} catch (ServiceException e) {
			throw new ServletException("Cannot delete project", e);
		}
	}
}
