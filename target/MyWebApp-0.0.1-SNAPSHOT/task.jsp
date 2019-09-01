<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Task</title>
</head>
<body>
    <form action="task" method="post">
        <fieldset>
            <div>
                <label for="id">Task ID</label> <input type="text"
                    name="id" value="<c:out value="${task.id}" />"
                    readonly="readonly" placeholder="Task ID" />
            </div>
            <div>
                <label for="name">Name</label> <input type="text"
                    name="name" value="<c:out value="${task.name}" />"
                    placeholder="name" />
            </div>
            <div>
                <input type="submit" value="Update task info" />
            </div>
        </fieldset>
    </form>
    
<%--      <form action="task/delete" method="post">
        <fieldset>
            <div>
            	<input type="hidden" name="id" value="<c:out value="${task.id}" />" />
                <input type="submit" value="Delete task" />
            </div>
        </fieldset>
    </form> --%>
</body>
</html>