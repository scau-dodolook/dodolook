/**
 * 
 */
package updater.service;

import org.apache.commons.lang.StringUtils;

import updater.common.ConfigHelper;
import updater.model.AppConfig;
import updater.model.Command;

/**
 * @author wangtao
 * 
 */
public class ConfigService {
	private ConfigHelper configHelper;
	private AppConfig appConfig;
	private AppConfig remoteConfig;

	public ConfigService(AppConfig appConfig) {
		configHelper = new ConfigHelper();
		this.appConfig = appConfig;
		this.remoteConfig = configHelper.getRemoteConfig(appConfig);
	}

	/**
	 * 检查应用是否存在新的版本
	 * 
	 * @return
	 */
	public boolean existNewVersion() {
		boolean existNewVersion = false;

		if (remoteConfig != null
				&& StringUtils.equals(remoteConfig.getVersion(),
						appConfig.getVersion()))
			existNewVersion = true;

		return existNewVersion;
	}

	/**
	 * 获取更新文件的下载地址
	 * 
	 * @return
	 */
	public String getUpdateFileDownloadUrl() {
		StringBuffer fileDownloadUrl = new StringBuffer();

		fileDownloadUrl.append(appConfig.getUpdateService());
		fileDownloadUrl.append("/");
		fileDownloadUrl.append(remoteConfig.getUpdateFile());

		return fileDownloadUrl.toString();
	}

	/**
	 * 获取应用程序的安装目录
	 * 
	 * @return
	 */
	public String getAppInstallDic() {
		return appConfig.getAppInstallDic();
	}
 
	/**
	 * 更新本地版本号
	 */
	public void updateLocalVersion() {
		String newVersion = remoteConfig.getVersion();
		configHelper.UpdateAppVersion(appConfig.getAppCode(), newVersion);
	}

	/**
	 * 获取更新应用时执行的命令
	 * 
	 * @return
	 */
	public Command getUpdateCommand() {
		Command updateCommand = new Command();
		if (appConfig.getCommand() != null)
			updateCommand = appConfig.getCommand();

		return updateCommand;
	}
}
