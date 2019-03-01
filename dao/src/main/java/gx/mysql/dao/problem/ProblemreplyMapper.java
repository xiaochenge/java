package gx.mysql.dao.problem;

import gx.common.entity.webproject.Problemreply;

import java.util.List;

public interface ProblemreplyMapper {
	
	Problemreply selectId(Long id);
	
	Long deleteByPrimaryKey(Long id);

    Long insertSelective(Problemreply problem);
    
    Long updateByPrimaryKeySelective(Problemreply problem);
    
    List<Problemreply> selectSelective(Problemreply problem);
    
    Integer count(Problemreply problem);
    

}