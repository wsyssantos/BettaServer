package br.com.ftt.bettaserver.streaming.http;

import static org.jboss.netty.handler.codec.http.HttpHeaders.*;
import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.*;
import static org.jboss.netty.handler.codec.http.HttpMethod.*;
import static org.jboss.netty.handler.codec.http.HttpResponseStatus.*;
import static org.jboss.netty.handler.codec.http.HttpVersion.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelFutureProgressListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.DefaultFileRegion;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.FileRegion;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.frame.TooLongFrameException;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.ssl.SslHandler;
import org.jboss.netty.handler.stream.ChunkedFile;
import org.jboss.netty.util.CharsetUtil;

public class BettaUdpFileServerHandler extends SimpleChannelUpstreamHandler
{
    @Override
    public void messageReceived( ChannelHandlerContext ctx, MessageEvent e ) throws Exception
    {
        HttpRequest request = (HttpRequest)e.getMessage( ) ;
        if( request.getMethod( ) != GET )
        {
            sendError( ctx, FORBIDDEN ) ;
            return ;
        }
        
        final String path = sanitizeUri( request.getUri( ) ) ;
        if( path == null )
        {
            sendError( ctx, FORBIDDEN ) ;
            return ;
        }
        
        File file = new File(path) ;
        if( file.isHidden( ) || !file.exists( ) )
        {
            sendError( ctx, NOT_FOUND ) ;
            return ;
        }
        
        RandomAccessFile raf ;
        try
        {
            raf = new RandomAccessFile( file, "r" ) ;
        }
        catch( FileNotFoundException fnfe )
        {
            sendError( ctx, NOT_FOUND ) ;
            return;
        }
        long fileLength = raf.length( ) ;
        HttpResponse response = new DefaultHttpResponse( HTTP_1_1, OK ) ;
        setContentLength( response, fileLength ) ;
        
        Channel ch = e.getChannel( ) ;
        
        //Escreve a linha inicial do cabeçalho
        ch.write( response ) ;
        
        // Escreve o conteúdo
        ChannelFuture writeFuture ;
        if( ch.getPipeline( ).get( SslHandler.class ) != null )
        {
            writeFuture = ch.write( new ChunkedFile( raf, 0, fileLength, 8192 ) ) ;
        }
        else
        {
            final FileRegion region = new DefaultFileRegion( raf.getChannel( ), 0, fileLength ) ;
            writeFuture = ch.write( region ) ;
            writeFuture.addListener( new ChannelFutureProgressListener( )
            {
                @Override
                public void operationComplete( ChannelFuture arg0 ) throws Exception
                {
                    region.releaseExternalResources( ) ;
                }
                
                @Override
                public void operationProgressed( ChannelFuture future, long amount, long current,  long total ) throws Exception
                {
                    System.out.printf( "%s: %d / %d (+%d)%n", path, current, total, amount );
                }
            }) ;
        }
        
        // Decide se fecha a conexão ou não!!
        if( !isKeepAlive( request ) )
        {
            writeFuture.addListener( ChannelFutureListener.CLOSE ) ;
        }
    }
    
    @Override
    public void exceptionCaught( ChannelHandlerContext ctx, ExceptionEvent e ) throws Exception
    {
        Channel ch = e.getChannel( ) ;
        Throwable cause = e.getCause( ) ;
        if( cause instanceof TooLongFrameException )
        {
            sendError( ctx, BAD_REQUEST ) ;
            return ;
        }
        
        cause.printStackTrace( ) ;
        if( ch.isConnected( ) )
        {
            sendError( ctx, INTERNAL_SERVER_ERROR ) ;
        }
    }
    
    private String sanitizeUri( String uri )
    {
        try
        {
            uri = URLDecoder.decode( uri, "UTF-8" ) ;
        }
        catch ( UnsupportedEncodingException e )
        {
            try
            {
                uri = URLDecoder.decode( uri, "ISO-8859-1" ) ;
            }
            catch ( UnsupportedEncodingException e1 )
            {
                throw new Error( ) ;
            }
        }
        
        // Converte barras separadoras de Arquivos
        uri = uri.replace( '/', File.separatorChar ) ;
        
        // Uma simples validação de segurança
        if( uri.contains( File.separator + "." ) ||
            uri.contains( "." + File.separator ) ||
            uri.startsWith( "." ) || uri.endsWith( "." ))
        {
            return null ;
        }
        
        // Converte o caminho absoluto
        return System.getProperty( "user.dir" ) + File.separator + uri ;
    }
    
    private void sendError( ChannelHandlerContext ctx, HttpResponseStatus status )
    {
        HttpResponse response = new DefaultHttpResponse( HTTP_1_1, status ) ;
        response.setHeader( CONTENT_TYPE, "tex/plain; charset=UTF-8" ) ;
        response.setContent( ChannelBuffers.copiedBuffer( "Failure: " + status.toString( ) + "\r\n", CharsetUtil.UTF_8 ) ) ;
        
        // Fecha a conexão assim que a mensagem é enviada
        ctx.getChannel( ).write( response ).addListener( ChannelFutureListener.CLOSE ) ;
    }
    
    public static void main( String[ ] args )
    {
        System.out.println( System.getProperty( "user.dir" ) + File.separator );
    }
}
