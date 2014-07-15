package br.com.ftt.betta.mobile.parser.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

import br.com.ftt.betta.mobile.parser.exception.M3UParserException;
import br.com.ftt.betta.mobile.parser.vo.Media;
import br.com.ftt.betta.mobile.parser.vo.Playlist;
import br.com.ftt.betta.mobile.parser.vo.PlaylistGroup;

public class FileParser
{
    private static String PARSER_LOG = "file_parser";

    public static PlaylistGroup downloadGroup( String path ) throws M3UParserException
    {
        PlaylistGroup group = null;
        BufferedReader in = null;
        try
        {
            URL url = new URL( path );

            in = new BufferedReader( new InputStreamReader( url.openStream( ) ) );
            String str = in.readLine( ) ;
            
            if( !str.startsWith( "#EXTM3U" ) ) 
            {
                throw new M3UParserException( "Playlist Inválido" ) ;
            }
            
            group = new PlaylistGroup( ) ;
            
            while ( ( str = in.readLine( ) ) != null )
            {
                if( str.startsWith( "#EXT-X-STREAM-INF" ) )
                {
                    String[] split = str.split( "BANDWIDTH=" ) ;
                    long bandwidth = Long.parseLong( split[1] ) ;
                    String playlistUrl = in.readLine( );
                    
                    Playlist playlist = new Playlist( playlistUrl ) ;
                    group.add( bandwidth, playlist ) ;
                }
            }
        }
        catch ( MalformedURLException e )
        {
            //Log.v( PARSER_LOG, e.getMessage( ) ) ;
            throw new M3UParserException( e ) ;
        }
        catch ( IOException e )
        {
           // Log.v( PARSER_LOG, e.getMessage( ) ) ;
            throw new M3UParserException( e ) ;
        }
        finally
        {
            if( in != null )
            {
                try
                {
                    in.close( ) ;
                }
                catch ( IOException e )
                {
                    e.printStackTrace();
                }
            }
        }
        return group;
    }
    
    public static void loadPlaylist( String path, Playlist playlist ) throws M3UParserException
    {
        List<Media> medias = null ;
        int targetDuration = 0;
        int initId = 0 ;
        BufferedReader in = null;
        try
        {
            URL url = new URL( path ) ;
            String relativeURL = getRelativePath( path ) ;
            in = new BufferedReader( new InputStreamReader( url.openStream( ) ) ) ;
            String str = in.readLine( ) ;
            
            if( !str.startsWith( "#EXTM3U" ) ) 
            {
                throw new M3UParserException( "Playlist Inválido" ) ;
            }
            
            medias = new LinkedList<Media>( ) ;
            
            while ( ( str = in.readLine( ) ) != null )
            {
                if( str.startsWith( "#EXT-X-TARGETDURATION" ) )
                {
                    String line = str.substring( 22 ) ;
                    targetDuration = Integer.parseInt( line ) ;
                }
                else if( str.startsWith( "#EXT-X-MEDIA-SEQUENCE" ) )
                {
                    String line = str.substring( 22 ) ;
                    initId = Integer.parseInt( line ) ;
                }
                else if ( str.startsWith( "#EXTINF" ) )
                {
                    int indexComma = str.indexOf( "," ) ;
                    String line =   ( indexComma != -1 ) ? ( str.substring( 8, indexComma ) ) : ( str.substring( 8 ) );
                    int duration = Integer.parseInt( line ) ;
                    Media media = new Media( ) ;
                    String newUrl = in.readLine( ) ;
                    
                    targetDuration += duration ;
                    media.setDuration( duration ) ;
                    media.setId( initId++ ) ;
                    String mediaUrl = relativeURL + newUrl ;
                    mediaUrl = URLDecoder.decode( mediaUrl, "UTF-8" );
                    media.setUrl( mediaUrl ) ;
                    medias.add( media ) ;
                }
            }
            playlist.setTotalDuration( targetDuration ) ;
            playlist.setMedias( medias ); 
        }
        catch ( MalformedURLException e )
        {
           // Log.v( PARSER_LOG, e.getMessage( ) ) ;
            throw new M3UParserException( e ) ;
        }
        catch ( IOException e )
        {
           // Log.v( PARSER_LOG, e.getMessage( ) ) ;
            throw new M3UParserException( e ) ;
        }
        finally
        {
            if( in != null )
            {
                try
                {
                    in.close( ) ;
                }
                catch ( IOException e )
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static String getRelativePath( String path )
    {
        String relativePath = path.substring( 0, path.lastIndexOf( "/" ) ) ;
        
        return relativePath + "/";
    }
}
