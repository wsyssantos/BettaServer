package br.com.ftt.bettaserver.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class ActionBetta
{
    protected String errorMessage ;
    protected String userName ;
    
    public ActionBetta( )
    {
        
    }
    
    protected Map<String, Object> getParameters( )
    {
        
        return ActionContext.getContext( ).getParameters( ) ;
    }
    
    protected String getParameterByName( String name )
    {
        Object paramObj = getParameters( ).get( name ) ;  
        if(paramObj == null) 
        {
            return null;
        }  
        
        return ((String[])paramObj)[0];
    }
    
    public String getUser( )
    {
    	Map attributes = ActionContext.getContext().getSession();    	
    	userName = (String)attributes.get("user");
    	return userName;
    }
    
    public void setUser( String user )
    {
    	Map attributes = ActionContext.getContext().getSession();    	
    	attributes.put("user", user);
    }
    
    public void resetUser(  )
    {
    	Map attributes = ActionContext.getContext().getSession();    	
    	attributes.remove("user");
    }
}
