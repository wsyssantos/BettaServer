package br.com.ftt.betta.mobile.parser.vo;

public class Media
{
    private int    id;
    private int    duration;
    private String url;

    public String getUrl( )
    {
        return url;
    }

    public void setUrl( String url )
    {
        this.url = url;
    }

    public int getId( )
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public int getDuration( )
    {
        return duration;
    }

    public void setDuration( int duration )
    {
        this.duration = duration;
    }
}