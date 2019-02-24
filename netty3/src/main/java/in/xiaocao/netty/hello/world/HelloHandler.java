package in.xiaocao.netty.hello.world;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 * 
 * @ClassName: HelloHandler 
 * @Description: 消息接收处理器
 * @author zhengchong.wan
 * @date 2019年2月24日 上午10:59:42
 *
 */
public class HelloHandler extends SimpleChannelHandler {

	// 接收消息
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		String s = (String) e.getMessage();
		System.out.println(s);
		//回写数据
		ctx.getChannel().write("hi " + s);
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

	// 连接已经建立,关闭通道时才会触发
	// 先触发channelDisconnected事件,在触发channelClosed事件
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
