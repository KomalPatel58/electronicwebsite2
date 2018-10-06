<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container" align="center" style="border: 5px dotted red;">

		<div class="panel-info">
			<div class="panel panel-heading">Add Product</div>
			<c:url value="/admin/addcategory" var="url"></c:url>
			<form:form modelAttribute="category" action="${url }" method="post"
				enctype="multipart/form-data">
				<table>
					<tr>
						<td></td>
						<!-- user need not give value for id, auto generation -->
						<td><form:hidden path="id"></form:hidden></td>
						<br>
					</tr>
					<tr>
						<td><b>Enter Category Name: </b></td>
						<td><form:input class="form-control" path="categoryname"
								placeholder="Categoryname is mandatory"></form:input> <form:errors
								path="categoryname" cssStyle="color:red"></form:errors> <br></td>
					</tr>
					<tr>
						<td><b>Upload image: </b></td>
						<td><form:input path="image" type="file" /> <br></td>
					</tr>

					<tr>

						<td></td>
						<td><input type="submit" value="Add Category"><br>
						<br></td>
					</tr>

				</table>

			</form:form>
		</div>
	</div>
</body>
</html>