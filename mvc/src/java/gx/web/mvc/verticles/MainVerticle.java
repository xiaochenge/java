/*package gx.web.mvc.verticles;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MainVerticle extends AbstractVerticle {


	
	@Override
	public void start() throws Exception {
		vertx.<ApplicationContext>executeBlocking(blockingCodeHandler -> {
			ApplicationContext act = new ClassPathXmlApplicationContext("spring-webmvc-context.xml");
			blockingCodeHandler.complete(act);
		}, resultHandler -> {
			ApplicationContext act = resultHandler.result();
			if(null == act) {
				System.out.println("spring 启动失败");
			} else {
				vertx.deployVerticle(new HttpServerVerticle(act));
				System.out.println("spring 启动成功");
			}
			
			
		});
	}
	 public void stop(Future<Void> stopFuture) throws Exception {
			  System.out.println("服务器关闭了");
	 }
	
}
*/