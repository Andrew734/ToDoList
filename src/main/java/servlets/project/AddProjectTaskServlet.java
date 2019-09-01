package servlets.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Task;
import service.ProjectService;
import service.ServiceException;
import service.TaskService;

@WebServlet("/project/addTask")
public class AddProjectTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectService projectService;
	private TaskService taskService;

	public void init() throws ServletException {
	    taskService = new TaskService();
	    projectService = new ProjectService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Task task = new Task();
        task.setName(request.getParameter("name"));

        try {
            task = taskService.create(task);
            int projectId = Integer.parseInt(request.getParameter("projectId"));
            int taskId = task.getId();
            projectService.addTask(projectId, taskId);
            response.sendRedirect(request.getContextPath() + "/projects");
        } catch (ServiceException e) {
            throw new ServletException("Cannot add task to the project", e);
        }
    }
}
