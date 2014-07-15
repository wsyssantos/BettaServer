package br.com.ftt.bettaserver.restful.form;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "nome", "url", "isVisible" } ) 
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlCategroria
{
    private String  nome;
    private String  url;       // >http://192.168.0.100:8080/betta/categories/categoriaFiccao.xml</url>
    private String  isVisible;
    
    public XmlCategroria( )
    {
        
    }

    public String getNome( )
    {
        return nome;
    }

    public void setNome( String nome )
    {
        this.nome = nome;
    }

    public String getUrl( )
    {
        return url;
    }

    public void setUrl( String url )
    {
        this.url = url;
    }

    public String getIsVisible( )
    {
        return isVisible;
    }

    public void setIsVisible( String isVisible )
    {
        this.isVisible = isVisible;
    }

}
