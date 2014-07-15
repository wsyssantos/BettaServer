package br.com.ftt.bettaserver.restful.business;

import br.com.ftt.bettaserver.restful.dao.UsuarioDAO;
import br.com.ftt.bettaserver.restful.form.Usuario;

public class UsuarioBusiness
{
    private UsuarioDAO dao = new UsuarioDAO( ) ;
    private static UsuarioBusiness userBusiness;
    
    private UsuarioBusiness( )
    {
        
    }
    
    public boolean excluiFilmePlaylist( int idFilme, int idUsuario )
    {
        return dao.excluiFilmePlaylist( idFilme, idUsuario ) ;
    }
    
    public Usuario fillUserInfo( Usuario user )
    {
        return dao.fillUserInfo( user ) ;
    }
    
    public boolean existUserName( String nome )
    {
        return dao.existUserName( nome ) ;
    }
    
    public boolean cadastrarUsuario( Usuario usuario )
    {
        return dao.cadastrarUsuario( usuario ) ;
    }
    
    public boolean cadastrarFavorito( int idUsuario, int idFilme )
    {
        return dao.cadastrarFavorito( idUsuario, idFilme ) ;
    }
    
    public boolean avaliaFilme( int idUsuario, int idFilme, int avaliacao )
    {
        return dao.avaliaFilme( idUsuario, idFilme, avaliacao ) ;
    }
    
    public int login( Usuario usuario )
    {
        return dao.loginUsuario( usuario ) ;
    }
    
    public static UsuarioBusiness getInstance( )
    {
        if( userBusiness == null )
        {
            userBusiness = new UsuarioBusiness( ) ;
        }
        
        return userBusiness;
    }
    
}
