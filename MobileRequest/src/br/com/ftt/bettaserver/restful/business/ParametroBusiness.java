package br.com.ftt.bettaserver.restful.business;

import br.com.ftt.bettaserver.restful.dao.ParametrosDAO;
import br.com.ftt.bettaserver.restful.form.ChaveParametro;

public class ParametroBusiness
{
    private static ParametroBusiness business;
    
    private ParametroBusiness()
    {
        
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
