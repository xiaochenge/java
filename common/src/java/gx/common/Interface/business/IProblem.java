package gx.common.Interface.business;

import gx.common.entity.PageBean;
import gx.common.entity.webproject.Problem;
import gx.common.entity.webproject.Problemreply;
import gx.common.error.BaseRuntimeException;

import java.util.List;

public interface IProblem {
	/**
	 * 保存发帖
	 * @param problem
	 * @return
	 */
	 Long saveProblem(Problem problem);
	/**
	 * 按条件查询发帖
	 * @param problem
	 * @return
	 */
	 List<Problem> selectSelective(Problem problem);
	/**
	 * 按条件分页查询
	 * @param problem
	 * @return
	 */
	 PageBean<Problem> selectSelectivePage(Problem problem);
	
	/**
	 * 保存帖子的回复
	 * @param problemreply
	 * @return
	 */
	 Long saveProblemreply(Problemreply problemreply);
	/**
	 * 查询主帖和下面一级的回复
	 * @param problemId
	 * @return
	 */
	 Problem selectThemeReply(Long problemId);
	
	/**
	 * 按条件更新帖子
	 * @param problem
	 */
	 Long updateProblemMapper(Problem problem);
	
	/**
	 * 楼主采纳某条回复
	 * @param problemreply
	 * @return
	 */
	 Long updateProblemAndReply(Problemreply problemreply)throws BaseRuntimeException;
	
	/**
	 * 点赞某条回复
	 * @param problemreplyId
	 * @return
	 */
	 Long SupportReply(Long problemreplyId);
	
	
    /**
     * 查询与指定用户右管的帖子
     * @param problem
     * @return
     */
	PageBean<Problem> findMyProblemAndReply(Problem problem);

}
