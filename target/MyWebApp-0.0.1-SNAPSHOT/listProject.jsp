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

<style type="text/css">
    body{
        text-align: center;
    }
    table {
        margin-left: 15%;
        min-width: 70%;
        border: 1px solid #CCC;
        border-collapse: collapse;
    }
    table tr{line-height: 30px;}
    table tr th { background: #4C4C70; color: #FFF;}
    table tr td { border:1px solid #CCC; margin: 5px;}
    input[type=text], input[type=email], input[type=tel]{
        min-width: 60%;
    }
    input[type=submit], a{
        background: green;
        padding: 5px;
        margin: 5px;
        color: #FFF;
    }
    a{
        text-decoration: none;
    }
</style>

</head>
<body>
            <c:forEach items="${projects}" var="project">
              <table>
              <thead>
            
              </thead>
              <tbody>
                <tr>
                  <th colspan="1">Id</th>
                  <th colspan="3"> Project</th>
                  <th colspan="2">Action</th>
                 </tr>
                <tr>
                     
                   <td ><span style="font-size: 18px; line-height: 16px;">
                         <b><c:out value="${project.id}"/></b><br></span> </td>
                    <td colspan="2"><span style="font-size: 18px; line-height: 16px;">
                         <b><c:out value="${project.name}"/></b><br></span> </td>
                    <td colspan="2"><a
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
                      <td colspan=6 height=10>
                        <form action="${context}/project/addTask" method="post">
                         <fieldset>
                            <legend>Add new task:</legend>
                            
                            <div>
                              <label for="name">Name</label> <input type="text"
                                     name="name" value="<c:out value="${task.name}" />"
                                     placeholder="name" />
                              <input type="hidden" value="${project.id}" name="projectId">
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
                            <th colspan="2">Deadline</th>
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
                    
                    <fmt:formatDate var="endDate" value="${task.date.getTime()}" pattern="yyyy-MM-dd HH:mm"  />
                    <td><c:out value="${endDate}" /></td>
               </tr>  
                         
                </c:forEach>
              </tbody>
              </table>
              <br>
              <br>
              <br>
            </c:forEach>
    
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