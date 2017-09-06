<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Catalogo de Gasto Corte</title>
<%@include file="../header.jsp"%>
<script type="text/javascript">
	function getMunicipios() {
		var contes = "${pageContext.request.contextPath}";
		var idEstado = $('select#idEstado').val();
		$.getJSON(contes + "/municipiosList.json?" , {
			idEstado : $('select#idEstado').val()
		}, function(data) {
			var html = '';
			var len = data.length;
			$('select#idMunicipio').empty().append(
					'<option value="0">--- Select ---</option>');
			for (var i = 0; i < len; i++) {
				html += '<option value="' + data[i].idMunicipio + '">'
						+ data[i].nombre + '</option>';
			}
			$('select#idMunicipio').append(html);
		});
	}
	$(function() {
		$('#datetimepickerfechaGastoCorte').datetimepicker({
			icons : {
				time : "fa fa-clock-o",
				date : "fa fa-calendar",
				up : "fa fa-arrow-up",
				down : "fa fa-arrow-down"
			}
		});
		$('#idEstado').change(function() {
			getMunicipios();
		});
	});
</script>
</head>
<body>
	<div id="wrapper">

		<!-- Navigation -->
		<%@include file="../menu.jsp"%>

		<div id="page-wrapper">
			<spring:url value="/gastoCorte/add" var="urlAddgastoCorte" />
			<div id="navbar">
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${gastoCorteForm['new']}">
							<li class="active"><a
								href="${pageContext.request.contextPath}/gastoCorte">Regresar</a></li>
						</c:when>
						<c:otherwise>
							<li class="active"><a href="${urlAddgastoCorte}">Agregar
									gastoCorte</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> Catalogo Gasto
								Corte</li>
						</ol>
						<c:choose>
							<c:when test="${gastoCorteForm['new']}">
								<h1>Agregar gastoCorte</h1>
							</c:when>
							<c:otherwise>
								<h1>Actualizar gastoCorte</h1>
							</c:otherwise>
						</c:choose>
						<br />

						<spring:url value="/gastoCorte" var="gastoCorteActionUrl" />

						<form:form class="form-horizontal" method="post"
							modelAttribute="gastoCorteForm" action="${gastoCorteActionUrl}">

							<form:hidden path="idGastoCorte" />
							<spring:bind path="estado.idEstado">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Estados : </label>
									<div class="col-sm-5">
										<form:select path="estado.idEstado" id="idEstado" class="form-control">
											<c:if test="${not empty estado.idEstado}">
												<form:option value="${estado.idEstado}" label="${estado.nombre}" />
											</c:if>
											<c:if test="${empty estado.idEstado}">
												<form:option value="0" label="--- Select ---" />
											</c:if>
											<form:options items="${estadosList}" />
										</form:select>
										<form:errors path="estado.idEstado" class="control-label" />
									</div>
									<div class="col-sm-5"></div>
								</div>
							</spring:bind>
							<spring:bind path="municipio.idMunicipio">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Municipio : </label>
									<div class="col-sm-5">
										<form:select path="municipio.idMunicipio" id="idMunicipio"
											class="form-control">
											<c:if test="${not empty municipio.idMunicipio}">
												<form:option value="${municipio.idMunicipio}"
													label="${municipio.nombre}" />
											</c:if>
											<c:if test="${ empty municipio.idMunicipio}">
												<form:option value="0" label="--- Select ---" />
											</c:if>
											<form:options items="${municipiosList}" itemLabel="nombre" itemValue="idMunicipio"/>
										</form:select>
										<form:errors path="municipio.idMunicipio" class="control-label" />
									</div>
									<div class="col-sm-5"></div>
								</div>
							</spring:bind>
							<spring:bind path="gastoCortePercent">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label"> Gasto por Corte %:</label>
									<div class="col-sm-5">
										<div class="input-group spinner">
											<form:input path="gastoCortePercent" class="form-control"
												id="gastoCortePercent" placeholder="porcentaje" value="0"
												pattern="^[0-9]" type="number" min="0" step="any" />
											<form:errors path="gastoCortePercent" class="control-label" />
										</div>
									</div>
								</div>
							</spring:bind>
							<spring:bind path="fechaGastoCorte">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Fecha</label>
									<div class="col-sm-5">
										<div class='input-group date' id='datetimepickerfechaGastoCorte'>
											<form:input path="fechaGastoCorte" type="text" class="form-control "
												id="fechaGastoCorte" placeholder="fecha" readonly="true" />
											<form:errors path="fechaGastoCorte" class="control-label" />
											<span class="input-group-addon"> <span
												class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
									</div>
								</div>
							</spring:bind>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<c:choose>
										<c:when test="${gastoCorteForm['new']}">
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