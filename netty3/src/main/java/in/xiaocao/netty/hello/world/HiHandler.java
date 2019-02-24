package in.xiaocao.netty.hello.world;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * 
 * @ClassName: HiHandler 
 * @Description: 客户端消息接收的处理器
 * @author zhengchong.wan
 * @date 2019年2月24日 上午11:25:33
 *
 */
public class HiHandler extends SimpleChannelHandler {

	// 接收消息
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		String s = (String) e.getMessage();
		System.out.println(s);
		super.messageReceived(ctx, e);
	}

	// 捕获异常
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		System.out.println("exceptionCaught");
		super.exceptionCaught(ctx, e);
	}

	// 连接建立
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("channelConnected");
		super.channelConnected(ctx, e);
	}

	// 必须是连接已经建立,关闭通道的时候才会触发
	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("channelDisconnected");
		super.channelDisconnected(ctx, e);
	}

	// channel关闭的时候触发
	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		System.out.println("channelClosed");
		super.channelClosed(ctx, e);
	}
}
