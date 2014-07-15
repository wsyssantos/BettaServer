package br.com.ftt.videosplitter;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.com.ftt.videosplitter.command.CommandProcessor;
import br.com.ftt.videosplitter.util.DatabaseUtil;
import br.com.ftt.videosplitter.util.StringConstants;
import br.com.ftt.videosplitter.videohandler.VideoQuality;

public class VideoSplitter
{
    private String       videoName ;
    private VideoQuality videoQuality ;
    private CommandProcessor commandProcessor;
    
    public VideoSplitter( VideoQuality videoQuality, String videoName )
    {
        this.videoQuality = videoQuality ;
        this.videoName    = videoName ;
        this.commandProcessor = new CommandProcessor( ) ;
    }
    
    public VideoSplitter( )
    {
        this.videoQuality = VideoQuality.ULTRA_LOW ;
    }
    
    public String splitVideoFile( String sourceFile, String destinationDir, String playlist ) throws Exception
    {
        String playlistName = "";
        String segmentTime = DatabaseUtil.searchServerPropertySplitMinutes( ) ; 
        int segmentSize = Integer.parseInt( segmentTime ) ;
        
        File      videoDir    = new File( destinationDir ) ;
        
        if( !videoDir.exists( ) )
        {
            videoDir.mkdirs( ) ;
        }
        
        String    playlistFileName = destinationDir + videoName + "_" + videoQuality.toString( ) + ".m3u8" ;
        playlistName    = StringConstants.encode(playlist + videoName + "_" + videoQuality.toString( ) + ".m3u8") ;
        
        if( !( new File( sourceFile ).exists( ) ) )
        {
            throw new Exception( "Arquivo não existe!" ) ;
        }
        
        Encoder encoder = new Encoder( ) ;
        
        MultimediaInfo info = encoder.getInfo( new File( sourceFile ) ) ;
        
        
       // int videoQual = ( StringConstants.osName.equals("linux") ) ? videoQuality.getValue( ) : ( videoQuality.getValue( ) / 1024 ) ;
        //boolean converted = commandProcessor.formatVideoToMp4( sourceFile, destinationFile, videoQual ) ;
        
        FileWriter fStream = new FileWriter( playlistFileName ) ;
        BufferedWriter out = new BufferedWriter( fStream ) ;
        out.write( "#EXTM3U" + StringConstants.lineSeparator + "#EXT-X-TARGETDURATION:" + segmentTime + StringConstants.lineSeparator+ "#EXT-X-MEDIA-SEQUENCE:0" + StringConstants.lineSeparator ) ;
        out.close( ) ;
        
        splitVideo( sourceFile, destinationDir, ( info.getDuration( ) / 1000 ), segmentSize, playlistFileName ) ;
        
        String segmentList = "#EXT-X-ENDLIST" + StringConstants.lineSeparator ;
        
        fStream = new FileWriter( playlistFileName, true ) ;
        out = new BufferedWriter( fStream ) ;
        out.write( segmentList ) ;
        out.close( );
        
        //commandProcessor.processCommands( destinationFile ) ;
            
        
        return playlistName.replace( '\\', '/' );
    }
    
    private void splitVideo( String sourceVideo, String videoDir, long duration, int timeSlice, String playListFile ) throws IOException
    {
        long timeInit = 0;
        long timeEnd = timeSlice;
        int counter = 1;
        
        while( timeEnd < duration )
        {
            String video = videoName + "_part_" + counter++ + ".mp4" ;
            String videoPath = videoDir + video ;
            videoPath = videoPath.replaceAll( " ", "_" ) ;
            
            if( ( timeInit + timeSlice ) <= duration )
                timeEnd = timeInit + timeSlice;
            else
                timeEnd = duration;
            
           // commandProcessor.splitVideo( sourceVideo, videoPath, timeInit, timeSlice ) ;
            commandProcessor.splitVideo( sourceVideo, videoPath, timeInit, timeSlice, videoQuality.getValue( ) ) ;
            
            File fileName = new File( videoPath ) ;
            long lastSegmentLength = timeEnd - timeInit ;
            FileWriter fStream = new FileWriter( playListFile, true ) ;
            BufferedWriter out = new BufferedWriter( fStream ) ;
            out.write( "#EXTINF:" + lastSegmentLength + ", no desc" + StringConstants.lineSeparator + StringConstants.encode( fileName.getName( ) ) + StringConstants.lineSeparator ) ;
            out.close( ) ;
            
            timeInit = timeEnd;
        }
    }
}
   
