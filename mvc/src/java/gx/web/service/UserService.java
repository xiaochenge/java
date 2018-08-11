package gx.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import gx.common.Interface.user.IUser;
import gx.common.annotation.InitProxyObject;
import gx.common.entity.user.User;
import gx.common.enums.DetailedRegister;
import gx.common.enums.ResponseStateEnums;
import gx.common.error.BaseException;
import gx.common.error.BaseRuntimeException;
import gx.common.utils.EmptyCheck;


@Service
public class UserService {
	@InitProxyObject
	private IUser iUser;
	
	
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 * @throws BaseException 
	 * @throws BaseRuntimeException 
	 */
	public int deleteByPrimaryKey(Long id) throws BaseRuntimeException {
		int deleteByPrimaryKey = iUser.deleteByPrimaryKey(id);
		return deleteByPrimaryKey;
	}

	/**
	 * 按条件新增
	 * @param record
	 * @return
	 * @throws BaseRuntimeException 
	 * @throws BaseException 
	 */
	public User insertSelectiveS(User record) throws BaseRuntimeException  {
		//校验用户信息
		if(EmptyCheck.isEmptyObject(record))
		throw new BaseRuntimeException(ResponseStateEnums.errer,"非法请求");
		
		if(EmptyCheck.isEmptyObject(record.getId())) {
		CheckUserRegister(record);
		}else {
		CheckUserUpdate(record);
		}
		User insertSelective=null;
		try {
			insertSelective= iUser.insertSelective(record);
			if(insertSelective==null)
			throw new BaseRuntimeException(ResponseStateEnums.errer,"该账户已经存在");
		} catch (Exception e) {
			throw new BaseRuntimeException(ResponseStateEnums.errer,e.getMessage());
		}
		
		
		return insertSelective;
	}
	
	/**
	 * 校验用户注册信息
	 * @return
	 * @throws BaseRuntimeException 
	 */
	private void CheckUserRegister(User record) throws BaseRuntimeException  {
		
		
		if(EmptyCheck.isEmptyObject(record.getLoginname()))
		throw new BaseRuntimeException(ResponseStateEnums.errer,"必须填写登陆账号");
		
		if(EmptyCheck.isEmptyObject(record.getPassword()))
		throw new BaseRuntimeException(ResponseStateEnums.errer,"必须填写登陆密码");
		
		if(record.getDetailedRegister()!=DetailedRegister.detailed)
		return;
		if(EmptyCheck.isEmptyObject(record.getZfbaccount()))
		throw new BaseRuntimeException(ResponseStateEnums.errer,"必须填写收款账号");
	}
	
	
	/**
	 * 修改个人信息校验
	 * @return
	 * @throws BaseRuntimeException 
	 */
	private void CheckUserUpdate(User record) throws BaseRuntimeException  {
		
		if(!EmptyCheck.isEmptyObject(record.getLoginname()))
		throw new BaseRuntimeException(ResponseStateEnums.errer,"请勿捣乱，暂时不支持修改登陆账号");
		
		if(EmptyCheck.isEmptyObject(record.getPassword()))
		throw new BaseRuntimeException(ResponseStateEnums.errer,"必须填写登陆密码");
		
		if(!EmptyCheck.isEmptyObject(record.getZfbaccount()))
		throw new BaseRuntimeException(ResponseStateEnums.errer,"请勿捣乱，暂时不支持修改收款账号");
	}
	

	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 * @throws BaseException 
	 */
	public User selectByPrimaryKey(Long id) throws BaseRuntimeException {
		return iUser.selectByPrimaryKey(id);
	}

	/**
	 * 根据条件更新
	 * @param record
	 * @return
	 * @throws BaseException 
	 */
	public int updateByPrimaryKeySelective(User record) throws BaseRuntimeException {
		return iUser.updateByPrimaryKeySelective(record);
	}

	/**
	 * 根据条件查询
	 * @param record
	 * @return
	 * @throws BaseException 
	 */
	public List<User> selectSelective(User record) throws BaseRuntimeException {
		return iUser.selectSelective(record);
	}
}
