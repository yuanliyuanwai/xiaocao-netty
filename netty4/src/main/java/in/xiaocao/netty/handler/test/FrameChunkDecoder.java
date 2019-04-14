/**
 * Project Name:netty4
 * File Name:FrameChunkDecoder.java
 * Package Name:in.xiaocao.netty.handler.test
 * Date:2019年4月14日下午9:32:49
 * Copyright (c) 2019, wanzhengchong@163.com All Rights Reserved.
 *
*/

package in.xiaocao.netty.handler.test;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

/**
 * ClassName:FrameChunkDecoder <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年4月14日 下午9:32:49 <br/>
 * @author   zhengchong.wan
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FrameChunkDecoder extends ByteToMessageDecoder {
	
	private final int maxFrameSize;
	
	public FrameChunkDecoder(int maxFrameSize) {
		this.maxFrameSize = maxFrameSize;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		int readableBytes = in.readableBytes();
		if (readableBytes > maxFrameSize) {
			in.clear();
			throw new TooLongFrameException();
		}
		ByteBuf buf = in.readBytes(readableBytes);
		out.add(buf);
	}

}

