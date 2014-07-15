package br.com.ftt.bettaserver.restful.resource;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.ftt.bettaserver.resfull.sessionmap.SessionMap;
import br.com.ftt.bettaserver.restful.business.FilmesBusiness;
import br.com.ftt.bettaserver.restful.business.UsuarioBusiness;
import br.com.ftt.bettaserver.restful.form.Filmes;
import br.com.ftt.bettaserver.restful.form.Usuario;

@Path( "/buscaFavoritos" )
public class BuscaFavoritoResource
{
    @GET
    @Produces( "text/xml" )
    public List<Filmes> buscaFavoritos( @QueryParam( "sessao" ) Long sessao )
    {
        Usuario user = SessionMap.getUsuarioSessao( sessao );
        List<Filmes> filmes = new LinkedList<Filmes>( );
        if ( user != null )
        {
            UsuarioBusiness business = UsuarioBusiness.getInstance( );
            int userId = business.login( user );
            FilmesBusiness filmesBusiness = FilmesBusiness.getInstance( );
            filmes = filmesBusiness.buscaFavoritos( userId ) ;
        }
        return filmes;
    }
}
