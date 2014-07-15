package br.com.ftt.bettaserver.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import br.com.ftt.bettaserver.business.FilmeBusiness;
import br.com.ftt.bettaserver.form.Filme;

public class VisualizarFilmeAction extends ActionBetta
{
    private Filme   filme;
    private List<Filme>  listaFilmes ;
    private String movieName;
    
    @Action( value = "consulta", results = { @Result( location = "consultaFilmes.jsp", name = "ok" ) } )
    public String buscarFilmes( )
    {
        
        return "ok";
    }
    
    @Action( value = "buscaFilme", results = { @Result( location = "consultaFilmes.jsp", name = "ok" ) } )
    public String pesquisar( )
    {
        FilmeBusiness business = FilmeBusiness.getInstance( ) ;
        listaFilmes = business.pesquisaFilmes( movieName ) ;
        return "ok";
    }

    @Action( value = "excluirFilme", results = { @Result( location = "consultaFilmes.jsp", name = "ok" ) } )
    public String excluirFilme( )
    {
        FilmeBusiness business = FilmeBusiness.getInstance( ) ;
        
        if( business.excluirFilme( filme ) )
        {
            errorMessage = "Filme excluido com sucesso" ;
        }
        else
        {
            errorMessage = "Não foi possível excluir o filme." ;
        }
        
        carregarFilmes( );
        return "ok";
    }
    
    private void carregarFilmes( )
    {
        FilmeBusiness business = FilmeBusiness.getInstance( ) ;
        listaFilmes = business.carregaTodosFilmes( ) ;
        
    }
    
    public List<Filme> getListaFilmes( )
    {
        return listaFilmes;
    }

    public void setListaFilmes( List<Filme> listaFilmes )
    {
        this.listaFilmes = listaFilmes;
    }

    public Filme getFilme( )
    {
        return filme;
    }

    public void setFilme( Filme filme )
    {
        this.filme = filme;
    }

    public String getMovieName( )
    {
        return movieName;
    }

    public void setMovieName( String movieName )
    {
        this.movieName = movieName;
    }
}
