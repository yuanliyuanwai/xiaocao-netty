/**
 * Project Name:netty4
 * File Name:FixedLengthFrameDecoderTest.java
 * Package Name:in.xiaocao.netty.handler.test
 * Date:2019年4月14日下午4:23:31
 * Copyright (c) 2019, wanzhengchong@163.com All Rights Reserved.
 *
*/

package in.xiaocao.netty.handler.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;

/**
 * ClassName:FixedLengthFrameDecoderTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年4月14日 下午4:23:31 <br/>
 * @author   zhengchong.wan
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class FixedLengthFrameDecoderTest {
	
	@Test
	public void testFramesDecoded() {
		ByteBuf buf = Unpooled.buffer();
		System.out.println(buf.refCnt());
		for (int i = 0; i < 9; i++) {
			buf.writeByte(i);
		}
		ByteBuf input = buf.duplicate();
		System.out.println(input.refCnt());
		EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
		assertTrue(channel.writeInbound(input.retain()));
		System.out.println(input.refCnt());
		assertTrue(channel.finish());
		
		ByteBuf read = (ByteBuf) channel.readInbound();
		System.out.println(read.refCnt());
		assertEquals(buf.readSlice(3), read);
		read.release();
		
		read = (ByteBuf) channel.readInbound();
		assertEquals(buf.readSlice(3), read);
		read.release();
		
		read = (ByteBuf) channel.readInbound();
		assertEquals(buf.readSlice(3), read);
		read.release();
		
		assertNull(channel.readInbound());
		buf.release();
	}
	
	@Test
	public void testFrameDecode2() {
		ByteBuf buf = Unpooled.buffer();
		for (int i = 0; i < 9; i++) {
			buf.writeByte(i);
		}
		ByteBuf input = buf.duplicate();
		EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
		// 返回false,因为没有一个完整的可供读取的帧
		assertFalse(channel.writeInbound(input.readBytes(2)));
		assertTrue(channel.writeInbound(input.readBytes(7)));
		
		ByteBuf read = (ByteBuf) channel.readInbound();
		System.out.println(read.refCnt());
		assertEquals(buf.readSlice(3), read);
		read.release();
		
		read = (ByteBuf) channel.readInbound();
		assertEquals(buf.readSlice(3), read);
		read.release();
		
		read = (ByteBuf) channel.readInbound();
		assertEquals(buf.readSlice(3), read);
		read.release();
		
		assertNull(channel.readInbound());
		buf.release();
	}

}

