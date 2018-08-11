/*package gx.web.mvc.http;

import java.io.File;
import java.util.Date;
import java.util.Set;

import org.springframework.context.ApplicationContext;

import gx.common.entity.user.User;
import gx.common.utils.EmptyCheck;
import gx.web.service.UserService;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;

*//**
 * 上传固件文件处理
 * 
 * @author chenjf
 *
 *//*
public class FirmwareUpdateFileHandler implements Handler<RoutingContext> {

	private ApplicationContext atc;

	private String path="img/portrait/";
	
	private String[] index=new String[] {"Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M"};
	
	private int i=0;
	
	public FirmwareUpdateFileHandler() {
		super();
		createFolder(index);
	}

	public FirmwareUpdateFileHandler(ApplicationContext atc) {
		super();
		this.atc = atc;
		createFolder(index);
	}

	@Override
	public void handle(RoutingContext ctx) {
		Vertx vertx = ctx.vertx();
		HttpServerResponse response = ctx.response();
		Set<FileUpload> uploads = ctx.fileUploads();
		String param = ctx.request().getParam("id");
		if(param == null) {
			response.end("内部错误，请重新登陆");
			return;
		}
		uploads.stream().forEach(upload -> {
				// 上传的文件名
				// 文件大小
				long fileSize = upload.size();
			
				// 文件上传到的服务器上的实际临时文件名称
				String uploadedFileName = upload.uploadedFileName();
				
				Date dt = new Date();
				String fileIndexName =String.valueOf(dt.getTime());
				
				FileSystem fileSystem = vertx.fileSystem();
				Buffer buffer = fileSystem.readFileBlocking(uploadedFileName);
				if(i==26)i=0;
				String savefileName=index[i]+fileIndexName;
				StringBuffer buff=new StringBuffer();
				buff.append(path);
				buff.append(index[i]+"/");
				buff.append(savefileName);				
				fileSystem.writeFile(buff.toString(),buffer, result -> {
				i++;
				    if(result.succeeded()) {
				    	UserService bean = atc.getBean("userService",UserService.class);
				    	User selectByPrimaryKey = bean.selectByPrimaryKey(Long.valueOf(param));
				    	if(!EmptyCheck.isEmptyObject(selectByPrimaryKey)) {
				    		User User=new User();
				    		User.setId(Long.valueOf(param));
				    		User.setPortrait(buff.toString());
				    		bean.updateByPrimaryKeySelective(User);
				         response.end(buff.toString());
				    	}else {
				    		response.end("内部错误，请重新登陆");
				    	}
				    }else {
				    	response.end("内部错误，请重新登陆");
				    }
				    //删除临时文件
				   fileSystem.deleteBlocking(uploadedFileName);
				   
				});
			});
		
	}

	public ApplicationContext getAtc() {
		return atc;
	}

	public void setAtc(ApplicationContext atc) {
		this.atc = atc;
	}
	
	
	public void createFolder(String... args) {
       for (String string : args) {
    	   File f = new File("img/portrait/"+string);
           // 创建文件夹
           if (!f.exists()) {
               f.mkdirs();
           }
	  }
    }
}
*/