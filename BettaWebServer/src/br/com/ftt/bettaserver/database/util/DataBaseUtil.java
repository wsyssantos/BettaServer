package br.com.ftt.bettaserver.database.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.PreparedStatement;

public class DataBaseUtil
{
    public static Connection getConnection( ) throws SQLException
    {
        Connection con = null ;
        Context context;
        try
        {
            context = new InitialContext( );
            DataSource dataSource = (DataSource) context.lookup( "java:comp/env/jdbc/BettaServerDB" ) ;
            con = dataSource.getConnection( ) ;
        }
        catch ( NamingException e )
        {
            e.printStackTrace();
        }
        
        return con ;
    }
    
    public static int getNextId( String tableName, Connection con ) throws SQLException
    {
        int nextId = 0 ;
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;
        String sql = "SELECT (COALESCE(MAX(ID),0) + 1) ID FROM " + tableName ;
        
        pstmt = con.prepareStatement(sql);
        rs = pstmt.executeQuery();
        
        if( rs.next() )
        {
        	nextId = rs.getInt("ID") ;
        }
        
        return nextId;
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
                    pstmt.close( );
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
}
