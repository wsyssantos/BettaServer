package br.com.ftt.bettaserver.streaming.http;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.http.HttpChunkAggregator;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;
import org.jboss.netty.handler.stream.ChunkedWriteHandler;

public class BettaUdpFileServerPipelineFactory implements ChannelPipelineFactory
{
    @Override
    public ChannelPipeline getPipeline( ) throws Exception
    {
        ChannelPipeline pipeline = pipeline( ) ;
        
        //Descomentar se for usar HTTPS
//        SSLEngine engine = SecureChatSslContextFactory.getServerContext( ).createSSLEngine( ) ;
//        engine.setUseClientMode( false ) ;
//        pipeline.addLast( "ssl", new SslHandler( engine ) ) ;
        
        pipeline.addLast( "decoder", new HttpRequestDecoder( ) ) ;
        pipeline.addLast( "aggregator", new HttpChunkAggregator( 65536 ) ) ;
        pipeline.addLast( "encoder", new HttpResponseEncoder( ) ) ;
        pipeline.addLast( "chunkedWriter", new ChunkedWriteHandler( ) ) ;
        
        pipeline.addLast( "handler", new BettaUdpFileServerHandler( ) ) ;
        
        return pipeline ;
    }
}
