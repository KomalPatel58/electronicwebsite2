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
	<div id="mycarousel" class="carousel slide" data-ride="carousel">
	    <ol class="carousel-indicators">
	    	<li data-target="#mycarousel" data-slide-to="0" class="active"></li>
	    	<li data-target="#mycarousel" data-slide-to="1"></li>
	    	<li data-target="#mycarousel" data-slide-to="2"></li>
	    	<li data-target="#mycarousel" data-slide-to="3"></li>
	    	<li data-target="#mycarousel" data-slide-to="4"></li>
	    </ol>
		<div class="carousel-inner">
		     <div class="item active">
		      <a href="<c:url value='/all/searchByCategory?searchCondition=Computer'></c:url>">  <img class="image-circle" src="<c:url value='/resources/images/s1.png'></c:url>" height="280px" width="1353px"></a>
		     	<div class="container">
                        <div class="carousel-caption">
                            <h1>World Of Computer</h1>
                            <p>Digital India</p>
                        </div>
                </div>       
		     </div>
		     
			<div class="item" id="s1">
		       <a href="<c:url value='/all/searchByCategory?searchCondition=Camera'></c:url>"> <img class="image-circle" src="resources/images/s2.png" height="480px" width="100%"></a>
		     	<div class="container">
                        <div class="carousel-caption">
                           <h1>Classic Camera</h1>
                            <p>View in the World</p>
                        </div>
                </div>       
		     </div>
		     
		     <div class="item">
		       <a href="<c:url value='/all/searchByCategory?searchCondition=Mobile'></c:url>"> <img class="image-circle" src="resources/images/s3.png" height="480px" width="100%"></a>
		     	<div class="container">
                        <div class="carousel-caption">
                           <h1>Mobile Mega Store</h1>
                            <p>Connect to World</p>
                        </div>
                </div>       
		     </div>
		
			 <div class="item" >
		       <a href="<c:url value='/all/searchByCategory?searchCondition=Home Entertainment'></c:url>"> <img class="image-circle" src="resources/images/s4.png" height="480px" width="100%"></a>
		     	<div class="container">
                        <div class="carousel-caption">
                          <h1>Home Entertainment</h1>
                            <p>Full on Dhamal</p>
                        </div>
                </div>       
		     </div>
		     
		     <div class="item">
		        <a href="<c:url value='/all/searchByCategory?searchCondition=Home Appliance'></c:url>"><img class="image-circle" src="resources/images/s5.png" height="480px" width="100%"></a>
		     	<div class="container">
                        <div class="carousel-caption">
                          <h1>Home Appliance</h1>
                            <p>Make your home a Dream</p>
                        </div>
                </div>       
		     </div>
		</div>
		<a class="left carousel-control" href="#mycarousel" role="button" data-slide="prev">
		<span class="glyphicon glyphicon-chevron-left"></span>
		<span class="sr-only">Previous</span>
		</a>
		
		<a class="right carousel-control" href="#mycarousel" role="button" data-slide="next">
		<span class="glyphicon glyphicon-chevron-right"></span>
		<span class="sr-only">Next</span>
		</a>
	</div>
</div>
<div class="container">
<div class="row">
<c:forEach items="${productlist}" var="p">
	<div class="col-lg-3 col-md-3" ><center>
	<a href="<c:url value='/all/getproduct/${p.id }'></c:url>"><img src="<c:url value='/resources/images/${p.id}.png'></c:url>" height="300" width="220"></a><br>
	<b><a href="<c:url value='/all/getproduct/${p.id }'></c:url>">${p.productname}<br>
	<span style="color:red;">${p.price}</span></a></b><br><br></center>
	
	</div>

</c:forEach>
</div>

</div>
</body>
</html>