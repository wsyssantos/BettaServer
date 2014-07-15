package br.com.ftt.bettaserver.restful.business;

import java.util.List;

import br.com.ftt.bettaserver.restful.dao.FilmeDAO;
import br.com.ftt.bettaserver.restful.database.util.StringConstants;
import br.com.ftt.bettaserver.restful.form.Filmes;
import br.com.ftt.bettaserver.restful.form.Playlist;

public class FilmesBusiness
{
    private static FilmesBusiness filmesBusiness;
    private FilmeDAO dao = new FilmeDAO( ) ;
    
    private FilmesBusiness( )
    {
        
    }
    
    public List<Filmes> buscaFavoritos( int idUsuario )
    {
        return dao.buscaFavoritos( idUsuario ) ;
    }
    
    public List<Filmes> buscarFilme( String nome )
    {
        return dao.buscarFilme( nome ) ;
    }
    
    public Filmes buscarFilme( int filmeId, int userId )
    {
        return dao.buscarFilme( filmeId, userId ) ;
    }
    
    public String buscaPlaylistPadrao( int idFilme )
    {
        StringBuilder sb = new StringBuilder( ) ;
        int idioma = dao.buscaIdiomaPadrao( idFilme );
        
        List<Playlist> playlists = dao.buscarFilmePlaylist( idFilme, idioma ) ;
        
        if( playlists != null )
        {
            appendPlaylistData( sb, playlists );
        }
        
        return sb.toString( ) ;
    }
    
    public String buscaPlaylist( int idFilme, int idIdioma )
    {
        StringBuilder sb = new StringBuilder( ) ;

        List<Playlist> playlists = dao.buscarFilmePlaylist( idFilme, idIdioma ) ;
        
        if( playlists != null )
        {
            appendPlaylistData( sb, playlists );
        }
        
        
        return sb.toString( ) ;
    }
    
    private void appendPlaylistData( StringBuilder sb, List<Playlist> playlists )
    {
        sb.append( "#EXTM3U" + StringConstants.lineSeparator ) ;
        for( Playlist playlist : playlists )
        {
            sb.append( "#EXT-X-STREAM-INF:PROGRAM-ID=1, BANDWIDTH=" + playlist.getBitrate( )  + StringConstants.lineSeparator ) ;
            sb.append( playlist.toString( ) + StringConstants.lineSeparator ) ;
        }
    }
    
    
    public List<Filmes> buscaFilmesPaginadosPorCategoria( int categoriaId )
    {
        return dao.buscaFilmesPaginadosPorCategoria( categoriaId ) ;
    }
    
    public static FilmesBusiness getInstance( )
    {
        if( filmesBusiness == null )
        {
            filmesBusiness = new FilmesBusiness( ) ;
        }
        return filmesBusiness ;
    }
}
