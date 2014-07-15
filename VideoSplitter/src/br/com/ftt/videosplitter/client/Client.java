package br.com.ftt.videosplitter.client;

import br.com.ftt.videosplitter.VideoCoder;



public class Client
{
    public static void main( String[ ] args )
    {
        try
        {
           /* VideoSplitter videoSplitter = new VideoSplitter( ) ;
            System.out.println( videoSplitter.segmentMediaFile( 
                    "C:\\dev\\Server Videos\\hmym201.mp4", 
                    "videos\\", "mp4" ) ) ;*/
           // VideoCoder coder = new VideoCoder( "C:\\dev\\Server Videos\\thor.mp4", 5 ) ;
            //coder.codeVideos( ) ;
            
            VideoCoder coder = new VideoCoder( "C:\\Users\\Wesley\\Downloads\\JDownloader\\Inglourious Basterds - Official Trailer 2 [HD](720p_H.264-AAC).mp4", 
                                    new int[] { 1, 8 } , 
                                    "C:\\Users\\Wesley\\Downloads\\JDownloader\\Splitted", "Bastardos" );
            coder.encodeVideos( ) ;
            
//            CommandProcessor proc = new CommandProcessor( ) ;
//            proc.formatVideoToMp4( "C:\\Users\\Wesley\\Downloads\\JDownloader\\Inglourious Basterds - Official Trailer 2 [HD](720p_H.264-AAC).mp4", 
//                    "C:\\Users\\Wesley\\Downloads\\JDownloader\\Bastardos2.mp4", 64000 ) ;
            
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
}
