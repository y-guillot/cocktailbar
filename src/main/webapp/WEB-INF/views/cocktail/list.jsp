<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../header.jsp" />
</head>
<body>
	<div class="container">
		<h2>Liste des cocktails :</h2>
		<table id="cocktailTable" class="table table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nom</th>
					<th>Prix</th>
					<th>Avec alcool</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:url value="/images" var="imgUrl" />
				<c:forEach items="${cocktailList}" var="cocktail">
					<tr id="line-${cocktail.id}">
						<td>${cocktail.id}</td>
						<td>${cocktail.name}</td>
						<td>${cocktail.price}</td>
						<td>${cocktail.withAlcohol}</td>
						<td>
							<a href="<c:url value="/cocktail/delete?id=${cocktail.id}" />">
								<img src="${imgUrl}/delete.png">
							</a>
							<a href="<c:url value="/cocktail/edit?id=${cocktail.id}" />">
								<img src="${imgUrl}/edit.png">
							</a>
							<a href="<c:url value="/ingredient/${cocktail.id}" />">
								<span class="glyphicon glyphicon-cog"></span>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<jsp:include page="../footer.jsp" />
	<script type="text/javascript">
		$(document).ready(() => {
			$('#cocktailTable').dataTable();
			$('table#cocktailTable tbody tr').each(
				(index, line) => {
					$(line).click((event) => {
						let selectedRow = event.currentTarget;
						let id = selectedRow.id.split('-')[1];
						// Ouvrir la fenêtre dialog avec l'id récupéré.
						let dialog = $('<div title="Recette du cocktail"></div>');
						dialog.load('<c:url value="/ingredient/view/" />' + id);
						dialog.appendTo(document.body);
						dialog.dialog({
							modal: true,
							position: { my: "top", of: "#line-" + id },
							open: (event, ui) => { $(selectedRow).addClass("selectedRow"); },
							close: (event, ui) => { $(selectedRow).removeClass("selectedRow"); }
						});
					});
				});
		});
	</script>
</body>
</html>