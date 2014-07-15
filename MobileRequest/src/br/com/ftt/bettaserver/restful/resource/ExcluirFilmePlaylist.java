package br.com.ftt.bettaserver.restful.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import br.com.ftt.bettaserver.resfull.sessionmap.SessionMap;
import br.com.ftt.bettaserver.restful.business.UsuarioBusiness;
import br.com.ftt.bettaserver.restful.form.Response;
import br.com.ftt.bettaserver.restful.form.Usuario;

@Path("/excluirPlaylist")
public class ExcluirFilmePlaylist
{
    @GET
    @Produces("text/xml")
    public Response excluirFilme( @QueryParam("filmeId") int filmeId, @QueryParam("sessao") long sessao )
    {
        Response response = new Response( ) ;
        Usuario user = SessionMap.getUsuarioSessao( sessao ) ;
        
        UsuarioBusiness business = UsuarioBusiness.getInstance( ) ;
        
        try
        {
            boolean result = business.excluiFilmePlaylist( filmeId, user.getId( ) ) ;
            
            response.setValor( String.valueOf( result ) ) ;
        }
        catch( Exception e )
        {
            e.printStackTrace( ) ;
        }
        return response;
    }
}
