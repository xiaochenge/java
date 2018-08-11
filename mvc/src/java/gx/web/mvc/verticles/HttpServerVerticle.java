/*package gx.web.mvc.verticles;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;

import gx.common.annotation.HttpRoute;
import gx.common.annotation.injectionBean;
import gx.web.mvc.http.DeleteFileHandler;
import gx.web.mvc.http.FirmwareUpdateFileHandler;
import gx.web.mvc.http.ReadFileHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

public class HttpServerVerticle extends AbstractVerticle {
	
	private ApplicationContext atx;

	public HttpServerVerticle() {
		super();
	}

	public HttpServerVerticle(ApplicationContext atx) {
		super();
		this.atx = atx;
	}

	@Override
	public void start() throws Exception {
		HttpServer httpServer = vertx.createHttpServer();
		Router router = Router.router(vertx);
		router.route().handler(CorsHandler.create("*").allowedHeader("Content-Type").allowedMethod(HttpMethod.GET).allowedMethod(HttpMethod.OPTIONS).allowedMethod(HttpMethod.DELETE));
		BodyHandler bodyHandler = BodyHandler.create();
		router.route().handler(bodyHandler);
		Route updateFile = router.route("/firmware/save").method(HttpMethod.POST).method(HttpMethod.OPTIONS).method(HttpMethod.DELETE);
		updateFile.blockingHandler(new FirmwareUpdateFileHandler(atx));
		
		Route deleteFileHandler = router.route("/deleteFile/user/portrait").method(HttpMethod.POST).method(HttpMethod.OPTIONS).method(HttpMethod.DELETE);
		deleteFileHandler.blockingHandler(new DeleteFileHandler(atx));
		
		Route readFileHandler = router.route("/user/readFileHandler").method(HttpMethod.GET).method(HttpMethod.OPTIONS).method(HttpMethod.DELETE);
		readFileHandler.blockingHandler(new ReadFileHandler());
		
		String path="gx.web.http";
		Text scan = new Text(path);
        List<String> fullyQualifiedClassNameList = scan.getFullyQualifiedClassNameList();
		//遍历map
		for (String string : fullyQualifiedClassNameList) {
			
			Class<?> class1 = Thread.currentThread().getContextClassLoader().loadClass(string);
			//创建该对象
			Object newInstance = class1.newInstance();
			//获取所有方法
			Method[] methods = class1.getMethods();
			//获取所有属性
			Field[] declaredFields = class1.getDeclaredFields();
			//从spring容器中注入属性
			for (Field field : declaredFields) {
				//获取该属性并且判断是否包含注解
				injectionBean annotation = field.getAnnotation(injectionBean.class);
				//该属性上包含指定注解时，需要从容器中获取实力并且赋值
				if(annotation!=null) {
					Object bean=null;
					//如果没有赋值则根据类型到容器中找
					if("".equals(annotation.value())) {
					bean = atx.getBean(field.getType());
					}else {
					bean = atx.getBean(annotation.value());
					}
					field.setAccessible(true);
					//程序执行到此处，该对象所有的属性都已经从容器中获取Bean对象
					field.set(newInstance, bean);
				}
			}
			//遍历所有方法，并且将注解上的路径映射到vert.x中

			for (Method method : methods) {
				HttpRoute annotation = method.getAnnotation(HttpRoute.class);
				//当指定的注解不为null并且不为空字符串，则开始映射
				if(annotation!=null && !"".equals(annotation.value())) {
					//请求路径前缀
					String value1 = class1.getAnnotation(HttpRoute.class).value();
					//请求路径后缀
					String value2 = annotation.value();
					String string2 = new StringBuffer("/").append(value1).append("/"+value2).toString();
					string2=string2.replace("///", "/");
					string2=string2.replace("//", "/");
					Route routeLogin = router.route(string2).method(HttpMethod.GET).method(HttpMethod.POST).method(HttpMethod.OPTIONS).method(HttpMethod.DELETE);
					routeLogin.blockingHandler(new Common(newInstance,method));
				}
			}
		}
		
		int port = 9000;
		
		httpServer.requestHandler(router::accept).listen(port, listenHandler -> {
			if (listenHandler.succeeded()) {
				System.out.println("启动成功，开始监听{}端口"+port);
			} else {
				System.out.println("启动失败");
			}
		});
		
	}

	public ApplicationContext getAtx() {
		return atx;
	}

	public void setAtx(ApplicationContext atx) {
		this.atx = atx;
	}
}
*/