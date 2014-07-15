package br.com.ftt.bettaserver.restful.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.ftt.bettaserver.restful.business.CategoriaBusiness;
import br.com.ftt.bettaserver.restful.business.FilmesBusiness;
import br.com.ftt.bettaserver.restful.form.CategoriaFilmes;
import br.com.ftt.bettaserver.restful.form.Filmes;
import br.com.ftt.bettaserver.restful.form.XmlCategroria;

@Path( "/buscaFilmesCategoria" )
public class BuscaFilmesCategoriaResource
{
   @GET
   @Produces( "text/xml" )
   public CategoriaFilmes buscaFilmesPorCategoria( @QueryParam("categoriaId") String catId )
   {
       CategoriaFilmes categoriaFilmes = null ;
       FilmesBusiness business = FilmesBusiness.getInstance( ) ;
       
       List<Filmes> filmes = business.buscaFilmesPaginadosPorCategoria( Integer.parseInt( catId ) ) ;
       
       if( filmes != null )
       {
           XmlCategroria categoria = CategoriaBusiness.getInstance( ).buscaCategoria( Integer.parseInt( catId ) ) ;
           categoriaFilmes = new CategoriaFilmes( ) ;
           categoriaFilmes.setCategoriaNome( categoria.getNome( ) ) ;
           categoriaFilmes.setCategoriaUrl( categoria.getUrl( ) ) ;
           categoriaFilmes.setFilme( filmes ) ;
           categoriaFilmes.setNumFilmes( filmes.size( ) ) ;
       }
       
       return categoriaFilmes ;
   }
    
}
