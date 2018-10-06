<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<SPAN STYLE="COLOR:RED;">${error }</SPAN><br><br>
${msg}<br><br>
<span style="color:red;"><b>Login Page</b></span>
<c:url var="url" value="/j_spring_security_check"></c:url>
<form method="post" action="${url }" class="form-horizontal">
<center>
<div class="form-group">
<span class="col-lg-2">Enter Email:</span>
<div class="col-lg-5">
<input type="text" class="form-control" name="j_username"></div></div>

<div class="form-group">
<span class="col-lg-2">
Enter Password:</span>
<div class="col-lg-5">
<input type="password" class="form-control" name="j_password"></div></div>
<input type="submit" value="Login">  
</center>
</form>
</body>
</html>