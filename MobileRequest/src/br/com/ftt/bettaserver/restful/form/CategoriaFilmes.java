package br.com.ftt.bettaserver.restful.form;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "categoria" )
@XmlAccessorType( XmlAccessType.FIELD )
public class CategoriaFilmes
{
    @XmlAttribute
    private int    numFilmes;

    private String categoriaNome;

    private String categoriaUrl;
    
    private List<Filmes> filme;

    public int getNumFilmes( )
    {
        return numFilmes;
    }

    public void setNumFilmes( int numFilmes )
    {
        this.numFilmes = numFilmes;
    }

    public String getCategoriaNome( )
    {
        return categoriaNome;
    }

    public void setCategoriaNome( String categoriaNome )
    {
        this.categoriaNome = categoriaNome;
    }

    public String getCategoriaUrl( )
    {
        return categoriaUrl;
    }

    public void setCategoriaUrl( String categoriaUrl )
    {
        this.categoriaUrl = categoriaUrl;
    }

    public List<Filmes> getFilme( )
    {
        return filme;
    }

    public void setFilme( List<Filmes> filme )
    {
        this.filme = filme;
    }
}
