<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<title>Betta Server - Configuração</title>
		<script type="text/javascript">

			function salvar( )
			{
				if( validateForm( ) )
				{
					//atribuirIdiomas( ) ;
					initLoading( ) ;
					salvaFilme( ) ;
					var form = $( 'form[name=frmCadastroFilmes]' ) ;
					$( form ).attr( 'action', 'cadastrarFilme' ) ;
					( form ).submit( ) ;
					
				}
				else
				{
					alert( 'Todos os campos são obrigatórios' ) ;
				} 
			}
			
			function atualizarImagem( )
			{
				var urlImagem = $( '#urlImagem' ).val( ); 
				alert( urlImagem );
				if( !isNullorEmpty( urlImagem ) )
				{
					$( '#capaFilmeImg' ).attr( 'src', urlImagem ) ;
				}
			}

			function validateForm( )
			{
				if( isNullorEmpty( $('#filmeName').val( ) ) )
				{
					return false ;
				}
				
				if( isNullorEmpty( $('#anoFilme').val( ) ) )
				{
					return false ;
				}
				
				if( isNullorEmpty( $('#urlImagem').val( ) ) )
				{
					return false ;
				}
				
				if( isNullorEmpty( $('#urlFilme').val( ) ) )
				{
					return false ;
				}
				
				if( isNullorEmpty( $('#filmeActors').val( ) ) )
				{
					return false ;
				}
				if( isNullorEmpty( $('#filmeDirectors').val( ) ) )
				{
					return false ;
				}
				if( isNullorEmpty( $('#filmeSinopse').val( ) ) )
				{
					return false ;
				}
				if( $( 'select[id=filmeCategories] :selected' ).size() <= 0 )
				{
					return false ;
				}
				return true ;
			}

			function inserirOpcao( )
			{
				var selSize = $('.inputSmallText:eq(0)  option').size() ;
				var selectCount = $('.inputSmallText').size();
				var atualSize = ( selectCount + 1 ) ;
				
				if( atualSize <= selSize ) 
				{
					$( '#languages' ).clone( ).appendTo( '#agroupLanguages' );
				} 
				else
				{
					alert( 'Não é possível inserir mais opções de idiomas, o máximo é ' + selSize ) ;
				}
			}

			function salvaFilme( )
			{
				
				var listaCategorias = new Array( );

				var index = 0;

				$( 'select[id=filmeCategories] :selected' ).each( function( ) { 
					listaCategorias[index++] = $(this).val( ) ;
				} ) ;

				$("#strCategorias").val(JSON.stringify(listaCategorias));

				var qualidades = new Array();
				index = 0;
				
				$( '.checkQuality:checked' ).each( function( ) {
					var value = $(this).val( );
					
					qualidades[index++] = value;
				} ) ; 
	
				$('#strQualidades').val( JSON.stringify(qualidades) ) ;
				
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
			<form name="frmCadastroFilmes" action="cadastrarFilme" method="post" enctype="multipart/form-data">
				
				<div id="content" >
					
					<div id="content_top"></div>
					<div id="content_main" style="display: table;">
						<!-- <div id="capaFilme"><img src="C:\Documents and Settings\All Users\Documentos\Minhas imagens\Amostras de imagens\Montanhas azuis.jpg" id="capaFilmeImg" /></div> -->	
						<h2>Cadastro de Filmes</h2>
						<p>&nbsp;</p>
	           			<div class="tableContainer">
		           			<table class="formTable" cellpadding="2" cellspacing="2">
		           				<tbody>
			           				<s:textfield  id="filmeName" name="filme.nome" label="Nome" cssClass="inputText" />
			           				<s:textfield id="anoFilme" name="filme.ano" cssClass="inputText" label="Ano"></s:textfield>
			           				<s:file name="filme.urlImagem" id="urlImagem" label="Capa do Filme"> </s:file>
			           				<s:file name="filme.urlFilme" id="urlFilme" label="Filme"> </s:file>
			           				 
			           				 <s:textarea name="filme.atores" id="filmeActors" label="Atores" cssClass="inputText"></s:textarea>
			           				<s:textarea name="filme.diretores" id="filmeDirectors" label="Diretores" cssClass="inputText"></s:textarea>
			           				<s:textarea  name="filme.sinopse" id="filmeSinopse" label="Sinopse" cssClass="inputText"></s:textarea>
			           				<s:select cssClass="inputText" name="list" id="filmeCategories" list="categorias"   listKey="id" listValue="nome" label="Gêneros" multiple="true"></s:select>
			           				
			           				<s:checkboxlist label="Qualidades" cssClass="checkQuality" listKey="id" listValue="nome" name="qual" list="qualidades" ></s:checkboxlist>
			           				<s:checkboxlist name="qual2" cssClass="checkQuality" listKey="id" listValue="nome" list="qualidades2" ></s:checkboxlist>
			           				<s:checkboxlist name="qual3" cssClass="checkQuality" listKey="id" listValue="nome" list="qualidades3" ></s:checkboxlist>
			           				<s:checkboxlist name="qual4" cssClass="checkQuality" listKey="id" listValue="nome" list="qualidades4" ></s:checkboxlist>
			           				
			           				<tr>
			           					<td><input class="formButton" id="btSalvar" type="button" value="Salvar" onclick="salvar( );" /></td>
			           					<td><input class="formButton" id="btCancelar" type="button" value="Cancelar" /></td>
			           				</tr>
			           			</tbody>
		           			</table>
	           			</div>
					</div>
					<div id="content_bottom"></div>
				</div>
			<s:hidden id="errorMessage" name="errorMessage"></s:hidden>
			<s:hidden id="strCategorias" name="filme.strCategorias"></s:hidden>
			<s:hidden id="strQualidades" name="filme.strQualidades"></s:hidden>
			</form>
		</div>
	</body>
</html>