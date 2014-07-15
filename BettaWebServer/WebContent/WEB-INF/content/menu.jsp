<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="user" class="java.lang.String" scope="session"></jsp:useBean>
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript">
	$( document ).ready( function( ) {
		$( '.menuitem a' ).click( function( ) { 
			var action = $( this ).attr( 'action' ) ;
			var form   = $( 'form[name=frmMenu]' ) ;
			$( form ).attr( 'action', action ) ;
			initLoading( ) ;
			$( form ).submit( ) ;
		} ) ;

		var errorMessage = $( 'input[id=errorMessage]' ).val( ) ;

		if( errorMessage != null && $.trim( errorMessage ) != "" )
		{
			alert( errorMessage ) ;
		}
	} ) ;

	function isNullorEmpty( str )
	{
		return ( str == null || $.trim( str ) == '' ) ;
	}
	
	function initLoading( )
	{
		var screenWidth = $( document ).width( ) ;
		var screenHeight = $( document ).height( ) ;
		$( '#loadingDiv' ).offset({ top: 0, left: 0 });
		$( '#loadingDiv' ).css( 'width', screenWidth ) ;
		$( '#loadingDiv' ).css( 'height', screenHeight ) ;
		$( '#loadingDiv' ).show( ) ;
		var top = ( screenHeight / 2 ) - 25 ;
		var left = ( screenWidth / 2 ) - 25 ;
		$( '#loadingImage' ).offset({ top: top, left: left });
	}

	function stopLoading( )
	{
		$( '#loadingDiv' ).hide( ) ;
	}
</script>
<div id="header">
	<h1>Betta<span class="off">Server</span></h1>
	<h2>Configuração</h2>
</div>
<div id="menu">
	<ul>
		<li class="menuitem"><a action="inicio" href="#">Início</a></li>
		<% 
			if( user != null && !user.trim().isEmpty())
			{
		%>		
			<li class="menuitem"><a action="consulta" href="#">Consulta de Filmes</a></li>
			<li class="menuitem"><a action="cadastro" href="#">Cadastro de Filmes</a></li>
			<li class="menuitem"><a action="categorias" href="#">Cadastro Gêneros</a></li><!-- 
			<li class="menuitem"><a action="idiomas" href="#">Cadastro de Idiomas</a></li> -->
			<li class="menuitem"><a action="configuracoes" href="#">Configurações</a></li>
			<li class="menuitem"><a action="logout" href="#">Logout</a></li>
		<%
			} 
		%>
	</ul>
</div>
<form name="frmMenu" method="post">
	
</form>
