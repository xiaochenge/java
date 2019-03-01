package gx.common.socket.model;

/**
 * 自定义解码器
 * 请求对象
 * @author 42179
 *
 */
public class Request {
	
	
	/**
	 * 请求模块
	 */
	private short module;
	/**
	 * 命令号
	 */
	private short cmd;
	
	/**
	 * 数据部分
	 */
	private byte data;

	public short getModule() {
		return module;
	}

	public void setModule(short module) {
		this.module = module;
	}

	public short getCmd() {
		return cmd;
	}

	public void setCmd(short cmd) {
		this.cmd = cmd;
	}

	public byte getData() {
		return data;
	}

	public void setData(byte data) {
		this.data = data;
	}
	
	
}
