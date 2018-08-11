package gx.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
public class SerializeObject  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1152802509107536768L;

	private List list;

	private String uuid;
	
	private String className;
	
	private String method;
	
	private String[] paras;
	
	private Object data;

	private Integer code;

	private String msg;

	public SerializeObject() {}
	
	public SerializeObject(String className,String method,Object data,String[] paras) {
		this.data=data;
		this.method=method;
		this.className=className;
		this.paras=paras;
		this.uuid=UUID.randomUUID().toString().replaceAll("-", "");
	}

}
