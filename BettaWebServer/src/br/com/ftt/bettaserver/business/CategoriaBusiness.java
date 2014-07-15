package br.com.ftt.bettaserver.business;

import java.util.List;

import br.com.ftt.bettaserver.dao.CategoriaDAO;
import br.com.ftt.bettaserver.form.Categoria;

public class CategoriaBusiness
{
    private static CategoriaBusiness categoriaBusiness;
    
    private CategoriaBusiness() 
    {
        
    }
    
    public boolean existeCategoria( String nome )
    {
        CategoriaDAO dao = new CategoriaDAO( ) ;
        return dao.existeCategoria( nome ) ;
    }
    
    public boolean inserirCategoria( Categoria categoria )
    {
        CategoriaDAO dao = new CategoriaDAO( ) ;
        return dao.inserirCategoria( categoria ) ;
    }
    
    public List<Categoria> buscaTodasCategorias( )
    {
        CategoriaDAO dao = new CategoriaDAO( ) ;
        return dao.buscaTodasCategorias( ) ;
    }
    
    public boolean excluirCategoria( int id )
    {
        CategoriaDAO dao = new CategoriaDAO( ) ;
        return dao.excluirCategoria( id ) ;
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
