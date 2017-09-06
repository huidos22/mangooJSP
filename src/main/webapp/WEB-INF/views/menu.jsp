<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-ex1-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="<c:url value='/homePage' />">Administracion
			de Ventas </a>
	</div>
	<!-- Top Menu Items -->
	<ul class="nav navbar-right top-nav">
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"><i class="fa fa-clock-o"></i> <b class="caret"></b></a>
			<ul class="dropdown-menu alert-dropdown">
				<li>
					<div class="media">
						<span class="pull-left"> <img class="media-object"
							src="http://placehold.it/50x50" alt="">
						</span>
						<div class="media-body">
							<h5 class="media-heading">
								<strong>Hora</strong>
							</h5>
							<a href="#"><fmt:formatDate type="time" dateStyle="medium"
									timeStyle="medium" value="${fecha}" /></a>
						</div>
					</div>
				</li>
				<li>
					<div class="media">
						<span class="pull-left"> <img class="media-object"
							src="http://placehold.it/50x50" alt="">
						</span>
						<div class="media-body">
							<h5 class="media-heading">
								<strong>Fecha</strong>
							</h5>
							<a href="#"><fmt:formatDate type="date" value="${fecha}" /></a>
						</div>
					</div>

				</li>
			</ul></li>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown"><i class="fa fa-user"></i>
				${sessionScope.usuarioObjSession.fullName}" <b class="caret"></b></a>
			<ul class="dropdown-menu">
				<li><a href="#"><i class="fa fa-fw fa-user"></i>
						${userSession.fullName}</a></li>
				<li><a href="#"><i class="fa fa-fw fa-envelope"></i>
						${userSession.userName}</a></li>
				<li><a href="#"><i class="fa fa-fw fa-gear"></i>
						${userSession.email}</a></li>
				<li class="divider"></li>
				<li><a href="/logout"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
				</li>
			</ul></li>
	</ul>

	<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav side-nav">
			<li class="active"><a href="<c:url value='/homePage' />"><i
					class="fa fa-fw fa-dashboard" aria-hidden="true"></i> Dashboard</a></li>
			<li><a href="<c:url value='/usuarios' />"><i
					class="fa fa-fw fa-users" aria-hidden="true"></i> Usuarios</a></li>
			<li><a href="<c:url value='/clientes' />"><i class="fa fa-user-plus"
					aria-hidden="true"></i> Clientes</a></li>
			<li><a href="<c:url value='/productos' />"><i
					class="fa fa-fw fa-lemon-o" aria-hidden="true"></i> Productos</a></li>
			<li><a href="<c:url value='/ventas/add' />"><i
					class="fa fa-fw fa-percent" aria-hidden="true"></i> Ventas</a></li>
			<li><a href="javascript:;" data-toggle="collapse" data-target="#demo"><i
					class="fa fa-fw fa-arrows-v"></i> Tablas <i
					class="fa fa-fw fa-caret-down"></i></a>
				<ul id="demo" class="collapse">
					<li><a href="/ventasxcliente">Ventas por cliente</a></li>
					<li><a href="/ventasxproducto">Ventas por Producto</a></li>
				</ul></li>
			<li><a href="<c:url value='/gastoCorte' />"><i
					class="fa fa-fw fa-gear" aria-hidden="true"></i> Gasto Corte </a></li>
			<li><a href="<c:url value='/reportes' />"><i
					class="fa fa-fw fa-book" aria-hidden="true"></i> Reportes </a></li>
		</ul>
	</div>
	<!-- /.navbar-collapse -->
</nav>