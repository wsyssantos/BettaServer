package br.com.ftt.bettaserver.restful.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.ftt.bettaserver.resfull.sessionmap.SessionMap;
import br.com.ftt.bettaserver.restful.business.UsuarioBusiness;
import br.com.ftt.bettaserver.restful.form.Response;
import br.com.ftt.bettaserver.restful.form.Usuario;

@Path("/gravarFavorito")
public class CadastrarFavoritoResource
{
    @GET
    @Produces( "text/xml" )
    public Response cadastrarFavorito( @QueryParam("sessao") Long sessao, @QueryParam("filmeId") int filmeId )
    {
        Response resp = new Response( ) ;
        Usuario user = SessionMap.getUsuarioSessao( sessao ) ;
        
        if( user != null )
        {
            UsuarioBusiness business = UsuarioBusiness.getInstance( );
            int userId = business.login( user ) ;
            boolean success = business.cadastrarFavorito( userId, filmeId ) ;
            resp.setValor( String.valueOf( success ) ) ;
        }
        else
        {
            resp.setValor( "false" ) ;
        }
        
        
        return resp ;
    }
}
