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
		<form role="form" id="loginForm" class="form-horizontal"
			action="LoginServlet" method="POST">
			<div class="form-group">
				<input class="form-control" required id="name" type="text"
					placeholder="Логін" name="login" />
			</div>
			<div class="form-group ">
				<input class="form-control" required id="pass" type="password"
					placeholder="Пароль" name="password" />
			</div>
			<div class="form-group ">
				<input class="btn btn-success btn-block" id="login" type="submit"
					value="Вхід" />
			</div>
		</form>
		<p>
			<c:if test="${not empty loginError}">
				<div class="error">
					<c:out value="${loginError}" />
				</div>
			</c:if>
		</p>
	</div>
	<div id="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>