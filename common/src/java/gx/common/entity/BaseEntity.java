package gx.common.entity;

import java.io.Serializable;

public class BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2588530144961079149L;
	public Long id;//主键
	
	private Integer page;//从第0条开始
	
	private Integer limit;//每页显示的记录数
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPage() {
		return this.page==null?null:(this.page-1)*(this.limit);
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return this.limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
}
