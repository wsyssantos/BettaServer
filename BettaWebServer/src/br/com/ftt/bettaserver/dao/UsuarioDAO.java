package br.com.ftt.bettaserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.ftt.bettaserver.database.util.Crypt;
import br.com.ftt.bettaserver.database.util.DataBaseUtil;
import br.com.ftt.bettaserver.form.Usuario;

public class UsuarioDAO
{
    public boolean loginUsuario( String userName, String password )
    {
        Connection con = null ;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select id from usuario_web where nome = ? and senha = ?";
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setString( 1, userName ) ;
                pstmt.setString( 2, Crypt.crypt( password ) ); 
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
            DataBaseUtil.close( con, pstmt, rs );
        }
        return false;
    }
    
    public boolean cadastrarNovoUsuario( Usuario usuario )
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "insert into usuario_web ( nome, senha, email ) values (?, ?, ?)";
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            con.setAutoCommit( false );
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setString( 1, usuario.getUserName( ) ) ;
                pstmt.setString( 2, Crypt.crypt( usuario.getPassword( ) ) ) ;
                pstmt.setString( 3, usuario.getEmail( ) ) ;
            }
            pstmt.executeUpdate( ) ;
            con.commit( ); 
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
            return false;
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, null );
        }
        return true ;
    }
}
