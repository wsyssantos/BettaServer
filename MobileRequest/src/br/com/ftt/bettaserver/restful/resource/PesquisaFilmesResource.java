package br.com.ftt.bettaserver.restful.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.ftt.bettaserver.resfull.sessionmap.SessionMap;
import br.com.ftt.bettaserver.restful.business.FilmesBusiness;
import br.com.ftt.bettaserver.restful.form.Filmes;
import br.com.ftt.bettaserver.restful.form.Usuario;

@Path("/pesquisaFilme")
public class PesquisaFilmesResource
{
    @GET
    @Produces("text/xml")
    public Filmes buscaFilmes( @QueryParam("idFilme") int idFilme, @QueryParam("sessao") long sessao )
    {
        Filmes filme = null ;
        FilmesBusiness business = FilmesBusiness.getInstance( ) ;
      
        try
        {
            Usuario user = SessionMap.getUsuarioSessao( sessao ) ;
            filme = business.buscarFilme( idFilme, user.getId( ) ) ;
        }
        catch (Exception e) 
        {
            e.printStackTrace( ) ;
        }
        
        return filme;
    }
}
