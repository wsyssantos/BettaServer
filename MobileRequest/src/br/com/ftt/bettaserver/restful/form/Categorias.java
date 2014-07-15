package br.com.ftt.bettaserver.restful.form;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement( name = "categorias" )
public class Categorias
{
    @XmlAttribute(required=true)
    private int numCategorias;
    
    private List<XmlCategroria> categoria;

    public int getNumCategories( )
    {
        return numCategorias;
    }

    public void setNumCategory( int numCategorias )
    {
        this.numCategorias = numCategorias;
    }

    public List<XmlCategroria> getCategory( )
    {
        return categoria;
    }

    public void setCategoria( List<XmlCategroria> categoria )
    {
        this.categoria = categoria;
    }
}
