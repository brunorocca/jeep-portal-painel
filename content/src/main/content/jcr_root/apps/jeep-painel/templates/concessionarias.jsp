<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/libs/foundation/global.jsp"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Painel de Controle - Concessionárias</title>
	</head>
     
	<body>

		<h1>Concessionárias</h1>
		<a href="/painel/concessionarias" style="font-size: 30px; text-decoration: none">Importar Concessionárias</a>
	
		<table style="width:100%">
			<tr>
				<th>Código</th>
				<th>Nome</th>
				<th>Endereço</th>
				<th>Email Vend.</th>
				<th>Email Consultor</th>
				<th>Latitude</th>
				<th>Longitude</th>
			</tr>
			<c:forEach var="ccr" items="${concessionarias}">
			<tr>
				<td>${ccr.codigo}</td>
				<td>${ccr.nome}</td>
				<td>${ccr.endereco}</td>
				<td>${ccr.email}</td>
				<td>${ccr.emailConsultor}</td>
				<td>${ccr.latitude}</td>
				<td>${ccr.longitude}</td>
			</tr>
			</c:forEach>
		</table>

	</body>
</html>