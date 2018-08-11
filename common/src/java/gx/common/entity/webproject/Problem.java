package gx.common.entity.webproject;

import java.util.Arrays;
import java.util.List;

import gx.common.entity.BaseEntity;
import gx.common.enums.ProblemType;
import gx.common.enums.Problemstate;
import gx.common.enums.State;

public class Problem  extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5727870703057378698L;
	/**
     * 标题
     */
    private String title;
    /**
     * 创建时间
     */
    private Long creationtime;
    /**
     * 更新时间
     */
    private Long updatetime;
    /**
     * 状态
     */
    private State state;
    /**
     * 创建者
     */
    private Long creator;
    /**
     * 金额
     */
    private Double money;
    /**
     * 查看次数
     */
    private Integer viewingtimes;
    /**
     * 是否解决
     */
    private Problemstate problemstate;
    /**
     * 发帖内容
     */
    private byte[] content;
    /**
     * 帖子类型
     */
    private ProblemType problemType;
    
    
    
    /**
     * 以下字段不做数据库映射，仅作查询条件或者展示使用
     */
    
    /**
     * 中文展示发帖内容
     */
    private String showContent;
    
    /**
     * 主题帖下面的回复
     */
    private List<Problemreply>  problemreplys;
    
    /**
     * 评论次数
     * @return
     */
    private Integer numberComments;
    /**
     * 创建者名称
     * @return
     */
    private String creatorName;
    
    public Integer getNumberComments() {
		return numberComments;
	}

	public void setNumberComments(Integer numberComments) {
		this.numberComments = numberComments;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Long creationtime) {
        this.creationtime = creationtime;
    }

    public Long getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getViewingtimes() {
        return viewingtimes;
    }

    public void setViewingtimes(Integer viewingtimes) {
        this.viewingtimes = viewingtimes;
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

	public Problemstate getProblemstate() {
		return problemstate;
	}

	public void setProblemstate(Problemstate problemstate) {
		this.problemstate = problemstate;
	}

	public List<Problemreply> getProblemreplys() {
		return problemreplys;
	}

	public void setProblemreplys(List<Problemreply> problemreplys) {
		this.problemreplys = problemreplys;
	}

	public ProblemType getProblemType() {
		return problemType;
	}

	public void setProblemType(ProblemType problemType) {
		this.problemType = problemType;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	@Override
	public String toString() {
		return "Problem [title=" + title + ", creationtime=" + creationtime + ", updatetime=" + updatetime + ", state="
				+ state + ", creator=" + creator + ", money=" + money + ", viewingtimes=" + viewingtimes
				+ ", problemstate=" + problemstate + ", content=" + Arrays.toString(content) + ", problemType="
				+ problemType + ", showContent=" + showContent + ", problemreplys=" + problemreplys
				+ ", numberComments=" + numberComments + ", creatorName=" + creatorName + "]";
	}
    
}