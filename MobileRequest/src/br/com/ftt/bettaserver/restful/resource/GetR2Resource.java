package br.com.ftt.bettaserver.restful.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.ftt.bettaserver.resfull.sessionmap.SessionMap;
import br.com.ftt.bettaserver.restful.form.Response;
import br.com.ftt.bettaserver.restful.form.Usuario;

@Path("/getr2")
public class GetR2Resource
{
    @GET
    @Produces("text/xml")
    public Response getR2( @QueryParam("sessao") Long sessao )
    {
        Response resp = new Response( ) ;
        Usuario user = SessionMap.getUsuarioSessao( sessao ) ;
        
        if( user != null )
        {
            int r2 = user.getChave( ).getR2( ) ;
            resp.setValor( String.valueOf( r2 ) ) ;
        }
        else
        {
            resp.setValor( "-1" ) ;
        }
        
        return resp;
    }
}
