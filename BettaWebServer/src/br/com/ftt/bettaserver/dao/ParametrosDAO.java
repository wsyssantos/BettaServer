package br.com.ftt.bettaserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.ftt.bettaserver.database.util.DataBaseUtil;
import br.com.ftt.bettaserver.form.ChaveParametro;
import br.com.ftt.bettaserver.form.IdiomasFilmes;
import br.com.ftt.bettaserver.form.Parametro;

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
    
    public List<IdiomasFilmes> buscaTodosIdiomas()
    {
        List<IdiomasFilmes> idiomas = null ;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null ;
        String sql = "select id, codigo, nome from idiomas_filmes";
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            pstmt = con.prepareStatement( sql ) ;
            rs = pstmt.executeQuery( ) ;
            
            if( rs.next( ) )
            {
                idiomas = new LinkedList<IdiomasFilmes>( ) ;
                do
                {
                    IdiomasFilmes idioma = new IdiomasFilmes( ) ;
                    idioma.setId( rs.getInt( "id" ) );
                    idioma.setCodigo( rs.getString( "codigo" ) ) ;
                    idioma.setNome( rs.getString( "nome" ) ) ;
                    idiomas.add( idioma );
                }
                while( rs.next( ) ) ;
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, rs ) ;
        }
        return idiomas ;
    }
    
    public boolean salvarParametros( Parametro[] parametros )
    {
        Connection con = null ;
        PreparedStatement pstmt = null;
        String sql = "update parametros set valor = ? where id = ?";
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            con.setAutoCommit( false ) ;
            
            for( Parametro param : parametros )
            {
                pstmt = con.prepareStatement( sql ) ;
                {
                    pstmt.setString( 1, param.getValor( ) ) ;
                    pstmt.setInt( 2, param.getId( ) ) ;
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
            return false ;
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, null ) ;
        }
        
        return true;
    }
    
    public List<Parametro> buscarTodosParametros( )
    {
        List<Parametro> parametros = null;
        Connection con = null ;
        PreparedStatement pstmt = null;
        ResultSet rs = null ;
        String sql = "select id, nome, valor from parametros" ;
        
        try
        {
            con = DataBaseUtil.getConnection( ) ;
            pstmt = con.prepareStatement( sql ) ;
            rs = pstmt.executeQuery( ) ;
            
            if( rs.next( ) )
            {
                parametros = new LinkedList<Parametro>( ) ;
                do
                {
                    Parametro parametro = new Parametro( ) ;
                    parametro.setId( rs.getInt( "id" ) ) ;
                    parametro.setNome( rs.getString( "nome" ) ) ;
                    parametro.setValor( rs.getString( "valor" ) ) ;
                    
                    parametros.add( parametro ) ;
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
        
        
        return parametros;
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
