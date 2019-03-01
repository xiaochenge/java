package gx.common.entity.webproject;

import gx.common.entity.BaseEntity;
import gx.common.enums.ProblemType;
import gx.common.enums.Problemstate;
import gx.common.enums.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private Date createTime;
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

}