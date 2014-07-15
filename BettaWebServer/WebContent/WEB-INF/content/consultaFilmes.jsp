<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<title>Betta Server - Configuração</title>
		<script type="text/javascript">
		function excluirFilme( e )
		{
			var nomeFilme = $( e ).attr( 'nomeFilme' ) ;
			var answer = confirm("Deseja realmente excluir o Filme " + nomeFilme + "?") ;
			
			if (answer)
			{
				var form = $( 'form[name=frmConsultaParametros]' ) ;
				var catId = $( e ).attr( 'idFilme' ) ;
				$( '#hdnFilmeId' ).val( catId ) ;
				$( '#hdnFilmeNome' ).val( nomeFilme ) ;
				initLoading( ) ;
				$( form ).attr( 'action', 'excluirFilme' ) ;
				$( form ).submit( ) ;
			}
		}
		</script>
	</head>
	<body>
		<div id="loadingDiv">
			<img id="loadingImage" alt="Carregando" style="width: 50px;" src="images/loading.gif">
		</div>
		<div id="container">
			<s:include value="menu.jsp" ></s:include>
			<div id="leftmenu">
				<div id="leftmenu_top"></div>
				<div id="leftmenu_main"> 
					<img alt="Betta Server" src="images/Betta Server Logo.png">
				</div>
				<div id="leftmenu_bottom"></div>
				<s:include value="footer.jsp" ></s:include>
			</div>
			<div id="content" >
				<div id="content_top"></div>
				<div id="content_main" style="height: 380px;">
					<h2>Consulta de Filmes</h2>
					<p>&nbsp;</p>
           			<p>&nbsp;</p>
           			<div class="tableContainer">
           				<form name="frmBuscaFilme" action="buscaFilme" method="POST">
			           		<table class="formTable" cellpadding="2" cellspacing="2">
		           				<tbody>
		           					<s:textfield id="user" name="movieName" label="Nome do Filme" cssClass="inputText" />
		           					<tr>
			           					<td colspan="2"><input class="formButton" id="btSalvar" type="submit" value="Buscar" onclick="cadastroUser( );" /></td>
			           				</tr>
		           				</tbody>
		           			</table>
	           			</form>
           			</div>
           			<p>&nbsp;</p>
           			<p>&nbsp;</p>
           			<s:if test="listaFilmes != null && !listaFilmes.isEmpty">
	           			<div style="width: 630px; height: 250px; overflow-y: auto; ">
	           				<form name="frmConsultaParametros" method="post">
	           					<table class="tableResults" cellpadding="0" cellspacing="3">
	           						<thead>
	           							<tr>
	           								<td colspan="2">Filme</td>
	           							</tr>
	           						</thead>
	           						<tbody>
	           							<s:iterator value="listaFilmes" status="groupStatus">
	           								<tr  class="<s:if test="#groupStatus.odd == true ">odd</s:if><s:else>even</s:else>">
	           									<td width="90%"><s:property value="nome" /></td>
	           									<td width="5%" title="Excluir Filme" idFilme="<s:property value='id' />" nomeFilme="<s:property value="nome" />" onclick="javascript:excluirFilme( this ) ;">
		           									<div class="icoExcluir"></div>
		           									<div style="display: none;"><input class="formButton" id="btExcluir" type="submit" value="Excluir" /></div>
		           								</td>
	           								</tr>
	           							</s:iterator>
	           						</tbody>
	           					</table>
	           					<s:hidden id="hdnFilmeId" name="filme.id"></s:hidden>
	           					<s:hidden id="hdnFilmeNome" name="filme.nome"></s:hidden>
	           				</form>
	           			</div>
           			</s:if>
           			<s:else>
           				<div class="tableContainer" style="width: 630px; height: 300px; overfow-y:auto;">
	           				Nenhum filme encontrado!
           				</div>
           			</s:else>
				</div>
				<div id="content_bottom"></div>
			</div>
		</div>
		<s:hidden id="errorMessage" name="errorMessage"></s:hidden>
	</body>
</html>