package br.com.ftt.bettaserver.database.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Crypt
{
    public static String crypt( String value )
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance( "MD5" );
            digest.digest( value.getBytes( ) ) ;
            BASE64Encoder encoder = new BASE64Encoder( ) ;
            return encoder.encode( digest.digest( ) ) ;
        }
        catch( NoSuchAlgorithmException e )
        {
            e.printStackTrace( );
        }
        return value;
    }
}
