package gx.common.enums;

public enum ErrerEnums {
	
	success(200, "操作成功！"),
	errer(300, "操作成功！");
	
	
	
	private Integer status;
	private String msg;
	
	private ErrerEnums(Integer status, String msg) {
		this.status=status;
		this.msg=msg;
	}
	
	private ErrerEnums() {
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
