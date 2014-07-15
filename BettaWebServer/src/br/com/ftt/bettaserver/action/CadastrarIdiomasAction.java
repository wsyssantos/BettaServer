package br.com.ftt.bettaserver.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import br.com.ftt.bettaserver.business.IdiomaBusiness;
import br.com.ftt.bettaserver.form.IdiomasFilmes;

public class CadastrarIdiomasAction
{
    private String              errorMessage;
    private IdiomasFilmes       idioma;
    private List<IdiomasFilmes> listaIdiomas;
    
    @Action( value = "idiomas", results = { @Result( location = "cadastroIdiomas.jsp", name = "ok" ) } )
    public String init( )
    {
        idioma = new IdiomasFilmes( );
        carregarListaIdiomas( );
        return "ok";
    }
    
    @Action( value = "cadastrarIdiomas", results = { @Result( location = "cadastroIdiomas.jsp", name="ok" ) } )
    public String incluir( )
    {
        IdiomaBusiness business = IdiomaBusiness.getInstance( ) ;
        boolean existeIdioma = business.existeIdioma( idioma.getNome( ), idioma.getCodigo( )  ) ;
        
        if( existeIdioma )
        {
            errorMessage = "Já existe idioma com esse nome ou código cadastrado." ;
        }
        else
        {
            boolean success = business.cadastrarNovoIdioma( idioma ) ;
            
            if( !success )
            {
                errorMessage = "Não foi possível incluir o idioma." ;
            }
            else 
            {
                errorMessage = "";
            }
        }
        idioma.setNome( "" );
        idioma.setCodigo( "" );
        carregarListaIdiomas( );
        return "ok";
    }
    
    @Action( value = "excluirIdiomas", results = { @Result( location = "cadastroIdiomas.jsp", name = "ok" ) } )
    public String excluir( )
    {
        IdiomaBusiness business = IdiomaBusiness.getInstance( ) ;
        
        boolean success = business.excluirIdioma( idioma.getId( ) ) ;

        if( !success )
        {
            errorMessage = "Não foi possível excluir o idioma." ;
        }
        else 
        {
            errorMessage = "";
        }
        carregarListaIdiomas( );
        return "ok";
    }
    
    private void carregarListaIdiomas( )
    {
        IdiomaBusiness business = IdiomaBusiness.getInstance( ) ;
        listaIdiomas = business.buscaTodosIdiomas( ) ;
    }

    public String getErrorMessage( )
    {
        return errorMessage;
    }

    public void setErrorMessage( String errorMessage )
    {
        this.errorMessage = errorMessage;
    }

    public IdiomasFilmes getIdioma( )
    {
        return idioma;
    }

    public void setIdioma( IdiomasFilmes idioma )
    {
        this.idioma = idioma;
    }

    public List<IdiomasFilmes> getListaIdiomas( )
    {
        return listaIdiomas;
    }

    public void setListaIdiomas( List<IdiomasFilmes> listaIdiomas )
    {
        this.listaIdiomas = listaIdiomas;
    }

}
