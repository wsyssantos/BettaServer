<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<title>Betta Server - Configuração</title>
		<script type="text/javascript">
			function salvarCategoria( )
			{
				var nomeCategoria = $('#txtNomeCategoria').val( ) ;
				if( nomeCategoria != null && $.trim( nomeCategoria ) != '' )
				{
					var form = $( 'form[name=frmCadastroCategorias]' ) ;
					initLoading( ) ;
					$( form ).submit( ) ;
					//setTimeout( function( ) { $('#loadingDiv').hide(); }, 3000 ) ;
				}
				else
				{
					alert( 'Digite um Gênero.' ) ;
				}
			}
			
			function excluirCategoria( e )
			{
				var answer = confirm("Deseja realmente excluir o Gênero?") ;
				
				if (answer)
				{
					var form = $( 'form[name=frmExcluirCategorias]' ) ;
					var catId = $( e ).attr( 'idCategoria' ) ;
					$( '#hdnCategoriaId' ).val( catId ) ;
					initLoading( ) ;
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
					<h2>Cadastro de Gêneros</h2>
					<p>&nbsp;</p>
           			<p>&nbsp;</p>
           			<div class="tableContainer" style="width: 630px;">
           				<form name="frmCadastroCategorias" action="cadastrarCategorias" method="post">
							<table class="formTable" cellpadding="2" cellspacing="2">
	           					<tbody>
	           						<s:textfield id="txtNomeCategoria" name="categoria.nome" label="Nome" cssClass="inputText" />
	           						<tr>
			           					<td><input class="formButton" id="btSalvar" type="button" onclick="salvarCategoria( );" value="Salvar" /></td>
			           				</tr>
			           			</tbody>
	           				</table>
           				</form>
           				<p>&nbsp;</p>
           				<div style="height: 250px; overflow-y: auto; ">
	           				<s:if test="listaCategorias != null && listaCategorias.size > 0">
	           					<form name="frmExcluirCategorias" action="excluirCategorias" method="post">
			           				<table class="tableResults" cellpadding="0" cellspacing="3">
			           					<thead>
			           						<tr>
			           							<td colspan="2">Gênero</td>
			           						</tr>
			           					</thead>
			           					<tbody>
			           						<s:iterator value="listaCategorias" status="groupStatus">
			           							<tr  class="<s:if test="#groupStatus.odd == true ">odd</s:if><s:else>even</s:else>">
			           								<td width="90%"><s:property value="nome" /></td>
			           								<td width="10%" title="Excluir Categoria" idCategoria="<s:property value='id' />" onclick="javascript:excluirCategoria( this ) ;">
			           									<div class="icoExcluir"></div>
			           									<div style="display: none;"><input class="formButton" id="btExcluir" type="submit" value="Excluir" /></div>
			           								</td>
			           							</tr>
			           						</s:iterator>
			           					</tbody>
			           				</table>
									<s:hidden id="hdnCategoriaId" name="categoria.id"></s:hidden>
		           				</form>
	           				</s:if>
           				</div>
           			</div>
				</div>
				<div id="content_bottom"></div>
			</div>
			<s:hidden id="errorMessage" name="errorMessage"></s:hidden>
		</div>
	</body>
</html>