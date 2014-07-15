package br.com.ftt.bettaserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.ftt.bettaserver.database.util.DataBaseUtil;
import br.com.ftt.bettaserver.form.Categoria;

public class CategoriaDAO
{
    
    public boolean existeCategoria( String nome )
    {
        Connection con = null ;
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;
        String sql = "select id from categorias where LOWER(nome) = ?" ;
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setString( 1, nome.toLowerCase( ) ) ;
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
    
    public boolean inserirCategoria( Categoria categoria )
    {
        Connection con = null ;
        PreparedStatement pstmt = null ;
        String sql = "insert into categorias ( id, nome ) values ( ?, ? )" ;
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            con.setAutoCommit( false ) ;
            
            int nextId = DataBaseUtil.getNextId( "categorias", con ) ;
            
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setInt( 1, nextId ) ;
                pstmt.setString( 2, categoria.getNome( ) ) ;
            }
            pstmt.executeUpdate( ) ;
            
            con.commit( );
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
            return false;
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, null ) ;
        }
    }
    
    public List<Categoria> buscaTodasCategorias( )
    {
        List<Categoria> list = null ;
        Connection con = null ;
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;
        String sql = "select id, nome from categorias order by id";
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            pstmt = con.prepareStatement( sql ) ;
            rs = pstmt.executeQuery( ) ;
            
            if( rs.next( ) )
            {
                list = new LinkedList<Categoria>( ) ;
                do
                {
                    int id = rs.getInt( "id" ) ;
                    String nome = rs.getString( "nome" ) ;
                    Categoria cat = new Categoria( );
                    cat.setId( id ) ;
                    cat.setNome( nome ) ;
                    
                    list.add( cat ) ;
                }
                while( rs.next( ) ) ;
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace( ) ;
        }
        return list ;
    }
    
    public boolean excluirCategoria( int id )
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "delete from categorias where id = ?" ;
        
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
