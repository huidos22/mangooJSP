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
			<spring:url value="/clientes/add" var="urlAddUser" />
			<div id="navbar">
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${clienteForm['new']}">
							<li class="active"><a
								href="${pageContext.request.contextPath}/clientes">Regresar</a></li>
						</c:when>
						<c:otherwise>
							<li class="active"><a href="${urlAddUser}">Agregar Cliente</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<ol class="breadcrumb">
							<i class="fa fa-dashboard"></i>
							<a href="<c:url value='/homePage' />"> Dashboard</a>
							<li class="active"><i class="fa fa-dashboard"></i> Catalogo Clientes
							</li>
						</ol>
						<c:choose>
							<c:when test="${clienteForm['new']}">
								<h1>Agregar cliente</h1>
							</c:when>
							<c:otherwise>
								<h1>Actualizar cliente</h1>
							</c:otherwise>
						</c:choose>
						<br />

						<spring:url value="/clientes" var="clienteActionUrl" />

						<form:form class="form-horizontal" method="post"
							modelAttribute="clienteForm" action="${clienteActionUrl}">

							<form:hidden path="idCliente" />

							<spring:bind path="nombre">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Nombre </label>
									<div class="col-sm-10">
										<form:input path="nombre" type="text" class="form-control "
											id="nombre" placeholder="Nombre del cliente" />
										<form:errors path="nombre" class="control-label" />
									</div>
								</div>
							</spring:bind>
							<spring:bind path="direccion">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Direcci&oacute;n </label>
									<div class="col-sm-10">
										<form:input path="direccion" type="text" class="form-control "
											id="direccion" placeholder="direccion del cliente" />
										<form:errors path="direccion" class="control-label" />
									</div>
								</div>
							</spring:bind>
							<spring:bind path="celular">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Nombre </label>
									<div class="col-sm-10">
										<form:input path="celular" type="text" class="form-control "
											id="celular" placeholder="celular del cliente" />
										<form:errors path="celular" class="control-label" />
									</div>
								</div>
							</spring:bind>
							<spring:bind path="telefono">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Tel&eacute;fono </label>
									<div class="col-sm-10">
										<form:input path="telefono" type="text" class="form-control "
											id="telefono" placeholder="telefono del cliente" />
										<form:errors path="telefono" class="control-label" />
									</div>
								</div>
							</spring:bind>
							<spring:bind path="rfc">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">R.F.C.</label>
									<div class="col-sm-10">
										<form:input path="rfc" type="text" class="form-control " id="rfc"
											placeholder="rfc del cliente" />
										<form:errors path="rfc" class="control-label" />
									</div>
								</div>
							</spring:bind>
							<spring:bind path="observacion">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Observaciones </label>
									<div class="col-sm-10">
										<form:textarea path="observacion" rows="5" class="form-control"
											id="observacion" placeholder="observacion" />
										<form:errors path="observacion" class="control-label" />
									</div>
								</div>
							</spring:bind>


							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<c:choose>
										<c:when test="${clienteForm['new']}">
											<button type="submit" class="btn-lg btn-primary pull-right">Agregar</button>
										</c:when>
										<c:otherwise>
											<button type="submit" class="btn-lg btn-primary pull-right">Actualizar</button>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</form:form>
					</div>
				</div>
				<!-- /.row -->
			</div>
		</div>
	</div>
</body>
</html>