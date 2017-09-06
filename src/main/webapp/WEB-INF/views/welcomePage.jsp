<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
<title>HelloWorld page</title>
<%@include file="header.jsp"%>
</head>
<body>
		<div id="page-wrapper">

			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<i class="fa fa-bar-chart-o fa-fw"></i> Bienvenido
								</h3>
							</div>
							<div class="panel-body" align="center">
								<div>
									<a href="${pageContext.request.contextPath}/loginPage">Login
										to Ventas</a>
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