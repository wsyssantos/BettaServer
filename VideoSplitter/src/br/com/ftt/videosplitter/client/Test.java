package br.com.ftt.videosplitter.client;

import java.io.IOException;
import java.net.URISyntaxException;

import br.com.ftt.videosplitter.VideoCoder;

public class Test
{
    public static void main( String[ ] args ) throws IOException, URISyntaxException
    {
        //VideoCoder coder = new VideoCoder( "/usr/share/example-content/Ubuntu_Free_Culture_Showcase/How fast.ogg",new int[] { 3 }, "/home/wesley/bettaserver/bettaserver/BettaStreamingServer/Videos", "br", "harrypotter7" ) ;
       // coder.encodeVideos( );
    	
    	//IContainer input = IContainer.make( ) ;
        
       /* Runtime runtime = Runtime.getRuntime( ) ;
        String[] parameters = new String[]
        		{
        			"ffmpeg", "-i", "/usr/share/example-content/Ubuntu_Free_Culture_Showcase/How fast.ogg",
        			"-b", "161000", "/home/wesley/bettaserver/bettaserver/BettaStreamingServer/Videos/harrypotter7/How fast-br/ultralow/How fast2.mp4"
        		};
        Process proc = runtime.exec( parameters ) ;
        
        BufferedReader error = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
        String line ;
        while((line = error.readLine()) != null) 
        {
            System.out.println(line);
        }*/
        
//        Thread commandThread = new Thread( new ThreadCommands( "ffmpeg -i \"C:\\dev\\Server Videos\\a-origem.mp4\" -b 161 \"C:\\dev\\Server Videos\\Novos Videos\\aorigem\\a-origem-br\\ultralow\\a-origem.mp4\"" ) ) ;
//        commandThread.start( ) ;
    }
}
