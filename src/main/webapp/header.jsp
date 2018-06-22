<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<form class="logoutForm" action="LogoutServlet" method="post">
		<span class="user">${user}</span>
		<button title="Вийти" class="logout-btn">
			<img class="logout-img" src="images/logout.png" />
		</button>
	</form>
	
	<c:if test="${user.lastName eq 'admin'}">
		<form class="operation" action="OperationServlet"
			method="POST">
			<div class="operation_container">
				<div>
					<button title="Управління викладачами" class="operation_button" value="1" name="operation">
						<img class="logout-img" src="images/add_teacher.png" />
					</button>
				</div>
				<div>
					<button title="Управління кафедрами" class="operation_button" value="2" name="operation">
						<img class="logout-img" src="images/kafedra.png" />
					</button>
				</div>
				<div>
					<button title="Установити залежність між предметом та викладачем" value="3" class="operation_button" name="operation">
						<img class="logout-img" src="images/dependency.png" />
					</button>
				</div>
			</div>
		</form>
	</c:if>
	
</body>
</html>