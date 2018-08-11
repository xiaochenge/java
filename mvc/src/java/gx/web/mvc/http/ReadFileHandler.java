/*package gx.web.mvc.http;

import java.io.File;

import gx.common.utils.EmptyCheck;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;


public class ReadFileHandler  implements Handler<RoutingContext>{


	@Override
	public void handle(RoutingContext event) {
		HttpServerResponse response = event.response();
		String params = event.request().getParam("portrait");
		if(!EmptyCheck.isEmptyObject(params)) {
			File file=new File(params);
			if(file.exists()) {
			response.sendFile(params);
			}else {
			response.close();
			}
		}else {
			response.close();
		}
	}

}
*/