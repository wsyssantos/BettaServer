package br.com.ftt.videosplitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

public class ThreadCommands implements Runnable
{
    private List<String> commands;
    private List<String[]> arrayCommands;
    private String       originalFile;

    public ThreadCommands( List<String> commands, List<String[]> arrayCommands )
    {
        this.commands = commands;
        this.arrayCommands = arrayCommands ;
    }
    
    public ThreadCommands( List<String[]> arrayCommands )
    {
    	this.arrayCommands = arrayCommands ;
    	this.commands = null ;
    }

    @Override
    public void run( )
    {
        System.out.println( "Iniciou Thread - " + Thread.currentThread( ).getId( ) ) ;
        try
        {
        	if( commands != null )
        	{
        		for ( String command : commands )
                {
        			Runtime runtime = Runtime.getRuntime( );
                    Process proc = runtime.exec( command ) ;
                    
                    BufferedReader error = new BufferedReader( new InputStreamReader(
                            proc.getErrorStream( ) ) );
                    String line;
                    
                    while ( ( line = error.readLine( ) ) != null )
                    {
                        System.out.println( line );
                    }
                }
        	}
        	else
        	{
        		for ( String[] command : arrayCommands )
                {
        			Runtime runtime = Runtime.getRuntime( );
                    Process proc = runtime.exec( command ) ;
                    
                    BufferedReader error = new BufferedReader( new InputStreamReader(
                            proc.getErrorStream( ) ) );
                    String line;
                    
                    while ( ( line = error.readLine( ) ) != null )
                    {
                        System.out.println( line );
                    }
                }
        	}
        }
        catch( Exception e )
        {
        	e.printStackTrace() ;
        }
        
        File original = new File( originalFile ) ;
        original.delete( ) ;
        System.out.println( "Finalizou Thread: " + Thread.currentThread( ).getId( ) );
    }

    public void setOriginalFile( String originalFile )
    {
        this.originalFile = originalFile;
    }

}
