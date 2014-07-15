package br.com.ftt.bettaserver.restful.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.ftt.bettaserver.restful.database.util.DataBaseUtil;
import br.com.ftt.bettaserver.restful.database.util.StringConstants;
import br.com.ftt.bettaserver.restful.form.ChaveParametro;
import br.com.ftt.bettaserver.restful.form.Filmes;
import br.com.ftt.bettaserver.restful.form.Idioma;
import br.com.ftt.bettaserver.restful.form.Playlist;

public class FilmeDAO
{
    public int buscaIdiomaPadrao( int idFilme )
    {
        int idiomaPadrao = 0 ;
        Connection con = null ;
        PreparedStatement pstmt = null;
        ResultSet rs = null ;
        String sql = "select id from idiomas_filmes where id = ( select MIN(id_idiomas_filmes) from bettaserver.playlists_filmes where id_filme = ? )" ;
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setInt( 1, idFilme ) ;
            }
            rs = pstmt.executeQuery( ) ;
            
            if( rs.next( ) )
            {
                idiomaPadrao = rs.getInt( "id" ) ;
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace( );
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, rs ) ;
        }
        
        return idiomaPadrao ;
    }
    
    private List<Idioma> listarIdiomasFilmes( Connection con, int idFilme ) throws SQLException
    {
        List<Idioma> idiomas = null ;
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;
        String sql = "select distinct idiomas_filmes.id, idiomas_filmes.codigo, idiomas_filmes.nome from idiomas_filmes inner join playlists_filmes on playlists_filmes.id_idiomas_filmes = idiomas_filmes.id where playlists_filmes.id_filme = ? order by idiomas_filmes.id";
        ParametrosDAO dao = new ParametrosDAO( ) ;
        String urlServidor = dao.buscaValorDeParametro( ChaveParametro.MOBILE_REQUEST ) ;
        
        pstmt = con.prepareStatement( sql ) ;
        {
            pstmt.setInt( 1, idFilme ) ;
        }
        rs = pstmt.executeQuery( ) ;
        
        if( rs.next( ) )
        {
            idiomas = new LinkedList<Idioma>( ) ;
            do
            {
                Idioma idioma = new Idioma( ) ;
                idioma.setId( rs.getInt( "id" ) ) ;
                idioma.setCodigo( rs.getString( "codigo" ) ) ;
                idioma.setNome( rs.getString( "nome" ) ) ;
                String urlIdioma = urlServidor + "/procuraPlaylist?idFilme=" + idFilme + "&#38;idIdioma=" + rs.getInt( "id" ) ;
                idioma.setUrlIdioma( StringConstants.encode(urlIdioma) );
                idiomas.add( idioma ) ;
            }
            while( rs.next( ) ) ;
        }
        
        DataBaseUtil.close( null, pstmt, rs ) ;
        
        return idiomas;
    }
    
    public List<Playlist> buscarFilmePlaylist( int idFilme, int idIdioma )
    {
        List<Playlist> playlists = null ;
        ParametrosDAO dao = new ParametrosDAO( ) ;
        String urlStreaming = dao.buscaValorDeParametro( ChaveParametro.STREAMING_PATH ) ;
        Connection con = null ;
        PreparedStatement pstmt = null;
        ResultSet rs = null ;
        String sql = "select bitrate, playlist from playlists_filmes where id_filme = ? and id_idiomas_filmes = ? order by bitrate" ;
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setInt( 1, idFilme ) ;
                pstmt.setInt( 2, idIdioma ) ;
            }
            rs = pstmt.executeQuery( ) ;
            
            if( rs.next( ) )
            {
                playlists = new LinkedList<Playlist>( ) ;
                do
                {
                    Playlist playlist = new Playlist( ) ;
                    playlist.setBitrate( rs.getString( "bitrate" ) ) ;
                    playlist.setPlaylistPath( rs.getString( "playlist" ) ) ;
                    playlist.setServerPath( urlStreaming ) ;
                    
                    playlists.add( playlist ) ;
                }
                while( rs.next( ) ) ;
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace( ) ;
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, rs ) ;
        }
        
        return playlists ;
    }
    
    public List<Filmes> buscarFilme( String nome )
    {
        List<Filmes> filmes = null ;
        
        Connection con = null ;
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;
        ParametrosDAO dao = new ParametrosDAO( ) ;
        String urlServidor = dao.buscaValorDeParametro( ChaveParametro.MOBILE_REQUEST ) ;
        String urlStreaming = dao.buscaValorDeParametro( ChaveParametro.STREAMING_PATH ) ;
        
        StringBuffer  sql = new StringBuffer();
        sql.append("SELECT id, ");
        sql.append("       nome, ");
        sql.append("       url_imagem, ");
        sql.append("       duracao, ");
        sql.append("       diretores, ");
        sql.append("       atores, ");
        sql.append("       sinopse, ");
        sql.append("       avaliacao, ");
        sql.append("       ano ");
        sql.append("FROM   filmes ");
        sql.append("WHERE  LOWER(nome) like ? ");      
        
        try
        {
            con = DataBaseUtil.getConnection( );
            pstmt = con.prepareStatement( sql.toString( ) ) ;
            {
                pstmt.setString( 1, "%" + nome.toLowerCase( ) + "%" ) ;
            }
            rs = pstmt.executeQuery( ) ;
            
            if( rs.next( ) )
            {
                filmes = new LinkedList<Filmes>( ) ;
                do
                {
                    Filmes filme = new Filmes( ) ;
                    filme.setId( rs.getInt( "id" ) ) ;
                    filme.setNome( rs.getString( "nome" ) ) ;
                    String urlFilme = urlStreaming + URLEncoder.encode( rs.getString( "url_imagem" ), "UTF-8" ) ;
                    filme.setUrlImagem( urlFilme ) ;
                    filme.setDuracao( rs.getString( "duracao" ) ) ;
                    filme.setDiretores( rs.getString( "diretores" ) ) ;
                    filme.setAtores( rs.getString( "atores" ) );
                    filme.setSinopse( rs.getString( "sinopse" ) ) ;
                    filme.setAvaliacao( rs.getInt( "avaliacao" ) ) ;
                    filme.setAno( rs.getInt( "ano" ) ) ;
                    filme.setUrlFilme( urlServidor + "/procuraPlaylistPadrao?idFilme=" + rs.getInt( "id" ) ) ;
                    filme.setIdioms( listarIdiomasFilmes( con, rs.getInt( "id" ) ) ) ;
                    filmes.add( filme ) ;
                }
                while( rs.next( ) ) ;
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace( );
        }
        catch ( UnsupportedEncodingException e )
        {
            e.printStackTrace();
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, rs ) ;
        }
        
        return filmes ;
    }
    
    public Filmes buscarFilme( int filmeId, int userId )
    {
        Filmes filme = null;
        Connection con = null ;
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;
        ParametrosDAO dao = new ParametrosDAO( ) ;
        String urlServidor = dao.buscaValorDeParametro( ChaveParametro.MOBILE_REQUEST ) ;
        String urlStreaming = dao.buscaValorDeParametro( ChaveParametro.STREAMING_PATH ) ;
        
        StringBuffer  sql = new StringBuffer();
        sql.append("SELECT id, ");
        sql.append("       nome, ");
        sql.append("       url_imagem, ");
        sql.append("       duracao, ");
        sql.append("       diretores, ");
        sql.append("       atores, ");
        sql.append("       sinopse, ");
        sql.append("       CASE WHEN avaliacao_usuario.avaliacao IS NOT NULL THEN 1 ELSE 0 END  avaliacao_usuario,  ");
        sql.append("       CASE WHEN user_playlist.id_filme IS NOT NULL THEN 1 ELSE 0 END playlist_usuario, ");
        sql.append("       filmes.avaliacao, ");
        sql.append("       ano ");
        sql.append("FROM   filmes ");
        sql.append("LEFT JOIN avaliacao_usuario ON avaliacao_usuario.id_filme = filmes.id AND avaliacao_usuario.id_usuario = ? ") ;
        sql.append("LEFT JOIN user_playlist ON user_playlist.id_filme = filmes.id AND user_playlist.id_user = ? ");
        sql.append("WHERE  id = ? ");      
        
        try
        {
            con = DataBaseUtil.getConnection( );
            pstmt = con.prepareStatement( sql.toString( ) ) ;
            {
                pstmt.setInt( 1, userId ) ;
                pstmt.setInt( 2, userId ) ;
                pstmt.setInt( 3, filmeId ) ;
            }
            rs = pstmt.executeQuery( ) ;
            
            if( rs.next( ) )
            {
                filme = new Filmes( ) ;
                filme.setId( rs.getInt( "id" ) ) ;
                filme.setNome( rs.getString( "nome" ) ) ;
                String urlFilme = urlStreaming + URLEncoder.encode( rs.getString( "url_imagem" ), "UTF-8" ) ;
                filme.setUrlImagem( urlFilme ) ;
                filme.setDuracao( rs.getString( "duracao" ) ) ;
                filme.setDiretores( rs.getString( "diretores" ) ) ;
                filme.setAtores( rs.getString( "atores" ) );
                filme.setSinopse( rs.getString( "sinopse" ) ) ;
                filme.setAvaliacaoUsuario( rs.getInt( "avaliacao_usuario" ) ) ;
                filme.setPlaylistUsuario( rs.getInt( "playlist_usuario" ) ) ;
                filme.setAvaliacao( rs.getInt( "avaliacao" ) ) ;
                filme.setAno( rs.getInt( "ano" ) ) ;
                filme.setUrlFilme( urlServidor + "/procuraPlaylistPadrao?idFilme=" + rs.getInt( "id" ) ) ;
                filme.setIdioms( listarIdiomasFilmes( con, rs.getInt( "id" ) ) ) ;
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace( );
        }
        catch ( UnsupportedEncodingException e )
        {
            e.printStackTrace();
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, rs ) ;
        }
        return filme;
    }
    
    public List<Filmes> buscaFavoritos( int idUsuario )
    {
        List<Filmes> filmes = null ;
        
        Connection con = null ;
        PreparedStatement pstmt = null ;
        ResultSet rs = null;
        ParametrosDAO dao = new ParametrosDAO( ) ;
        String urlServidor = dao.buscaValorDeParametro( ChaveParametro.MOBILE_REQUEST ) ;
        String urlStreaming = dao.buscaValorDeParametro( ChaveParametro.STREAMING_PATH ) ;
        
        StringBuffer  sql = new StringBuffer();
        sql.append("SELECT      id, ");
        sql.append("            nome, ");
        sql.append("            url_imagem, ");
        sql.append("            duracao, ");
        sql.append("            diretores, ");
        sql.append("            atores, ");
        sql.append("            sinopse, ");
        sql.append("            avaliacao, ");
        sql.append("            ano ");
        sql.append("FROM        filmes ");
        sql.append("INNER JOIN  user_playlist ");
        sql.append("ON          filmes.id = user_playlist.id_filme ");
        sql.append("WHERE       user_playlist.id_user = ?");
        
        try
        {
            con = DataBaseUtil.getConnection( );
            pstmt = con.prepareStatement( sql.toString( ) ) ;
            {
                pstmt.setInt( 1, idUsuario ) ;
            }
            rs = pstmt.executeQuery( ) ;
            
            if( rs.next( ) )
            {
                filmes = new LinkedList<Filmes>( ) ;
                do
                {
                    Filmes filme = new Filmes( ) ;
                    filme.setId( rs.getInt( "id" ) ) ;
                    filme.setNome( rs.getString( "nome" ) ) ;
                    String urlFilme = urlStreaming + URLEncoder.encode( rs.getString( "url_imagem" ), "UTF-8" ) ;
                    filme.setUrlImagem( urlFilme ) ;
                    filme.setDuracao( rs.getString( "duracao" ) ) ;
                    filme.setDiretores( rs.getString( "diretores" ) ) ;
                    filme.setAtores( rs.getString( "atores" ) );
                    filme.setSinopse( rs.getString( "sinopse" ) ) ;
                    filme.setAvaliacao( rs.getInt( "avaliacao" ) ) ;
                    filme.setAno( rs.getInt( "ano" ) ) ;
                    filme.setUrlFilme( urlServidor + "/procuraPlaylistPadrao?idFilme=" + rs.getInt( "id" ) ) ;
                    filme.setIdioms( listarIdiomasFilmes( con, rs.getInt( "id" ) ) ) ;
                    filmes.add( filme ) ;
                }
                while( rs.next( ) ) ;
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace( );
        }
        catch ( UnsupportedEncodingException e )
        {
            e.printStackTrace();
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, rs ) ;
        }
        
        return filmes;
    }
    
    public List<Filmes> buscaFilmesPaginadosPorCategoria( int categoriaId )
    {
        List<Filmes> filmes = null ;
        
        Connection con = null ;
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;
        ParametrosDAO dao = new ParametrosDAO( ) ;
        String urlServidor = dao.buscaValorDeParametro( ChaveParametro.MOBILE_REQUEST ) ;
        String urlStreaming = dao.buscaValorDeParametro( ChaveParametro.STREAMING_PATH ) ;
        
        StringBuffer  sql = new StringBuffer();
        sql.append("SELECT id, ");
        sql.append("       nome, ");
        sql.append("       url_imagem, ");
        sql.append("       duracao, ");
        sql.append("       diretores, ");
        sql.append("       atores, ");
        sql.append("       sinopse, ");
        sql.append("       avaliacao, ");
        sql.append("       ano ");
        sql.append("FROM   filmes ");
        sql.append("       INNER JOIN categorias_filmes ");
        sql.append("         ON categorias_filmes.id_filmes = filmes.id ");
        sql.append("WHERE  categorias_filmes.id_categorias = ? ");      
        
        try
        {
            con = DataBaseUtil.getConnection( );
            pstmt = con.prepareStatement( sql.toString( ) ) ;
            {
                pstmt.setInt( 1, categoriaId ) ;
            }
            rs = pstmt.executeQuery( ) ;
            
            if( rs.next( ) )
            {
                filmes = new LinkedList<Filmes>( ) ;
                do
                {
                    Filmes filme = new Filmes( ) ;
                    filme.setId( rs.getInt( "id" ) ) ;
                    filme.setNome( rs.getString( "nome" ) ) ;
                    String urlFilme = urlStreaming + URLEncoder.encode( rs.getString( "url_imagem" ), "UTF-8" ) ;
                    filme.setUrlImagem( urlFilme ) ;
                    filme.setDuracao( rs.getString( "duracao" ) ) ;
                    filme.setDiretores( rs.getString( "diretores" ) ) ;
                    filme.setAtores( rs.getString( "atores" ) );
                    filme.setSinopse( rs.getString( "sinopse" ) ) ;
                    filme.setAvaliacao( rs.getInt( "avaliacao" ) ) ;
                    filme.setAno( rs.getInt( "ano" ) ) ;
                    filme.setUrlFilme( urlServidor + "/procuraPlaylistPadrao?idFilme=" + rs.getInt( "id" ) ) ;
                    filme.setIdioms( listarIdiomasFilmes( con, rs.getInt( "id" ) ) ) ;
                    filmes.add( filme ) ;
                }
                while( rs.next( ) ) ;
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace( );
        }
        catch ( UnsupportedEncodingException e )
        {
            e.printStackTrace();
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, rs ) ;
        }
        
        return filmes ;
    }
}
