package gx.service.business;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import gx.common.Interface.business.IProblem;
import gx.common.entity.PageBean;
import gx.common.entity.webproject.Problem;
import gx.common.entity.webproject.Problemreply;
import gx.common.enums.AdoptState;
import gx.common.enums.Problemstate;
import gx.common.enums.ResponseStateEnums;
import gx.common.enums.State;
import gx.common.error.BaseRuntimeException;
import gx.common.utils.EmptyCheck;
import gx.common.utils.PageHelperUtil;
import gx.mysql.dao.problem.ProblemMapper;
import gx.mysql.dao.problem.ProblemreplyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Slf4j
@Service("iProblem")
public class ProblemImpl implements IProblem{
	
	/**
	 * 发帖相关
	 */
	@Autowired
	private ProblemMapper problemMapper;
	/**
	 * 回复帖子相关
	 */
	@Autowired
	private ProblemreplyMapper problemreplyMapper;
	
	/**
	 * 保存发帖
	 */
	@Override
	public Long saveProblem(Problem problem) {
		log.info("保存发帖problem->{}", JSONObject.toJSONString(problem));
		problemMapper.insertSelective(problem);
		return problem.getId();
	}
	
	/**
	 * 按条件查询主帖
	 */
	@Override
	public List<Problem> selectSelective(Problem problem) {
		log.info("按条件查询主帖problem->{}",JSONObject.toJSONString(problem));
		List<Problem> selectSelective = problemMapper.selectSelective(problem);
		return selectSelective;
	}
	/**
	 * 分页查询主帖子
	 */
	@Override
	public PageBean<Problem> selectSelectivePage(Problem problem) {
		log.info("分页查询主帖problem->{}",JSONObject.toJSONString(problem));
		PageBean<Problem> pages = PageHelperUtil.getPages(problem.getPage(), problem.getLimit(), problem, problemMapper::selectSelective);
		return pages;
	}
	/**
	 * 保存回复
	 */
	public Long saveProblemreply(Problemreply problemreply) {
		log.info("保存帖子回复信息->{}",problemreply);
		problemreplyMapper.insertSelective(problemreply);
		return problemreply.getId();
	}
	/**
	 * 根据主帖查询主帖和相关的回复
	 */
	public Problem selectThemeReply(Long problemId) {
		Problem selectId = problemMapper.selectId(problemId);
		log.info("保根据主贴id查询相关回复信息->{}",JSONObject.toJSONString(selectId));
		if(selectId==null)
		return selectId;
		selectId.setViewingtimes(selectId.getViewingtimes()==null?1:selectId.getViewingtimes()+1);
		problemMapper.updateByPrimaryKeySelective(selectId);
		Problemreply problemreply = Problemreply.builder().state(State.enabled).problemId(problemId).build();
		List<Problemreply> selectSelective = problemreplyMapper.selectSelective(problemreply);
		for (Problemreply pro : selectSelective) {
			try {
				pro.setShowContent(new String(pro.getContent(),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				log.error("发帖内容二进制转成字符串失败,回复贴id->{}",pro.getId());
			}
			pro.setContent(null);
		}
		selectId.setProblemreplys(ProblemreplyTree(selectSelective));
		return selectId;
	}
	
	/**
	 * 采纳主帖下的某条回复
	 */
	public Long updateProblemMapper(Problem problem) {
		log.info("帖主采纳某条回复->{}",problem);
		Long l=problemMapper.updateByPrimaryKeySelective(problem);
		return l;
	}

	@Override
	@Transactional
	public Long updateProblemAndReply(Problemreply problemreply) throws BaseRuntimeException {
		Problem roblem1=new Problem();
		roblem1.setCreator(problemreply.getProblemUserId());
		roblem1.setId(problemreply.getProblemId());
		List<Problem> selectSelective = problemMapper.selectSelective(roblem1);
		if(EmptyCheck.isEmptyList(selectSelective)){
		throw new BaseRuntimeException(ResponseStateEnums.errer);
		}
		Problem listIndex = EmptyCheck.getListIndex(selectSelective, 0, "未知错误");
		listIndex.setProblemstate(Problemstate.resolved);
		Long updateProblemMapper = problemMapper.updateByPrimaryKeySelective(listIndex);//主帖设置已解决
		problemreply.setAdoptState(AdoptState.tAdopted);
		Long updateByPrimaryKeySelective = problemreplyMapper.updateByPrimaryKeySelective(problemreply);//设置回复为已解决
		if(updateByPrimaryKeySelective!=1){
		throw new BaseRuntimeException(ResponseStateEnums.errer);
		}
		return updateByPrimaryKeySelective+updateProblemMapper;
	}
	
	public Long SupportReply(Long id) {
		Problemreply selectId = problemreplyMapper.selectId(id);
		if(EmptyCheck.isEmptyObject(selectId))
		throw new BaseRuntimeException(ResponseStateEnums.errer);
		Problemreply select = new Problemreply();
		select.setId(id);
		select.setSupportNumber(selectId.getSupportNumber()==null?0:selectId.getSupportNumber()+1);
		Long updateByPrimaryKeySelective = problemreplyMapper.updateByPrimaryKeySelective(select);
		return updateByPrimaryKeySelective;
	}

	@Override
	public PageBean<Problem> findMyProblemAndReply(Problem problem) {
		PageBean pageBean=null;
		try {
		PageHelper.startPage(problem.getPage(), problem.getLimit());
		List<Problem> selectSelective = problemMapper.findMyProblemAndReply(problem);
		for (Problem p : selectSelective) {
			if(!EmptyCheck.isEmptyObject(p.getContent()))
			p.setShowContent(new String(p.getContent(),"UTF-8"));
			p.setContent(null);
		}
			pageBean = new PageBean(selectSelective);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return pageBean;
	}
	
	/**
	 * 将查询出来的数据以树结构返回
	 * @param list
	 * @return
	 */
	private List<Problemreply> ProblemreplyTree(List<Problemreply> list){
		
		Map<Long,Problemreply> mapKey=new HashMap<>();
		List<Problemreply> List=new ArrayList<>();
		for (Problemreply problemreply : list) {
			if(problemreply.getParent() == null) {
				mapKey.put(problemreply.getId(), problemreply);
			}else {
				List.add( problemreply);
			}
		}
		List<Problemreply> returnList=new ArrayList<>();
		for (Problemreply pro:List) {
			Problemreply problemreply2 = mapKey.get(pro.getParent());
			if(problemreply2.getProblemreplyList()==null)
			problemreply2.setProblemreplyList(new ArrayList<>());
			problemreply2.getProblemreplyList().add(pro);
		}
			
		Iterator<Problemreply> it = mapKey.values().iterator();
		while (it.hasNext()) 
		returnList.add(it.next());
		return returnList;
	
	}
}
