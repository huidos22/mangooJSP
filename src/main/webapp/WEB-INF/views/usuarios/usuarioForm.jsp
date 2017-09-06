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
				<c:choose>
					<c:when test="${usuarioForm['new']}">
						<li class="active"><a href="${pageContext.request.contextPath}/usuarios">Regresar</a></li>
					</c:when>
					<c:otherwise>
						<li class="active"><a href="${urlAddUser}">Agregar Usuario</a></li>
					</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<ol class="breadcrumb">
                            <li class="active">
                                <i class="fa fa-dashboard"></i> Catalogo Usuarios
                            </li>
                    	</ol>
						<c:choose>
							<c:when test="${usuarioForm['new']}">
								<h1>Agregar usuario</h1>
							</c:when>
							<c:otherwise>
								<h1>Actualizar usuario</h1>
							</c:otherwise>
						</c:choose>
						<br />

						<spring:url value="/usuarios" var="usuarioActionUrl" />

						<form:form class="form-horizontal" method="post"
							modelAttribute="usuarioForm" action="${usuarioActionUrl}">

							<form:hidden path="id" />

							<spring:bind path="userName">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Nombre de Usuario</label>
									<div class="col-sm-10">
										<span class="input-group-addon">@</span>
										<form:input path="userName" type="text" class="form-control "
											id="userName" placeholder="Nombre de usuario" />
										<form:errors path="userName" class="control-label" />
									</div>
								</div>
							</spring:bind>

							<spring:bind path="fullName">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Nombre Completo</label>
									<div class="col-sm-10">
										<form:input path="fullName" class="form-control" id="fullName"
											placeholder="Nombre " />
										<form:errors path="fullName" class="control-label" />
									</div>
								</div>
							</spring:bind>

							<spring:bind path="password">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Password</label>
									<div class="col-sm-10">
										<form:password path="password" class="form-control"
											id="password" placeholder="password" />
										<form:errors path="password" class="control-label" />
									</div>
								</div>
							</spring:bind>

							<spring:bind path="confirmPassword">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Confirmar Password</label>
									<div class="col-sm-10">
										<form:password path="confirmPassword" class="form-control"
											id="confirmPassword" placeholder="confirmPassword" />
										<form:errors path="confirmPassword" class="control-label" />
									</div>
								</div>
							</spring:bind>
							<spring:bind path="email">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">E-mail</label>
									<div class="col-sm-10">
										<form:input path="email" class="form-control" id="email"
											placeholder="Email" />
										<form:errors path="email" class="control-label" />
									</div>
								</div>
							</spring:bind>
							<spring:bind path="address">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Direcci&oacute;n</label>
									<div class="col-sm-10">
										<form:textarea path="address" rows="5" class="form-control"
											id="address" placeholder="Direccion" />
										<form:errors path="address" class="control-label" />
									</div>
								</div>
							</spring:bind>
							<spring:bind path="number">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Numero</label>
									<div class="col-sm-10">
										<form:input path="number" type="text" class="form-control "
											id="number" placeholder="Numero" />
										<form:errors path="number" class="control-label" />
									</div>
								</div>
							</spring:bind>
							<spring:bind path="newsletter">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Newsletter</label>
									<div class="col-sm-10">
										<div class="checkbox">
											<label> <form:checkbox path="newsletter"
													id="newsletter" />
											</label>
											<form:errors path="newsletter" class="control-label" />
										</div>
									</div>
								</div>
							</spring:bind>
							<spring:bind path="sex">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Sexo</label>
									<div class="col-sm-10">
										<label class="radio-inline"> <form:radiobutton
												path="sex" value="M" /> Hombre
										</label> <label class="radio-inline"> <form:radiobutton
												path="sex" value="F" /> Mujer
										</label> <br />
										<form:errors path="sex" class="control-label" />
									</div>
								</div>
							</spring:bind>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<c:choose>
										<c:when test="${usuarioForm['new']}">
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