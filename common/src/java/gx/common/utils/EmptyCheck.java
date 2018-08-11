package gx.common.utils;

import java.util.List;

import gx.common.enums.ResponseStateEnums;
import gx.common.error.BaseRuntimeException;

public class EmptyCheck<T> {
	
	/**
	 * 判断对象是否为空
	 * @return
	 */
	public static Boolean isEmptyObject(Object... objects ) {
		for (Object object : objects) {
			if(object == null)
				return true;
		}
		return false;
	}
	
	/**
	 * 判断对象是否为空
	 * @return
	 */
	public static Boolean isEmptyObject(String... str ) {
		for (String object : str) {
			if(object == null || object.isEmpty())
				return true;
		}
		return false;
	}
	
	/**
	 * 判断对象是否为空
	 * @return
	 */
	public static Boolean isEmptyList(List<?> list) {
		if(list == null || list.size()==0)
		return true;
		return false;
	}
	
	/**
	 * 判断对象是否为空
	 * @return 
	 * @return
	 * @throws BaseRuntimeException 
	 */
	public static <T> T getListIndex(List<T> list,int index,String msg) throws BaseRuntimeException {
		Boolean emptyList = isEmptyList(list);
		T t=null;
		if(!emptyList) {
		t = list.get(index);
		}else 
		throw new BaseRuntimeException(ResponseStateEnums.errer,msg);
		return t;
	}
}
