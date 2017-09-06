<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Ventas</title>
<%@include file="../header.jsp"%>

</head>
<body>

	<div id="wrapper">

		<!-- Navigation -->
		<%@include file="../menu.jsp"%>
		<spring:url value="/ventasxproducto" var="ventaActionUrl" />
		<div id="page-wrapper">
			<div class="container-fluid">
				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Busqueda de <small>Ventas </small>
						</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> <a
								href="<c:url value='/homePage' />"> Dashboard</a> <i class="fa fa-usd "></i>
								Ventas por producto</li>
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
									<form:hidden path="usuario.id" />
									<spring:bind path="idProducto">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Producto : </label>
											<div class="col-sm-5">
												<form:select path="idProducto" class="form-control">
													<form:option value="0" label="--- Select ---" />
													<form:options items="${productos}" />
												</form:select>
												<form:errors path="idProducto" class="control-label" />
											</div>
											<div class="col-sm-5"></div>
										</div>
									</spring:bind>

									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button type="submit" class="btn-lg btn-primary pull-right">Buscar</button>
										</div>
									</div>
								</form:form>
								<c:if test="${not empty producto.idProducto }">
									<div class="col-lg-12">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<h3 class="panel-title">Info producto</h3>
											</div>

											<div class="panel-body">
												<ul class="list-group">
													<li class="list-group-item"><b>Nombre: </b>${producto.nombre }</li>
													<li class="list-group-item"><b>Variedad: </b>${producto.variedad }</li>

												</ul>
											</div>

										</div>
									</div>
								</c:if>
								<div class="col-lg-12">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h3 class="panel-title">
												<i class="fa fa-money fa-fw"></i> Ventas del Producto 
											</h3>
										</div>
										<div class="panel-body">
											<div class="table-responsive">
												<table class="table table-bordered table-hover table-striped">
													<thead>
														<tr>
															<th>fecha de venta</th>
															<th>Cliente</th>
															<th>Id de Venta</th>
															<th>Observacion</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="venta" items="${lstVentasxProducto}">
															<tr>
																<td align="center">${venta.fecha}</td>
																<td align="center">${venta.cliente.nombre}</td>
																<td align="center">${venta.idVenta}</td>
																<td>${venta.observacion}</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
										<div class="panel-body">
											<div class="table-responsive">
												<table class="table table-bordered table-hover table-striped">
													<thead>
														<tr>
															<th>Id venta</th>
															<th>Id gasto Corte</th>
															<th>Cajas</th>
															<th>Kilos</th>
															<th>Precio por kilo</th>
															<th>Forma de pago</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="detVent" items="${lstDetalleVentas}">
															<tr>
																<td align="center">${detVent.id.idVenta}</td>
																<td align="center">${detVent.id.idGastoCorte}</td>
																<td align="center">${detVent.cajas}</td>
																<td align="center">${detVent.kilos}</td>
																<td>${detVent.precioKilo}</td>
																<td>${detVent.formaPago}</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
											<div class="text-right">
												<a href="/homePage">Regresar<i class="fa fa-arrow-circle-right"></i></a>
											</div>
										</div>
									</div>
								</div>
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