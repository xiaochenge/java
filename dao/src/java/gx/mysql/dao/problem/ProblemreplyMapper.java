package gx.mysql.dao.problem;

import java.util.List;

import gx.common.entity.webproject.Problemreply;

public interface ProblemreplyMapper {
	
	Problemreply selectId(Long id);
	
	Long deleteByPrimaryKey(Long id);

    Long insertSelective(Problemreply problem);
    
    Long updateByPrimaryKeySelective(Problemreply problem);
    
    List<Problemreply> selectSelective(Problemreply problem);
    
    Integer count(Problemreply problem);
    

}