package br.com.ftt.betta.mobile.parser.util;



import br.com.ftt.betta.mobile.parser.exception.M3UParserException;
import br.com.ftt.betta.mobile.parser.vo.PlaylistGroup;

public class Client
{
    public static void main( String[ ] args )
    {
        PlaylistGroup plg = null;

        try
        {
            plg = FileParser.downloadGroup( "http://localhost:8080/MobileRequest/procuraPlaylistPadrao?idFilme=4" );
        }
        catch ( M3UParserException e1 )
        {
            // TODO Auto-generated catch block
            e1.printStackTrace( );
        }
    }
}

