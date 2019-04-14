/**
 * Project Name:netty4
 * File Name:AbsIntegerEncoderTest.java
 * Package Name:in.xiaocao.netty.handler.test
 * Date:2019年4月14日下午8:07:41
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
 * ClassName:AbsIntegerEncoderTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019年4月14日 下午8:07:41 <br/>
 * @author   zhengchong.wan
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class AbsIntegerEncoderTest {
	
	@Test
	public void testEncoded() {
		ByteBuf buf = Unpooled.buffer();
		for (int i = 1; i < 10; i++) {
			buf.writeInt(i * -1);
		}
		
		EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());
		assertTrue(channel.writeOutbound(buf));
		assertTrue(channel.finish());
		
		for (int i = 1; i < 10; i++) {
			assertEquals(Integer.valueOf(i), channel.readOutbound());
		}
		assertNull(channel.readOutbound());
	}

}

