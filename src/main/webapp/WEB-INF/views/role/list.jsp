<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<table id="roleTable" class="table table-striped">
    <thead>
        <tr>
            <th>Id</th>
            <th>Nom</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
    	<c:url value="/images" var="imgPrefixUrl" />
        <c:forEach items="${roleList}" var="role">
            <tr>
                <td>${role.id}</td>
                <td>${role.name}</td>
                <td>
                	<a href="<c:url value="/role/delete?id=${role.id}" />">
                		<img src="${imgPrefixUrl}/delete.png">
                	</a>
                	<a href="<c:url value="/role/edit?id=${role.id}" />">
                		<img src="${imgPrefixUrl}/edit.png">
                	</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<script type="text/javascript">
	$(document).ready(() => $('#roleTable').dataTable());
</script>
