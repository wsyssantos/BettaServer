package br.com.ftt.bettaserver.restful.form;

public class Idioma
{
    private int id;
    private String codigo;
    private String nome;
    private String urlIdioma;
    
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
	
    public String getUrlIdioma() 
	{
		return urlIdioma;
	}
	
	public void setUrlIdioma(String urlIdioma) 
	{
		this.urlIdioma = urlIdioma;
	}
}
