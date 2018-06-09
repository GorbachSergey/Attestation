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
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<title>ЧНТУ Атестація</title>
</head>
<body>
	<div id="header"></div>
	<div class=content>
		<form action="AddMarkServlet" method="POST">
			<table class="table  table-condensed table-bordered">
				<thead>
					<tr>
						<th>Прізвище</th>
						<th>Ім'я</th>
						<th>По батькові</th>
						<th>Оцінка</th>
						<th>Н/б</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="elem" items="${list}">
						<tr>
							<td>${elem.key.lastName}</td>
							<td>${elem.key.firstName}</td>
							<td>${elem.key.middleName}</td>

							<td class="elem"><input type="text"
								name="mark${elem.key.id}" value="${elem.value.mark}" /></td>
							<td class="elem"><input type="text"
								name="pass${elem.key.id}" value="${elem.value.countOfPass}" /></td>
							<td id="save"><button value="${elem.key.id}" type="submit"
									name="studId" class="but">
									<img id="save-icon" alt="" src="images/save.png">
								</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
	<div id="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
	<script type="text/javascript">
	$(document).ready(function(){

		 var bt = document.getElementsByClassName("but");
		  for (var i = 0; i < bt.length; i++) {
		    bt[i].onclick = alert(this.value);
		  }

		});
	</script>
</body>
</html>