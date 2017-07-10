<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="/account/edit" var="postUrl" />

<form:form modelAttribute="account" action="${postUrl}" method="post">
	<form:hidden path="id" />
	<div class="form-group">
		<label for="username">Nom d'utilisateur :</label>
		<form:input id="username" path="username" />
		<form:errors path="username" delimiter=" - " cssClass="alert alert-danger fixed" />
	</div>
	<div class="form-group">
		<label for=password>Mot de passe :</label>
		<form:input type="password" id="password" path="password" />
		<form:errors path="password" delimiter=" - " cssClass="alert alert-danger fixed"/>
	</div>
	<div class="form-group">
		<label for="enabled">Actif :</label>
		<form:checkbox id="enabled" path="enabled"/>
	</div>
	<div class="form-inline">
		<label for="role">Role :</label>
		<form:select selected="true" id="role" path="role" items="${roleList}" itemLabel="name" itemValue="id" class="form-control" />
	</div>
	<div class="form-group">
		<button class="btn btn-info">Valider</button>	
	</div>
</form:form>