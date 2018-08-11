package gx.web.mvc.http;

import gx.common.entity.ResponseBody;
import gx.common.entity.user.User;
import gx.common.enums.ResponseStateEnums;
import gx.common.error.BaseRuntimeException;
import gx.common.utils.EmptyCheck;
import gx.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * 注册用户
	 * @param jsonArray
	 * @return
	 */
	@RequestMapping("insertUser")
	public Object save(@RequestBody User user) {
		ResponseBody responseBody=null;
		try {
			User insertSelective = userService.insertSelectiveS(user);
			responseBody=new ResponseBody(insertSelective,ResponseStateEnums.success);
		} catch (BaseRuntimeException e) {
			responseBody=new ResponseBody(e);
		} catch (Exception e) {
			responseBody=new ResponseBody(ResponseStateEnums.errer,"未知错误");
		}
		
	return responseBody;
	}
	
	@RequestMapping("loginUser")
	public Object loginUser(@RequestBody User user) {
		ResponseBody responseBody=null;
		try {
			if(EmptyCheck.isEmptyObject(user) || EmptyCheck.isEmptyObject(user.getLoginname(),user.getPassword()))
				throw new BaseRuntimeException(ResponseStateEnums.errer,"用户名或者密码未填写");
			List<User> selectSelective = userService.selectSelective(user);
			User listIndex = EmptyCheck.getListIndex(selectSelective, 0,"账户或者密码错误");
			listIndex.setLoginname(null);
			listIndex.setZfbaccount(null);
			responseBody=new ResponseBody(listIndex,ResponseStateEnums.success);
		} catch (BaseRuntimeException e) {
			responseBody=new ResponseBody(e);
		} catch (Exception e) {
			responseBody=new ResponseBody(ResponseStateEnums.errer,"未知错误");
		}
		return responseBody;
	}
}
