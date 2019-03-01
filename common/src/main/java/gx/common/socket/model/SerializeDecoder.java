package gx.common.socket.model;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 请求编码类型
 * @author 42179
 *
 */
public class SerializeDecoder extends ByteToMessageDecoder{
	
	@Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        ByteBufToBytes read = new ByteBufToBytes();
        Object obj = ByteObjConverter.byteToObject(read.read(in));
        out.add(obj);
    }


}
