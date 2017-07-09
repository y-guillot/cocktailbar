<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../header.jsp" />
</head>
<body>
	<div class="container">
		<h2>Créer un nouveau cocktail</h2>
		<c:url value="/cocktail/add" var="postUrl"/>
		
		<form:form modelAttribute="cocktail" class="container" action ="${postUrl}" method="POST">
			<form:hidden path="id"/>
			<div class="form-group">
				<label for="name">Nom :</label>
				<form:input id="name" path="name"/>
			</div>
			<div class="form-group">
				<label for="price">Price :</label>
				<form:input id="price" path="price" type="number" step="0.05"/>
			</div>
			<div>			
				<label for="withAlcohol">Avec alcool :</label>
				<form:checkbox id="withAlcohol" path="withAlcohol"/>
			</div>
			<button class="btn btn-info">Valider</button>
		</form:form>
		
		<jsp:include page="../footer.jsp" />
	</div>
</body>
</html>