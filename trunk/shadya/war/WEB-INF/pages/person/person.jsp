<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Person</title>
</head>
<body>

<form:form commandName="person" method="post">
	<div>
	    <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
	    <input type="hidden" name="_eventId" value="searchPerson"/>
		<br />
		Name:  <form:input path="name" />		
		<br/>
		<input type="submit" value="BUSCA"/> 
	</div>	
</form:form>

</body>
</html>