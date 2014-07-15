package br.com.ftt.videosplitter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.ftt.videosplitter.util.StringConstants;
import br.com.ftt.videosplitter.videohandler.VideoFile;
import br.com.ftt.videosplitter.videohandler.VideoQuality;

public class VideoCoder
{
    private String                   videoName ;
    private String                   videoPath ;
    private HashMap<Integer, String> playlists ;
    private String                   destinationDir ;
    private String                   pathVideoName;
    private int[ ]                   versions;
    private long                     duracao;
    
    
    public VideoCoder( String videoPath, int[] versions, String destinationDir, String pathVideoName )
    {
        this.videoPath   = videoPath ;
        this.versions   = versions ;
        this.destinationDir = destinationDir ;
        this.playlists   = new HashMap<Integer, String>( ) ;
        
        VideoFile videoFile;
        try
        {
            videoFile = new VideoFile( videoPath );
            this.videoName = videoFile.getVideoName( );
            this.pathVideoName = pathVideoName;
            this.duracao = videoFile.getDuration( ) ;
           
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }
    
    public HashMap<Integer, String> encodeVideos( )
    {
        String destinationDirBase = StringConstants.pathSeparator + pathVideoName + StringConstants.pathSeparator + videoName + StringConstants.pathSeparator;
        String dirDestination = destinationDir ;
        try
        {
            VideoQuality videoQuality = null ;
            
            for( int i : versions )
            {
                switch( i )
                {
                    case 1 : videoQuality = VideoQuality.ULTRA_LOW ; break;
                    case 2 : videoQuality = VideoQuality.EXTRA_LOW ; break;
                    case 3 : videoQuality = VideoQuality.LOW ; break;
                    case 4 : videoQuality = VideoQuality.LOW_MEDIUM ; break;
                    case 5 : videoQuality = VideoQuality.HIGH_MEDIUM ; break;
                    case 6 : videoQuality = VideoQuality.HIGH ; break;
                    case 7 : videoQuality = VideoQuality.EXTRA_HIGH ; break;
                    case 8 : videoQuality = VideoQuality.ULTRA_HIGH ; break;
                    default : return null;
                }
                String bottomPath = destinationDirBase + videoQuality.toString( ) + StringConstants.pathSeparator ;
                destinationDir = dirDestination + destinationDirBase + videoQuality.toString( ) + StringConstants.pathSeparator ;
                VideoSplitter splitter = new VideoSplitter( videoQuality, videoName ) ;
                //String playlist = splitter.segmentMediaFile( videoPath, destinationDir, bottomPath, "mp4" ) ;
                String playlist = splitter.splitVideoFile( videoPath, destinationDir.replaceAll( " ", "_" ), bottomPath ) ;
                playlists.put( videoQuality.getValue( ), playlist ) ;
            }
        }
        catch( Exception e )
        {
            e.printStackTrace( ) ;
            return null ;
        }
        return playlists;
    }
    
    public void createPlaylistFile( String destinationDir, Map<Integer, String> files ) throws IOException
    {
        String    playlistFileName = destinationDir + videoName + ".m3u8" ;
        FileWriter fStream = new FileWriter( playlistFileName ) ;
        BufferedWriter out = new BufferedWriter( fStream ) ;
        out.write( "#EXTM3U" + StringConstants.lineSeparator ) ;
        
        for (Map.Entry<Integer, String> entry : files.entrySet()) {
            int    key   = entry.getKey( ) ;
            String value = entry.getValue( ) ;
            out.write( "#EXT-X-STREAM-INF:PROGRAM-ID=1, BANDWIDTH=" + key + StringConstants.lineSeparator ) ;
            out.write( value + StringConstants.lineSeparator ) ;
        }
        out.close( ) ;
    }

    public long getDuracao( )
    {
        return duracao;
    }
}
