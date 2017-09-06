<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Ventas</title>
<%@include file="../header.jsp"%>

<script type="text/javascript">
	function getMunicipios() {
		$('select#idMunicipio').empty();
		var contes = "${pageContext.request.contextPath}";
		var idEstado = $('select#idEstado').val();
		$.getJSON(contes + "/municipiosList.json?", {
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

	function getGastoCorteByMpio() {
		$('select#idGastoCorte').empty();
		var contes = "${pageContext.request.contextPath}";
		var idEstado = $('select#idEstado').val();
		var idMunicipio = $('select#idMunicipio').val();
		$.getJSON(contes + "/gastoCorteDtosList.json?", {
			idEstado : $('select#idEstado').val(),
			idMunicipio : $('select#idMunicipio').val()
		}, function(data) {
			var html = '';
			var len = data.length;
			$('select#idGastoCorte').empty().append(
					'<option value="0">--- Select ---</option>');
			for (var i = 0; i < len; i++) {
				html += '<option value="' + data[i].idGastoCorte + '">'
						+ data[i].gastoCortePercent + '</option>';
			}
			$('select#idGastoCorte').append(html);
		});
	}
	$(function() {
		$('#datetimepickerventas').datetimepicker({
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
		$('#idMunicipio').change(function() {
			getGastoCorteByMpio();
		});
	});
</script>
</head>
<body>

	<div id="wrapper">

		<!-- Navigation -->
		<%@include file="../menu.jsp"%>
		<spring:url value="/ventas" var="ventaActionUrl" />
		<div id="page-wrapper">
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Captura de <small>Ventas </small>
						</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> <a
								href="<c:url value='/homePage' />"> Dashboard</a> <i class="fa fa-usd "></i>
								Ventas</li>
						</ol>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-12">
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
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<i class="fa fa-bar-chart-o fa-fw"></i>Venta
								</h3>
							</div>

							<div class="panel-body">
								<form:form class="form-horizontal" method="post"
									modelAttribute="ventaForm" action="${ventaActionUrl}">
									<form:hidden path="idVenta" />
									<form:hidden path="usuario.id" />
									<form:hidden path="fechaGastoCorte" />
									<spring:bind path="fecha">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Fecha</label>
											<div class="col-sm-5">
												<div class='input-group date' id='datetimepickerventas'>
													<form:input path="fecha" type="text" class="form-control "
														id="fecha" placeholder="fecha" readonly="true" />
													<form:errors path="fecha" class="control-label" />
													<span class="input-group-addon"> <span
														class="glyphicon glyphicon-calendar"></span>
													</span>
												</div>
											</div>
										</div>
									</spring:bind>

									<spring:bind path="cliente.idCliente">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Cliente : </label>
											<div class="col-sm-5">
												<form:select path="cliente.idCliente" class="form-control">
													<form:option value="0" label="--- Select ---" />
													<form:options items="${clientesList}" />
												</form:select>
												<form:errors path="cliente.idCliente" class="control-label" />
											</div>
											<div class="col-sm-5"></div>
										</div>
									</spring:bind>
									<spring:bind path="idProducto">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Producto : </label>
											<div class="col-sm-5">
												<form:select path="idProducto" class="form-control">
													<form:option value="0" label="--- Select ---" />
													<form:options items="${productosList}" />
												</form:select>
												<form:errors path="idProducto" class="control-label" />
											</div>
											<div class="col-sm-5"></div>
										</div>
									</spring:bind>
									<spring:bind path="estado.idEstado">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Estados : </label>
											<div class="col-sm-5">
												<form:select path="estado.idEstado" id="idEstado"
													class="form-control">
													<form:option value="0" label="--- Select ---" />
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
													<form:option value="0" label="--- Select ---" />
												</form:select>
												<form:errors path="municipio.idMunicipio" class="control-label" />
											</div>
											<div class="col-sm-5"></div>
										</div>
									</spring:bind>
									<spring:bind path="gastoCorteDto.idGastoCorte">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label"> Gasto por Corte %:</label>
											<div class="col-sm-5">
												<form:select path="gastoCorteDto.idGastoCorte" class="form-control" id="idGastoCorte" >
													<c:if test="${not empty gastoCorteDto.idGastoCorte}">
														<form:option value="${ gastoCorteDto.idGastoCorte}"
															label="${ gastoCorteDto.gastoCortePercent}" />
													</c:if>
													<c:if test="${empty gastoCorteDto.idGastoCorte}">
														<form:option value="0" label="--- Select ---" />
													</c:if>
												</form:select>
												<form:errors path="gastoCorteDto.idGastoCorte" class="control-label" />
											</div>
											<div class="col-sm-5"></div>
										</div>
									</spring:bind>
									<spring:bind path="retProd">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Ret Prod %:</label>
											<div class="col-sm-5">
												<form:input path="retProd" class="form-control" type="number" min="0"
													step="1" pattern="^[0-9]" id="retProd" 
													placeholder="retProd" />
												<form:errors path="retProd" class="control-label" />
											</div>
										</div>
									</spring:bind>
									<spring:bind path="observacion">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Observaciones: </label>
											<div class="col-sm-5">
												<form:textarea path="observacion" rows="5" class="form-control"
													id="observacion" placeholder="observacion" />
												<form:errors path="observacion" class="control-label" />
											</div>
										</div>
									</spring:bind>
									<spring:bind path="cajas">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Cajas:</label>
											<div class="col-sm-5">
												<form:input path="cajas" class="form-control" type="number" min="0"
													step="1" pattern="^[0-9]" id="detalleVenta.cajas"
													placeholder="cajas" />
												<form:errors path="cajas" class="control-label" />
											</div>
										</div>
									</spring:bind>
									<spring:bind path="formaPago">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Forma Pago:</label>
											<div class="col-sm-5">
												<form:select path="formaPago" class="form-control">
													<form:option value="NONE" label="--- Select ---" />
													<form:options items="${formaPagoList}" />
												</form:select>
												<form:errors path="formaPago" class="control-label" />
											</div>
											<div class="col-sm-5"></div>
										</div>
									</spring:bind>
									<spring:bind path="precioKilo">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Precio Kilo (MXN):</label>
											<div class="col-sm-5">
												<form:input path="precioKilo" class="form-control" id="precioKilo"
													type="number" min="0" step="any" placeholder="precio Kilo" />
												<form:errors path="precioKilo" class="control-label" />
											</div>
										</div>
									</spring:bind>
									<spring:bind path="kilos">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label"> Kilos:</label>
											<div class="col-sm-5">
												<form:input path="kilos" class="form-control" id="kilos"
													pattern="^[0-9]" type="number" min="0" step="any"
													placeholder="precio Kilo" />
												<form:errors path="kilos" class="control-label" />
											</div>
										</div>
									</spring:bind>

									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button type="submit" class="btn-lg btn-primary pull-right">Realiza
												Venta</button>
										</div>
									</div>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.container-fluid -->
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
	<%@include file="../footer.jsp"%>
</body>
</html>