<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h2>Spring MVC and List Example</h2>

	<c:if test="${not empty allLogList}">

		<table border="1" >
			<tr>
				<th>Event Date</th>
				<th>Severity Level</th>
				<th>Event Producer</th>
				<th>Message</th>
			</tr>
			<c:forEach var="allLog" items="${allLogList}">
				<tr>
					<td><c:out value="${allLog.date}" /></td>
					<td><c:out value="${allLog.priority}" /></td>
					<td><c:out value="${allLog.javaClassName}" /></td>
					<td><c:out value="${allLog.description}" /></td>
				</tr>
			</c:forEach>
		</table>

	</c:if>
</body>
</html>