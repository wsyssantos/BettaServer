package br.com.ftt.videosplitter.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesGetter
{
    private static Properties props ;
    private static File      file ;
    static
    {
        file  = new File("videoSplitter.properties");
        props = new Properties( ) ;
        
        FileInputStream fis = null ;
        
        try
        {
            fis = new FileInputStream( file ) ;
            props.load( fis ) ;
            fis.close( ) ;
        }
        catch ( FileNotFoundException e )
        {
            e.printStackTrace();
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        } 
    }
    
    public static String getPropertyValue( String key )
    {
        return props.getProperty( key ) ;
    }
    
    public static String getPropertyValue( String key, String defaultValue )
    {
        return props.getProperty( key, defaultValue ) ;
    }
}
