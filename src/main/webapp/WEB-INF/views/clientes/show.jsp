<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Catalogo de Clientes</title>
<%@include file="../header.jsp"%>
</head>
<body>
	<div id="wrapper">

		<!-- Navigation -->
		<%@include file="../menu.jsp"%>

		<div id="page-wrapper">
			<spring:url value="/clientes/add" var="urlAddClient" />
			<div id="navbar">
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="${urlAddClient}"><i
							class="fa fa-arrow-circle-right">Agregar cliente</i></a></li>

				</ul>
			</div>
			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<ol class="breadcrumb">
							<i class="fa fa-dashboard"></i>
							<a href="<c:url value='/homePage' />"> Dashboard</a>
							<li class="active"><i class="fa fa-dashboard"></i> Catalogo
								Clientes</li>
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

						<h1>Detalles del Cliente</h1>
						<br />

						<div class="row">
							<label class="col-sm-2">Nombre de cliente</label>
							<div class="col-sm-10">${cliente.nombre}</div>
						</div>

						<div class="row">
							<label class="col-sm-2">Direcci&oacute;n</label>
							<div class="col-sm-10">${cliente.direccion}</div>
						</div>

						<div class="row">
							<label class="col-sm-2">Celular</label>
							<div class="col-sm-10">${cliente.celular}</div>
						</div>
						<div class="row">
							<label class="col-sm-2">Tel&eacute;fono</label>
							<div class="col-sm-10">${cliente.telefono}</div>
						</div>

						<div class="row">
							<label class="col-sm-2">RFC</label>
							<div class="col-sm-10">${cliente.rfc}</div>
						</div>
						<div class="row">
							<label class="col-sm-2">Observaci&oacute;n</label>
							<div class="col-sm-10">${cliente.observacion}</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
							<a href="${pageContext.request.contextPath}/clientes">Regresar<i
										class="fa fa-arrow-circle-right"></i></a>
						</div>
					</div>
				</div>
				<!-- /.row -->
			</div>
		</div>
	</div>
</body>
</html>