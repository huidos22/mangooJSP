<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Catalogo de Clientes</title>
<%@include file="../header.jsp"%>
<spring:url value="/clientes/add" var="urlAddClient" />


<script src="${pageContext.request.contextPath}/js/clientes/clientesjqGrid.js"
	type="text/javascript"></script>
</head>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<%@include file="../menu.jsp"%>

		<div id="page-wrapper">

			<div id="navbar">
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="${urlAddClient}">Agregar cliente</a></li>
				</ul>
			</div>
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<ol class="breadcrumb">
							<i class="fa fa-dashboard"></i>
							<a href="<c:url value='/homePage' />"> Dashboard</a>
							<li class="active"><i class="fa fa-dashboard"></i> Catalogo Clientes</li>
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
									<th>nombre de cliente</th>
									<th>Direcci&oacute;n</th>
									<th>Celular</th>
									<th>Tel&eacute;fono</th>
									<th>RFC</th>
									<th>observaci&oacute;n</th>
								</tr>
							</thead>
							<c:forEach var="cliente" items="${clientes}">
								<tr>
									<td>${cliente.idCliente}</td>
									<td>${cliente.nombre}</td>
									<td>${cliente.direccion}</td>
									<td>${cliente.celular}</td>
									<td>${cliente.telefono}</td>
									<td>${cliente.rfc}</td>
									<td>${cliente.observacion}</td>

									<td><spring:url value="/clientes/${cliente.idCliente}"
											var="clienteUrl" /> <spring:url
											value="/clientes/${cliente.idCliente}/delete" var="deleteUrl" /> <spring:url
											value="/clientes/${cliente.idCliente}/update" var="updateUrl" />
										<button class="btn btn-info" onclick="location.href='${clienteUrl}'">Mostrar</button>
										<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Actualizar</button>
										<button class="btn btn-danger" onclick="location.href='${deleteUrl}'">Borrar</button>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div id='jqgrid'>
							<table id='grid'></table>
							<div id='pager'></div>
						</div>
						<div id='msgbox' title='' style='display: none'></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>