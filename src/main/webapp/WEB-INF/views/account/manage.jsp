<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../header.jsp" />
</head>
<body>
	<div class="container">
		<h2>Liste des comptes utilisateur :</h2>
		<jsp:include page="list.jsp" />	
	</div>
	<hr />
	<div class="container">
		<h2>Ajouter ou modifier un compte utilisateur :</h2>
		<jsp:include page="edit.jsp" />
	</div>
	<jsp:include page="../footer.jsp" />	
</body>
</html>