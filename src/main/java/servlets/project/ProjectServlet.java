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

@WebServlet("/projects")
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectService projectService;

	public void init() throws ServletException {
		projectService = new ProjectService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("projects", projectService.findAll());
			request.getRequestDispatcher("/listProject.jsp").forward(request, response);
		} catch (ServiceException e) {
			throw new ServletException("Cannot find all projects", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Project project = new Project();
		project.setName(request.getParameter("name"));

		try {
			projectService.create(project);
			response.sendRedirect(request.getContextPath() + "/projects");

		} catch (ServiceException e) {
			throw new ServletException("Cannot create project", e);
		}
	}
}
