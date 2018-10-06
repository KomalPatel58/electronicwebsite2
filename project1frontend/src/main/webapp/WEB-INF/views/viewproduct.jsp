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
		<div class="panel-info">
			<div class="panel panel-heading">Product Details</div>
			<div class="panel panel-body">
			<c:url value="/cart/addtocart/${productobj.id}" var="url"></c:url>
			<form action="${url }" >
			<img src="<c:url value='/resources/images/${productobj.id}.png'></c:url>" height="350px" width="330px"><br>
				<span style="color:red;"><b>Product Name:</b></span>${productobj.productname}<br>
				<span style="color:red;"><b>Product Description:</b></span>${productobj.productdesc}<br>
				<span style="color:red;"><b>Price:</b></span>${productobj.price}<br>
				<span style="color:red;"><b>Quantity:</b></span>${productobj.quantity}<br>
				<span style="color:red;"><b>Category:</b></span>${productobj.category.categoryname}<br>
			
				<c:if test="${productobj.quantity==0 }">
			
			<button class="btn btn-primary btn-lg" disabled>Out Of Stock</button>
		</c:if>
		<c:if test="${productobj.quantity>0 }">
		
				 <security:authorize access="hasRole('ROLE_USER')"> 
				<span style="color:red;">Enter Quantity:</span><input type="number" min="1" max="${productobj.quantity}" name="requestedQuantity">
				<button class="btn btn-lg" type="submit"><span class="glyphicon glyphicon-shopping-cart"></span>Add To Cart</button>
				</security:authorize>
				</c:if>
		

				</form>
			</div>
		</div>
	</div>
<a href="<c:url value='/all/getallproducts'></c:url>">Browse All Products</a>
</body>
</html>