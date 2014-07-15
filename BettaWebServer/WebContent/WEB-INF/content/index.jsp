<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="user" class="java.lang.String" scope="session"></jsp:useBean>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<title>Betta Server - Configuração</title>
		<script type="text/javascript">
			function login( )
			{
				alert( "Usuário cadastrado com sucesso" ) ;
				/* var form   = $( 'form[name=frmAction]' ) ;
				initLoading( ) ;
				$( form ).submit( ) ; */
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
					<h2>Configure aqui o seu servidor Betta Streaming</h2>
					<p>&nbsp;</p>
           			<p>&nbsp;</p>
           			<h3>Facilidade</h3>
           			<p>Aqui você tem controle total de todo o seu conteúdo de vídeo do servidor multimídia Betta Server.</p>
           			<p>&nbsp;</p>
           			<p>&nbsp;</p>
           			<h3>Gerencie seus vídeos</h3>
           			<p>Insira novos vídeos ao seu portfólio, atualize cadastro e pesquise informações de forma simples e fácil.</p>
           			<p>&nbsp;</p>
           			<p>&nbsp;</p>
           			<%
           				if ( user == null || user.trim().isEmpty() )
           				{
           			%>
           				<form name="frmAction" action="login" method="POST">
		           			<table class="formTable" cellpadding="2" cellspacing="2">
				           		<tbody>
				           			<tr>
						           		<s:textfield name="userName" id="filmeName" label="Usuário" cssClass="inputMicroText" />
						           		<s:password name="userPassword" id="senhaUser" label="Senha" cssClass="inputMicroText"></s:password>
					           		</tr>
					           		<tr>
					           			<td colspan="2" align="right"><a href="novoUsuario">Cadastrar Novo</a></td>
					           		</tr>
					           		<tr>
					           			<td colspan="2" align="right">
					           				<input class="formButton" id="btSalvar" type="submit" value="Login" onclick="login( );" />
					           			</td>
					           		</tr>
		         				</tbody>
		         			</table>
	         			</form>
	         		<% }  %>
        		</div>
        		<div id="content_bottom"></div>
			</div>
		</div>
	</body>	
</html>