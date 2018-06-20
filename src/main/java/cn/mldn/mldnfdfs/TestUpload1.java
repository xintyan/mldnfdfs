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

public class TestUpload1 {
	public static void main(String[] args) throws Exception {
		// 1、定义要上传的文件，如果此时不是文件利用InputStream类来处理
		File file = new File("D:" + File.separator + "nophoto.jpg");
		// 2、要想上传则需要明确的知道文件的后缀
		String ext = "jpg"; // 实际的开发之中一定要自己根据上传文件拆分出后缀
		// 3、加载要读取的资源配置文件（通过CLASSPATH加载）
		ClassPathResource classPathResource = new ClassPathResource("fastdfs.properties");
		// 4、进行上传初始化操作
		ClientGlobal.init(classPathResource.getClassLoader().getResource("fastdfs.properties").getPath());
		// 5、创建Tracker客户端类对象
		TrackerClient client = new TrackerClient();
		TrackerServer trackerServer = client.getConnection(); // 获取tracker服务类对象
		StorageServer storageServer = null;
		// 6、真正进行上传处理是通过StorageClient负责完成的
		StorageClient1 storageClient = new StorageClient1(trackerServer, storageServer);
		// 7、上传文件需要定义相关的元数据信息
		NameValuePair[] metaList = new NameValuePair[3];
		metaList[0] = new NameValuePair("fileName", file.getName());
		metaList[1] = new NameValuePair("fileExtName", ext);
		metaList[2] = new NameValuePair("fileLength", String.valueOf(file.length()));
		// 8、开始上传
		String upload_file = storageClient.upload_file1(file.getPath(), ext, metaList);
		System.out.println(upload_file);
		trackerServer.close();
	}
}
