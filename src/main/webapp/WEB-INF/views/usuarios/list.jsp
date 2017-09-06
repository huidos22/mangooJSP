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
					<li class="active"><a href="${urlAddUser}">Agregar usuario</a></li>
				</ul>
			</div>
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> Catalogo Usuarios</li>
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
					</div>
				</div>

				<div class="row">
					<div class="col-lg-12">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>#ID</th>
									<th>nombre de usuario</th>
									<th>Nombre</th>
									<th>Password</th>
									<th>Action</th>
								</tr>
							</thead>

							<c:forEach var="usuario" items="${usuarios}">
								<tr>
									<td>${usuario.id}</td>
									<td>${usuario.username}</td>
									<td>${usuario.fullName}</td>
									<td>${usuario.password}</td>

									<td><spring:url value="/usuarios/${usuario.id}" var="usuarioUrl" />
										<spring:url value="/usuarios/${usuario.id}/delete" var="deleteUrl" />
										<spring:url value="/usuarios/${usuario.id}/update" var="updateUrl" />

										<button class="btn btn-info" onclick="location.href='${usuarioUrl}'">Mostrar</button>
										<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Actualizar</button>
										<button class="btn btn-danger" onclick="location.href='${deleteUrl}'">Borrar</button></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<!-- /.row -->
			</div>
		</div>
	</div>
</body>
</html>