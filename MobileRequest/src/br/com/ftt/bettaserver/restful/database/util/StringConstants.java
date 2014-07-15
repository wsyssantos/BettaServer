package br.com.ftt.bettaserver.restful.database.util;

public class StringConstants 
{
	public static String pathSeparator;
	public static String lineSeparator;
	public static String osName;
	
	static
	{
		 pathSeparator = System.getProperty("file.separator");
		 lineSeparator = System.getProperty("line.separator");
		 osName = System.getProperty("os.name").toLowerCase();
	}
	
	public static String noSpaceEncode( String input )
	{
	    StringBuilder resultStr = new StringBuilder( ) ;
        
        for ( char ch : input.toCharArray( ) ) 
        {
            if ( isNoSpaceUnsafe( ch ) ) 
            {
                resultStr.append( '%' ) ;
                resultStr.append( toHex(ch / 16) ) ;
                resultStr.append( toHex(ch % 16) ) ;
            } 
            else 
            {
                resultStr.append( ch ) ;
            }
        }
        return resultStr.toString( ) ;
	}
	
	public static String encode(String input) 
	{
        StringBuilder resultStr = new StringBuilder( ) ;
        
        for ( char ch : input.toCharArray( ) ) 
        {
            if ( isUnsafe( ch ) ) 
            {
                resultStr.append( '%' ) ;
                resultStr.append( toHex(ch / 16) ) ;
                resultStr.append( toHex(ch % 16) ) ;
            } 
            else 
            {
                resultStr.append( ch ) ;
            }
        }
        return resultStr.toString( ) ;
    }

    private static char toHex( int ch ) 
    {
        return (char) (ch < 10 ? '0' + ch : 'A' + ch - 10);
    }
    
    private static boolean isNoSpaceUnsafe( char ch )
    {
        return " %$&+,;=?@<>#%".indexOf(ch) >= 0;
    }

    private static boolean isUnsafe( char ch ) 
    {
        if (ch > 128 || ch < 0)
            return true;
        return " %$&+,;=?@<>#%".indexOf(ch) >= 0;
    }
}
