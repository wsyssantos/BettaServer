package br.com.ftt.bettaserver.restful.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.ftt.bettaserver.resfull.sessionmap.SessionMap;
import br.com.ftt.bettaserver.restful.form.Response;

@Path("/iniciaSessao")
public class IniciaSessaoUsuarioResource
{
    @GET
    @Produces("text/xml")
    public Response iniciaSessao( @QueryParam("r1") int r1 )
    {
        Response resp = new Response( ) ;
        Long sessionId = SessionMap.iniciaSessao( r1 ) ;
        resp.setValor( String.valueOf( sessionId ) ) ;
        return resp;
    }
}
