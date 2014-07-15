package br.com.ftt.bettaserver.restful.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.ftt.bettaserver.restful.business.UsuarioBusiness;
import br.com.ftt.bettaserver.restful.form.Response;

@Path("/checkUserName")
public class VerificaNomeUsuarioResource
{
    @GET
    @Produces( "text/xml" )
    public Response getName( @QueryParam("nome") String nome )
    {
        Response response = new Response( ) ;
        
        UsuarioBusiness business = UsuarioBusiness.getInstance( ) ;
        
        boolean resp = business.existUserName( nome );
        
        response.setValor( String.valueOf( resp ) ) ;
        
        return response;
    }
}
