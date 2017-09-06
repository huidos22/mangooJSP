<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Reportes</title>
<%@include file="../header.jsp"%>
</head>
<body>

	<div id="wrapper">

		<!-- Navigation -->
		<%@include file="../menu.jsp"%>

		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Reportes en  <small>Excel</small>
						</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> Dashboard</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->

				<!-- /.row -->

				<div class="row">
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-comments fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
<!-- 										<div class="huge">26</div> -->
										<div>Lista de Clientes</div>
									</div>
								</div>
							</div>
							<a href="/clientesAll.xls">
								<div class="panel-footer">
									<span class="pull-left">Bajar reporte</span> <span class="pull-right"><i
										class="fa fa-arrow-circle-right"> </i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-green">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-tasks fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
<!-- 										<div class="huge">12</div> -->
										<div> Reporte General</div>
									</div>
								</div>
							</div>
							<a href="/originalReporte.xls">
								<div class="panel-footer">
									<span class="pull-left">Bajar reporte</span> <span class="pull-right"><i
										class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-yellow">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-shopping-cart fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
<!-- 										<div class="huge">124</div> -->
										<div>Productos</div>
									</div>
								</div>
							</div>
							<a href="/allProductos.xls">
								<div class="panel-footer">
									<span class="pull-left">Bajar reporte</span> <span class="pull-right"><i
										class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-red">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-support fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
<!-- 										<div class="huge">13</div> -->
										<div>Reporte por Producto</div>
									</div>
								</div>
							</div>
							<a href="/allProductos.xls">
								<div class="panel-footer">
									<span class="pull-left">Bajar reporte</span> <span class="pull-right"><i
										class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->

	<%@include file="../footer.jsp"%>
</body>
</html>