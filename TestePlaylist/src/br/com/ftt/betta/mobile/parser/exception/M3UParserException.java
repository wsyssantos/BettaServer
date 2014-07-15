package br.com.ftt.betta.mobile.parser.exception;

public class M3UParserException extends Exception
{
    private static final long serialVersionUID = 3521112316261324615L;

    public M3UParserException( String detailMessage )
    {
        super( detailMessage ) ;
    }
    
    public M3UParserException( Throwable throwable )
    {
        super( throwable ) ;
    }
}
