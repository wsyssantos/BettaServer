package br.com.ftt.bettaserver.digital.reader.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Livro
{
    private int    id;
    private String titulo;
    private String autor;
    private String editora;
    private int    numeroPaginas;
    private String enderecoArquivo;

    public int getId( )
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public String getTitulo( )
    {
        return titulo;
    }

    public void setTitulo( String titulo )
    {
        this.titulo = titulo;
    }

    public String getAutor( )
    {
        return autor;
    }

    public void setAutor( String autor )
    {
        this.autor = autor;
    }

    public String getEditora( )
    {
        return editora;
    }

    public void setEditora( String editora )
    {
        this.editora = editora;
    }

    public int getNumeroPaginas( )
    {
        return numeroPaginas;
    }

    public void setNumeroPaginas( int numeroPaginas )
    {
        this.numeroPaginas = numeroPaginas;
    }

    public String getEnderecoArquivo( )
    {
        return enderecoArquivo;
    }

    public void setEnderecoArquivo( String enderecoArquivo )
    {
        this.enderecoArquivo = enderecoArquivo;
    }
}
