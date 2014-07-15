package br.com.ftt.bettaserver.restful.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.ftt.bettaserver.resfull.sessionmap.SessionMap;
import br.com.ftt.bettaserver.restful.business.UsuarioBusiness;
import br.com.ftt.bettaserver.restful.form.Response;
import br.com.ftt.bettaserver.restful.form.Usuario;

@Path( "/avaliacaoFilme" )
public class AvaliacaoFilme
{
    @GET
    @Produces( "text/xml" )
    public Response avaliaFilme( @QueryParam("idFilme") int idFilme, @QueryParam("sessao") Long sessao, @QueryParam("avaliacao") int avaliacao )
    {
        Response response = new Response( ); 
        UsuarioBusiness business = UsuarioBusiness.getInstance( ) ;
        
        try
        {
            Usuario user = SessionMap.getUsuarioSessao( sessao );
            boolean retorno = business.avaliaFilme( user.getId( ), idFilme, avaliacao ) ;
            response.setValor( String.valueOf( retorno ) ) ;
        }
        catch( Exception e )
        {
            e.printStackTrace( ) ;
        }
        
        return response;
    }
}
