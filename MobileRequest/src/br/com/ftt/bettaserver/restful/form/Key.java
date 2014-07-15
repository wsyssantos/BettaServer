package br.com.ftt.bettaserver.restful.form;

import java.math.BigDecimal;
import java.util.Random;

public class Key
{
    private int g = 7;
    private int n = 23;
    private int y;
    private int r1;
    private int k;
    
    public Key( )
    {
        this.y = new Random( ).nextInt( 100 ) ;
    }

    public int getG( )
    {
        return g;
    }

    public void setG( int g )
    {
        this.g = g;
    }

    public int getN( )
    {
        return n;
    }

    public void setN( int n )
    {
        this.n = n;
    }

    public int getY( )
    {
        return y;
    }

    public void setY( int y )
    {
        this.y = y;
    }
    
    public void setR1( int r1 )
    {
        this.r1 = r1;
    }

    public int getR1( )
    {
        return r1;
    }

    public int getR2( )
    {
        BigDecimal calculator = new BigDecimal( g ) ;
        calculator = calculator.pow( y ) ;
        calculator = calculator.remainder( new BigDecimal( n ) ) ;
        
        return calculator.intValue( ) ;
    }

    public int getK( )
    {
        BigDecimal calculator = new BigDecimal( r1 ) ;
        calculator = calculator.pow( y ) ;
        calculator = calculator.remainder( new BigDecimal( n ) ) ;
        
        return calculator.intValue( ) ;        
    }

    public void setK( )
    {
        BigDecimal calculator = new BigDecimal( r1 ) ;
        calculator = calculator.pow( y ) ;
        calculator = calculator.remainder( new BigDecimal( n ) ) ;
        
        this.k = calculator.intValue( ) ;
    }
    
    public boolean isKeyValid( int remoteK )
    {
        return( this.k == remoteK ) ;
    }

}
