<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Catalogo de Productos</title>
<%@include file="../header.jsp"%>
</head>
<body>
	<div id="wrapper">

		<!-- Navigation -->
		<%@include file="../menu.jsp"%>

		<div id="page-wrapper">
			<spring:url value="/productos/add" var="urlAddProduct" />
			<div id="navbar">
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${productoForm['new']}">
							<li class="active"><a
								href="${pageContext.request.contextPath}/productos">Regresar</a></li>
						</c:when>
						<c:otherwise>
							<li class="active"><a href="${urlAddProduct}">Agregar Producto</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> Catalogo Productos
							</li>
						</ol>
						<c:choose>
							<c:when test="${productoForm['new']}">
								<h1>Agregar producto</h1>
							</c:when>
							<c:otherwise>
								<h1>Actualizar producto</h1>
							</c:otherwise>
						</c:choose>
						<br />

						<spring:url value="/productos" var="productoActionUrl" />

						<form:form class="form-horizontal" method="post"
							modelAttribute="productoForm" action="${productoActionUrl}">

							<form:hidden path="idProducto" />

							<spring:bind path="nombre">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Nombre </label>
									<div class="col-sm-10">
										<form:input path="nombre" type="text" class="form-control "
											id="nombre" placeholder="Nombre del producto" />
										<form:errors path="nombre" class="control-label" />
									</div>
								</div>
							</spring:bind>
							<spring:bind path="variedad">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Variedad
									</label>
									<div class="col-sm-10">
										<form:input path="variedad" type="text" class="form-control "
											id="variedad" placeholder="variedad" />
										<form:errors path="variedad" class="control-label" />
									</div>
								</div>
							</spring:bind>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<c:choose>
										<c:when test="${productoForm['new']}">
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