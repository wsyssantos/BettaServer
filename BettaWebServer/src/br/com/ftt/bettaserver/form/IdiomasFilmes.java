package br.com.ftt.bettaserver.form;

public class IdiomasFilmes
{
    private int id;
    private String codigo;
    private String nome;
    private String urlFilme ;
    
    public int getId( )
    {
        return id;
    }
    public void setId( int id )
    {
        this.id = id;
    }
    public String getCodigo( )
    {
        return codigo;
    }
    public void setCodigo( String codigo )
    {
        this.codigo = codigo;
    }
    public String getNome( )
    {
        return nome;
    }
    public void setNome( String nome )
    {
        this.nome = nome;
    }
    public String getUrlFilme( )
    {
        return urlFilme;
    }
    public void setUrlFilme( String urlFilme )
    {
        this.urlFilme = urlFilme;
    }
}
