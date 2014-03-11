/**
 * 
 */
package updater.service;

import updater.common.ConfigHelper;
import updater.model.AppConfig;
import updater.model.UpdaterConfig;

/**
 * @author wangtao
 *
 */
public class UpdaterService {
	public void core(){
		 ConfigHelper  configHelper = new ConfigHelper();
		 
		 UpdaterConfig updateConfig = configHelper.getLocalConfig();
		 if(updateConfig == null 
				 || updateConfig.getAppConfigs() == null
				 || updateConfig.getAppConfigs().isEmpty())
			 return;
		 
		 
		 ConfigService appConfig = null;
		 UpdateCommandService commandSvc = null;
		 FileService fileSvc = null;
		 for(AppConfig app : updateConfig.getAppConfigs()){
			 try{
				 appConfig = new ConfigService(app);
				 commandSvc = new UpdateCommandService(appConfig.getUpdateCommand());
				 fileSvc = new FileService(appConfig.getAppInstallDic(), "", appConfig.getUpdateFileDownloadUrl());
				 
				 
				 commandSvc.execCommandBeforUpdate();
				 fileSvc.updateFile();
				 commandSvc.execCommandAfterUpdate();
				 appConfig.updateLocalVersion(); 
			 }catch(Exception ex){
				 ex.printStackTrace();
			 }
		 }
	}
}
