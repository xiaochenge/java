package gx.common.socket.client;

import gx.common.entity.SerializeObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.java.Log;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * 消息接受处理类
 * @author -琴兽-
 *
 */

@Log
public class HiHandler extends  SimpleChannelInboundHandler<SerializeObject>{

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, SerializeObject msg) {
		Object result=null;
		try {
			log.info("开始执行远程调用，请求参数"+msg.toString());
			Object obj=Client.app.getBean(Class.forName(msg.getClassName()));
			Method  method=null;
			lableB:
			for (Method m : obj.getClass().getMethods()) {
			  if(m.getName().equals(msg.getMethod())) {
				  if(StringUtils.isEmpty(msg.getParas()) && m.getParameterTypes().length ==0) {method=m;break;};
				  if(m.getParameterTypes().length == msg.getParas().length) {
					  String [] paras=new String[m.getParameterTypes().length];
					  for (int i=0;i<m.getParameterTypes().length;i++)paras[i]=m.getParameterTypes()[i].getName();
						for (int j=0;j<paras.length;j++)
						if(!paras[j].equals(msg.getParas()[j])) break;
					  method=m;
					  break lableB;

				}
			  }
			}
			Class<?>[] getTypeParameters = method.getParameterTypes();
			if(getTypeParameters.length==0){
				result=method.invoke(obj);
			}else {
				Object[] s1=(Object[]) msg.getData();
				result=method.invoke(obj,s1);
			}
		} catch (Exception e) {
			log.info("远程调用失败，请求参数:"+msg.toString()+"错误原因"+e.getMessage());
			e.printStackTrace();
			msg.setCode(500);
			msg.setMsg(e.getMessage());
		}
		msg.setCode(200);
		msg.setData(result);
		ctx.writeAndFlush(msg);
	}
	
	
	/**
	* 判断object是否为基本类型
	* @param object
	* @return
	*/
	public boolean isBaseType(Object object) {
	    Class<?> className = object.getClass();
	    if (className.equals(java.lang.Integer.class) ||
	        className.equals(java.lang.Byte.class) ||
	        className.equals(java.lang.Long.class) ||
	        className.equals(java.lang.Double.class) ||
	        className.equals(java.lang.Float.class) ||
	        className.equals(java.lang.Character.class) ||
	        className.equals(java.lang.Short.class) ||
	        className.equals(java.lang.Boolean.class)) {
	        return true;
	    }
	    return false;
	}

}
