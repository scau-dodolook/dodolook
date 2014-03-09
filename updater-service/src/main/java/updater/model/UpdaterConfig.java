/**
 * 
 */
package updater.model;

import java.util.ArrayList;

/**
 * 更新服务配置信息
 * @author wangtao
 *
 */
public class UpdaterConfig {
	private ArrayList<AppConfig> appConfigs;

	/**
	 * @return the appConfigs
	 */
	public ArrayList<AppConfig> getAppConfigs() {
		return appConfigs;
	}

	/**
	 * @param appConfigs the appConfigs to set
	 */
	public void setAppConfigs(ArrayList<AppConfig> appConfigs) {
		this.appConfigs = appConfigs;
	}
}
