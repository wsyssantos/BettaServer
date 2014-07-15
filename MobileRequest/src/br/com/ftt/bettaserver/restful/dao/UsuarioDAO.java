package br.com.ftt.bettaserver.restful.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.ftt.bettaserver.restful.database.util.DataBaseUtil;
import br.com.ftt.bettaserver.restful.form.Usuario;

public class UsuarioDAO
{
    public boolean excluiFilmePlaylist( int idFilme, int idUsuario )
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM user_playlist WHERE id_user = ? AND id_filme = ?";
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            con.setAutoCommit( false ) ;
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setInt( 1, idUsuario ) ;
                pstmt.setInt( 2, idFilme ) ;
            }
            pstmt.executeUpdate( ) ;
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
            catch (SQLException e1) 
            {
                e1.printStackTrace( );
            }
            e.printStackTrace( ) ;
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, null ) ;
        }
        
        return true;
    }
    
    public Usuario fillUserInfo( Usuario user )
    {
        Connection con = null;
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;
        String sql = "SELECT email, facebook_acess_token, facebook_expires, twitter_token, twitter_secret_token from usuario where ID = ?";
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setInt( 1, user.getId( ) ) ;
            }
            rs = pstmt.executeQuery( ) ;
            
            if( rs.next( ) )
            {
                user.setEmail( rs.getString( "email" ) ) ;
                user.setFacebookToken( rs.getString( "facebook_acess_token" ) ) ;
                user.setFacebookExpires( rs.getString( "facebook_expires" ) ) ;
                user.setTwitterToken( rs.getString( "twitter_token" ) ) ;
                user.setTwitterSecretToken( rs.getString( "twitter_secret_token" ) ) ;
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
        
        return user;
    }
    
    public boolean existUserName( String nome )
    {
        Connection con = null ;
        PreparedStatement pstmt = null ;
        ResultSet rs = null;
        String sql = "SELECT ID from usuario WHERE login = ?" ;
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setString( 1, nome.trim( ) ) ;
            }
            rs = pstmt.executeQuery( ) ;
            
            return rs.next( ) ;
        }
        catch( SQLException e )
        {
            e.printStackTrace( );
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, rs ) ;
        }
        
        return false ;
    }
    
    private void excluiAvaliacao( Connection con, int idUsuario, int idFilme ) throws SQLException
    {
        String sql = "delete from avaliacao_usuario where id_usuario = ? and id_filme = ?" ;
        PreparedStatement pstmt = con.prepareStatement( sql ) ;
        {
            pstmt.setInt( 1, idUsuario ) ;
            pstmt.setInt( 2, idFilme ) ;
        }
        pstmt.executeUpdate( ) ;
    }
    
    public boolean cadastrarUsuario( Usuario usuario )
    {
        Connection con = null;
        PreparedStatement pstmt = null ;
        String sql = "INSERT INTO usuario ( id, login, email, senha, facebook_acess_token, facebook_expires, twitter_token, twitter_secret_token ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )" ;
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            con.setAutoCommit( false ) ;
            int userId = DataBaseUtil.getNextId( "usuario", con ) ;
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setInt( 1, userId ) ;
                pstmt.setString( 2, usuario.getLogin( ) ) ;
                pstmt.setString( 3, usuario.getEmail( ) ) ;
                pstmt.setString( 4, usuario.getSenha( ) ) ;
                pstmt.setString( 5, usuario.getFacebookToken( ) ) ;
                pstmt.setString( 6, usuario.getFacebookExpires( ) ) ;
                pstmt.setString( 7, usuario.getTwitterToken( ) ) ;
                pstmt.setString( 8, usuario.getTwitterSecretToken( ) ) ;
            }
            pstmt.executeUpdate( ) ;
            con.commit( ) ;
        }
        catch ( SQLException e )
        {
            e.printStackTrace( ) ;
            try
            {
                if( con != null )
                {
                    con.rollback( ) ;
                }
            }
            catch (SQLException e1) 
            {
                e1.printStackTrace( );
            }
            return false ;
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, null ) ;
        }
        return true ;
    }
    
    public boolean cadastrarFavorito( int idUsuario, int idFilme )
    {
        Connection con = null ;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO user_playlist ( id_user, id_filme ) VALUE ( ?, ? )";
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            con.setAutoCommit( false ) ;
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setInt( 1, idUsuario ) ;
                pstmt.setInt( 2, idFilme ) ;
            }
            pstmt.executeUpdate( ) ;
            con.commit( ) ;
        }
        catch ( SQLException e )
        {
            e.printStackTrace( ) ;
            try
            {
                if( con != null )
                {
                    con.rollback( ) ;
                }
            }
            catch (SQLException e1) 
            {
                e1.printStackTrace( );
            }
            return false ;
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, null ) ;
        }
        
        return true ;
    }
    
    public boolean avaliaFilme( int idUsuario, int idFilme, int avaliacao )
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "insert into avaliacao_usuario ( id_usuario, id_filme, avaliacao ) values (?,?,?) ";

        try
        {
            con = DataBaseUtil.getConnection( );
            con.setAutoCommit( false ) ;
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setInt( 1, idUsuario ) ;
                pstmt.setInt( 2, idFilme ) ;
                pstmt.setInt( 3, avaliacao ) ;
            }
            excluiAvaliacao( con, idUsuario, idFilme ) ;
            pstmt.executeUpdate( ) ;
            atualizarAvaliacaoFilme( con, idFilme ) ;
            con.commit( ) ;
        }
        catch ( SQLException e )
        {
            e.printStackTrace( ) ;
            try
            {
                if( con != null )
                {
                    con.rollback( ) ;
                }
            }
            catch (SQLException e1) 
            {
                e1.printStackTrace( );
            }
            return false ;
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, null ) ;
        }

        return true;
    }
    
    private void atualizarAvaliacaoFilme( Connection con, int idFilme ) throws SQLException
    {
        String sql = "update filmes set avaliacao = ( select avg(avaliacao) from bettaserver.avaliacao_usuario where id_filme = ? group by id_filme ) where id = ?" ;
        PreparedStatement pstmt = con.prepareStatement( sql ) ;
        {
            pstmt.setInt( 1, idFilme ) ;
            pstmt.setInt( 2, idFilme ) ;
        }
        pstmt.executeUpdate( ) ;
    }

    public int loginUsuario( Usuario usuario )
    {
        int idUsuario = -1;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select id from usuario where login = ? and senha = ?";

        try
        {
            con = DataBaseUtil.getConnection( );
            pstmt = con.prepareStatement( sql );
            {
                pstmt.setString( 1, usuario.getLogin( ) );
                pstmt.setString( 2, usuario.getSenha( ) );
            }
            rs = pstmt.executeQuery( );

            if ( rs.next( ) )
            {
                idUsuario = rs.getInt( "id" );
            }
        }
        catch ( SQLException e )
        {
            e.printStackTrace( );
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, rs );
        }

        return idUsuario;
    }
}
