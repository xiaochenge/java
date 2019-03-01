package gx.mysql.dao.user;

import gx.common.entity.user.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);
    
    List<User> selectSelective(User record);

}