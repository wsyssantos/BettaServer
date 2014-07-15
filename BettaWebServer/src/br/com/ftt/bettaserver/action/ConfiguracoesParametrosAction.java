package br.com.ftt.bettaserver.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.google.gson.Gson;

import br.com.ftt.bettaserver.business.ParametroBusiness;
import br.com.ftt.bettaserver.form.IdiomasFilmes;
import br.com.ftt.bettaserver.form.Parametro;

public class ConfiguracoesParametrosAction extends ActionBetta
{
    private List<Parametro> parametros;
    private List<IdiomasFilmes> idiomasFilmes;
    
    public ConfiguracoesParametrosAction( )
    {
        
    }

    @Action( value = "configuracoes", results = { @Result( location = "configuracoesServidor.jsp", name = "ok" ) } )
    public String init( )
    {
        carregarParametros( ) ;
        
        return "ok";
    }
    
    @Action( value = "salvarParametro", results = { @Result( location = "configuracoesServidor.jsp", name = "ok" ) } )
    public String salvar( )
    {
        String jSonParametro = getParameterByName( "parametros" ) ;
        Gson gson = new Gson( ) ;
        
        Parametro[] parametros = gson.fromJson( jSonParametro, Parametro[].class ) ; 
        
        ParametroBusiness business = ParametroBusiness.getInstance( ) ;
        boolean success = business.salvarParametros( parametros ) ;
        
        if( !success )
        {
            errorMessage = "Não foi possível salvar os parâmetros de configuração." ;
        }
        
        return "ok";
    }

    private void carregarParametros( )
    {
        ParametroBusiness business = ParametroBusiness.getInstance( );
        parametros = business.buscarTodosParametros( );
    }

    public String getErrorMessage( )
    {
        return errorMessage;
    }

    public void setErrorMessage( String errorMessage )
    {
        this.errorMessage = errorMessage;
    }

    public List<Parametro> getParametros( )
    {
        return parametros;
    }

    public void setParametros( List<Parametro> parametros )
    {
        this.parametros = parametros;
    }

    public List<IdiomasFilmes> getIdiomasFilmes( )
    {
        return idiomasFilmes;
    }

    public void setIdiomasFilmes( List<IdiomasFilmes> idiomasFilmes )
    {
        this.idiomasFilmes = idiomasFilmes;
    } 
}
