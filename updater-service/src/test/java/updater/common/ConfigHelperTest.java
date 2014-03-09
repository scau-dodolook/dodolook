/**
 * 
 */
package updater.common;

import net.sf.json.JSONObject;

import org.junit.Test;

import updater.model.UpdaterConfig;

/**
 * @author wangtao
 *
 */
public class ConfigHelperTest {
	@Test
	public void configReadTest(){
		ConfigHelper updaterConfig = new ConfigHelper();
		UpdaterConfig config = updaterConfig.getUpdaterConfig();
		JSONObject jsonObject = JSONObject.fromObject( config );
		System.out.println(jsonObject);
	}
	
	@Test
	public void configFileMD5Test(){
		ConfigHelper updaterConfig = new ConfigHelper();
		for(int i = 0; i < 5 ; i++){
			updaterConfig.conifgFileChanged() ;
		} 
	}
	
	@Test
	public void updateVersionTest(){ 
		ConfigHelper updaterConfig = new ConfigHelper();
		UpdaterConfig config = updaterConfig.getUpdaterConfig();
		JSONObject jsonObject = JSONObject.fromObject( config );
		System.out.println(jsonObject);
		
		int i = 0;
		while(i < 10){
			if (i < 5){
				String version = String.format("1.1.%d", i);
				updaterConfig.UpdateAppVersion("nsca", version);
			}
			 
			config = updaterConfig.getUpdaterConfig();
			jsonObject = JSONObject.fromObject( config );
			System.out.println(jsonObject);
			i++;
		}
	}
}