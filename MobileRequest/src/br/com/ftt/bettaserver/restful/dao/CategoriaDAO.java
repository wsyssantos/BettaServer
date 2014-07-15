package br.com.ftt.bettaserver.restful.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import br.com.ftt.bettaserver.restful.database.util.DataBaseUtil;
import br.com.ftt.bettaserver.restful.form.Categorias;
import br.com.ftt.bettaserver.restful.form.ChaveParametro;
import br.com.ftt.bettaserver.restful.form.XmlCategroria;

public class CategoriaDAO
{
    public XmlCategroria buscaCategoria( int id )
    {
        XmlCategroria categoria = null ;
        Connection con = null ;
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;
        String sql = "select id, nome  from categorias where id = ?" ;
        ParametrosDAO dao = new ParametrosDAO( ) ;
        String urlServidor = dao.buscaValorDeParametro( ChaveParametro.MOBILE_REQUEST ) ;
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setInt( 1, id ) ;
            }
            rs = pstmt.executeQuery( ) ;
            
            if( rs.next( ) )
            {
                categoria = new XmlCategroria( ) ;
                categoria.setNome( rs.getString( "nome" ) ) ;
                categoria.setUrl( urlServidor + "/buscaFilmesCategoria?categoriaId="+id) ;
                
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
        
        return categoria ;
    }
    
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
    
    public Categorias buscaTodasCategorias( )
    {
        Categorias list = null ;
        Connection con = null ;
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;
        String sql = "select id, nome from categorias order by nome";
        ParametrosDAO dao = new ParametrosDAO( ) ;
        String urlServidor = dao.buscaValorDeParametro( ChaveParametro.MOBILE_REQUEST ) ;
        
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            pstmt = con.prepareStatement( sql ) ;
            rs = pstmt.executeQuery( ) ;
            
            if( rs.next( ) )
            {
                list = new Categorias( ) ;
                list.setCategoria( new LinkedList<XmlCategroria>( ) ) ;
                do
                {
                    int id = rs.getInt( "id" ) ;
                    String nome = rs.getString( "nome" ) ;
                    XmlCategroria cat = new XmlCategroria( );
                    cat.setNome( nome ) ;
                    cat.setUrl( urlServidor + "/buscaFilmesCategoria?categoriaId="+id) ;
                    cat.setIsVisible( "true" ) ;
                    list.getCategory( ).add( cat ) ;
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
    }
}
