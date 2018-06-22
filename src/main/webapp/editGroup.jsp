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
<title>Редагувати</title>
</head>
<body>
	<div id="header">
		<jsp:include page="header.jsp"></jsp:include>
	</div>

	<div class=content>
		<h3>Група</h3>
		<form action="ExecuteOperationServlet" method="POST">
			<input type="hidden" name="tableName" value="${tableName}" />
			<table class="table table-condensed table-bordered">
				<thead>
					<tr>
						<th>Назва</th>
						<th>Курс</th>
						<th>Спеціальність</th>
						<th colspan="2">Операції</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="elem" items="${lst}">
						<tr>
							<td class="elem3"><input type="text" class="input"
								name="nameGroup${elem.id}" value="${elem.name}" /></td>
							<td class="elem"><input type="hidden" class="input" value ="${elem.course}"
							name="course" />${elem.course}</td>
							<td class="elem1"><select size="1" id="ins"
								name="nameSpec${elem.id}">
									<c:forEach var="spec" items="${lst1}">
										<option value="${spec.id}">${spec}</option>
									</c:forEach>
							</select></td>
							<td id="save"><button title="Оновити" value="${elem.id}"
									type="submit" name="edit" class="but">
									<img id="save-icon" alt="" src="images/update.png">
								</button></td>
							<td id="save"><button title="Видалити" value="${elem.id}"
									type="submit" name="delete">
									<img id="save-icon" alt="" src="images/delete.png">
								</button></td>
						</tr>
					</c:forEach>
					<tr>
						<td class="add3"><input type="text" class="input"
							name="nameGroup" /></td>
						<td class="elem"><input type="hidden" class="input" value ="${courseId}"
							name="courseGroup" />${courseId}</td>
						<td><input type="hidden" value="${specId}"
							name="nameSpec" />${name}</td>
						<td colspan="2" id="save"><button class="but" title="Додати"
								type="submit" name="add">
								<img id="save-icon" alt="" src="images/add.png">
							</button></td>
					</tr>
				</tbody>
			</table>
			<input type="hidden" value="${id}" name="id" />
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
					}
					var a = document.getElementsByName("nameGroup" + this.value);
					a[0].setAttribute('required', 'true');
					
				}
			}

			var text1 = '${lst[0].specialty}';
			$("select option").filter(function() {
				return $(this).text() == text1;
			}).attr('selected', true);
		});
	</script>
</body>
</html>