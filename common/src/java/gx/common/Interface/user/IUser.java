package gx.common.Interface.user;

import java.util.List;

import gx.common.entity.user.User;
import gx.common.error.BaseRuntimeException;

public interface IUser {
	/**
	 * 根据主键删除user表信息
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Long id) ;
    
    /**
     * 按条件注册用户
     * 新增用户,邮箱，手机,登陆账号不可重复,插入前会先查询
     * @param record
     * @return
     */
    User insertSelective(User record);
    
    /**
     * 根据主键查询用户
     * @param id
     * @return
     */
    User selectByPrimaryKey(Long id) ;
    /**
     * 按条件更新用户
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record) ;
    /**
     * 按条件查询用户
     * @param record
     * @return
     */
    List<User> selectSelective(User record) ;
}
