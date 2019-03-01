package gx.common.entity;

import gx.common.enums.ResponseStateEnums;
import gx.common.error.BaseRuntimeException;

public class ResponseBody {
	private String msg;//数据说明
	private Object obj;//响应的数据
	private ResponseStateEnums responseState;
	private byte[] binary;
	private Integer status;
	public ResponseBody() {}
	
	public ResponseBody(BaseRuntimeException baseRuntimeException) {
		this.responseState=baseRuntimeException.getResponseStateEnums();
		this.msg=baseRuntimeException.getMsg();
		this.status=responseState.getStatus();
	}
	
	public ResponseBody (ResponseStateEnums responseStateEnums,String msg) {
		this.responseState=responseStateEnums;
		this.msg=msg;
	}
	
	
	public ResponseBody (ResponseStateEnums responseStateEnums) {
		this.responseState=responseStateEnums;
		this.status=responseStateEnums.getStatus();
	}
	
	public ResponseBody(Object obj,String msg,ResponseStateEnums responseState) {
		this.msg=msg;
		this.obj=obj;
		this.responseState=responseState;
	}
	public ResponseBody(Object obj,ResponseStateEnums responseState) {
		this.obj=obj;
		this.responseState=responseState;
		this.msg=responseState.getMsg();
		this.status=responseState.getStatus();
	}
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public ResponseStateEnums getResponseState() {
		return responseState;
	}
	public void setResponseState(ResponseStateEnums responseState) {
		this.responseState = responseState;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public byte[] getBinary() {
		return binary;
	}

	public void setBinary(byte[] binary) {
		this.binary = binary;
	}
}
