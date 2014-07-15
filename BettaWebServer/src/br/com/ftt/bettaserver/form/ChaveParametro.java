package br.com.ftt.bettaserver.form;

public enum ChaveParametro
{
    MEDIA_ADDESS,
    WEB_PATH,
    IMAGE_ADDESS,
    STREAMING_PATH;
    
    public String toString( )
    {
        if( this == MEDIA_ADDESS )
        {
            return "MEDIA_ADDESS";
        }
        if( this == WEB_PATH )
        {
            return "WEB_PATH";
        }
        if( this == IMAGE_ADDESS )
        {
            return "IMAGE_ADDESS";
        }
        if( this == STREAMING_PATH )
        {
            return "STREAMING_PATH";
        }
        return null;
    }
}