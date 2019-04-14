/**
 * Project Name:netty4
 * File Name:FixedLengthFrameDecoder.java
 * Package Name:in.xiaocao.netty.handler.test
 * Date:2019年4月14日下午4:18:55
 * Copyright (c) 2019, wanzhengchong@163.com All Rights Reserved.
 *
*/

package in.xiaocao.netty.handler.test;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * ClassName:FixedLengthFrameDecoder <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年4月14日 下午4:18:55 <br/>
 * @author   zhengchong.wan
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FixedLengthFrameDecoder extends ByteToMessageDecoder {
	
	private final int frameLength;
	
	public FixedLengthFrameDecoder(int frameLength) {
		this.frameLength = frameLength;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		while (in.readableBytes() >= frameLength) {
			ByteBuf buf = in.readBytes(frameLength);
			out.add(buf);
		}
	}

}

