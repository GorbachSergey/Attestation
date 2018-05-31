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
<title>Інститут</title>
</head>
<body>
	<div id="header"></div>

	<!-- 	<div class="content"> -->
	<!-- 		<div class="content-white"> -->

	<form action="SelectTableServlet" method="POST">

		<c:forEach var="elem" items="${list}">

			<button type="submit" value="${elem.tableName}|${elem.id}" name="elemId">${elem}</button>

		</c:forEach>

	</form>

	<!-- 		</div> -->
	<!-- 	</div> -->
</body>
</html>