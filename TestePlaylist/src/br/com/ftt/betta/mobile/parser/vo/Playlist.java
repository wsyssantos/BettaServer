package br.com.ftt.betta.mobile.parser.vo;

import java.util.List;

import br.com.ftt.betta.mobile.parser.exception.M3UParserException;
import br.com.ftt.betta.mobile.parser.util.FileParser;

public class Playlist
{
    private int totalDuration ;
    private List<Media> medias;

    public Playlist( String url ) throws M3UParserException
    {
        totalDuration = 0;
        FileParser.loadPlaylist( url, this ) ;
    }
    
    public int getTotalDuration( )
    {
        return totalDuration;
    }
    
    public void addTotalDuration( int duration )
    {
        this.totalDuration += duration;
    }

    public void setTotalDuration( int totalDuration )
    {
        this.totalDuration = totalDuration;
    }

    public List<Media> getMedias( )
    {
        return medias;
    }

    public void setMedias( List<Media> medias )
    {
        this.medias = medias;
    }
}
