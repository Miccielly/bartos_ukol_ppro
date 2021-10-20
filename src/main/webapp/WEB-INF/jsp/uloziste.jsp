<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inzeraty</title>
</head>
<body>
	<h1>Inzeraty</h1>
	<table border="1">
		<tr>
			<th>Popis</th>
			<th>Kategorie</th>
			<th>Cena</th>
			<th>Datum vystavení</th>
		</tr>
		<c:forEach var="p" items="${polozky}">
			<tr>
				<td>${p.text }</td>
				<td>${p.kategorie }</td>
				<td>${p.cena }</td>
				<td>${p.datum }</td>
			</tr>>
		</c:forEach>

	</table>

	[<a href="sprava.do">Správa inzerátů</a>]<br/>

</body>
</html>