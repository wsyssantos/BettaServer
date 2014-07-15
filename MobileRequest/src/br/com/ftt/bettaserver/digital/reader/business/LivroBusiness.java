package br.com.ftt.bettaserver.digital.reader.business;

import java.util.List;

import br.com.ftt.bettaserver.digital.reader.dao.LivroDAO;
import br.com.ftt.bettaserver.digital.reader.vo.Livro;

public class LivroBusiness
{
    private static LivroBusiness business;
    private LivroDAO dao = new LivroDAO( ) ;
    
    private LivroBusiness( )
    {
    }
    
    public List<Livro> buscaTodosLivros( )
    {
        return dao.buscaTodosLivros( ) ;
    }
    
    public static LivroBusiness getInstance( )
    {
        if( business == null )
        {
            business = new LivroBusiness( ); 
        }
        return business;
    }
}
