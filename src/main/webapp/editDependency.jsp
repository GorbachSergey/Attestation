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
		<h3>Залежність між викладачем та предметом</h3>
		<form action="ExecuteOperationServlet" method="POST">
			<input type="hidden" name="tableName" value="${tableName}" />
			<table class="table table-condensed table-bordered">
				<thead>
					<tr>
						<th>Викладач</th>
						<th>Предмет</th>
						<th>Операції</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="teach" items="${lst}">
						<c:forEach var="sub" items="${teach.subjects}">
							<tr>
								<td><input type="hidden" name="teacher${teach.id}"
									value="${sub.id}" />${teach}</td>
								<td>${sub.str}</td>
								<td id="save"><button title="Видалити" value="${teach.id}"
										type="submit" name="delete">
										<img id="save-icon" alt="" src="images/delete.png">
									</button></td>
							</tr>
						</c:forEach>
					</c:forEach>
					<tr>
						<td><select size="1" name="teacher">
								<c:forEach var="teach" items="${lst}">
									<option value="${teach.id}">${teach}</option>
								</c:forEach>
						</select></td>
						<td><select size="1" name="subject">
								<c:forEach var="sub" items="${lst1}">
									<option value="${sub.id}">${sub.str}</option>
								</c:forEach>
						</select></td>
						<td id="save"><button class="but" title="Додати"
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
</body>
</html>