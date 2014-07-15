package br.com.ftt.videosplitter.videohandler;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;
import java.io.IOException;

public class VideoFile
{
    private String videoName ;
    private File   videoFile ;
    private MultimediaInfo videoContainer ;
    
    public VideoFile( String path ) throws IOException
    {
        videoFile = new File( path ) ;
        
        if( !videoFile.exists( ) )
        {
            File paths = videoFile.getParentFile( ) ;
            paths.mkdirs( ) ;
            videoFile.createNewFile( ) ;
        }
        
        
        videoName = setVideoName( );
    }
    
    private String setVideoName( )
    {
        String fileName = videoFile.getName( ) ;
        return fileName.substring( 0, fileName.lastIndexOf( "." ) );
    }
    
    public String getVideoName( )
    {
        return videoName ;
    }
    
    public File getVideoFile( ) 
    {
        return videoFile ;
    }
    
    public long getDuration( )
    {
        Encoder encoder = new Encoder( );
        try
        {
            videoContainer = encoder.getInfo( videoFile ) ;
        }
        catch ( InputFormatException e )
        {
            e.printStackTrace();
        }
        catch ( EncoderException e )
        {
            e.printStackTrace();
        }
        
        if ( videoContainer != null)
        {
            return videoContainer.getDuration( );
        }
        return 0 ;        
    }
}
