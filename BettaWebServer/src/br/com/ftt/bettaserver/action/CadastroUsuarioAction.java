package br.com.ftt.bettaserver.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import br.com.ftt.bettaserver.business.UsuarioBusiness;
import br.com.ftt.bettaserver.form.Usuario;

public class CadastroUsuarioAction extends ActionBetta
{
    private Usuario newUser;

    @Action( value = "cadastrarUsuario", results = { @Result( location = "index.jsp", name = "ok" ) } )
    public String init( )
    {
        UsuarioBusiness business = UsuarioBusiness.getInstance( ) ;
        
        if( newUser != null )
        {
            business.cadastrarNovoUsuario( newUser ) ;
        }
        return "ok";
    }
    
    public Usuario getNewUser( )
    {
        return newUser;
    }

    public void setNewUser( Usuario newUser )
    {
        this.newUser = newUser;
    }
}
