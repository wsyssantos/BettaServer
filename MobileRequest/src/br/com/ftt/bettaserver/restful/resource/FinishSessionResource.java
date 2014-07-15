package br.com.ftt.bettaserver.restful.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.ftt.bettaserver.resfull.sessionmap.SessionMap;
import br.com.ftt.bettaserver.restful.form.Response;

@Path("/finishSession")
public class FinishSessionResource
{
    @GET
    @Produces("text/xml")
    public Response iniciaSessao( @QueryParam("session") long session )
    {
        Response resp = new Response( ) ;
        SessionMap.finishSession( session ) ;
        resp.setValor( "true" ) ;
        return resp;
    }
}
