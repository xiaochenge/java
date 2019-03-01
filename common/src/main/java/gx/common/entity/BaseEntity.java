package gx.common.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class BaseEntity implements Serializable{

	private static final long serialVersionUID = -2588530144961079149L;
	public Long id;//主键
	
	private Integer page;//从第页开始
	
	private Integer limit;//每页显示的记录数

}
