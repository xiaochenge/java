package gx.common.entity.webproject;

import gx.common.entity.BaseEntity;
import gx.common.enums.AdoptState;
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
public class Problemreply extends BaseEntity{


    /**
	 * 
	 */
	private static final long serialVersionUID = -6183923905187912044L;

	private Long problemId;//管理发帖id

    private Long creator;//创建者Id

    private Date createTime;//创建时间

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
}