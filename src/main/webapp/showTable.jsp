<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<title>ЧНТУ Атестація</title>
</head>
<body>
	<div id="header"></div>
	<div class=content>
		<form action="SelectTableServlet" method="POST">
			<div class="buttons">
				<c:forEach var="elem" items="${list}">
					<div>
						<button type="submit" value="${elem.tableName}|${elem.id}"
							name="elemId">
							<img id="folder" alt="" src="images/dark-blue-folder.png">
							<p id="p1">${elem}</p>
						</button>
					</div>
				</c:forEach>
			</div>
		</form>
	</div>
	<div id="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>