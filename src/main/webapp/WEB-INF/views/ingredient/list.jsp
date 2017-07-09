<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<table id="ingredientTable" class="table">
		<thead>
			<tr>
				<th>Produit</th>
				<th>Quantité</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ingredientList}" var="ingredient">
				<tr>
					<td>${ingredient.product.name}</td>
					<td>${ingredient.quantity}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>