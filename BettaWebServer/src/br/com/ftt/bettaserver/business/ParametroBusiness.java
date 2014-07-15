package br.com.ftt.bettaserver.business;

import java.util.List;

import br.com.ftt.bettaserver.dao.ParametrosDAO;
import br.com.ftt.bettaserver.form.ChaveParametro;
import br.com.ftt.bettaserver.form.IdiomasFilmes;
import br.com.ftt.bettaserver.form.Parametro;

public class ParametroBusiness
{
    private static ParametroBusiness business;
    
    private ParametroBusiness()
    {
        
    }
    
    public List<IdiomasFilmes> buscaTodosIdiomas()
    {
        ParametrosDAO dao = new ParametrosDAO( ) ;
        return dao.buscaTodosIdiomas( );
    }
    
    public boolean salvarParametros( Parametro[] parametros )
    {
        ParametrosDAO dao = new ParametrosDAO( ) ;
        return dao.salvarParametros( parametros ) ;
    }
    
    public List<Parametro> buscarTodosParametros( )
    {
        ParametrosDAO dao = new ParametrosDAO( );
        return dao.buscarTodosParametros( ) ;
    }
    
    public String buscaValorDeParametro( ChaveParametro chave )
    {
        ParametrosDAO dao = new ParametrosDAO( );
        return dao.buscaValorDeParametro( chave ) ;
    }
    
    public static ParametroBusiness getInstance( )
    {
        if( business == null )
        {
            business = new ParametroBusiness( ) ;
        }
        return business ;
    }
}
