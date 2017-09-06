<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
<title>Sesion Invalida</title>
<%@include file="header.jsp"%>
</head>
<body onload='document.login.username.focus();'>
	<div id="page-wrapper">

		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">
								<i class="fa fa-bar-chart-o fa-fw"></i> Sesi&oacute;n Inv&aacute;lida
							</h3>
						</div>
						<div class="panel-body" align="center">
							<div class="alert alert-warning">
								<strong>Tu sesi&oacute;n ha terminado </strong> 
							</div>
							<div>

								<h3>
									<a href="${pageContext.request.contextPath}/loginPage">Login to
										Ventas</a>
								</h3>
							</div>
						</div>
					</div>
				</div>
			</div>


		</div>
	</div>

	<%@include file="footer.jsp"%>
</body>

</html>