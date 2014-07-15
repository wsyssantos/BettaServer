package br.com.ftt.bettaserver.restful.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import br.com.ftt.bettaserver.restful.business.FilmesBusiness;

@Path("/procuraPlaylist")
public class BuscaPlaylistResource
{
    @GET
    @Produces( "audio/x-mpequrl" )
    public String getPlaylist( @QueryParam("idFilme") String idFilme, @QueryParam("idIdioma") String idIdioma )
    {
        String m3uFile = "" ;
        
        try
        {
            FilmesBusiness bus = FilmesBusiness.getInstance( ) ;
            m3uFile = bus.buscaPlaylist( Integer.parseInt( idFilme ), Integer.parseInt( idIdioma ) ) ;
        }
        catch (Exception e) 
        {
            e.printStackTrace( ) ;
        }
        
        
        return m3uFile;
    }
    
}
