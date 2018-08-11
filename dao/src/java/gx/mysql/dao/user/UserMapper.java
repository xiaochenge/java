package gx.mysql.dao.user;

import java.util.List;

import gx.common.entity.user.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);
    
    List<User> selectSelective(User record);

}