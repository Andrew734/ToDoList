package servlets.task;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Task;
import service.ServiceException;
import service.TaskService;

@WebServlet("/task")
public class UpdateTaskServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String UPDATE = "/task.jsp";
	private TaskService taskService;

	public void init() throws ServletException {
		taskService = new TaskService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int taskId = Integer.parseInt(request.getParameter("id"));

		try {
			request.setAttribute("task", taskService.findOne(taskId));
			request.getRequestDispatcher(UPDATE).forward(request, response);
		} catch (ServiceException e) {
			throw new ServletException("Cannot find task", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endDate;
        GregorianCalendar endDateCalendar = new GregorianCalendar();
		Task task = new Task();
		task.setName(request.getParameter("name"));
		String taskId = request.getParameter("id");
		
		try {
            endDate = dateFormat.parse(request.getParameter("endDate"));
        } catch (ParseException e) {
            throw new ServletException("Cannot parse the date of semesterSchedules", e);
        }
		endDateCalendar.setTime(endDate);

		try {
			task.setId(Integer.parseInt(taskId));
			task.setDate(endDateCalendar);
			taskService.update(task);
			response.sendRedirect(request.getContextPath() + "/projects");
		} catch (ServiceException e) {
			throw new IOException("Cannot update task", e);
		}
	}
}
