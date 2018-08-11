package gx.common.enums;

/**
 * 响应请求状态
 * @author 42179
 *
 */
public enum ResponseStateEnums {
	
	success(200, "操作成功！"),
	errer(300, "操作失败！");
	
	private Integer status;
	private String msg;
	
	private ResponseStateEnums(Integer status, String msg) {
		this.status=status;
		this.msg=msg;
	}
	
	private ResponseStateEnums() {
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
