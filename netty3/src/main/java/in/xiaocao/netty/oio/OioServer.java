package in.xiaocao.netty.oio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @ClassName: OioServer
 * @Description: 传统socket服务端
 * @author zhengchong.wan
 * @date 2019年2月23日 下午8:21:57
 *
 */
public class OioServer {

	public static void main(String[] args) throws Exception {
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		// 创建socket服务,监听10101端口
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(10101);
		System.out.println("服务器启动！");
		while (true) {
			// 获取一个套接字（阻塞）
			final Socket socket = server.accept();
			System.out.println("来个一个新客户端！");
			newCachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					// 业务处理
					handle(socket);
				}
			});
		}
	}

	/**
	 * 
	 * @Description: 读取数据
	 * @param socket
	 */
	public static void handle(Socket socket) {
		try {
			byte[] bytes = new byte[10];
			InputStream inputStream = socket.getInputStream();
			while (true) {
				// 读取数据（阻塞）
				int read = inputStream.read(bytes);
				if (read != -1) {
					System.out.println(new String(bytes, 0, read));
				} else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("socket关闭");
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
