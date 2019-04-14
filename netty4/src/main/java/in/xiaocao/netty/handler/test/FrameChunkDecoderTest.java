/**
 * Project Name:netty4
 * File Name:FrameChunkDecoderTest.java
 * Package Name:in.xiaocao.netty.handler.test
 * Date:2019年4月14日下午9:35:59
 * Copyright (c) 2019, wanzhengchong@163.com All Rights Reserved.
 *
*/

package in.xiaocao.netty.handler.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.TooLongFrameException;
import junit.framework.Assert;

/**
 * ClassName:FrameChunkDecoderTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年4月14日 下午9:35:59 <br/>
 * @author   zhengchong.wan
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FrameChunkDecoderTest {
	
	@Test
	public void testFramesDecoded() {
		ByteBuf buf = Unpooled.buffer();
		for (int i = 0; i < 9; i++) {
			buf.writeByte(i);
		}
		ByteBuf input = buf.duplicate();
		EmbeddedChannel channel = new EmbeddedChannel(new FrameChunkDecoder(3));
		assertTrue(channel.writeInbound(input.readBytes(2)));
		try {
			channel.writeInbound(input.readBytes(4));
			Assert.fail();
		} catch (TooLongFrameException e) {
			
		}
		assertTrue(channel.writeInbound(input.readBytes(3)));
		assertTrue(channel.finish());
		
		ByteBuf read = (ByteBuf) channel.readInbound();
		System.out.println(read.getByte(0));
		System.out.println(read.getByte(1));
		assertEquals(buf.readSlice(2), read);
		
		read = (ByteBuf) channel.readInbound();
		System.out.println(read.getByte(0));
		System.out.println(read.getByte(1)); 
		System.out.println(read.getByte(2)); 
		assertEquals(buf.skipBytes(4).readSlice(3), read);
		read.release();
		buf.release();
	}

}

