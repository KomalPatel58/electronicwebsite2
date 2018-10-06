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
<div class="container">
	
	 
	<c:url value="/admin/addproduct" var="url"></c:url>
	<form:form modelAttribute="product" action="${url }" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td></td>
				<!-- user need not give value for id, auto generation -->
				<td><form:hidden path="id"></form:hidden></td>
			</tr>
			<tr>
				<td><b>Enter Product Name</b>
				</td>
				<td><form:input class="form-control" path="productname" placeholder="Productname is mandatory"></form:input>
				<form:errors path="productname" cssStyle="color:red"></form:errors>
				</td>
				
			</tr>
			<tr>
			<td><b>Enter Product Description</b></td>
			<td><form:textarea  class="form-control" path="productdesc"></form:textarea>
			<form:errors path="productdesc" cssStyle="color:red"> </form:errors>
			</td>
			
			</tr>
			<tr>
			<td><b>Enter Price</b></td>
			<td><form:input class="form-control" path="price"></form:input>
			<form:errors path="price" cssStyle="color:red"></form:errors>
			</td></tr>
			<tr><td><b>Enter Quantity</b></td>
			<td><form:input class="form-control" path="quantity" type="number"></form:input>
			<form:errors path="quantity" cssStyle="color:red"></form:errors>
			</td></tr>
			<tr><td><b>Select the Category</b></td>
			
			<td><form:select class="form-control" path="category.id"><!-- FK column value -->
			<!-- value is to set the value for the path category.id -->
		    <c:forEach items="${categories }" var="c">
		    <form:option value="${c.id }">${c.categoryname }</form:option>
		    </c:forEach>
			</form:select></td></tr>
			<tr><td><b>Upload image</b></td><td>
			<form:input  path="image" type="file"/>
			</td></tr>
			<tr>
			
			<td></td>
			<td><input type="submit" value="Add Product"></td>
			</tr>


		</table>

	</form:form>
	</div>
</body>
</html>