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
	<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
<title>ЧНТУ Атестація</title>
</head>
<body>
	<div id="header">
	<jsp:include page="header.jsp"></jsp:include>
	</div>
	<div class=content>
		<form action="AddMarkServlet" method="POST">
			<table class="table table-condensed table-bordered">
				<thead>
					<tr>
						<th>Прізвище</th>
						<th>Ім'я</th>
						<th>По батькові</th>
						<th>Оцінка</th>
						<th>Н/б</th>
						<th colspan="2">Операції</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="elem" items="${list}">
						<tr>
							<td class="td-stud">${elem.key.lastName}</td>
							<td class="td-stud">${elem.key.firstName}</td>
							<td class="td-stud">${elem.key.middleName}</td>

							<td class="elem"><input type="text" class="input"
								name="mark${elem.key.id}" value="${elem.value.mark}" /></td>
							<td class="elem"><input class="input" type="text"
								name="pass${elem.key.id}" value="${elem.value.countOfPass}" /></td>
							<td id="save"><button title="Зберегти"
									value="${elem.key.id}" type="submit" name="studId" class="but">
									<img id="save-icon" alt="" src="images/save.png">
								</button></td>
							<td id="save"><button title="Очистити"
									value="${elem.value.id}" type="submit" name="clear">
									<img id="save-icon" alt="" src="images/clear.png">
								</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
	<div class="back">
		<form action="BackServlet">
			<button type="submit" name="back" value="Institut"
				class="btn btn-primary">На головну</button>
		</form>
	</div>
	<div id="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			var q = document.getElementsByClassName("input");
			var bt = document.getElementsByClassName("but");
			for (var i = 0; i < bt.length; i++) {
				bt[i].onclick = function() {
					for (var i = 0; i < q.length; i++) {
						q[i].removeAttribute('required');
						q[i].removeAttribute('pattern');
					}
					var a = document.getElementsByName("mark" + this.value);
					a[0].setAttribute('required', 'true');
					a[0].setAttribute('pattern', '^(100)|[0-9]{1,2}$');
					var b = document.getElementsByName("pass" + this.value);
					b[0].setAttribute('required', 'true');
					b[0].setAttribute('pattern', '^(0)|[1-9]{1,2}$');
				}
			}
		});
	</script>
</body>
</html>