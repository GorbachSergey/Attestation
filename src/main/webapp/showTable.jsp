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
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<title>ЧНТУ Атестація</title>
</head>
<body>
	<div id="header"></div>
	<div class=content>
		<form action="SelectTableServlet" method="POST">
			<c:forEach var="elem" items="${list}">
				<div>
					<button class="button" style="vertical-align: middle" type="submit"
						value="${elem.tableName}|${elem.id}" name="elemId">
						<span>${elem}</span>
					</button>
				</div>
			</c:forEach>
		</form>
		<c:if test="${user.lastName eq 'admin'}">
		<form action="SelectTableForEditServlet" method="POST">
		<input type="hidden" name="tableName" value="${list[0].tableName}"/>
		<input type="hidden" name="id" value="${list[0].id}"/>
			<div>
				<button class="buttonEdit" style="vertical-align: middle"
					name="edit" >
					<span>Редагувати таблицю</span>
				</button>
			</div>
			</form>
		</c:if>
	</div>
	<div id="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>