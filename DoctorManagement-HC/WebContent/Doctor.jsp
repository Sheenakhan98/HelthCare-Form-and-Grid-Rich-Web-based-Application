<%@ page import="com.Doctor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Doctor Management</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Component/jquery-3.4.1.min.js" type="text/javascript"></script>
	<script src="Component/Doctor.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-6">
		<h1>Doctor Management</h1>
			<form id="doctorForm" name="doctorForm">
			
			First Name:
			<input name="FirstName" id="FirstName" type="text" class="form-control form-control-sm" ><br>
			
			Last Name:
			<input name="LastName" id="LastName" type="text" class="form-control form-control-sm"><br>
			
			Specialization:
			<input name="Specialization" id="Specialization" type="text" class="form-control form-control-sm" ><br>
			
			Gender:
			<input name="Gender" id="Gender" type="text" class="form-control form-control-sm" ><br>
			
			Email:
			<input name="Email" id="Email" type="email" class="form-control form-control-sm" ><br>
			
			Contact No:
			<input name="ContactNo" id="ContactNo" type="text" class="form-control form-control-sm" ><br>
			
			Standard Fees:
			<input name="StandardFees" id="StandardFees" type="text"  class="form-control form-control-sm"><br>
			
			Password:
			<input name="Password" id="Password" type="password" class="form-control form-control-sm"><br>
			
			<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"><br>
			<input type="hidden" id="hidDocIDSave" name="hidDocIDSave" value="">
			
		</form>
		
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		
		<br>
			<div id=divDocGrid>
				<%
					Doctor doc = new Doctor();
					out.print(doc.readDoctors());	//read doctors
				%>
			</div>
		</div>
	</div>
</div>
</body>
</html>