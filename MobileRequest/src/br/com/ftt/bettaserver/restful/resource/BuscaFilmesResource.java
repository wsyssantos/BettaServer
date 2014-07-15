package br.com.ftt.bettaserver.restful.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.ftt.bettaserver.restful.business.FilmesBusiness;
import br.com.ftt.bettaserver.restful.form.Filmes;

@Path( "/buscaFilmes" )
public class BuscaFilmesResource
{
    @GET
    @Produces("text/xml")
    public List<Filmes> buscaFilmes( @QueryParam("nome") String nome )
    {
        List<Filmes> filmes = null ;
        FilmesBusiness business = FilmesBusiness.getInstance( ) ;
      
        try
        {
            filmes = business.buscarFilme( nome ) ;
        }
        catch (Exception e) 
        {
            e.printStackTrace( ) ;
        }
        
        return filmes;
    }
}
