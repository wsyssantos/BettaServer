package br.com.ftt.videosplitter.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtil
{
    private static String DB_URL = "jdbc:mysql://localhost:3306/bettaserver" ;
    private static String DB_USERNAME = "root";
    private static String DB_PASSWORD = "123456";
    
    static
    {
        try
        {
            Class.forName( "com.mysql.jdbc.Driver" ).newInstance( ) ;
            
        }
        catch ( InstantiationException e )
        {
            e.printStackTrace( );
        }
        catch ( IllegalAccessException e )
        {
            e.printStackTrace( );
        }
        catch ( ClassNotFoundException e )
        {
            e.printStackTrace( );
        }
    }
    
    public static Connection getConnection( ) throws SQLException
    {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
    
    public static void close( Connection con, PreparedStatement pstmt, ResultSet rs )
    {    
        try
        {
            if( rs != null )
            {
                rs.close( ) ;
            }
            
            if( pstmt != null )
            {
                pstmt.close( ) ;
            }
            
            if( con != null )
            {
                con.close( ) ;
            }
        }
        catch ( SQLException e )
        {
            e.printStackTrace();
        }
    }
    
    public static String searchServerPropertySplitMinutes( )
    {
        String result = "5" ;
        Connection con = null ;
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;
        String sql = "select valor from parametros where chave = ?"  ;
        
        try
        {
            con = getConnection( ) ;
            pstmt = con.prepareStatement( sql ) ;
            {
                pstmt.setString( 1, "MOVIE_TIME_SLICE" ) ;
            }
            rs = pstmt.executeQuery( ) ;
            
            if( rs.next( ) )
            {
                result = rs.getString( "valor" ) ;
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace( ) ;
        }
        finally
        {
            close( con, pstmt, rs ) ;
        }
        
        return result ;
    }
}
