<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Catalogo de Usuarios</title>
<%@include file="../header.jsp"%>
</head>
<body>
	<div id="wrapper">

		<!-- Navigation -->
		<%@include file="../menu.jsp"%>

		<div id="page-wrapper">
			<spring:url value="/usuarios/add" var="urlAddUser" />
			<div id="navbar">
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="${urlAddUser}"><i
							class="fa fa-arrow-circle-right">Agregar usuario</i></a></li>
				</ul>
			</div>
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> Catalogo Usuarios
							</li>
						</ol>
						<c:if test="${not empty msg}">
							<div class="alert alert-${css} alert-dismissible" role="alert">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<strong>${msg}</strong>
							</div>
						</c:if>

						<h1>usuario Detail</h1>
						<br />

						<div class="row">
							<label class="col-sm-2">Nombre de usuario</label>
							<div class="col-sm-10">${usuario.userName}</div>
						</div>

						<div class="row">
							<label class="col-sm-2">Nombre</label>
							<div class="col-sm-10">${usuario.fullName}</div>
						</div>

						<div class="row">
							<label class="col-sm-2">Password</label>
							<div class="col-sm-10">${usuario.password}</div>
						</div>
						<div class="row">
							<label class="col-sm-2">Sex</label>
							<div class="col-sm-10">${usuario.sex}</div>
						</div>

						<div class="row">
							<label class="col-sm-2">Number</label>
							<div class="col-sm-10">${usuario.number}</div>
						</div>
						<div class="row">
							<label class="col-sm-2">Email</label>
							<div class="col-sm-10">${usuario.email}</div>
						</div>

						<div class="row">
							<label class="col-sm-2">Address</label>
							<div class="col-sm-10">${usuario.address}</div>
						</div>

						<div class="row">
							<label class="col-sm-2">Newsletter</label>
							<div class="col-sm-10">${usuario.newsletter}</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<a href="${pageContext.request.contextPath}/usuarios">Regresar<i
									class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
				</div>
				<!-- /.row -->
			</div>
		</div>
	</div>
</body>
</html>