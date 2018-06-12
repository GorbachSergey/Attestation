<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<form class="logoutForm" action="LogoutServlet" method="post">
		<span class="user">${user}</span><button title="Вийти" class="logout-btn">
			<img class="logout-img" src="images/logout.png" />
		</button>
	</form>
</body>
</html>