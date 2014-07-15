package br.com.ftt.bettaserver.restful.form;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuario
{
    private int    id;
    private long   session;
    private String login;
    private String senha;
    private String email;
    private String facebookToken;
    private String facebookExpires;
    private Key    chave;
    private String twitterToken;
    private String twitterSecretToken;
    
    public Usuario( )
    {
        chave = new Key( ) ;
    }

    public String getLogin( )
    {
        return login;
    }

    public void setLogin( String login )
    {
        this.login = login;
    }

    public String getSenha( )
    {
        return senha;
    }

    public void setSenha( String senha )
    {
        this.senha = senha;
    }

    public int getId( )
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public Key getChave( )
    {
        return chave;
    }

    public void setChave( Key chave )
    {
        this.chave = chave;
    }

    public long getSession( )
    {
        return session;
    }

    public void setSession( long session )
    {
        this.session = session;
    }

    public String getEmail( )
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getFacebookToken( )
    {
        return facebookToken;
    }

    public void setFacebookToken( String facebookToken )
    {
        this.facebookToken = facebookToken;
    }

    public String getFacebookExpires( )
    {
        return facebookExpires;
    }

    public void setFacebookExpires( String facebookExpires )
    {
        this.facebookExpires = facebookExpires;
    }

    public String getTwitterToken( )
    {
        return twitterToken;
    }

    public void setTwitterToken( String twitterToken )
    {
        this.twitterToken = twitterToken;
    }

    public String getTwitterSecretToken( )
    {
        return twitterSecretToken;
    }

    public void setTwitterSecretToken( String twitterSecretToken )
    {
        this.twitterSecretToken = twitterSecretToken;
    }
}
