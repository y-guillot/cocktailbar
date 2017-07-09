<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../header.jsp" />
</head>
<body>
	<div class="container">
		<h2>Liste des roles</h2>
		<jsp:include page="list.jsp"/>
	</div>
	<hr/>
	<div class="container">
		<h2>Ajouter ou modifier un rôle</h2>
		<jsp:include page="edit.jsp"/>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>