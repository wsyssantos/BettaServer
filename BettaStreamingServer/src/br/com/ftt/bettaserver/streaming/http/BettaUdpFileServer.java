package br.com.ftt.bettaserver.streaming.http;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ConnectionlessBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class BettaUdpFileServer
{
    public static void main( String[ ] args )
    {
        ConnectionlessBootstrap bootstrap = new ConnectionlessBootstrap( 
                new NioServerSocketChannelFactory( Executors.newCachedThreadPool( ), Executors.newCachedThreadPool( ) )); 
    
        bootstrap.setPipelineFactory( new BettaUdpFileServerPipelineFactory( ) ) ;
        
        bootstrap.bind( new InetSocketAddress( 48080 ) ) ;
    }
}
