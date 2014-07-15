package br.com.ftt.bettaserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.ftt.bettaserver.database.util.DataBaseUtil;
import br.com.ftt.bettaserver.form.Filme;
import br.com.ftt.bettaserver.form.Playlist;

public class FilmesDAO
{
    public void atualizarDuracao( int filmeId, long duracao )
    {
        Connection con = null ;
        PreparedStatement pstmt = null ;
        String sql = "UPDATE filmes SET duracao = ? where ID = ?";
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            con.setAutoCommit( false ) ;
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setLong( 1, duracao ) ;
                pstmt.setInt( 2, filmeId );
            }
            pstmt.executeUpdate( ) ;
            con.commit( ) ;
        }
        catch( SQLException e )
        {
            e.printStackTrace( ) ;
            try
            {
                if( con != null )
                {
                    con.rollback( ) ;
                }
            }
            catch( SQLException e1 )
            {
                e1.printStackTrace( ) ;
            }
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, null ) ;
        }
    }
    
    public List<Filme> pesquisaFilmes( String nome )
    {
        List<Filme> filmes = null ;
        Connection con = null ;
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;
        String sql = "SELECT ID , NOME FROM filmes where LOWER(NOME) LIKE ? ORDER BY NOME" ;
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            pstmt = con.prepareStatement( sql );
            {
                pstmt.setString( 1, "%" + nome.toLowerCase( ) + "%" ) ;
            }
            rs = pstmt.executeQuery( ) ;
            
            if( rs.next( ) )
            {
                filmes = new LinkedList<Filme>( ) ;
                do
                {
                    Filme filme = new Filme( ) ;
                    filme.setId( rs.getInt( "id" ) );
                    filme.setNome( rs.getString( "nome" ) ) ;
                    filmes.add( filme );
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
        
        return filmes;
    }
    
    
    public boolean excluirFilme( int filmeId )
    {
        Connection con = null ;
        PreparedStatement pstmt = null ;
        String sql = "delete from filmes where id = ?" ;
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            con.setAutoCommit( false ) ;
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setInt( 1, filmeId ) ;
            }
            pstmt.executeUpdate( ) ;
            excluirCategoriasFilmes(con, filmeId) ;
            excluirPlaylistsFilme(con, filmeId) ;
            excluirAvaliacaoUsuarios(con, filmeId) ;
            con.commit( );
        }
        catch (SQLException e) 
        {
            e.printStackTrace( ) ;
            try
            {
                if( con != null )
                {
                    con.rollback( ) ;
                }
            }
            catch( SQLException e1 )
            {
                e1.printStackTrace( ) ;
            }
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, null );
        }
        
        return true ;
    }
    
    private void excluirCategoriasFilmes( Connection con, int filmeId ) throws SQLException
    {
    	PreparedStatement pstmt = null ;
        String sql = "delete from categorias_filmes where id_filmes = ?" ;
        
        pstmt = con.prepareStatement(sql) ;
        {
        	pstmt.setInt(1, filmeId) ;
        }
        pstmt.executeUpdate();
    }
    
    private void excluirPlaylistsFilme( Connection con, int filmeId ) throws SQLException
    {
    	PreparedStatement pstmt = null ;
        String sql = "delete from playlists_filmes where id_filme = ?" ;
        
        pstmt = con.prepareStatement(sql) ;
        {
        	pstmt.setInt(1, filmeId) ;
        }
        pstmt.executeUpdate();
    }
    
    private void excluirAvaliacaoUsuarios(  Connection con, int filmeId  ) throws SQLException
    {
    	PreparedStatement pstmt = null ;
        String sql = "delete from avaliacao_usuario where id_filme = ?" ;
        
        pstmt = con.prepareStatement(sql) ;
        {
        	pstmt.setInt(1, filmeId) ;
        }
        pstmt.executeUpdate();
    }
    
    public List<Filme> carregaTodosFilmes( )
    {
        List<Filme> filmes = null ;
        Connection con = null ;
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;
        String sql = "SELECT ID , NOME FROM filmes ORDER BY NOME" ;
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            pstmt = con.prepareStatement( sql ) ;
            rs = pstmt.executeQuery( ) ;
            
            if( rs.next( ) )
            {
                filmes = new LinkedList<Filme>( ) ;
                do
                {
                    Filme filme = new Filme( ) ;
                    
                    filme.setId( rs.getInt( "ID" ) ) ;
                    filme.setNome( rs.getString( "NOME" ) ) ;
                    
                    filmes.add( filme ) ;
                }
                while( rs.next( ) ) ;
            }
        }
        catch (SQLException e) 
        {
            e.printStackTrace( ) ;
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, rs ) ;
        }
        
        
        return filmes;
    }
    public int incuirFilme( Filme filme )
    {
        Connection con = null ;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO filmes (ID,NOME,URL_IMAGEM,DURACAO,DIRETORES,ATORES,SINOPSE,ANO) VALUES (?,?,?,?,?,?,?,?)" ;
        int filmeId = 0;
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            con.setAutoCommit( false ) ;
            filmeId = DataBaseUtil.getNextId( "filmes", con ) ;
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setInt( 1, filmeId ) ;
                pstmt.setString( 2, filme.getNome( ) ) ;
                pstmt.setString( 3, filme.getUrlImagemFileName( ) ) ;
                pstmt.setString( 4, filme.getDuracao( ) ) ;
                pstmt.setString( 5, filme.getDiretores( ) ) ;
                pstmt.setString( 6, filme.getAtores( ) ) ;
                pstmt.setString( 7, filme.getSinopse( ) ) ;
                pstmt.setInt(8, filme.getAno( ) ) ;
            }
            pstmt.executeUpdate( ) ;
            
            inserirCategoriasDeFilme( filmeId, filme.getListaCategorias( ), con ) ;
            
            con.commit( ) ;
        }
        catch( SQLException e )
        {
            try
            {
                if( con != null )
                {
                    con.rollback( ) ;
                }
            }
            catch ( SQLException e1 )
            {
                e1.printStackTrace();
            }
            e.printStackTrace( ) ;
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, null ) ;
        }
        
        return filmeId ;
    }
    
    private void inserirCategoriasDeFilme( int filmeId, String[ ] categorias, Connection con ) throws SQLException
    {
        PreparedStatement pstmt = null ;
        String sql = "INSERT INTO categorias_filmes (id_categorias, id_filmes) VALUES (?,?)" ;
        
        try
        {
            for( String cat : categorias )
            {
                pstmt = con.prepareStatement( sql ) ;
                {
                    pstmt.setInt( 1, Integer.parseInt( cat ) ) ;
                    pstmt.setInt( 2, filmeId ) ;
                }
                pstmt.executeUpdate( ) ;
            }
        }
        finally
        {
            DataBaseUtil.close( null, pstmt, null );
        }
    }
    
    public boolean cadastrarPlaylistsDeFilmes( List<Playlist> playlists )
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO playlists_filmes (id_filme,id_idiomas_filmes,bitrate,playlist) VALUES (?,?,?,?)" ;
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            con.setAutoCommit( false ) ;
            
            for( Playlist playlist : playlists )
            {
                pstmt = con.prepareStatement( sql ) ;
                {
                    pstmt.setInt( 1, playlist.getIdFilme( ) ) ;
                    pstmt.setInt( 2, playlist.getIdIdiomaFilme( ) ) ;
                    pstmt.setInt( 3, playlist.getBitrate( ) ) ;
                    pstmt.setString( 4, playlist.getPlaylistName( ) ) ;
                }
                pstmt.executeUpdate( ) ;
            }
            con.commit( ) ;
        }
        catch( SQLException e )
        {
            try
            {
                if( con != null )
                {
                    con.rollback( ) ;
                }
            }
            catch ( SQLException e1 )
            {
                e1.printStackTrace();
            }
            e.printStackTrace( ) ;
            return false;
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, null ) ;
        }
        return true ;
    }
}
