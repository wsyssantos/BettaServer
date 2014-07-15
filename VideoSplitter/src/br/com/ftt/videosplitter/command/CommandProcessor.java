package br.com.ftt.videosplitter.command;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncoderProgressListener;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;
import it.sauronsoftware.jave.VideoAttributes;
import it.sauronsoftware.jave.VideoSize;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import br.com.ftt.videosplitter.ThreadCommands;
import br.com.ftt.videosplitter.util.DateUtil;
import br.com.ftt.videosplitter.util.StringConstants;

public class CommandProcessor
{
    private List<String> commands ;
    private List<String[]> arrayCommands ;
    
    public CommandProcessor( )
    {
        commands = new LinkedList<String>( ) ;
        arrayCommands = new LinkedList<String[]>() ;
    }
    
    public boolean formatVideoToMp4( String originFilePath, String destinationFilePath, int videoBitrate )
    {
        File source = new File( originFilePath ) ;
        File target  = new File( destinationFilePath ) ;

        Encoder encoder = new Encoder();
        
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libfaac");
        audio.setBitRate(new Integer(128000));
        audio.setChannels(new Integer(2));
        audio.setSamplingRate(new Integer(44100));

        VideoAttributes video = new VideoAttributes();
        video.setCodec("mpeg4");
        video.setBitRate(new Integer(videoBitrate));
        video.setFrameRate(new Integer(30));
        video.setSize( new VideoSize( 480, 360 ) ) ;
        
        try
        {
            MultimediaInfo info = encoder.getInfo( source ) ;
            video.setSize( info.getVideo( ).getSize( ) ) ;
        }
        catch ( InputFormatException e1 )
        {
            e1.printStackTrace();
        }
        catch ( EncoderException e1 )
        {
            e1.printStackTrace();
        }
        
        

        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp4");
        attrs.setAudioAttributes( audio );
        attrs.setVideoAttributes( video );
        try
        {
            encoder.encode(source, target, attrs);
        }
        catch ( IllegalArgumentException e )
        {
            e.printStackTrace();
        }
        catch ( InputFormatException e )
        {
            e.printStackTrace();
        }
        catch ( EncoderException e )
        {
            e.printStackTrace();
        }
        /*String command = "ffmpeg -i \"" + originFilePath + "\" -b " + videoBitrate + "k \"" + destinationFilePath + "\"" ;
        commands.add( command ) ;
        
        String[] commandsArr = new String[]
		{
        	"ffmpeg", "-i", originFilePath, "-b", String.valueOf(videoBitrate) + "k", destinationFilePath
		} ;
        arrayCommands.add(commandsArr);
        */
        return true ;
    }
    
    public boolean formatVideoToMp4( String originFilePath, String destinationFilePath )
    {
        String command = "ffmpeg -i \"" + originFilePath + "\"  \"" + destinationFilePath + "\"" ;

        commands.add( command ) ;
        String[] commandsArr = new String[]
		{
        	"ffmpeg", "-i", originFilePath, destinationFilePath
		} ;
        arrayCommands.add(commandsArr);
        return true ;
    }
    
    public boolean splitVideo( String originalFile, String destinationFile, float secondsInit, float duration, int bitrate )
    {
        File source = new File( originalFile ) ;
        File target  = new File( destinationFile ) ;

        Encoder encoder = new Encoder();
        
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libfaac");
        audio.setBitRate(new Integer(128000));
        audio.setChannels(new Integer(2));
        audio.setSamplingRate(new Integer(44100));
        
        VideoAttributes video = new VideoAttributes();
        video.setCodec("mpeg4");
        video.setBitRate(new Integer(bitrate));
        video.setFrameRate(new Integer(30));
        video.setSize( new VideoSize( 480, 360 ) ) ;
        
        EncodingAttributes attrs = new EncodingAttributes();
        
        attrs.setFormat("mp4");
        attrs.setAudioAttributes( audio );
        attrs.setVideoAttributes( video );
        attrs.setDuration( duration ) ;
        attrs.setOffset( secondsInit ) ;
        
        try
        {
            EncoderProgressListener listener = new EncoderProgressListener( )
            {
                
                @Override
                public void sourceInfo( MultimediaInfo arg0 )
                {
                    // TODO Auto-generated method stub
                    
                }
                
                @Override
                public void progress( int arg0 )
                {
                    System.out.println( "Progresso da divisão de arquivos: " + arg0 );
                }
                
                @Override
                public void message( String arg0 )
                {
                    System.out.println( arg0 );
                }
            };
            encoder.encode(source, target, attrs, listener);
        }
        catch ( IllegalArgumentException e )
        {
            e.printStackTrace();
        }
        catch ( InputFormatException e )
        {
            e.printStackTrace();
        }
        catch ( EncoderException e )
        {
            e.printStackTrace();
        }
        return true ;
    }
    
    public boolean splitVideo( String originalFile, String destinationFile, long secondsInit, long secondsEnd )
    {
        String command = "ffmpeg -i \"" + originalFile + "\" -vcodec copy -acodec copy -ss " + DateUtil.formatIntoHHMMSS( secondsInit ) + " -t " + DateUtil.formatIntoHHMMSS( secondsEnd ) + "  \"" + destinationFile + "\"" ;
        
        commands.add( command ) ;
        String[] commandsArr = new String[]
		{
        	"ffmpeg", "-i", originalFile, "-vcodec", "copy", "-acodec", "copy", "-ss",  DateUtil.formatIntoHHMMSS( secondsInit ), "-t", DateUtil.formatIntoHHMMSS( secondsEnd ), destinationFile
		} ;
        arrayCommands.add(commandsArr);
        return true ;
    }
    
    public void processCommands( String originalFile )
    {
        ThreadCommands command =  null ;
        if ( StringConstants.osName.equals("linux") )
        {
        	command = new ThreadCommands( arrayCommands );
        }
        else
        {
        	command = new ThreadCommands( commands, null ) ;
        }
        
        command.setOriginalFile( originalFile ) ;
        Thread commandThread = new Thread( command ) ;
        commandThread.start( ) ;
    }
    
    public static synchronized void processCommand( String[] string ) throws Exception
    {
        try 
        {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(string);

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line=null;

            while((line=input.readLine()) != null) 
            {
                System.out.println(line);
            }
            
            BufferedReader error = new BufferedReader(new InputStreamReader(pr.getErrorStream()));

            while((line = error.readLine()) != null) 
            {
                System.out.println(line);
            }

        }
        catch (Exception e) 
        {
            System.out.println(e.toString());
            throw e;
        }
    }
    
    public static void processCommand( String string ) throws Exception
    {
        try 
        {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(string);

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line=null;

            while((line=input.readLine()) != null) 
            {
                System.out.println(line);
            }
            
            BufferedReader error = new BufferedReader(new InputStreamReader(pr.getErrorStream()));

            while((line = error.readLine()) != null) 
            {
                System.out.println(line);
            }

        }
        catch (Exception e) 
        {
            System.out.println(e.toString());
            throw e;
        }
    }
}
