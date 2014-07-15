<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<title>Betta Server - Configuração</title>
		<script type="text/javascript">
			function cadastroUser( )
			{
				var form   = $( 'form[name=frmCadastro]' ) ;
				initLoading( ) ;
				$( form ).submit( ) ;
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
				<div id="content_main" style="height: 320px;">
					<h2>Cadastro de Usuário</h2>
					<p>&nbsp;</p>
           			<div class="tableContainer">
           				<form name="frmCadastro" action="cadastrarUsuario" method="POST">
			           		<table class="formTable" cellpadding="2" cellspacing="2">
		           				<tbody>
		           					<s:textfield id="user" name="newUser.userName" label="Usuário" cssStyle="width: 110px;" cssClass="inputText" />
				           			<s:textfield id="email" name="newUser.email" label="Email" cssStyle="width: 170px;" cssClass="inputText" />
				           			<s:password id="senha" name="newUser.password" label="Senha" cssStyle="width: 130px;" cssClass="inputText" />
				           			<s:password  id="confirmarSenha" label="Confirmar Senha" cssStyle="width: 130px;" cssClass="inputText" />
		           					<tr>
			           					<td colspan="2"><input class="formButton" id="btSalvar" type="button" value="Salvar" onclick="cadastroUser( );" /></td>
			           				</tr>
		           				</tbody>
		           			</table>
	           			</form>
	           		</div>
				</div>
        		<div id="content_bottom"></div>
			</div>
		</div>
	</body>
</html>