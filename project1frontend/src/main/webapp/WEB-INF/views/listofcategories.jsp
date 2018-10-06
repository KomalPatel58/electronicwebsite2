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
			<div class="panel panel-heading">Categories</div>
			<table class="table table-striped" border="1">
				<thead>
					<tr>
						<th>Id</th>
						<th>Category Name</th>
						<th>Image</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<!-- For each object in the list, tr has to be repeated -->
					<!--  items refers to the collection of objects -->
					<!-- var is local variable ,takes objects one by one from the collection -->
					<c:forEach var="c" items="${categoriesList}">
						<c:set var="c1" value="c"></c:set>
						<c:set var="img1" value="${c1}${c.id}"></c:set>

						<tr>
							<td><a
								href="<c:url value='/all/getcategory/${c.id }'></c:url>">${c.id }</a></td>
							<td><a
								href="<c:url value='/all/getcategory/${c.id }'></c:url>">${c.categoryname }</a></td>
							<td><a
								href="<c:url value='/all/getcategory/${c.id }'></c:url>"><img
									src="<c:url value='/resources/images/${img1}.png' ></c:url>"
									height="30px" width="30px" alt="${c.id }"></a></td>
							<td><a
								href="<c:url value='/all/getcategory/${c.id }'></c:url>"> <span
									class="glyphicon glyphicon-info-sign"></span></a> <security:authorize
									access="hasRole('ROLE_ADMIN')">
									<a
										href="<c:url value='/admin/deletecategory/${c.id }'></c:url>"><span
										class="glyphicon glyphicon-trash"></span></a>
									<a
										href="<c:url value='/admin/getupdatecategoryform/${c.id }'></c:url>"><span
										class="glyphicon glyphicon-pencil"></span></a>
								</security:authorize></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
</body>
</html>