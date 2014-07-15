package br.com.ftt.bettaserver.business;

import java.util.List;

import br.com.ftt.bettaserver.dao.IdiomaDAO;
import br.com.ftt.bettaserver.form.IdiomasFilmes;

public class IdiomaBusiness
{
    private static IdiomaBusiness business ;
    
    private IdiomaBusiness( )
    {
        
    }
    
    public boolean existeIdioma( String nome, String codigo )
    {
        IdiomaDAO dao = new IdiomaDAO( ) ;
        return dao.existeIdioma( nome, codigo ) ;
    }
    
    public boolean cadastrarNovoIdioma( IdiomasFilmes idioma )
    {
        IdiomaDAO dao = new IdiomaDAO( ) ;
        return dao.cadastrarNovoIdioma( idioma );
    }
    
    public boolean excluirIdioma( int id )
    {
        IdiomaDAO dao = new IdiomaDAO( ) ;
        return dao.excluirIdioma( id ) ;
    }
    
    public List<IdiomasFilmes> buscaTodosIdiomas( )
    {
        IdiomaDAO dao = new IdiomaDAO( ) ;
        return dao.buscaTodosIdiomas( ) ;
    }
    
    public static IdiomaBusiness getInstance( )
    {
        if( business == null )
        {
            business = new IdiomaBusiness( ); 
        }
        return business;
    }
}
