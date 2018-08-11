package gx.common.entity.webproject;

import java.util.List;

import gx.common.entity.BaseEntity;
import gx.common.enums.AdoptState;
import gx.common.enums.State;

public class Problemreply extends BaseEntity{


    /**
	 * 
	 */
	private static final long serialVersionUID = -6183923905187912044L;

	private Long problemid;//管理发帖id

    private Long creator;//创建者Id

    private Long creationTime;//创建时间

    private State state;//状态

    private Long parent;//父级id
    
    private AdoptState adoptState;//是否采纳
    
    private byte[] content;//储存主体
    
    private Integer supportNumber;//点赞数量回复以此排序
    
    private Integer top;//置顶
    
    
    
    //以下传输数据使用
    /**
     * 数据传输使用
     */
    private Long problemUserId;//楼主用户id
    
    /**
     * 中文展示发帖内容
     */
    private String showContent;
    /**
     * 创建者名称
     * @return
     */
    private String creatorName;
    
    
    private List<Problemreply> problemreplyList;//遍历自身以树结构返回
    
    
    public Long getProblemUserId() {
		return problemUserId;
	}

	public void setProblemUserId(Long problemUserId) {
		this.problemUserId = problemUserId;
	}

	public Long getProblemid() {
        return problemid;
    }

    public void setProblemid(Long problemid) {
        this.problemid = problemid;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state ;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

	public String getShowContent() {
		return showContent;
	}

	public void setShowContent(String showContent) {
		this.showContent = showContent;
	}

	public AdoptState getAdoptState() {
		return adoptState;
	}

	public void setAdoptState(AdoptState adoptState) {
		this.adoptState = adoptState;
	}

	public Integer getSupportNumber() {
		return supportNumber;
	}

	public void setSupportNumber(Integer supportNumber) {
		this.supportNumber = supportNumber;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public List<Problemreply> getProblemreplyList() {
		return problemreplyList;
	}

	public void setProblemreplyList(List<Problemreply> problemreplyList) {
		this.problemreplyList = problemreplyList;
	}
    
}