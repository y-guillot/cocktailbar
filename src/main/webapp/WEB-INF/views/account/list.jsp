<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table id="accountTable" class="table table-striped">
    <thead>
        <tr>
            <th>Id</th>
            <th>Nom d'utilisateur</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
    	<c:url value="/images" var="imgPrefixUrl" />
        <c:forEach items="${accountList}" var="account">
            <tr>
                <td>${account.id}</td>
                <td>${account.username}</td>
                <td>${account.role.name}</td>
                <td>
                	<a href="<c:url value="/account/delete?id=${account.id}" />">
                		<img src="${imgPrefixUrl}/delete.png">
                	</a>
                	<a href="<c:url value="/account/edit?id=${account.id}" />">
                		<img src="${imgPrefixUrl}/edit.png">
                	</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<script type="text/javascript">
	$(document).ready(() => $('#accountTable').dataTable());
</script>