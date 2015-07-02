<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Logs Report</title>
<link href="<c:url value="/resources/css/logs.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/jquery.dataTables.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/jqui.css" />"
	rel="stylesheet">
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-2.1.4.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery.dataTables.js" />"></script>
<script type="text/javascript">
	$(document).ready( function () {
		var tableOptions =
	    {
	      //"dom" : '<"H"lfr>t<"F"ip>',
	      "scrollY" : 200,
	      "scrollCollapse" : true,
	      "paging" : true,
	      "rowHeight" : 'auto',
	      "pageLength" : 20,
	      "jQueryUI" : true
	    };
		try
	    {
	      table = $('#logger1').dataTable(tableOptions);
	    }
		catch(e)
		{
			alert(e);
		}
});
	</script>
</head>
<body>
	<h2>Spring MVC and List Example</h2>

	<c:if test="${not empty allLogList}">

		<table border="1" id="logger1">
			<thead>
			<tr>
				<th>Event Date</th>
				<th>Severity Level</th>
				<th>Event Producer</th>
				<th>Message</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="allLog" items="${allLogList}">
				<tr>
					<td><c:out value="${allLog.date}" /></td>
					<td><c:out value="${allLog.priority}" /></td>
					<td><c:out value="${allLog.javaClassName}" /></td>
					<td><c:out value="${allLog.description}" /></td>
				</tr>
			</c:forEach>
			</tbody>
			<tfoot>
		<tr>
			<th>Date</th>
			<th>Severity</th>
			<th>class Name</th>
			<th>description</th>
		</tr>
	</tfoot>
		</table>

	</c:if>
</body>
</html>