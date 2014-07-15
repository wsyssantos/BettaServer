package br.com.ftt.bettaserver.action;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import br.com.ftt.bettaserver.business.CategoriaBusiness;
import br.com.ftt.bettaserver.business.FilmeBusiness;
import br.com.ftt.bettaserver.business.ParametroBusiness;
import br.com.ftt.bettaserver.form.Categoria;
import br.com.ftt.bettaserver.form.Filme;
import br.com.ftt.bettaserver.form.IdiomasFilmes;
import br.com.ftt.bettaserver.form.Qualidade;

import com.google.gson.Gson;

public class CadastroFilmesAction extends ActionBetta
{
    private String              errorMessage;
    private Filme               filme;
    private List<Categoria>     categorias;
    private List<IdiomasFilmes> listaIdiomas;
    private List<Qualidade>		qualidades;
    private List<Qualidade>		qualidades2;
    private List<Qualidade>		qualidades3;
    private List<Qualidade>		qualidades4;

    @Action( value = "cadastro", results = { @Result( location = "cadastroFilmes.jsp", name = "ok" ) } )
    public String init( )
    {
        filme = new Filme( );
        loadQualities();
        carregarCategorias( );
        return "ok";
    }
    
    private void loadQualities ( )
    {
    	qualidades = new LinkedList<Qualidade>();
    	qualidades2 = new LinkedList<Qualidade>();
    	qualidades3 = new LinkedList<Qualidade>();
    	qualidades4 = new LinkedList<Qualidade>();
    	qualidades.add(new Qualidade( 1, "Ultra Low" ));
    	qualidades.add(new Qualidade( 2,"Extra Low"));
    	qualidades2.add(new Qualidade( 3,"Low"));
    	qualidades2.add(new Qualidade( 4,"Low Medium"));
    	qualidades3.add(new Qualidade( 5,"High Medium"));
    	qualidades3.add(new Qualidade( 6,"High"));
    	qualidades4.add(new Qualidade( 7,"Extra High"));
    	qualidades4.add(new Qualidade( 8,"Ultra High"));
    }

    @Action( value = "cadastrarFilme", results = { @Result( location = "cadastroFilmes.jsp", name = "ok" ) } )
    public String cadastrarFilme( ) throws Exception
    {
        Gson gson = new Gson( ) ;

        if ( filme != null )
        {
            int[] qualidades = gson.fromJson( filme.getStrQualidades( ), int[].class ) ;
            String[] categorias = gson.fromJson( filme.getStrCategorias( ), String[].class ) ;
            
            filme.setQualidades( qualidades ) ;
            filme.setListaCategorias( categorias ) ;
            
            if ( filme.getUrlImagemFileName( ) != null )
            {
                filme.setFileImagem( new File( filme.getUrlImagemFileName( ) ) );
                FileUtils.copyFile( filme.getUrlImagem( ), filme.getFileImagem( ) );
                
                filme.setFileFilme( new File( filme.getUrlFilmeFileName( ) ) ) ;
                FileUtils.copyFile( filme.getUrlFilme( ), filme.getFileFilme( ) ) ;
                
                //filme.setFileImagem( filme.getUrlImagem( ) ) ;
            }

            FilmeBusiness business = FilmeBusiness.getInstance( );
            boolean success = business.inserirNovoFilme( filme );

            if ( !success )
            {
                errorMessage = "Não foi possível cadastrar o filme";
            }
            else
            {
                errorMessage = "Filme cadastrado com sucesso!";
            }
        }
        filme = new Filme( );
        loadQualities();
        carregarCategorias( );
        return "ok";
    }

    private void carregarCategorias( )
    {
        CategoriaBusiness business = CategoriaBusiness.getInstance( );
        categorias = business.buscaTodasCategorias( );
        ParametroBusiness paramteroBus = ParametroBusiness.getInstance( ) ;
        listaIdiomas = paramteroBus.buscaTodosIdiomas( ) ;
    }

    public Filme getFilme( )
    {
        return filme;
    }

    public void setFilme( Filme filme )
    {
        this.filme = filme;
    }

    public String getErrorMessage( )
    {
        return errorMessage;
    }

    public void setErrorMessage( String errorMessage )
    {
        this.errorMessage = errorMessage;
    }

    public List<Categoria> getCategorias( )
    {
        return categorias;
    }

    public void setCategorias( List<Categoria> categorias )
    {
        this.categorias = categorias;
    }

    public List<IdiomasFilmes> getListaIdiomas( )
    {
        return listaIdiomas;
    }

    public void setListaIdiomas( List<IdiomasFilmes> listaIdiomas )
    {
        this.listaIdiomas = listaIdiomas;
    }

	public List<Qualidade> getQualidades() {
		return qualidades;
	}

	public void setQualidades(List<Qualidade> qualidades) {
		this.qualidades = qualidades;
	}

	public List<Qualidade> getQualidades2() {
		return qualidades2;
	}

	public void setQualidades2(List<Qualidade> qualidades2) {
		this.qualidades2 = qualidades2;
	}

	public List<Qualidade> getQualidades3() {
		return qualidades3;
	}

	public void setQualidades3(List<Qualidade> qualidades3) {
		this.qualidades3 = qualidades3;
	}

	public List<Qualidade> getQualidades4() {
		return qualidades4;
	}

	public void setQualidades4(List<Qualidade> qualidades4) {
		this.qualidades4 = qualidades4;
	}
}
