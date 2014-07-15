package br.com.ftt.bettaserver.restful.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.ftt.bettaserver.restful.business.UsuarioBusiness;
import br.com.ftt.bettaserver.restful.form.Response;
import br.com.ftt.bettaserver.restful.form.Usuario;

@Path("/cadastrarUsuario")
public class CadastroUsuarioResource
{
    @POST
    @Consumes("text/xml")
    @Produces("text/xml")
    public Response cadastro( Usuario usuario )
    {
        Response response = new Response( ) ;
        UsuarioBusiness business = UsuarioBusiness.getInstance( ) ;
        
        try
        {
            boolean resp = business.cadastrarUsuario( usuario ) ;
            response.setValor( String.valueOf( resp ) ) ;
        }
        catch( Exception e )
        {
            e.printStackTrace( ) ;
        }
        
        return response;
    }
}
