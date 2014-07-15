package br.com.ftt.bettaserver.business;

import br.com.ftt.bettaserver.dao.UsuarioDAO;
import br.com.ftt.bettaserver.form.Usuario;

public class UsuarioBusiness
{
    private static UsuarioBusiness business;

    private UsuarioBusiness( )
    {
        
    }
    
    public boolean loginUsuario( String userName, String password )
    {
        UsuarioDAO dao = new UsuarioDAO( ) ;
        return dao.loginUsuario( userName, password ) ;
    }
    
    public boolean cadastrarNovoUsuario( Usuario newUser )
    {
        UsuarioDAO dao = new UsuarioDAO( ) ;
        return dao.cadastrarNovoUsuario( newUser ) ;
    }
    
    public static UsuarioBusiness getInstance( )
    {
        if ( business == null )
        {
            business = new UsuarioBusiness( ) ;
        }
        return business;
    }
}
