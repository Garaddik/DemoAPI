<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html lang="en">
<head>

<!--end page level css-->
</head>

<body>
<h1>Login Page</h1>
<form:form action="login" id="authentication" method="post"  commandName="user">
	<form:input type="text" id="userName" path="userName" name="userName" placeholder="User Name" />
	<form:input type="password" path="password" id="password" name="password" placeholder="Password" />
	<input type="submit" value="Sign In" class="btn btn-primary btn-block" />								
</form:form>
</body>
</html>
