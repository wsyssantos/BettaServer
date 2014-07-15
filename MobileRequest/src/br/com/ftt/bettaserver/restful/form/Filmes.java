package br.com.ftt.bettaserver.restful.form;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "filme")
@XmlAccessorType(XmlAccessType.FIELD)
public class Filmes
{
    private int              id;
    private String           nome;
    private String           urlImagem;
    private String           urlFilme;
    private String           duracao;
    private String           diretores;
    private String           atores;
    private String           sinopse;
    private int              avaliacaoUsuario;
    private int              playlistUsuario;
    private int              ano;
    private int              avaliacao;
    @XmlElement(name="idiomas")
    private List<Idioma>     idiomas;
    
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
    public String getUrlFilme( )
    {
        return urlFilme;
    }
    public void setUrlFilme( String urlFilme )
    {
        this.urlFilme = urlFilme;
    }
    public String getDuracao( )
    {
        return duracao;
    }
    public void setDuracao( String duracao )
    {
        this.duracao = duracao;
    }
    public String getDiretores( )
    {
        return diretores;
    }
    public void setDiretores( String diretores )
    {
        this.diretores = diretores;
    }
    public String getAtores( )
    {
        return atores;
    }
    public void setAtores( String atores )
    {
        this.atores = atores;
    }
    public String getSinopse( )
    {
        return sinopse;
    }
    public void setSinopse( String sinopse )
    {
        this.sinopse = sinopse;
    }
    public String getUrlImagem( )
    {
        return urlImagem;
    }
    public void setUrlImagem( String urlImagem )
    {
        this.urlImagem = urlImagem;
    }
    public int getAno( )
    {
        return ano;
    }
    public void setAno( int ano )
    {
        this.ano = ano;
    }
    public int getAvaliacao( )
    {
        return avaliacao;
    }
    public void setAvaliacao( int avaliacao )
    {
        this.avaliacao = avaliacao;
    }
    public List<Idioma> getIdiomas( )
    {
        return idiomas;
    }
    public void setIdioms( List<Idioma> idiomas )
    {
        this.idiomas = idiomas;
    }
    public int getAvaliacaoUsuario( )
    {
        return avaliacaoUsuario;
    }
    public void setAvaliacaoUsuario( int avaliacaoUsuario )
    {
        this.avaliacaoUsuario = avaliacaoUsuario;
    }
    public int getPlaylistUsuario( )
    {
        return playlistUsuario;
    }
    public void setPlaylistUsuario( int playlistUsuario )
    {
        this.playlistUsuario = playlistUsuario;
    }
}
