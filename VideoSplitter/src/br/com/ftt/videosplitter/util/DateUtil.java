package br.com.ftt.videosplitter.util;

public class DateUtil
{
    public static String formatIntoHHMMSS( int secsIn )
    {

        int hours = secsIn / 3600, remainder = secsIn % 3600, minutes = remainder / 60, seconds = remainder % 60;

        return ( ( hours < 10 ? "0" : "" ) + hours + ":"
                + ( minutes < 10 ? "0" : "" ) + minutes + ":"
                + ( seconds < 10 ? "0" : "" ) + seconds );

    }
    
    public static String formatIntoHHMMSS( long secsIn )
    {

        long hours = secsIn / 3600, remainder = secsIn % 3600, minutes = remainder / 60, seconds = remainder % 60;

        return ( ( hours < 10 ? "0" : "" ) + hours + ":"
                + ( minutes < 10 ? "0" : "" ) + minutes + ":"
                + ( seconds < 10 ? "0" : "" ) + seconds );

    }
    
}
