<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<h2>Menu de l'application</h2>
	<ul>
		<c:url value="/" var="prefixUrl" />
		<c:forEach items="${menu}" var="menuItem">
			<li>
				<a href="${prefixUrl}${menuItem.url}">${menuItem.title}</a>
			</li>
		</c:forEach>
	</ul>
</div>