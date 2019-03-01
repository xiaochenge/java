package gx.web.mvc.verticles;/*package gx.web.mvc.http;

import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSON;

import gx.common.entity.ResponseBody;
import gx.common.entity.user.User;
import gx.common.enums.ResponseStateEnums;
import gx.common.utils.EmptyCheck;
import gx.web.service.UserService;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.file.FileSystem;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;


public class DeleteFileHandler  implements Handler<RoutingContext>{
	
	private ApplicationContext atc;
	
	public DeleteFileHandler() {
		super();
	}

	public DeleteFileHandler(ApplicationContext atc) {
		super();
		this.atc = atc;

	}

	@Override
	public void handle(RoutingContext event) {
		ResponseBody responseBody=null;
		Vertx vertx = event.vertx();
		FileSystem fileSystem = vertx.fileSystem();
		HttpServerResponse response = event.response();
		try {
		String params = event.getBodyAsString();
		User user=JSON.parseObject(params, User.class);
		if(EmptyCheck.isEmptyObject(user.getPortrait())) {
		responseBody =new ResponseBody(ResponseStateEnums.success);
		response.end(JSON.toJSONString(responseBody));
		return;
		}
		fileSystem.delete(user.getPortrait(), handle ->{
			ResponseBody body=null;
			if(handle.succeeded()) {
				UserService bean = atc.getBean("userService",UserService.class);
				User selectByPrimaryKey = bean.selectByPrimaryKey(user.getId());
				if(!EmptyCheck.isEmptyObject(selectByPrimaryKey)) {
					User u=new User();
					u.setId(user.getId());
					u.setPortrait("");
				   bean.updateByPrimaryKeySelective(u);
				body =new ResponseBody(ResponseStateEnums.success);
				}
				response.end(JSON.toJSONString(body));
			}else {
				body =new ResponseBody(ResponseStateEnums.errer,"系统错误");
				response.end(JSON.toJSONString(body));
			}
		});
		}catch (Exception e) {
			responseBody=new ResponseBody(ResponseStateEnums.errer,"系统错误");
			response.end(JSON.toJSONString(responseBody));
		}
	}
	
	public ApplicationContext getAtc() {
		return atc;
	}

	public void setAtc(ApplicationContext atc) {
		this.atc = atc;
	}
}
*/