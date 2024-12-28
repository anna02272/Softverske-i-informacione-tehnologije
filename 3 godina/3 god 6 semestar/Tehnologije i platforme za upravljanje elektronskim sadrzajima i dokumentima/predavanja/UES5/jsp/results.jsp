<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Search results</title>
	</head>
	<body>
		<c:if test="${data==null}">
			<script type="text/javascript">
				window.location = "search.jsp";
			</script>
		</c:if>
		<center>
			<img alt="logo" src="ftn.jpg" width="150px" height="150px">
			<br>Search results<br>
			<br>
			<table width="80%">
				<c:forEach items="${data}" var="res">
					<tr><td colspan="2">&nbsp;</td></tr>
					<tr><td width="20%">title</td><td>${res.title}</td></tr>
					<tr><td>Keywords</td><td>${res.keywords}</td></tr>
					<tr><td colspan="2">${res.highlight}</td></tr>
					<tr><td colspan="2"><a href="${res.location}" target="_blank">Download</a></td></tr>
				</c:forEach>
			</table>
		</center>
		
		
		Back to:
		<br><a href="search.jsp">search</a>
		<br><a href="upload.jsp">file upload</a>
	</body>
</html>