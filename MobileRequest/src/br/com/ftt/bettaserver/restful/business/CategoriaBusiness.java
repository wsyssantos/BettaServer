package br.com.ftt.bettaserver.restful.business;

import br.com.ftt.bettaserver.restful.dao.CategoriaDAO;
import br.com.ftt.bettaserver.restful.form.Categorias;
import br.com.ftt.bettaserver.restful.form.XmlCategroria;

public class CategoriaBusiness
{
    private static CategoriaBusiness categoriaBusiness;
    private CategoriaDAO dao = new CategoriaDAO( ) ;
    
    private CategoriaBusiness() 
    {
        
    }
    
    public XmlCategroria buscaCategoria( int id )
    {
        return dao.buscaCategoria( id ) ;
    }
    
    public Categorias buscaTodasCategorias( )
    {
        Categorias categorias = dao.buscaTodasCategorias( ) ;
        categorias.setNumCategory( categorias.getCategory( ).size( ) ) ;
        return categorias ;
    }
    
    public static CategoriaBusiness getInstance( )
    {
        if ( categoriaBusiness == null )
        {
            categoriaBusiness = new CategoriaBusiness( ) ;
        }
        return categoriaBusiness;
    }
}
