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
		<form action="ExecuteOperationServlet" method="POST">
		<input type="hidden" name="tableName" value="${tableName}" />
			<table class="table table-condensed table-bordered">
				<thead>
					<tr>
						<th>Прізвище</th>
						<th>Ім'я</th>
						<th>По батькові</th>
						<th>Група</th>
						<th>Оцінка</th>
						<th>Н/б</th>
						<th colspan="2">Операції</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="elem" items="${list}">
						<tr>
							<td class="elem2"><input type="text" class="input"
								name="lastName${elem.key.id}" value="${elem.key.lastName}" /></td>
							<td class="elem2"><input type="text" class="input"
								name="firstName${elem.key.id}" value="${elem.key.firstName}" /></td>
							<td class="elem2"><input type="text" class="input"
								name="middleName${elem.key.id}" value="${elem.key.middleName}" /></td>
							<td class="elem"><input type="hidden" class="input"
								name="group" value="${elem.key.group.id}" />${elem.key.group.name}</td>
							<td class="elem">${elem.value.mark}</td>
							<td width="75px">${elem.value.countOfPass}</td>
							<td id="save"><button title="Оновити" value="${elem.key.id}"
									type="submit" name="edit" class="but">
									<img id="save-icon" alt="" src="images/update.png">
								</button></td>
							<td id="save"><button title="Видалити" value="${elem.key.id}"
									type="submit" name="delete">
									<img id="save-icon" alt="" src="images/delete.png">
								</button></td>
						</tr>
					</c:forEach>
					<tr>
						<td class="add2"><input type="text" class="input"
							name="lastName" /></td>
						<td class="add2"><input type="text" class="input"
							name="firstName"/></td>
						<td class="add2"><input type="text" class="input"
							name="middleName"/></td>
						<td><input type="hidden" value="${groupId}"
							name="groupId" />${nameGroup}</td>
						<td colspan="2" class="elem"></td>
						<td colspan="2" id="save"><button class="but" title="Додати"
								type="submit" name="add">
								<img id="save-icon" alt="" src="images/add.png">
							</button></td>
					</tr>
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
					var a = document.getElementsByName("firstName" + this.value);
					a[0].setAttribute('required', 'true');
					var b = document.getElementsByName("lastName" + this.value);
					b[0].setAttribute('required', 'true');
					var c = document.getElementsByName("middleName" + this.value);
					c[0].setAttribute('required', 'true');
				}
			}
		});
	</script>
</body>
</html>