package gx.common.socket.server;

import gx.common.entity.SerializeObject;
import gx.common.utils.ScanningClass;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.java.Log;

/**
 * 消息接受处理类
 * @author -琴兽-
 *
 */
@Log
public class HelloHandler extends SimpleChannelInboundHandler<SerializeObject>  {

	
	public final static ChannelHandlerContext[] arrAy=new ChannelHandlerContext[1];

	/*
     * 覆盖了 channelRead0() 事件处理方法。
     * 每当从服务端读到客户端写入信息时，
     * 其中如果你使用的是 Netty 5.x 版本时，
     * 需要把 channelRead0() 重命名为messageReceived()
     */
    protected void messageReceived(ChannelHandlerContext arg0, SerializeObject arg1)
            throws Exception {

    	 SerializeObject  serializeObject=ScanningClass.concurrentHashMap.get(arg1.getUuid());
    	 String notify=serializeObject.getUuid();
    	 //设置返回值，然后唤醒
    	 ScanningClass.concurrentHashMap.put(notify, arg1);
    	 synchronized (notify) {
    		notify.notify();
    	 }
    }

    /*
     * 覆盖channelActive 方法在channel被启用的时候触发（在建立连接的时候）
     * 覆盖了 channelActive() 事件处理方法。服务端监听到客户端活动
     */
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.channelActive(ctx);
        System.out.println(ctx);
        System.out.println("建立连接1");
    }

    /*
     * (non-Javadoc)
     * 覆盖了 handlerAdded() 事件处理方法。
     * 每当从服务端收到新的客户端连接时
     */
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.handlerAdded(ctx);
        arrAy[0]=ctx;
        System.out.println("建立连接2");
    }

    /*
     * (non-Javadoc)
     * .覆盖了 handlerRemoved() 事件处理方法。
     * 每当从服务端收到客户端断开时
     */
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        super.handlerRemoved(ctx);
        System.out.println("断开连接");
    }

    /*
     * exceptionCaught() 事件处理方法是当出现 Throwable 对象才会被调用，
     * 即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时。
     * 在大部分情况下，捕获的异常应该被记录下来并且把关联的 channel 给关闭掉。
     * 然而这个方法的处理方式会在遇到不同异常的情况下有不同的实现，
     * 比如你可能想在关闭连接之前发送一个错误码的响应消息。
     */
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        // TODO Auto-generated method stub
        super.exceptionCaught(ctx, cause);
        System.out.println("发送异常");
    }
	
	
/* // TODO Auto-generated method stub
	 System.out.println("接收消息客户端消息"+arg1);
	 SerializeObject  serialize = JSON.parseObject(arg1, SerializeObject.class);
	 SerializeObject  serializeObject=ScanningClass.concurrentHashMap.get(serialize.getUuid());
	 String notify=serializeObject.getUuid();
	 //设置返回值，然后唤醒
	 ScanningClass.concurrentHashMap.put(notify, serialize);
	 synchronized (notify) {
		notify.notify();
	 }*/
}
