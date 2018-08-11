package gx.common.socket.client;

import javax.net.ssl.SSLException;

import org.springframework.context.ApplicationContext;

import gx.common.socket.model.SerializeDecoder;
import gx.common.socket.model.SerializeEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;



/**
 * netty客户端入门
 * @author -琴兽-
 *
 */
public class Client {
	
	private int port;
	
	private String address;
	
	public static ApplicationContext app;
	
	public Client(String address,int port,ApplicationContext app)  {
		this.port=port;
		this.address=address;
		this.app=app;
		clientMethod();
	}
	public void clientMethod()  {
		//服务类
		Bootstrap bootstrap = new Bootstrap();
		
		//worker
		EventLoopGroup woker = new NioEventLoopGroup();
		bootstrap.group(woker);
		
		//设置工厂
		bootstrap.channel(NioSocketChannel.class);
		try {
		//设置管道
		bootstrap.handler(new ChannelInitializer<Channel>() {

			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();  
                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));  
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));  
                pipeline.addLast("decoder", new SerializeDecoder());  
                pipeline.addLast("encoder", new SerializeEncoder());
				ch.pipeline().addLast(new HiHandler());
			}
		});
		 bootstrap.connect(address,port);
		while (true) {
			/*System.out.println("请输入");
			String readLine;
			
			readLine = buff.readLine();
			
			connect.channel().writeAndFlush(readLine);*/
		}
		} finally {
			woker.shutdownGracefully();
		}
	}

}
