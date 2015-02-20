<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/libs/foundation/global.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Painel de Controle - Concession&aacute;rias</title>
		<style>
			.concessionariaTable {
				margin:0px;padding:0px;
				width:100%;
				border:1px solid #ffffff;
				
				-moz-border-radius-bottomleft:0px;
				-webkit-border-bottom-left-radius:0px;
				border-bottom-left-radius:0px;
				
				-moz-border-radius-bottomright:0px;
				-webkit-border-bottom-right-radius:0px;
				border-bottom-right-radius:0px;
				
				-moz-border-radius-topright:0px;
				-webkit-border-top-right-radius:0px;
				border-top-right-radius:0px;
				
				-moz-border-radius-topleft:0px;
				-webkit-border-top-left-radius:0px;
				border-top-left-radius:0px;
			}.concessionariaTable table{
			    border-collapse: collapse;
			        border-spacing: 0;
				width:100%;
				height:100%;
				margin:0px;padding:0px;
			}.concessionariaTable tr:last-child td:last-child {
				-moz-border-radius-bottomright:0px;
				-webkit-border-bottom-right-radius:0px;
				border-bottom-right-radius:0px;
			}
			.concessionariaTable table tr:first-child td:first-child {
				-moz-border-radius-topleft:0px;
				-webkit-border-top-left-radius:0px;
				border-top-left-radius:0px;
			}
			.concessionariaTable table tr:first-child td:last-child {
				-moz-border-radius-topright:0px;
				-webkit-border-top-right-radius:0px;
				border-top-right-radius:0px;
			}.concessionariaTable tr:last-child td:first-child{
				-moz-border-radius-bottomleft:0px;
				-webkit-border-bottom-left-radius:0px;
				border-bottom-left-radius:0px;
			}.concessionariaTable tr:hover td{
				
			}
			.concessionariaTable tr:nth-child(odd){ background-color:#aad4ff; }
			.concessionariaTable tr:nth-child(even)    { background-color:#ffffff; }.concessionariaTable td{
				vertical-align:middle;
				
				
				border:1px solid #ffffff;
				border-width:0px 1px 1px 0px;
				text-align:left;
				padding:7px;
				font-size:10px;
				font-family:Arial;
				font-weight:normal;
				color:#000000;
			}.concessionariaTable tr:last-child td{
				border-width:0px 1px 0px 0px;
			}.concessionariaTable tr td:last-child{
				border-width:0px 0px 1px 0px;
			}.concessionariaTable tr:last-child td:last-child{
				border-width:0px 0px 0px 0px;
			}
			.concessionariaTable tr:first-child td{
					background:-o-linear-gradient(bottom, #007fff 5%, #007fff 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #007fff), color-stop(1, #007fff) );
				background:-moz-linear-gradient( center top, #007fff 5%, #007fff 100% );
				filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#007fff", endColorstr="#007fff");	background: -o-linear-gradient(top,#007fff,007fff);
			
				background-color:#007fff;
				border:0px solid #ffffff;
				text-align:center;
				border-width:0px 0px 1px 1px;
				font-size:12px;
				font-family:Arial;
				font-weight:bold;
				color:#ffffff;
			}
			.concessionariaTable tr:first-child:hover td{
				background:-o-linear-gradient(bottom, #007fff 5%, #007fff 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #007fff), color-stop(1, #007fff) );
				background:-moz-linear-gradient( center top, #007fff 5%, #007fff 100% );
				filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#007fff", endColorstr="#007fff");	background: -o-linear-gradient(top,#007fff,007fff);
			
				background-color:#007fff;
			}
			.concessionariaTable tr:first-child td:first-child{
				border-width:0px 0px 1px 0px;
			}
			.concessionariaTable tr:first-child td:last-child{
				border-width:0px 0px 1px 1px;
			}
		</style>
		</head>
	<body>

		<c:if test="${not empty msg}">
			<div class="message">${msg}</div>
		</c:if>

		<h1>Concession&aacute;rias</h1>
	
		<form action="/painel/concessionarias" method="POST" enctype="multipart/form-data">
			Importar arquivo CSV: <br /> 
			<input type="file" name="file" value="Selecionar...">
			<br /> 
			<input type="submit" value="Importar"> 
			<br />
		</form>
		
		<form action="/painel/concessionarias" method="DELETE" id="formDeleteConcessionaria" class="formDeleteConcessionaria">
			<input type="hidden" name="idConcessionaria" id="idConcessionaria" value="">
		</form>
	
		<div class="concessionariaTable">
			<table style="width:100%">
				<tr>
					<td>C&oacute;digo</td>
					<td>Nome</td>
					<td>Endere&ccedil;o</td>
					<td>Email Vend.</td>
					<td>Email Consultor</td>
					<td>Latitude</td>
					<td>Longitude</td>
					<td>A&ccedil;&atilde;o</td>
				</tr>
				<c:forEach var="ccr" items="${concessionarias}">
				<tr>
					<td>${ccr.codigo}</td>
					<td>${ccr.nomeFantasia}</td>
					<td>${ccr.endereco}</td>
					<td>${ccr.email}</td>
					<td>${ccr.emailConsultor}</td>
					<td>${ccr.latitude}</td>
					<td>${ccr.longitude}</td>
					<td><a href="#">Editar</a> <a href="#" onclick="excluir(${ccr.codigo})">Excluir</a></td>
				</tr>
				</c:forEach>
			</table>
		</div>
		<script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
		<script type="text/javascript">
			function excluir(idConcessionaria) {
				console.log("idConcessionaria: " +idConcessionaria);
	    		$.ajax({
	    		    url: '/painel/concessionarias?idConcessionaria='+idConcessionaria,
	    		    type: 'DELETE',
	    		    success: function(result) {
	    		    	location.reload();
	    		    }
	    		});
	    	}
		</script>
	</body>
</html>