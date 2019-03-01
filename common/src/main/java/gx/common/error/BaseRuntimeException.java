package gx.common.error;

import gx.common.enums.ResponseStateEnums;
import lombok.Data;

@Data
public class BaseRuntimeException extends RuntimeException{
	
	/**
	 * 
	 */
	public static final long serialVersionUID = -4469085357860079349L;

	private String msg;
	
	private ResponseStateEnums ResponseStateEnums;
	
	public BaseRuntimeException(ResponseStateEnums errerEnums) {
		super(errerEnums.getMsg());
		this.ResponseStateEnums = errerEnums;
		this.setMsg(errerEnums.getMsg());
	}

	public BaseRuntimeException(ResponseStateEnums errerEnums, String message) {
		super(message);
		this.ResponseStateEnums = errerEnums;
		this.setMsg(message);
	}

}
