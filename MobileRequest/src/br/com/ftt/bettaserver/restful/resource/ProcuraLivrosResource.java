package br.com.ftt.bettaserver.restful.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.ftt.bettaserver.digital.reader.business.LivroBusiness;
import br.com.ftt.bettaserver.digital.reader.vo.Livro;

@Path("/buscaLivros")
public class ProcuraLivrosResource
{
    @GET
    @Produces( "text/xml" )
    public List<Livro> buscaLivros( )
    {
        List<Livro> livros = null ;
        
        LivroBusiness business = LivroBusiness.getInstance( ) ;
        
        livros = business.buscaTodosLivros( ) ;
        
        return livros;
    }
}
