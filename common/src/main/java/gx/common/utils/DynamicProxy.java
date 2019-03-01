package gx.common.utils;

import gx.common.entity.SerializeObject;
import gx.common.socket.server.HelloHandler;
import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler{

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		 //如果传进来是一个已实现的具体类（略过此逻辑)
       if (Object.class.equals(method.getDeclaringClass())) {
           try {  
               return method.invoke(this, args);  
           } catch (Throwable t) {  
               t.printStackTrace();  
           }  
         //如果传进来的是一个接口（核心)
        } 
       
         return run(method.getDeclaringClass().getName(),method,args);  

	}

    	/**
	    * 实现接口的核心方法,通过netty调用远程接口
	    * @param method
	    * @param args
	    * @return
	    */
	   private  Object run(String proxy,Method method, Object args){
		   Class<?>[] parameterTypes = method.getParameterTypes();
		   String [] paras=new String[parameterTypes.length];
		   for (int i=0;i<parameterTypes.length;i++) {
			   paras[i]=parameterTypes[i].getName();
		   }
		   SerializeObject serializeObject=new SerializeObject(proxy,method.getName(),args,paras);
		   ChannelHandlerContext channel=HelloHandler.arrAy[0];
		   //准备发送.保存uuid，用于得到消息时解锁
		   ScanningClass.concurrentHashMap.put(serializeObject.getUuid(), serializeObject);
		   channel.writeAndFlush(serializeObject);
		   synchronized (serializeObject.getUuid()) {
			   try {
				serializeObject.getUuid().wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		   SerializeObject serialize=ScanningClass.concurrentHashMap.get(serializeObject.getUuid());
		   Object obj=serialize.getData();
	       return obj;
	   } 
	   
	  /* *//**
	   * 
	   * @param m
	   *            要序列化的对象
	   * @return byte[] 序列化后的字节数组
	   *//*
	   private byte[] tobyte(SerializeObject obj) {
	   // 输出流ByteArrayOutputStream是跟字节交互的
	   ByteArrayOutputStream baos = new ByteArrayOutputStream();
	   // 输出流 ObjectOutputStream 是跟对象交互的 可以吧对象转换成ByteArrayOutputStream输出流
	   ObjectOutputStream oos;
	   byte[] bytes = null;
	   try {
	   oos = new ObjectOutputStream(baos);
	   oos.writeObject(obj);
	   bytes = baos.toByteArray();
	   oos.close();
	   } catch (Exception e) {
	   e.printStackTrace();
	   }
	   return bytes;
	   }
	   	
	   
	   
	   *//**
	   * 
	   * 把序列化的字节数组 返序列化成对象
	   * 
	   * @param byte[] 序列化后的字节数组
	   * 
	   * @return Model
	   *//*
	      //序列化传输对象
		   System.out.println("准备开始调用远程接口");
		   Class<?>[] parameterTypes = method.getParameterTypes();
		   String [] paras=new String[parameterTypes.length];
		   for (int i=0;i<parameterTypes.length;i++) {
			   paras[i]=parameterTypes[i].getName();
		   }
		   SerializeObject serializeObject=new SerializeObject(proxy,method.getName(),args,paras);
		   ChannelHandlerContext channel=HelloHandler.arrAy[0];
		   String str=JSON.toJSONString(serializeObject);
		   //准备发送.保存uuid，用于得到消息时解锁
		   ScanningClass.concurrentHashMap.put(serializeObject.getUuid(), serializeObject);
		   System.out.println("发送请求");
		   channel.writeAndFlush(str);
		   synchronized (serializeObject.getUuid()) {
			   try {
				   System.out.println("加锁等待");
				serializeObject.getUuid().wait();
				System.out.println("释放锁");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		   SerializeObject serialize=ScanningClass.concurrentHashMap.get(serializeObject.getUuid());
		   System.out.println("观察此对象"+serialize);
		   Object obj=serialize.getData();
	   */
}
