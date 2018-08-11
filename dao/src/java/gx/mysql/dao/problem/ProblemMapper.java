package gx.mysql.dao.problem;

import java.util.List;

import gx.common.entity.webproject.Problem;

public interface ProblemMapper {
	Problem selectId(Long id);
	
	Long deleteByPrimaryKey(Long id);

    Long insertSelective(Problem problem);
    
    Long updateByPrimaryKeySelective(Problem problem);
    
    List<Problem> selectSelective(Problem problem);
    
    Integer count(Problem problem);

    /**
     * 查询与指定用户有关帖子
     * @param userId
     * @return
     */
    List<Problem> findMyProblemAndReply(Problem problem);
    /**
     * 查询指定用户有关帖子的总数
     * @param problem
     * @return
     */
    Integer findMyProblemAndReplyCount(Problem problem);

}