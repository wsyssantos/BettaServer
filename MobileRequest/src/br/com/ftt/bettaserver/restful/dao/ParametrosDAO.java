package br.com.ftt.bettaserver.restful.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.ftt.bettaserver.restful.database.util.DataBaseUtil;
import br.com.ftt.bettaserver.restful.form.ChaveParametro;

public class ParametrosDAO
{
    public String getCodigoIdioma( int idiomaId )
    {
        String codigo = "";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null ;
        String sql = "select codigo from idiomas_filmes where id = ?";
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setInt( 1, idiomaId );
            }
            rs = pstmt.executeQuery( ) ;
            
            if( rs.next( ) )
            {
                codigo = rs.getString( "codigo" );
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
        
        return codigo;
    }
    
    
    
    public String buscaValorDeParametro( ChaveParametro chave )
    {
        String value = "" ;
        Connection con = null ;
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;
        String sql = "select valor from parametros where chave = ?"  ;
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setString( 1, chave.toString( ) ) ;
            }
            rs = pstmt.executeQuery( ) ;
            
            if( rs.next( ) )
            {
                value = rs.getString( "valor" ) ;
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
        return value ;
    }
}
