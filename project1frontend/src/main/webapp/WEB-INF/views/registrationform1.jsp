<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
	<script type="text/javascript">
		function fillShippingAddress(form)
		{
			if(form.shippingaddressform.checked==true)
				{
					form["shippingaddress.apartmentnumber"].value=form["billingaddress.apartmentnumber"].value;
					form["shippingaddress.streetname"].value=form["billingaddress.streetname"].value;
					form["shippingaddress.city"].value=form["billingaddress.city"].value;
					form["shippingaddress.state"].value=form["billingaddress.state"].value;
					form["shippingaddress.country"].value=form["billingaddress.country"].value;
					form["shippingaddress.zipcode"].value=form["billingaddress.zipcode"].value;
				}
			if(form.shippingaddressform.checked==false)
			{
				form["shippingaddress.apartmentnumber"].value="";
				form["shippingaddress.streetname"].value="";
				form["shippingaddress.city"].value="";
				form["shippingaddress.state"].value="";
				form["shippingaddress.country"].value="";
				form["shippingaddress.zipcode"].value="";
			}
		}
		
		$(document).ready(function(){
			$('#form').validate({
				rules:{
					firstname:{required:true},
					lastname:{required:true},
					phonenumber:{required:true,number:true,minlength:10,maxlength:10},
					"user.email":{required:true,email:true},
					"user.password":{required:true,minlength:5,maxlength:10},
					"billingaddress.apartmentnumber":{required:true},
					"billingaddress.streetname":{required:true},
					"billingaddress.state":{required:true},
					"billingaddress.city":{required:true},
					"billingaddress.country":{required:true},
					"billingaddress.zipcode":{required:true,number:true}
				},
				messages:{
					firstname:{required:"Firstname is mandatory"},
					phonenumber:{required:"Phonenumber is required"},
					"user.email":{required:"Email is required",email:"Please enter valid email address"}
				}
			})
		})
		
	</script>
</head>
<body>
<div class="container">
<c:url var="url" value="/all/registercustomer"></c:url>
<form:form class="form-horizontal" align="center" modelAttribute="customer" action="${url}" method="post" id="form">
<span style="color:red;"><b>Customer Details</b></span><br>
		<form:hidden path="id"/>
		
		<div class="form-group">
		<form:label class="label-control col-lg-2" path="firstname">Enter FirstName:</form:label>
		<div class="col-lg-6" >
		<form:input class="form-control" path="firstname" id="firstname"/>
		</div>
		</div>
		
		<div class="form-group">
		<form:label class="label-control col-lg-2" path="lastname">LastName:</form:label>
		<div class="col-lg-6" >
		<form:input class="form-control" path="lastname" id="lastname"/>
		</div>
		</div>
		
		<div class="form-group">
		<form:label class="label-control col-lg-2" path="phonenumber">Enter PhoneNumber:</form:label>
		<div class="col-lg-6" >
		<form:input class="form-control" path="phonenumber" id="phonenumber"></form:input>
		</div>
		</div>
		
		<hr>
		<span style="color:red;"><b>Login Credential</b></span><br>
		<div class="form-group">
		<form:label class="label-control col-lg-2" path="user.email">Enter Email:</form:label>
		<div class="col-lg-6" >
		<form:input class="form-control" path="user.email" id="user.email" type="email"/>
		<span style="color:red">${error }</span>
		</div>
		</div>
		
		<div class="form-group">
		<form:label class="label-control col-lg-2" path="user.password">Enter Password:</form:label>
		<div class="col-lg-6" >
		<form:input class="form-control" path="user.password" id="user.password" type="password"></form:input>
		</div>
		</div>
		
		<hr>
	
		<span style="color:red;"><b>Billing Address</b></span><br>
		<div class="form-group">
		<form:label class="label-control col-lg-2" path="billingaddress.apartmentnumber">Enter Apartmentnumber</form:label>
		<div class="col-lg-6" >
			<form:input class="form-control" path="billingaddress.apartmentnumber" id="billingaddress.apartmentnumber"/>
		</div>
		</div>
		
		<div class="form-group">
			<form:label class="label-control col-lg-2" path="billingaddress.streetname">Enter streetname</form:label>
			<div class="col-lg-6" >
			<form:input class="form-control" path="billingaddress.streetname" id="billingaddress.streetname"/>
			</div>
			</div>
			
			<div class="form-group">
		<form:label class="label-control col-lg-2" path="billingaddress.city">Enter city</form:label>
		<div class="col-lg-6" >
			<form:input class="form-control" path="billingaddress.city" id="billingaddress.city"/>
			</div>
			</div>
			
			<div class="form-group">
		<form:label class="label-control col-lg-2" path="billingaddress.state">Enter state</form:label>
		<div class="col-lg-6" >
			<form:input class="form-control" path="billingaddress.state" id="billingaddress.state"/>
		</div>
		</div>
		
		<div class="form-group">
			<form:label class="label-control col-lg-2" path="billingaddress.country">Enter country</form:label>
			<div class="col-lg-6" >
			<form:input class="form-control" path="billingaddress.country" id="billingaddress.country"/>
			</div>
			</div>
			
			<div class="form-group">
		<form:label class="label-control col-lg-2" path="billingaddress.zipcode">Enter Zipcode</form:label>
		<div class="col-lg-6" >
			<form:input class="form-control" path="billingaddress.zipcode" id="billingaddress.zipcode"/>
			</div>
			</div>
			
		<hr>
		<span style="color:red;"><b>Shipping Address</b></span>
		<b><input type="checkbox" onclick="fillShippingAddress(this.form)" id="shippingaddressform"></b><br>
		<div class="form-group">
		<form:label class="label-control col-lg-2" path="shippingaddress.apartmentnumber">Enter Apartmentnumber</form:label>
		<div class="col-lg-6" >
			<form:input class="form-control" path="shippingaddress.apartmentnumber" id="shippingaddress.apartmentnumber"/>
		</div>
		</div>
		
		<div class="form-group">
			<form:label class="label-control col-lg-2" path="shippingaddress.streetname">Enter streetname</form:label>
			<div class="col-lg-6" >
			<form:input class="form-control" path="shippingaddress.streetname" id="shippingaddress.streetname"/>
			</div>
			</div>
			
			<div class="form-group">
		<form:label class="label-control col-lg-2" path="shippingaddress.city">Enter city</form:label>
		<div class="col-lg-6" >
			<form:input class="form-control" path="shippingaddress.city" id="shippingaddress.city"/>
			</div>
			</div>
			
			<div class="form-group">
		<form:label class="label-control col-lg-2" path="shippingaddress.state">Enter state</form:label>
		<div class="col-lg-6" >
			<form:input class="form-control"  path="shippingaddress.state" id="shippingaddress.state"/>
		</div>
		</div>
		
		<div class="form-group">
			<form:label class="label-control col-lg-2" path="shippingaddress.country">Enter country</form:label>
			<div class="col-lg-6" >
			<form:input class="form-control" path="shippingaddress.country" id="shippingaddress.country"/>
			</div>
			</div>
			
			<div class="form-group">
				<form:label class="label-control col-lg-2"
					path="shippingaddress.zipcode">Enter Zipcode</form:label>
				<div class="col-md-5">
					<form:input class="form-control" path="shippingaddress.zipcode"
						id="shippingaddress.zipcode" />
				</div>
			</div>
			<input type="submit" value="Register" style="background-color:maroon;">
		
		
		
	
</form:form>
</div>
</body>
</html>