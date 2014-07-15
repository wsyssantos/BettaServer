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
				alert( 'O arquivo de filme selecionado não está em um formato válido!'  ) ;
				/*if( validateForm( ) )
				{
					//atribuirIdiomas( ) ;
					initLoading( ) ;
					salvaFilme( ) ;
					alert( 'Filme cadastrado com sucesso.' ) ;
					var form = $( 'form[name=frmCadastroFilmes]' ) ;
					$( form ).attr( 'action', 'cadastro' ) ;
					( form ).submit( ) ;
					
				}
				else
				{
					alert( 'Todos os campos são obrigatórios' ) ;
				}*/
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
				if( isNullorEmpty( $('#urlImagem').val( ) ) )
				{
					return false ;
				}
				var err = false;
				
				$('.urlFilme').each( function( ) {
					if( isNullorEmpty( $(this).val( ) ) )
					{
						err = true;
						return;
					}
				} ) ;

				if( err )
				{
					return false;
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
				var filme = {} ;
				filme.nome = $( '#filmeName' ).val( ) ;
				filme.ano = $( '#anoFilme' ).val( ) ;
				filme.urlImagemFileName = $('#urlImagem').val( ) ;
				filme.atores = $('#filmeActors').val( );
				filme.diretores = $('#filmeDirectors').val( );
				filme.sinopse =  $('#filmeSinopse').val( ) ;

				var listaCategorias = new Array( );

				var index = 0;

				$( 'select[id=filmeCategories] :selected' ).each( function( ) { 
					listaCategorias[index++] = $(this).val( ) ;
				} ) ;

				filme.listaCategorias = listaCategorias;

				var idiomas = new Array( ) ;

				index = 0;
				
				$('.tipoFilme').each( function( ) {
					var idioma = {} ;
					
					var select = $(this).find( '.filmeIdioma' );
					idioma.id = $( select ).val( ) ;
					var input = $(this).find('.urlFilme');
					idioma.urlFilme = $( input ).val( ) ;

					idiomas[index++] = idioma;
				} );

				filme.idiomas = idiomas;
				
				var qualidades = new Array();
				index = 0;
				
				
				$( '.checkQuality:checked' ).each( function( ) {
					var value = $(this).val( );
					
					qualidades[index++] = value;
				} ) ; 
	
				filme.qualidades = qualidades;
				
				$.ajax
				({
					url: "cadastrarFilme",
					type: "POST",
					data: "parametros="+JSON.stringify(filme),
					dataType: "json",
					cache: false,
					sucess: function(data)
					{
						stopLoading( ) ;
					}, 
					error: function (xhr, ajaxOptions, thrownError)
					{
						stopLoading( ) ;
					}
				} ) ;
			}

			function atribuirIdiomas( )
			{
				var index = 0;
				$('.languageOptions').each( function(){
					var select = $(this).children( '.filmeIdioma' );
					$( select ).attr( 'name', 'filme.idiomas[' + index + '].id' ) ;
					var input = $(this).children('.urlFilme');
					$( input ).attr( 'name', 'filme.idiomas[' + index + '].urlFilme' ) ;
					index++;
				} );
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
			           				<s:textfield  id="filmeName" label="Nome" cssClass="inputText" />
			           				<s:textfield id="anoFilme" cssClass="inputText" label="Ano"></s:textfield>
			           				<s:file label="Capa do Filme"> </s:file>
			           				<!-- <tr>
			           					<td class="tdLabel"><label for="filmeImage" class="label">Capa do Filme:</label></td>
			           					<td>
			           						<input type="text"  value="" id="urlImagem" class="inputText" style="float: left;">
			           						<div class="refreshImage" title="Atualizar Capa" onclick="atualizarImagem();" style="float: left;"></div>
		           						</td>
			           				</tr> -->
			           				<tr>
			           					<td colspan="2">
				           					<fieldset class="field">
				           						<legend>Idiomas</legend>
				           						<div id="agroupLanguages">
				           							<div id="languages" class="languageOptions tipoFilme">
					           							<select  class="inputSmallText filmeIdioma" >
						           							<s:iterator value="listaIdiomas" status="status">
						           								<option value='<s:property value="id" />'><s:property value="nome" /></option>
						           							</s:iterator>
						           						</select>
						           						<table>
						           							<s:file label="Arquivo"> </s:file>
						           							<%-- <s:textfield  id="urlFilme" label="Caminho" cssClass="inputText urlFilme" /> --%>
						           						</table>
					           						</div>
				           						</div>
				           						<div>
				           							<input class="formButtonBig" id="btNovo" type="button" value="Nova Opção de Idioma" onclick="inserirOpcao( );" />
				           						</div>
			           						</fieldset>
			           					</td>
			           				</tr>
			           				<%-- <s:textfield name="filme.duracao"  id="filmeDuration" label="Duração" cssClass="inputText" />
			           				 --%>
			           				 
			           				 <s:textarea   id="filmeActors" label="Atores" cssClass="inputText"></s:textarea>
			           				<s:textarea  id="filmeDirectors" label="Diretores" cssClass="inputText"></s:textarea>
			           				<s:textarea   id="filmeSinopse" label="Sinopse" cssClass="inputText"></s:textarea>
			           				<s:select  cssClass="inputText" name="list" id="filmeCategories" list="categorias"   listKey="id" listValue="nome" label="Gêneros" multiple="true"></s:select>
			           				<%-- <s:select cssClass="inputText" name="filme.numQualidades" id="numQualidades" label="Versões" list="#{'1':'01','2':'02','3':'03','4':'04','5':'05','6':'06','7':'07','8':'08'}" ></s:select>
			           				 --%>
			           				<s:checkboxlist label="Qualidades" cssClass="checkQuality" listKey="id" listValue="nome" name="qual" list="qualidades" ></s:checkboxlist>
			           				<s:checkboxlist name="qual2" cssClass="checkQuality" listKey="id" listValue="nome" list="qualidades2" ></s:checkboxlist>
			           				<s:checkboxlist name="qual3" cssClass="checkQuality" listKey="id" listValue="nome" list="qualidades3" ></s:checkboxlist>
			           				<s:checkboxlist name="qual4" cssClass="checkQuality" listKey="id" listValue="nome"list="qualidades4" ></s:checkboxlist>
			           				
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
			</form>
		</div>
	</body>
</html>