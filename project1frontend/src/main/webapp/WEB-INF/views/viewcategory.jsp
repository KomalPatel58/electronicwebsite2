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
			<div class="panel panel-heading">Category Details</div>
			<div class="panel panel-body">
				<c:set var="c" value="c"></c:set>
							<c:set var="img1" value="${c}${category.id}"></c:set>
							
				<img
					src="<c:url value='/resources/images/${img1}.png' ></c:url>"
					height="250px" width="230px" alt="catimage"><br> <span
					style="color: red;"><b>Category Name:</b></span>${category.categoryname}<br>
				<br>
				<div class="container">
					<b>List of Products</b>
					<table class="table table-striped" border="1" style="width: 600px;">
						<thead>
							<tr>
								<th>Id</th>
								<th>Image</th>
								<th>Product Name</th>
								<th>Price</th>
							</tr>
						</thead>
						<tbody>
							<!-- For each object in the list, tr has to be repeated -->
							<!--  items refers to the collection of objects -->
							<!-- var is local variable ,takes objects one by one from the collection -->
							
							<c:forEach var="p" items="${products}">
							
								<tr>
									<td><a
										href="<c:url value='/all/getproduct/${p.id }'></c:url>">${p.id }</a></td>
									<td><a
										href="<c:url value='/all/getproduct/${p.id }'></c:url>"><img
											src="<c:url value='/resources/images/${p.id}.png'></c:url>"
											height="30px" width="30px" alt="${p.id }"></a></td>
									<td><a
										href="<c:url value='/all/getproduct/${p.id }'></c:url>">${p.productname }</a></td>
									<td>${p.price }</td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<a href="<c:url value='/all/getallcategories'></c:url>">Browse All
		Categories</a>
</body>
</html>