package br.com.ftt.bettaserver.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import br.com.ftt.bettaserver.business.UsuarioBusiness;

public class LoginAction extends ActionBetta
{
    private String userName;
    private String userPassword;

    @Action( value = "login", results = { @Result( location = "index.jsp", name = "ok" ) } )
    public String init( )
    {
        UsuarioBusiness business = UsuarioBusiness.getInstance( ) ;
        
        if ( business.loginUsuario( userName, userPassword ) )
        {
            setUser( userName ) ;
        }
        return "ok";
    }
    
    @Action( value = "logout", results = { @Result( location = "index.jsp", name = "ok" ) } )
    public String logout( )
    {
        resetUser( ) ;
        return "ok";
    }
    
    @Action( value = "novoUsuario", results = { @Result( location = "cadastroUsuario.jsp", name = "ok" ) } )
    public String novo( )
    {
        return "ok";
    }

    public String getUserName( )
    {
        return userName;
    }

    public void setUserName( String userName )
    {
        this.userName = userName;
    }

    public String getUserPassword( )
    {
        return userPassword;
    }

    public void setUserPassword( String userPassword )
    {
        this.userPassword = userPassword;
    }
}
