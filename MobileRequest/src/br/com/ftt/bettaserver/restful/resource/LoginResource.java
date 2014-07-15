package br.com.ftt.bettaserver.restful.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.ftt.bettaserver.resfull.sessionmap.SessionMap;
import br.com.ftt.bettaserver.restful.business.UsuarioBusiness;
import br.com.ftt.bettaserver.restful.form.Response;
import br.com.ftt.bettaserver.restful.form.Usuario;

@Path( "/login" )
public class LoginResource
{
    @POST
    @Consumes("text/xml")
    @Produces("text/xml")
    public Response login( Usuario usuario  )
    {
        Response response = new Response( ) ;
        UsuarioBusiness business = UsuarioBusiness.getInstance( ) ;
        
        try
        {
            int idUsuario = business.login( usuario ) ;
            
            response.setValor( String.valueOf( ( idUsuario != -1 ) ) ) ;
            
            if( idUsuario != -1 )
            {
                Long session = usuario.getSession( ) ;
                Usuario user = SessionMap.getUsuarioSessao( session ) ;
                user.setId( idUsuario ) ;
                user.setLogin( usuario.getLogin( ) ) ;
                user.setSenha( usuario.getSenha( ) ) ;
                
                user = business.fillUserInfo( user ) ;
            }
        }
        catch( Exception e )
        {
            e.printStackTrace( ) ;
        }
        return response; 
    }
}
