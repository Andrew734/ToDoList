package servlets.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Project;
import service.ProjectService;
import service.ServiceException;

@WebServlet("/project")
public class UpdateProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectService projectService;

	public void init() throws ServletException {
		projectService = new ProjectService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int projectId = Integer.parseInt(request.getParameter("id"));

		try {
			request.setAttribute("noProjectTasks", projectService.findNoProjectTasks());
			request.setAttribute("project", projectService.findOne(projectId));
			request.getRequestDispatcher("/project.jsp").forward(request, response);
		} catch (ServiceException e) {
			throw new ServletException("Cannot find project", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Project project = new Project();
		project.setName(request.getParameter("name"));
		String projectId = request.getParameter("id");

		try {
			project.setId(Integer.parseInt(projectId));
			projectService.update(project);
			response.sendRedirect(request.getContextPath() + "/projects");
		} catch (ServiceException e) {
			throw new IOException("Cannot update project", e);
		}
	}
}
