package br.com.ftt.bettaserver.action;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import br.com.ftt.bettaserver.form.ExampleForm;

public class ExemploAction 
{
    private String      errorMessage;
    private String      name;
    private ExampleForm form;

    @Action( value = "ola", results = { @Result( location = "mensagem.jsp", name = "ok" ) } )
    public String getMensagem( )
    {
        name = "José";
        form = new ExampleForm( );
        form.setNome( "Wesley" );
        form.setSobrenome( "Santos" );
        return "ok";
    }

    public ExampleForm getForm( )
    {
        return form;
    }

    public void setForm( ExampleForm form )
    {
        this.form = form;
    }

    public String getName( )
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getErrorMessage( )
    {
        return errorMessage;
    }

    public void setErrorMessage( String errorMessage )
    {
        this.errorMessage = errorMessage;
    }
}
