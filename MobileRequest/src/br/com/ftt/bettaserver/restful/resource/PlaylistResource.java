package br.com.ftt.bettaserver.restful.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.ftt.bettaserver.restful.business.FilmesBusiness;

@Path("/procuraPlaylistPadrao")
public class PlaylistResource
{
    @GET
    @Produces( "audio/x-mpequrl" )
    public String getPlaylist( @QueryParam("idFilme") String idFilme )
    {
        String m3uFile = "" ;
        
        try
        {
            FilmesBusiness bus = FilmesBusiness.getInstance( ) ;
            m3uFile = bus.buscaPlaylistPadrao( Integer.parseInt( idFilme ) ) ;
        }
        catch (Exception e) 
        {
            e.printStackTrace( ) ;
        }
        
        return m3uFile;
    }
}
