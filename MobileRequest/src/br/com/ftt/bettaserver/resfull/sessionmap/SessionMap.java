package br.com.ftt.bettaserver.resfull.sessionmap;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import br.com.ftt.bettaserver.restful.form.Usuario;

public class SessionMap
{
    private static Map<Long, Usuario> mapUsers = null ;
    
    public static Long iniciaSessao( int r1 )
    {
        if( mapUsers == null )
        {
            mapUsers = new LinkedHashMap<Long, Usuario>( ) ;
        }
        
        Usuario user = new Usuario( ) ;
        user.getChave( ).setR1( r1 ) ;
        
        Long sessionId = 0L;
        
        do
        {
            sessionId = new Random( ).nextLong( ) ;
        } 
        while( mapUsers.containsKey( sessionId ) ) ;
        
        
        mapUsers.put( sessionId, user ) ;
        
        return sessionId ;
    }
    
    public static void finishSession( Long sessionId )
    {
        mapUsers.remove( sessionId ) ;
    }
    
    public static Usuario getUsuarioSessao( Long session )
    {
        return mapUsers.get( session ) ;
    }
}
