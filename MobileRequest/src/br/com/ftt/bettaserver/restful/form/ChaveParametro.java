package br.com.ftt.bettaserver.restful.form;

public enum ChaveParametro
{
    MEDIA_ADDESS,
    WEB_PATH,
    IMAGE_ADDESS,
    STREAMING_PATH,
    MOBILE_REQUEST;
    
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
        if( this == MOBILE_REQUEST )
        {
            return "MOBILE_REQUEST";
        }
        return null;
    }
}