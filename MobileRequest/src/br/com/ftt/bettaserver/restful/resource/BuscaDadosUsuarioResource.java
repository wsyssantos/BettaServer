package br.com.ftt.bettaserver.restful.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.ftt.bettaserver.resfull.sessionmap.SessionMap;
import br.com.ftt.bettaserver.restful.form.Usuario;

@Path("/buscaDadosUser")
public class BuscaDadosUsuarioResource
{

    @GET
    @Produces( "text/xml" )
    public Usuario buscaDadosUsuario(  @QueryParam("sessao") Long sessionId )
    {
        Usuario user = SessionMap.getUsuarioSessao( sessionId );
        return user;
    }
}
