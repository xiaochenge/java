package gx.web.service;

import gx.common.Interface.business.IProblem;
import gx.common.entity.PageBean;
import gx.common.entity.webproject.Problem;
import gx.common.entity.webproject.Problemreply;
import gx.common.enums.ResponseStateEnums;
import gx.common.enums.State;
import gx.common.error.BaseRuntimeException;
import gx.common.utils.EmptyCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Slf4j
@Service
public class ProblemService {
	@Autowired
	private IProblem iProblem;
	/**
	 * 插入发帖
	 * @param problem
	 * @return
	 */
	public Long insertProblem(Problem problem) throws BaseRuntimeException{
		problem.setCreateTime(new Date());
		if(EmptyCheck.isEmptyString(problem.getShowContent())){
			throw new BaseRuntimeException(ResponseStateEnums.errer,"发帖内容必须填写");
		}
		problem.setContent(problem.getShowContent().getBytes());
		Long saveProblem = iProblem.saveProblem(problem);
		return saveProblem;
	}
	
	
	/**
	 * 按条件查询发帖
	 * @param problem
	 * @return
	 */
	public List<Problem> selectSelective(Problem problem) throws BaseRuntimeException{
		List<Problem> selectSelective = iProblem.selectSelective(problem);
		return selectSelective;
	}
	
	/**
	 * 按条件分页查询
	 * @param problem
	 * @return
	 */
	public PageBean<Problem> selectSelectivePage(Problem problem) throws BaseRuntimeException{
		PageBean<Problem> selectSelectivePage = iProblem.selectSelectivePage(problem);
		return selectSelectivePage;
	}
	/**
	 * 保存帖子的回复
	 * @param problemreply
	 * @return
	 */
	public Long saveProblemreply(Problemreply problemreply) throws BaseRuntimeException{
		problemreply.setContent(problemreply.getShowContent().getBytes());
		problemreply.setCreateTime(new Date());
		problemreply.setSupportNumber(0);
		problemreply.setState(State.enabled);
		Long insertSelective = iProblem.saveProblemreply(problemreply);
		return insertSelective;
	}
	
	/**
	 * 根据主帖id查询主帖和下面一级的回复
	 * @param problemId
	 * @return
	 */
	public Problem selectThemeReply(Long problemId) throws BaseRuntimeException{
		Problem selectThemeReply = iProblem.selectThemeReply(problemId);
		return selectThemeReply;
	}
	
	
	/**
	 * 采纳某条回复
	 * @param roblem
	 * @return
	 */
	public Long updateProblem(Problemreply roblem) throws BaseRuntimeException{
		
		if(EmptyCheck.isEmptyObject(roblem.getProblemUserId()) && EmptyCheck.isEmptyObject(roblem.getProblemId()) && EmptyCheck.isEmptyObject(roblem.getId()))
		throw new BaseRuntimeException(ResponseStateEnums.errer,"错误的参数");
		if(roblem.getProblemUserId()==roblem.getCreator())
		throw new BaseRuntimeException(ResponseStateEnums.errer,"自己的回复无法采纳");
		Long updateProblemAndReply = iProblem.updateProblemAndReply(roblem);
		
		return updateProblemAndReply;
	}
	
	/**
	 * 攒点某条回复
	 * @param ProblemreplyId
	 * @return
	 */
	public void SupportReply(Long ProblemreplyId) throws BaseRuntimeException{
		if(EmptyCheck.isEmptyObject(ProblemreplyId))
		throw new BaseRuntimeException(ResponseStateEnums.errer,"回复id不能为空");
		iProblem.SupportReply(ProblemreplyId);
	}
	
	
	/**
     * 查询与指定用户右管的帖子
     * @param problem
     * @return
     */
	public PageBean<Problem> findMyProblemAndReply(Problem problem){
		PageBean<Problem> findMyProblemAndReply = iProblem.findMyProblemAndReply(problem);
		return findMyProblemAndReply;
    }
}
