<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
<title>HelloWorld page</title>
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
								<i class="fa fa-bar-chart-o fa-fw"></i> Ingrese Credenciales
							</h3>
						</div>
						<div class="panel-body" align="center">
							<div>
								<h3>Ingreso de Ventas</h3>
								<c:if test="${not empty error}">
									<div class="alert alert-danger">${error}</div>
								</c:if>
								<c:if test="${not empty message}">
									<div class="alert alert-warning">${message}</div>
								</c:if>

								<form name='login' action="<c:url value='/loginPage' />" method='POST'>
									<table>
										<tr>
											<td>UserName:</td>
											<td><input type='text' name='username' value=''
												class="form-control" placeholder="Nombre"></td>
										</tr>
										<tr>
											<td>Password:</td>
											<td><input type='password' name='password' class="form-control"
												placeholder="Contraseña" /></td>
										</tr>
										<tr>
											<td colspan='2'><input name="submit" type="submit"
												class="btn btn-primary" value="Entrar" /></td>
										</tr>
									</table>
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
								</form>
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