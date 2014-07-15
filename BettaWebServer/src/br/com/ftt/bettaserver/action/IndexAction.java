package br.com.ftt.bettaserver.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

public class IndexAction extends ActionBetta
{
    @Action( value="inicio", results = { @Result( location = "index.jsp", name="ok" ) } )
    public String init( ) 
    {
        return "ok";
    }
}
