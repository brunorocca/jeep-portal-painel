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
			
			
			
			<c:forEach var="ccr" items="${concessionarias}">
				</br>${ccr.codigo} - ${ccr.nome} - ${ccr.nomeFantasia} 
			</c:forEach>
		</table>

	</body>
</html>