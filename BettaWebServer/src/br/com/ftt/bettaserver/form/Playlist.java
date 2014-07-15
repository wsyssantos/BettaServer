package br.com.ftt.bettaserver.form;

public class Playlist
{
    private int    idFilme;
    private int    bitrate;
    private int    idIdiomaFilme;
    private String playlistName;
    public int getIdFilme( )
    {
        return idFilme;
    }
    public void setIdFilme( int idFilme )
    {
        this.idFilme = idFilme;
    }
    public int getBitrate( )
    {
        return bitrate;
    }
    public void setBitrate( int bitrate )
    {
        this.bitrate = bitrate;
    }
    public String getPlaylistName( )
    {
        return playlistName;
    }
    public void setPlaylistName( String playlistName )
    {
        this.playlistName = playlistName;
    }
    public int getIdIdiomaFilme( )
    {
        return idIdiomaFilme;
    }
    public void setIdIdiomaFilme( int idIdiomaFilme )
    {
        this.idIdiomaFilme = idIdiomaFilme;
    }
}
