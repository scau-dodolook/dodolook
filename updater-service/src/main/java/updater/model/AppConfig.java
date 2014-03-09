/**
 * 
 */
package updater.model;

/**
 * 软件信息配置节点
 * @author wangtao
 *
 */
public class AppConfig { 
	private String appCode;
	private String appInstallDic;
	private String updateService;
	private String versionFile;
	private String updateFile;
	private String localVersion;
	private String interval;
	private Command command;
	/**
	 * @return the appCode
	 */
	public String getAppCode() {
		return appCode;
	}
	/**
	 * @param appCode the appCode to set
	 */
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	/**
	 * @return the appInstallDic
	 */
	public String getAppInstallDic() {
		return appInstallDic;
	}
	/**
	 * @param appInstallDic the appInstallDic to set
	 */
	public void setAppInstallDic(String appInstallDic) {
		this.appInstallDic = appInstallDic;
	}
	/**
	 * @return the updateService
	 */
	public String getUpdateService() {
		return updateService;
	}
	/**
	 * @param updateService the updateService to set
	 */
	public void setUpdateService(String updateService) {
		this.updateService = updateService;
	}
	/**
	 * @return the versionFile
	 */
	public String getVersionFile() {
		return versionFile;
	}
	/**
	 * @param versionFile the versionFile to set
	 */
	public void setVersionFile(String versionFile) {
		this.versionFile = versionFile;
	}
	/**
	 * @return the updateFile
	 */
	public String getUpdateFile() {
		return updateFile;
	}
	/**
	 * @param updateFile the updateFile to set
	 */
	public void setUpdateFile(String updateFile) {
		this.updateFile = updateFile;
	}
	/**
	 * @return the localVersion
	 */
	public String getLocalVersion() {
		return localVersion;
	}
	/**
	 * @param localVersion the localVersion to set
	 */
	public void setLocalVersion(String localVersion) {
		this.localVersion = localVersion;
	}
	/**
	 * @return the interval
	 */
	public String getInterval() {
		return interval;
	}
	/**
	 * @param interval the interval to set
	 */
	public void setInterval(String interval) {
		this.interval = interval;
	}
	/**
	 * @return the command
	 */
	public Command getCommand() {
		return command;
	}
	/**
	 * @param command the command to set
	 */
	public void setCommand(Command command) {
		this.command = command;
	}
	 
}