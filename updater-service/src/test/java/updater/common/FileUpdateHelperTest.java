/**
 * 
 */
package updater.common;

import org.junit.Test;

/**
 * @author wangtao
 *
 */
public class FileUpdateHelperTest {
	@Test
	public void testDownloadFile(){
		String fileUrl = "http://www.updaterserver.com:8008/updateFiles.zip";
		String saveDir= "F:";
		
		FileUpdateHelper fileUpdateHelper = new FileUpdateHelper();
		String fileSavePath = fileUpdateHelper.downloadUpdateFile(fileUrl, saveDir);
		System.out.println(fileSavePath);
	}
	
	@Test
	public void testUnzip(){
		String filePath = "F:/NSCA_Service.zip";
		String targetDic = "F:/Unzip";
		
		FileUpdateHelper fileUpdateHelper = new FileUpdateHelper();
		fileUpdateHelper.unzipFile(filePath, targetDic);  
	}
}
