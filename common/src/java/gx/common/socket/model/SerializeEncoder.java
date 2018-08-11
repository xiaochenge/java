package gx.common.socket.model;

import gx.common.entity.SerializeObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class SerializeEncoder  extends MessageToByteEncoder<SerializeObject> {

    @Override
    protected void encode(ChannelHandlerContext ctx, SerializeObject user, ByteBuf out) throws Exception {
        byte[] datas = ByteObjConverter.objectToByte(user);
        out.writeBytes(datas);
        ctx.flush();
    }
}
