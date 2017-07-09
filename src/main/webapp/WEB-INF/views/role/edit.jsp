<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<c:url value="/role/save" var="saveUrl" />

<form class="container" action="${saveUrl}" method="post">
	<c:if test="${not empty role}">
		<input type="hidden" name="id" value="${role.id}">
	</c:if>
	<div class="form-group">
		<label for="name">Nom : </label>
		<input id="name" name="name" required="required" value="${not empty role ? role.name : '' }">
		<button class="btn btn-info">Valider</button>
	</div>
</form>
		
	