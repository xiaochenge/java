/*package gx.web.mvc.verticles;

import java.lang.reflect.Method;

import com.alibaba.fastjson.JSON;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

*//**
 *  此对象作为超类,不可继承
 * @author 42179
 *
 *//*
public final class Common implements Handler<RoutingContext> {
	
	private Object obj;
	private Method method;
	public Common(Object obj ,Method method) {
		this.obj=obj;
		this.method=method;
	}
	
	@Override
	public void handle(RoutingContext event) {
		HttpServerResponse response = event.response();
		response.putHeader("Accept", "application/json");
		response.putHeader("Content-Type", "application/json;charset=UTF-8");
		String bodyAsString = event.getBodyAsString();
		 Object invoke=null;
		try {
			
			invoke = method.invoke(obj, bodyAsString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		response.end(JSON.toJSONString(invoke));
	}
}
*/