package cn.mldn.mldnfdfs;

import java.io.File;
import java.util.Arrays;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

public class TestDelete {
	public static void main(String[] args) throws Exception {
		String filePath = "group1/M00/00/00/wKi8mVspyciAHGk-AABQ69Iao58134.jpg" ; // 要删除的文件
		// 3、加载要读取的资源配置文件（通过CLASSPATH加载）
		ClassPathResource classPathResource = new ClassPathResource("fastdfs.properties") ;
		// 4、进行上传初始化操作
		ClientGlobal.init(classPathResource.getClassLoader().getResource("fastdfs.properties").getPath()); 
		// 5、创建Tracker客户端类对象
		TrackerClient client = new TrackerClient() ;
		TrackerServer trackerServer = client.getConnection() ; // 获取tracker服务类对象
		StorageServer storageServer = null ;
		// 6、真正进行上传处理是通过StorageClient负责完成的
		StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer) ;
		int code = storageClient.delete_file1(filePath) ;
		System.out.println(code); 
		trackerServer.close();  
	}
}
