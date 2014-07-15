package br.com.ftt.bettaserver.restful.form;

public class Playlist
{
    private String serverPath;
    private String bitrate; 
    private String playlistPath;
    
    public String getServerPath( )
    {
        return serverPath;
    }
    public void setServerPath( String serverPath )
    {
        this.serverPath = serverPath;
    }
    public String getPlaylistPath( )
    {
        return playlistPath;
    }
    public void setPlaylistPath( String playlistPath )
    {
        this.playlistPath = playlistPath;
    }
    @Override
    public String toString( )
    {
        return serverPath + playlistPath ;
    }
    public String getBitrate( )
    {
        return bitrate;
    }
    public void setBitrate( String bitrate )
    {
        this.bitrate = bitrate;
    }
}
