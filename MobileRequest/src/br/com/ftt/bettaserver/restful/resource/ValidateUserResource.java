package br.com.ftt.bettaserver.restful.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.ftt.bettaserver.resfull.sessionmap.SessionMap;
import br.com.ftt.bettaserver.restful.form.Response;
import br.com.ftt.bettaserver.restful.form.Usuario;

@Path("/validateUser")
public class ValidateUserResource
{
    @GET
    @Produces("text/xml")
    public Response validateUser( @QueryParam("sessao") Long sessao, @QueryParam("k") int k )
    {
        Response resp = new Response( ) ;
        Usuario user = SessionMap.getUsuarioSessao( sessao ) ;
        
        if( user != null )
        {
            int serverK = user.getChave( ).getK( ) ;
            boolean valid = ( serverK == k ) ;
            resp.setValor( String.valueOf( valid ) ) ;
        }
        else
        {
            resp.setValor( "false" ) ;
        }
        return resp;
    }
}
