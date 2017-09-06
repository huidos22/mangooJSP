<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/ui.multiselect.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/searchFilter.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/tether.min.css" />

<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/webjars/jquery-ui/1.12.1/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/webjars/jquery-ui/1.12.1/jquery-ui.structure.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/webjars/jquery-ui/1.12.1/jquery-ui.theme.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/webjars/bootstrap-glyphicons/bdd2cbfba0/css/bootstrap-glyphicons.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/webjars/bootstrap/4.0.0-alpha.3/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/webjars/bootstrap-datetimepicker/2.3.5/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/webjars/morrisjs/0.5.1/morris.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/css/sb-admin.css" />

<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/webjars/font-awesome/4.6.3/css/font-awesome.min.css" />


<script src="${pageContext.request.contextPath}/webjars/jquery/3.1.1/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/tether.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/4.0.0-alpha.3/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/webjars/jquery-ui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/webjars/bootstrap-datetimepicker/2.3.5/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/webjars/morrisjs/0.5.1/morris.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/webjars/raphaeljs/2.1.2/raphael-min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/js/jquery/jqgrid.4.6.0/jquery.jqGrid.min.js"type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jqgrid.4.6.0/plugins/grid.locale-es.js"type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jqgrid.4.6.0/plugins/grid.addons.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jqgrid.4.6.0/plugins/grid.postext.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jqgrid.4.6.0/plugins/grid.setcolumns.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jqgrid.4.6.0/plugins/jquery.contextmenu.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jqgrid.4.6.0/plugins/jquery.searchFilter.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jqgrid.4.6.0/plugins/jquery.tablednd.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery/jqgrid.4.6.0/plugins/ui.multiselect.js" type="text/javascript"></script>
