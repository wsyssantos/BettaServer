package br.com.ftt.bettaserver.form;

import java.io.File;

public class Filme
{
    private int              id;
    private String           nome;
    private File             fileImagem;
    private File             fileFilme;
    public File getFileFilme( )
    {
        return fileFilme;
    }

    public void setFileFilme( File fileFilme )
    {
        this.fileFilme = fileFilme;
    }

    private File             urlImagem;
    private String           urlImagemFileName;
    private String           urlImagemContentType;
    private String           duracao;
    private String           diretores;
    private String           atores;
    private String           sinopse;
    private int              numQualidades;
    private String[ ]        listaCategorias;
    private String           strCategorias;
    private IdiomasFilmes[ ] idiomas;
    private int              ano;
    private int[ ]           qualidades;
    private String           strQualidades;
    private File             urlFilme;
    private String           urlFilmeFileName;
    private String           urlFilmeContentType;

    public File getUrlImagem( )
    {
        return urlImagem;
    }

    public void setUrlImagem( File urlImagem )
    {
        this.urlImagem = urlImagem;
    }

    public String getUrlImagemContentType( )
    {
        return urlImagemContentType;
    }

    public void setUrlImagemContentType( String urlImagemContentType )
    {
        this.urlImagemContentType = urlImagemContentType;
    }

    public String getStrCategorias( )
    {
        return strCategorias;
    }

    public void setStrCategorias( String strCategorias )
    {
        this.strCategorias = strCategorias;
    }

    public String getStrQualidades( )
    {
        return strQualidades;
    }

    public void setStrQualidades( String strQualidades )
    {
        this.strQualidades = strQualidades;
    }

    public File getUrlFilme( )
    {
        return urlFilme;
    }

    public void setUrlFilme( File urlFilme )
    {
        this.urlFilme = urlFilme;
    }

    public String getUrlFilmeFileName( )
    {
        return urlFilmeFileName;
    }

    public void setUrlFilmeFileName( String urlFilmeFileName )
    {
        this.urlFilmeFileName = urlFilmeFileName;
    }

    public String getUrlFilmeContentType( )
    {
        return urlFilmeContentType;
    }

    public void setUrlFilmeContentType( String urlFilmeContentType )
    {
        this.urlFilmeContentType = urlFilmeContentType;
    }

    public String getNome( )
    {
        return nome;
    }

    public void setNome( String nome )
    {
        this.nome = nome;
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

    public String getUrlImagemFileName( )
    {
        return urlImagemFileName;
    }

    public void setUrlImagemFileName( String urlImagemFileName )
    {
        this.urlImagemFileName = urlImagemFileName;
    }

    public String[ ] getListaCategorias( )
    {
        return listaCategorias;
    }

    public void setListaCategorias( String[ ] listaCategorias )
    {
        this.listaCategorias = listaCategorias;
    }

    public int getNumQualidades( )
    {
        return numQualidades;
    }

    public void setNumQualidades( int numQualidades )
    {
        this.numQualidades = numQualidades;
    }

    public File getFileImagem( )
    {
        return fileImagem;
    }

    public void setFileImagem( File fileImagem )
    {
        this.fileImagem = fileImagem;
    }

    public IdiomasFilmes[ ] getIdiomas( )
    {
        return idiomas;
    }

    public void setIdiomas( IdiomasFilmes[ ] idiomas )
    {
        this.idiomas = idiomas;
    }

    public int getId( )
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public int getAno( )
    {
        return ano;
    }

    public void setAno( int ano )
    {
        this.ano = ano;
    }

    public int[ ] getQualidades( )
    {
        return qualidades;
    }

    public void setQualidades( int[ ] qualidades )
    {
        this.qualidades = qualidades;
    }

}
