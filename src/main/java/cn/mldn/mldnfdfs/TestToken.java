package cn.mldn.mldnfdfs;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

public class TestToken {
	public static void main(String[] args) throws Exception {
		String filePath = "M00/00/00/wKi8mVspzy2AZo2CAABQ69Iao58761.jpg"; // 要删除的文件
		// 3、加载要读取的资源配置文件（通过CLASSPATH加载）
		ClassPathResource classPathResource = new ClassPathResource("fastdfs.properties");
		// 4、进行上传初始化操作
		ClientGlobal.init(classPathResource.getClassLoader().getResource("fastdfs.properties").getPath());
		// 5、创建Tracker客户端类对象
		TrackerClient client = new TrackerClient();
		TrackerServer trackerServer = client.getConnection(); // 获取tracker服务类对象
		int ts = (int) (System.currentTimeMillis() / 1000) ;
		StringBuffer accessUrl = new StringBuffer() ;
		accessUrl.append("http://") ;
		accessUrl.append(trackerServer.getInetSocketAddress().getHostString()) ;
		accessUrl.append("/group1/").append(filePath) ;
		accessUrl.append("?token=").append(ProtoCommon.getToken(filePath, ts, ClientGlobal.g_secret_key));
		accessUrl.append("&ts=").append(ts) ;
		System.out.println(accessUrl);
	}
}
