<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../header.jsp" />
</head>
<body>
	<div class="container">
		<h2>Recette du cocktail ${cocktail.name}</h2>
		<h3>Liste des ingredients</h3>
		<table id="ingredientTable" class="table table-striped">
			<thead>
				<tr>
					<th>Produit</th>
					<th>Quantité</th>
					<th/>
				</tr>
			</thead>
			<tbody>
				<c:url value="/images" var="imgUrl" />
				<c:forEach items="${ingredientList}" var="ingredient">
					<tr>
						<td>${ingredient.product.name}</td>
						<td>${ingredient.quantity}</td>
						<td>
							<a href="<c:url value="/ingredient/delete/${ingredient.product.id}" />">
								<img src="${imgUrl}/delete.png">
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="3">
						<form action="<c:url value="/ingredient/add" />" method="POST" class="form-inline"> <!-- c:url se base sur le conctexte root de la servlet -->
							<div class="form-group">
								<label for="product">Produit :</label>
								<select id="product" name="productId" class="form-control">
									<c:forEach items="${productList}" var="product">
										<option value="${product.id}">${product.name}</option>
									</c:forEach>
								</select>							
							</div>
							<div class="form-group">
								<label for="quantity">Quantité :</label>
								<input type="number" min="1" id="quantity" required="required" name="quantity" class="form-control"/>
							</div>
							<button class="btn btn-info">Ajouter</button>
						</form>
					</td>
				</tr>
			</tfoot>
		</table>
		<div>
			<c:url value="/ingredient/save" var="saveUrl"/>
			<button class="btn btn-primary" onclick="window.document.location='${saveUrl}'">Valider</button>
		</div>
		<jsp:include page="../footer.jsp" />
	</div>
</body>
</html>