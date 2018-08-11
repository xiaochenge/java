package gx.service.user;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gx.common.Interface.user.IUser;
import gx.common.entity.user.User;
import gx.common.utils.EmptyCheck;
import gx.mysql.dao.user.UserMapper;
@Service("iUser")
public class UserImpl implements IUser{
	@Autowired
	private UserMapper userMapper;
	@Override
	public int deleteByPrimaryKey(Long id)   {
		int deleteByPrimaryKey = userMapper.deleteByPrimaryKey(id);
		return deleteByPrimaryKey;
	}

	@Override
	public User insertSelective(User record) {
		User user=new User();
		user.setLoginname(record.getLoginname());
		List<User> selectSelective = userMapper.selectSelective(user);
		if(!EmptyCheck.isEmptyList(selectSelective) && selectSelective.get(0).getId() != record.getId())
		return null;
		if(EmptyCheck.isEmptyObject(record.getId())) {
		user.setCreationtime(new Date().getTime());
		userMapper.insertSelective(record);
		}else {
		userMapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	
	@Override
	public User selectByPrimaryKey(Long id)   {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User record)  {
		return userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<User> selectSelective(User record)  {
		return userMapper.selectSelective(record);
	}

}
