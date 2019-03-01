package gx.common.socket.server;

import gx.common.socket.model.SerializeDecoder;
import gx.common.socket.model.SerializeEncoder;
import gx.common.utils.ScanningClass;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import org.springframework.context.ApplicationContext;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

public final class Server {
	
	private int port;
	
	static final boolean SSL = System.getProperty("ssl") != null;
	
	public Server(int port,String scanningPath,ApplicationContext app) throws SSLException, CertificateException, InterruptedException {
		this.port=port;
		ScanningClass ScanningClass=new ScanningClass();
		ScanningClass.InitProxyObject(scanningPath, app);
		serverMethod();
	}
	
	private final void serverMethod() {
		//服务类
		ServerBootstrap bootstrap = new ServerBootstrap();
		
		//boss和woker
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup woker = new NioEventLoopGroup();
		bootstrap.group(boss,woker);
		//设置socket工厂
		bootstrap.channel(NioServerSocketChannel.class);
		try {
		bootstrap.childHandler(new ChannelInitializer<Channel>() {

			@Override
			protected void initChannel(Channel arg0) throws Exception {
				ChannelPipeline pipeline = arg0.pipeline();  
                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));  
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));  
                pipeline.addLast("decoder", new SerializeDecoder());  
                pipeline.addLast("encoder", new SerializeEncoder());
				arg0.pipeline().addLast(new HelloHandler());
			}
			
			
		});
			ChannelFuture bind= bootstrap.bind(port);
			System.out.println("服务器启动");
			//等待服务端关闭
			bind.channel().closeFuture().sync();
		}catch (Exception e) {
			// TODO: handle exception
		} finally {
			boss.shutdownGracefully();
			woker.shutdownGracefully();
		}
	}
}
