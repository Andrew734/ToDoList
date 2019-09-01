<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Current Projects</title>
</head>
<body>


    <table>
        <thead>
            
        </thead>
        <tbody>
            <c:forEach items="${projects}" var="project">
                <tr>
                  <th>Id</th>
                  <th> Project</th>
                  <th colspan="2">Action</th>
                 </tr>
                <tr>
                     
<%--                   <td> <form action="project" method="post">
                    <fieldset>
                      <div>
                        <label for="id">Project ID</label> <input type="text"
                               name="id" value="<c:out value="${project.id}" />"
                               readonly="readonly" placeholder="Project ID" />
                      </div>
                      <div>
                        <label for="name">Name</label> <input type="text"
                               name="name" value="<c:out value="${project.name}" />"
                               placeholder="name" />
                      </div>
                      <div>
                        <input type="submit" value="Update project info" />
                      </div>
                  </fieldset>
                    </form></td> --%>
                   <td><span style="font-size: 18px; line-height: 16px;">
                         <b><c:out value="${project.id}"/></b><br></span> </td>
                    <td><span style="font-size: 18px; line-height: 16px;">
                         <b><c:out value="${project.name}"/></b><br></span> </td>
                    <td><a
                        href="project?id=<c:out value="${project.id}" />">Update</a></td>
                    <td> 
                        <form action="deleteProject" method="post">
                          <fieldset>    
                            <div>
                              <input type="hidden" name="id" value="<c:out value="${project.id}" />" />
                              <input type="submit" value="Delete project" />
                            </div>
                          </fieldset>
                        </form> 
                    </td>
                    <tr> 
                      <td colspan=4 height=10>
                        <form action="${context}/project/addTask" method="post">
                         <fieldset>
                            <legend>Add new task:</legend>
                            
                            <div>
                              <label for="name">Name</label> <input type="text"
                                     name="name" value="<c:out value="${task.name}" />"
                                     placeholder="name" />
                            <!-- </div> -->
                            <!-- <div> -->
                              <input type="hidden" value="${project.id}" name="projectId">
                              <%-- <input type="hidden" value="${name}" name="taskName"> --%>
                              <input type="submit" value="Submit" />
                            </div>
                         </fieldset>
                        </form>
                      </td>
                 </tr>
                 
                
                 <thead>
                         <tr>
                            <th>Task ID</th>
                            <th> Name of Task</th>
                            <th colspan="2">Action</th>
                            <th colspan="1">Done</th>
                            </tr>
                      </thead>
                 
                 <c:forEach items="${project.getTasks()}" var="task">	  
                       
      			
               <tr>
                    <td><c:out value="${task.id}" /></td>
                    <td><c:out value="${task.name}" /></td>
                    
                    <td><a
                    href="project/deleteTask?taskId=<c:out value="${task.id}" />&projectId=<c:out value="${project.id}"/>">Delete</a></td>
                    <td><a
                    href="task?id=<c:out value="${task.id}" />">Update</a></td>
                    <td><input type="checkbox" ></td>
                    <td><c:out value="${startDate}" /></td>
                      <fmt:formatDate var="endDate" value="${semesterSchedule.endDate.getTime()}" pattern="yyyy-MM-dd HH:mm"  />
               </tr>  
                         
                </c:forEach>
            </c:forEach>
        </tbody>
    </table>
     <form action="projects" method="post">
        <fieldset>
        <h2> Add new project</h2>
            <div>
                <label for="name">Name</label> <input type="text"
                    name="name" value="<c:out value="${project.name}" />"
                    placeholder="name" />
            </div>
            <div>
                <input type="submit" value="Submit" />
            </div>
        </fieldset>
    </form>
    <a href="index.jsp"><c:out value="Back to Start page" /></a> <br> <br> 
</body>
</html>