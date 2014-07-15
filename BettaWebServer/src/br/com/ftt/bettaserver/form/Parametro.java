package br.com.ftt.bettaserver.form;

public class Parametro
{
    private int    id;
    private String nome;
    private String valor;
    
    public int getId( )
    {
        return id;
    }
    public void setId( int id )
    {
        this.id = id;
    }
    public String getNome( )
    {
        return nome;
    }
    public void setNome( String nome )
    {
        this.nome = nome;
    }
    public String getValor( )
    {
        return valor;
    }
    public void setValor( String valor )
    {
        this.valor = valor;
    }
}
