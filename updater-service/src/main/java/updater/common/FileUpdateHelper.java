/**
 * 
 */
package updater.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 * @author wangtao
 *
 */
public class FileUpdateHelper {
	/**
	 * 下载更新文件
	 * @param fileUrl 更新文件url
	 * @param saveDir 文件保存目录
	 * @return
	 */
	public String downloadUpdateFile(String fileUrl, String saveDir){
		String fileSavePath = "";
		URL url = null;
		HttpURLConnection httpUrl = null;
		
		try {
			url = new URL(fileUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			
			File targetFile = new File(saveDir, url.getFile());
			fileSavePath = targetFile.getPath();
			System.out.println(fileSavePath);
			if (targetFile.exists())
				targetFile.delete();
			
			DataInputStream in = new DataInputStream(httpUrl.getInputStream());
			DataOutputStream out = new DataOutputStream(new FileOutputStream(fileSavePath));
			
			byte[] buffer = new byte[4096];
			int count = 0 ;
			
			while((count = in.read(buffer)) > 0){
				out.write(buffer,0, count);
			}
			
			out.close();
			in.close();  
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fileSavePath;
	}
	
	public void unzipFile(String zipFilePath, String targetDir){ 
		File zipFile = new File(zipFilePath);
		
		if (!zipFile.isFile() || !zipFile.exists()){
			System.out.println("指定文件不存在.");
		}
		
		try {
			ZipFile zFile = new ZipFile(zipFile);
			zFile.setFileNameCharset("GBK");
			if (!zFile.isValidZipFile()){
				System.out.println("压缩文件不合法,可能已被损坏.");
			}
			
			File destDir = new File("C:/Users/ASUS/Desktop/ZipTest/");
			if (destDir.isDirectory() && !destDir.exists()){
				destDir.mkdirs();
			}
			
			zFile.extractAll(destDir.getPath());
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
}
