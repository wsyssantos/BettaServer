package br.com.ftt.bettaserver.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import br.com.ftt.bettaserver.business.CategoriaBusiness;
import br.com.ftt.bettaserver.form.Categoria;

public class CadastrarCategoriasAction extends ActionBetta
{
    private String          errorMessage;
    private Categoria       categoria;
    private List<Categoria> listaCategorias;

    @Action( value = "categorias", results = { @Result( location = "cadastroCategorias.jsp", name = "ok" ) } )
    public String init( )
    {
        categoria = new Categoria( );
        carregarListaCategorias( ) ;
        return "ok";
    }
    
    @Action( value = "cadastrarCategorias", results = { @Result( location = "cadastroCategorias.jsp", name="ok" ) } )
    public String incluir( )
    {
        CategoriaBusiness business = CategoriaBusiness.getInstance( ) ;
        boolean existeCategoria = business.existeCategoria( categoria.getNome( ) ) ;
                
        if( existeCategoria )
        {
            errorMessage = "Já existe gênero com esse nome cadastrado." ;
        }
        else
        {
            boolean success = business.inserirCategoria( categoria ) ;
            
            if( !success )
            {
                errorMessage = "Não foi possível incluir o gênero." ;
            }
            else 
            {
                errorMessage = "Gênero cadastrado com sucesso!";
            }
        }
        categoria.setNome( "" ) ;
        carregarListaCategorias( ) ;
        return "ok";
    }
    
    @Action( value = "excluirCategorias", results = { @Result( location = "cadastroCategorias.jsp", name = "ok" ) } )
    public String excluir( )
    {
        CategoriaBusiness business = CategoriaBusiness.getInstance( ) ;
        boolean success = business.excluirCategoria( categoria.getId( ) ) ;
        
        if( !success )
        {
            errorMessage = "Não foi possível excluir a categoria." ;
        }
        else 
        {
            errorMessage = "";
        }
        categoria.setNome( "" ) ;
        carregarListaCategorias( );
        return "ok" ;
    }
    
    private void carregarListaCategorias( )
    {
        CategoriaBusiness business = CategoriaBusiness.getInstance( ) ;
        listaCategorias = business.buscaTodasCategorias( ) ;
    }

    public Categoria getCategoria( )
    {
        return categoria;
    }

    public void setCategoria( Categoria categoria )
    {
        this.categoria = categoria;
    }

    public List<Categoria> getListaCategorias( )
    {
        return listaCategorias;
    }

    public void setListaCategorias( List<Categoria> listaCategorias )
    {
        this.listaCategorias = listaCategorias;
    }

    public String getErrorMessage( )
    {
        return errorMessage;
    }

    public void setErrorMessage( String errorMessage )
    {
        this.errorMessage = errorMessage;
    }

}
