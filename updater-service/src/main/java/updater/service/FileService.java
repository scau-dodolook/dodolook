/**
 * 
 */
package updater.service;

import updater.common.FileUpdateHelper;

/**
 * @author wangtao
 *
 */
public class FileService {
	private String appInstallDic;
	private String updateFileDownloadUrl;
	private String downloadDic;
	private FileUpdateHelper fileUpdateHelper;
	
	public FileService(String appInstallDic, String downloadDic, String updateFileDownloadUrl){
		this.appInstallDic = appInstallDic;
		this.downloadDic = downloadDic;
		this.updateFileDownloadUrl = updateFileDownloadUrl;
		fileUpdateHelper = new FileUpdateHelper();
	}
	
	/**
	 * 更新应用程序文件
	 */
	public void updateFile(){
		String updateFilePath = fileUpdateHelper.downloadUpdateFile(updateFileDownloadUrl, downloadDic);
		fileUpdateHelper.unzipFile(updateFilePath, appInstallDic);
	}
}
