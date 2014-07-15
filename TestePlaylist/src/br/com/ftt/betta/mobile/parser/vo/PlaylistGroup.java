package br.com.ftt.betta.mobile.parser.vo;

import java.util.LinkedHashMap;

public class PlaylistGroup
{
    private LinkedHashMap<Long, Playlist> mapPlaylist;

    public PlaylistGroup( )
    {
        mapPlaylist = new LinkedHashMap<Long, Playlist>( ) ;
    }
    
    public void add( Long key, Playlist playlist )
    {
        mapPlaylist.put( key, playlist ) ;
    }
    
    public LinkedHashMap<Long, Playlist> getMapPlaylist( )
    {
        return mapPlaylist;
    }

    public void setMapPlaylist( LinkedHashMap<Long, Playlist> mapPlaylist )
    {
        this.mapPlaylist = mapPlaylist;
    }
    
    
}
