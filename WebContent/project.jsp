<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Project</title>
</head>
<body>
   <form action="project" method="post">
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
    </form>
    
</body>
</html>