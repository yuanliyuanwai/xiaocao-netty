/**
 * Project Name:netty4
 * File Name:AbsIntegerEncoder.java
 * Package Name:in.xiaocao.netty.handler.test
 * Date:2019年4月14日下午8:03:56
 * Copyright (c) 2019, wanzhengchong@163.com All Rights Reserved.
 *
*/

package in.xiaocao.netty.handler.test;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

/**
 * ClassName:AbsIntegerEncoder <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年4月14日 下午8:03:56 <br/>
 * @author   zhengchong.wan
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class AbsIntegerEncoder extends MessageToMessageEncoder<ByteBuf> {

	@Override
	protected void encode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		// 检查是否有足够的字节来编码
		while (in.readableBytes() >= 4) {
			int value = Math.abs(in.readInt());
			out.add(value);
		}
	}

}

