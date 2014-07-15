package br.com.ftt.bettaserver.restful.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import br.com.ftt.bettaserver.restful.business.CategoriaBusiness;
import br.com.ftt.bettaserver.restful.form.Categorias;

@Path("/categorias")
public class CategoriaResource
{
    @GET
    @Produces("text/xml")
    public Categorias buscarCategorias( )
    {
        Categorias listaCategorias = null ;
        CategoriaBusiness business = CategoriaBusiness.getInstance( ) ;
        
        try
        {
            listaCategorias = business.buscaTodasCategorias( ) ;
        }
        catch (Exception e) 
        {
            e.printStackTrace( ) ;
        }
        
        return listaCategorias;
    }
}
