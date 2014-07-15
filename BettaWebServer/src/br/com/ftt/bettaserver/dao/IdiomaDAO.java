package br.com.ftt.bettaserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.ftt.bettaserver.database.util.DataBaseUtil;
import br.com.ftt.bettaserver.form.IdiomasFilmes;

public class IdiomaDAO
{
    public boolean existeIdioma( String nome, String codigo )
    {
        Connection con = null ;
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;
        String sql = "select id from idiomas_filmes where LOWER(nome) = ? or LOWER(codigo) = ?" ;
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setString( 1, nome.toLowerCase( ) ) ;
                pstmt.setString( 2, codigo.toLowerCase( ) ) ;
            }
            rs = pstmt.executeQuery( ) ;
            
            return rs.next( ) ;
            
        }
        catch( SQLException e )
        {
            e.printStackTrace( ) ;
        }
        return false ;
    }

    public boolean cadastrarNovoIdioma( IdiomasFilmes idioma )
    {
        Connection con = null ;
        PreparedStatement pstmt = null;
        String sql = "insert into idiomas_filmes ( nome, codigo ) values ( ?, ? )";
        
        try
        {
            con = DataBaseUtil.getConnection( );
            con.setAutoCommit( false );
            pstmt = con.prepareStatement( sql );
            {
                pstmt.setString( 1, idioma.getNome( ) );
                pstmt.setString( 2, idioma.getCodigo( ) );
            }
            pstmt.executeUpdate( );
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
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, null );
        }
        
        return true ;
    }
    
    public List<IdiomasFilmes> buscaTodosIdiomas( )
    {
        List<IdiomasFilmes> idiomas = null ;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null ;
        String sql = "select id, nome, codigo from idiomas_filmes order by nome";
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            pstmt = con.prepareStatement( sql );
            rs = pstmt.executeQuery( );
            
            if( rs.next( ) )
            {
                idiomas = new LinkedList<IdiomasFilmes>( );
                
                do
                {
                    IdiomasFilmes idioma = new IdiomasFilmes( ) ;
                    
                    idioma.setId( rs.getInt( "id" ) ) ;
                    idioma.setNome( rs.getString( "nome" ) ) ;
                    idioma.setCodigo( rs.getString( "codigo" ) ) ;
                    
                    idiomas.add( idioma ) ;
                }
                while( rs.next( ) ) ;
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
        
        return idiomas;
    }
    
    public boolean excluirIdioma( int id )
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "delete from idiomas_filmes where id = ?" ;
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            con.setAutoCommit( false ) ;
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setInt( 1, id ) ;
            }
            pstmt.executeUpdate( );
            con.commit( ) ;
            return true ;
        }
        catch( SQLException e )
        {
            if( con != null )
            {
                try
                {
                    con.rollback( );
                }
                catch ( SQLException e1 )
                {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace( ) ;
            return false ;
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, null );
        }
    }
    
}
