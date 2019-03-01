package gx.web.mvc.http;

import gx.common.entity.PageBean;
import gx.common.entity.ResponseBody;
import gx.common.entity.webproject.Problem;
import gx.common.entity.webproject.Problemreply;
import gx.common.enums.Problemstate;
import gx.common.enums.ResponseStateEnums;
import gx.common.enums.State;
import gx.common.error.BaseRuntimeException;
import gx.common.utils.EmptyCheck;
import gx.web.service.ProblemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@Slf4j
@RestController
@RequestMapping("/iProblem")
public class ProblemController {
	
	@Autowired
	private ProblemService problemService;

	/**
	 * 插入帖子
	 * @param people
	 * @return
	 */
	@RequestMapping("/insert/Problem")
	public ResponseBody iProblem(@RequestBody Problem people) {
		ResponseBody responseBody=null;
		try {
		people.setProblemstate(Problemstate.Unsolved);
		people.setState(State.enabled);
		Long insertProblem = problemService.insertProblem(people);
		responseBody=new ResponseBody(insertProblem,ResponseStateEnums.success);
		}catch (BaseRuntimeException e) {
			responseBody=new ResponseBody(e);
		}catch (Exception e) {
			log.error("保存帖子发生异常 people->{}",e);
			responseBody=new ResponseBody(ResponseStateEnums.errer,"未知错误");
		}
		return responseBody;
	}
	
	/**
	 * 查询所有帖子
	 * @param people
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/selectAll/Problem")
	public ResponseBody selectAll(@RequestBody Problem people) {
		ResponseBody responseBody=null;
		try {
		people.setState(State.enabled);
		PageBean<Problem> selectSelectivePage = problemService.selectSelectivePage(people);
		for (Problem problem : selectSelectivePage.getList()) {
			problem.setShowContent(new String(problem.getContent(),"UTF-8"));
			problem.setContent(null);
		}
		responseBody=new ResponseBody(selectSelectivePage,ResponseStateEnums.success);
		}catch (BaseRuntimeException e) {
			responseBody=new ResponseBody(e);
		}catch (Exception e) {
			log.error("查询所有帖子发生异常 people->{}",e);
			responseBody=new ResponseBody(ResponseStateEnums.errer,"未知错误");
		}
		return responseBody;
	}
	
	
	/**SupportReply
	 * 根据主帖主键查询详细信息
	 * @param people
	 * @return
	 * @throws
	 */
	@RequestMapping("/Problem/findByProblemId")
	public ResponseBody findByProblemId(@RequestBody Problem people)  {
		ResponseBody responseBody=null;
		try {
		Problem peopleList = problemService.selectThemeReply(people.getId());
		if(peopleList!=null) {
			peopleList.setShowContent(new String(peopleList.getContent(),"UTF-8"));
			peopleList.setContent(null);
		}
		responseBody=new ResponseBody(peopleList,ResponseStateEnums.success);
		}catch (BaseRuntimeException e) {
			responseBody=new ResponseBody(e);
		}catch (Exception e) {
			log.error("查询指定帖子和回复发生异常 people->{}",e);
			responseBody=new ResponseBody(ResponseStateEnums.errer,"未知错误");
		}
		return responseBody;
		
		
	}
	/**
	 * 楼主采纳回复
	 * @param problemreply
	 * @return
	 */
	@RequestMapping("/Problem/saveReplyAdopt")
	public ResponseBody saveReplyAdopt(@RequestBody Problemreply problemreply) {
		ResponseBody responseBody=null;
		try {
		if(EmptyCheck.isEmptyObject(problemreply))
		throw new BaseRuntimeException(ResponseStateEnums.errer,"非法请求");
		Long saveProblemreply = problemService.updateProblem(problemreply);
		responseBody=new ResponseBody(saveProblemreply,ResponseStateEnums.success);
		}catch (BaseRuntimeException e) {
			responseBody=new ResponseBody(e);
		}catch (Exception e) {
			responseBody=new ResponseBody(ResponseStateEnums.errer,"未知错误");
		}
		return responseBody;
	}
	
	/**
	 * 保存帖子的回复
	 * @param problemreply
	 * @return
	 */
	@RequestMapping("/Problem/saveProblemreply")
	public ResponseBody saveProblemreply(@RequestBody Problemreply problemreply) {
		ResponseBody responseBody=null;
		try {
		Long	saveProblemreply = problemService.saveProblemreply(problemreply);
		responseBody=new ResponseBody(saveProblemreply,ResponseStateEnums.success);
		} catch (BaseRuntimeException e) {
			responseBody=new ResponseBody(e);
			e.printStackTrace();
		}
		return responseBody;
	}
	
	/**
	 * 点赞某条回复
	 * @param problemreply
	 * @return
	 */
	@RequestMapping("/Problem/SupportReply")
	public ResponseBody SupportReply(@RequestBody Problemreply problemreply) {
		ResponseBody responseBody=null;
		try {
		problemService.SupportReply(problemreply.getId());
		responseBody=new ResponseBody(ResponseStateEnums.success);
		} catch (BaseRuntimeException e) {
			responseBody=new ResponseBody(e);
			e.printStackTrace();
		}
		return responseBody;
	}
	
	/**
	 * 用户中心分页查询与我有关的帖子
	 * @param problem
	 * @return
	 */
	@RequestMapping("/Problem/findMyProblemAndReply")
	public ResponseBody findMyProblemAndReply(@RequestBody Problem problem) {
		ResponseBody responseBody=null;
		try {
		PageBean<Problem> findMyProblemAndReply = problemService.findMyProblemAndReply(problem);
		responseBody=new ResponseBody(findMyProblemAndReply,ResponseStateEnums.success);
		} catch (BaseRuntimeException e) {
			responseBody=new ResponseBody(e);
			e.printStackTrace();
		}
		return responseBody;
	}
}
