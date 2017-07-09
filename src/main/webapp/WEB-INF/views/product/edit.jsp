<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../header.jsp" />
</head>
<body>
	<div class="container">
	
		<c:choose>
			<c:when test="${ not empty product}">
				<h2>Modifier un produit existant</h2>
			</c:when>
			<c:otherwise>
				<h2>Créer un nouveeau produit</h2>
			</c:otherwise>
		</c:choose>
		
		<form action="<c:url value="/product/add" />" method="POST">
			<c:if test="${not empty product}">
				<input type="hidden" name="id" value="${product.id}">
			</c:if>
			<div class="form-group">
				<label for="name">Nom :</label>
				<input id="name" name="name" class="form-control"
					value="${not empty product ? product.name : ''}">
			</div>
			<div class="form-group">
				<label for="stock">Stock :</label>
				<input id="stock" name="stock" type="number" min="0" class="form-control"
					value="${not empty product ? product.stock : ''}" >
			</div>
			<button class="btn btn-primary">Valider</button>
		</form>
		<p></p>
		<c:if test="${not empty message}">
			<div class="alert alert-dismissible fade ${error ? 'alert-danger' : 'alert-success'} role='alert'">
				 <button type="button" class="close" data-dismiss="alert" aria-label="Close">
				 	<span aria-hidden="true">&times;</span>
				 </button>
				${message}
			</div>
		</c:if>
		<script type="text/javascript">
			$('.fade').addClass('in');
		</script>
		<jsp:include page="../footer.jsp" />
	</div>
</body>
</html>