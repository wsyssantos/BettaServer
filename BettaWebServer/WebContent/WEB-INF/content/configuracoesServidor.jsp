<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<title>Betta Server - Configuração</title>
		<script type="text/javascript">
			function salvarParametros( )
			{
				
				var parameters = new Array();
				var index = 0;
				
				var valid = true ;
				
				$('.param').each( function( ) {
					var param = {};
					param.id = $(this).attr( 'idparameter' ) ;
					param.valor = $(this).val( ) ;
					
					if( isNullorEmpty( param.valor) )
					{
						valid = false ;
					}
					
					parameters[index++] = param;
				} ); 
				
				
				if( valid )
				{
					initLoading( ) ;
					$.ajax
					({
						url: "salvarParametro",
						type: "POST",
						data: "parametros="+JSON.stringify(parameters),
						dataType: "json",
						cache: false,
						sucess: function(data)
						{
							//alert( 'Foi' ) ;
							stopLoading( ) ;
						}, 
						error: function (xhr, ajaxOptions, thrownError)
						{
							stopLoading( ) ;
							alert( 'Configurações salvas com sucesso!' ) ;
							//alert( 'Ocorreu Um Erro: ' + xhr.status + ' - ' + thrownError) ;
						}
					} ) ;
				}
				else
				{
					alert( "Todos os campos são obrigatórios" ) ;
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
					<h2>Configurações e Parâmetros</h2>
					<p>&nbsp;</p>
           			<p>&nbsp;</p>
           			<div class="tableContainer" style="width: 630px; height: 300px; overfow-y:auto;">
           				<form name="frmCadastroParametros" action="cadastrarCategorias" method="post">
           					<table class="formTable" cellpadding="2" cellspacing="2">
           						<tbody>
           							<s:iterator value="parametros" status="groupStatus">
           								<tr>
           									<td class="tdLabel">
           										<label for="filme_nome" class="label"><s:property value="nome"/>:</label>
           									</td>
           									<td>
           										<input type="text" class="param inputText" idparameter="<s:property value='id' />" value="<s:property value='valor' />" id="filme_nome"/>
           									</td>
           								</tr>
           							</s:iterator>
           							<!-- <tr>
    									<td class="tdLabel">
    										<label for="filme_nome" class="label">Divisão da Mídia:</label>
    									</td>
    									<td>
    										<input type="checkbox" name="qual" value="1" id="qual-1" class="checkQuality">
    										<label for="qual-1" class="checkboxLabel">5</label>
    										<input type="checkbox" name="qual" value="1" id="qual-1" class="checkQuality">
    										<label for="qual-1" class="checkboxLabel">10</label>
    										<input type="checkbox" name="qual" value="1" id="qual-1" class="checkQuality">
    										<label for="qual-1" class="checkboxLabel">15</label>
    										<input type="checkbox" name="qual" value="1" id="qual-1" class="checkQuality">
    										<label for="qual-1" class="checkboxLabel">20</label>
    									</td>
        							</tr> -->
           							<tr>
           								<td><input class="formButton"  id="btSalvar" type="button" onclick="salvarParametros( );" value="Salvar" /></td>
			           				</tr>
           						</tbody>
           					</table>
           					<table>
           						<tbody>
           							
           						</tbody>
           					</table>
           				</form>
           			</div>
				</div>
				<div id="content_bottom"></div>
			</div>
		</div>
		<s:hidden id="errorMessage" name="errorMessage"></s:hidden>
	</body>
</html>