package br.com.ftt.bettaserver.restful.form;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response
{
    private String valor;

    public String getValor( )
    {
        return valor;
    }

    public void setValor( String valor )
    {
        this.valor = valor;
    }
    
}
