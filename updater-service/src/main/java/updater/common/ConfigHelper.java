/**
 * 
 */
package updater.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

import updater.model.AppConfig;
import updater.model.Command;
import updater.model.UpdaterConfig;

import com.thoughtworks.xstream.XStream;

public class ConfigHelper {
	private static final String configFilePath = System.getProperty("user.dir") + "/src/test/resources/RemoteServerConfig.xml";
	private static String configFileMD5 = "";
	private static UpdaterConfig config;

	/**
	 * 获取本地配置
	 * @return
	 */
	public UpdaterConfig getLocalConfig() {
		if ((config == null) || (conifgFileChanged())) {
			try {
				config = readConfig();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ConfigNotFoundException e) {
				e.printStackTrace();
			}
		}
		return config;
	}
	
	/**
	 * 获取远程服务器上的配置文件
	 * @return
	 */
	public UpdaterConfig getRemoteConfig(AppConfig appConfig){ 
		UpdaterConfig remoteConfig = null; 
		HttpURLConnection httpUrl = null;
		
		try { 
			URL url = new URL(getRomoteConfigUrl(appConfig));
			httpUrl = (HttpURLConnection) url.openConnection();

			httpUrl.connect();  
			XStream xStream = getConfigXStream(); 
			remoteConfig = (UpdaterConfig) xStream.fromXML(httpUrl.getInputStream()); 
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (httpUrl != null)
				httpUrl.disconnect();
		}
		
		return remoteConfig;
	}

	/**
	 * 检查配置文件是否有更新
	 * @return
	 */
	public boolean conifgFileChanged() {
		File configFile = new File(configFilePath);
		MessageDigest digest = null;
		FileInputStream in = null;
		byte[] buffer = new byte[1024];

		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(configFile);
			int len;
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BigInteger bigInt = BigInteger.ZERO;
		if (digest != null)
			bigInt = new BigInteger(1, digest.digest());

		String md5 = bigInt.toString();
		System.out.println("config file md5 = " + md5);

		if (!StringUtils.equals(md5, configFileMD5)) {
			configFileMD5 = md5;
			return false;
		}

		return true;
	}

	/**
	 * 读取配置文件内容
	 * @return
	 * @throws ConfigNotFoundException
	 * @throws FileNotFoundException
	 */
	private UpdaterConfig readConfig() throws ConfigNotFoundException,
			FileNotFoundException {
		UpdaterConfig updaterCfg;

		File configFile = new File(configFilePath); 
		if (!configFile.exists())
			throw new ConfigNotFoundException();

		XStream xStream = getConfigXStream();
		InputStream in = new FileInputStream(new File(configFilePath));
		updaterCfg = (UpdaterConfig) xStream.fromXML(in);

		return updaterCfg;
	}

	/**
	 * 更新应用程序版本
	 * @param appCode
	 * @param version
	 */
	public void UpdateAppVersion(String appCode, String version) {
		UpdaterConfig updaterConfig = getLocalConfig();

		for (AppConfig appCfg : updaterConfig.getAppConfigs()) {
			if (StringUtils.equals(appCfg.getAppCode(), appCode)
					&& !StringUtils.equals(version, appCfg.getVersion())) {
				appCfg.setVersion(version);

				try {
					XStream xStream = getConfigXStream();
					PrintWriter printWriter = new PrintWriter(configFilePath,
							"UTF-8");
					xStream.toXML(updaterConfig, printWriter);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 获取XStream
	 * @return
	 */
	private XStream getConfigXStream() {
		XStream xStream = new XStream();

		xStream.alias("cmdCommand", Command.class);
		xStream.alias("app", AppConfig.class);
		xStream.alias("appUpdaterConfig", UpdaterConfig.class);

		return xStream;
	}

	/**
	 * 获取服务端配置文件url地址
	 * @param appConfig
	 * @return
	 */
	private String getRomoteConfigUrl(AppConfig appConfig){
		String remoteConfigUrl;
		
		StringBuffer urlParts = new StringBuffer();
		urlParts.append(appConfig.getUpdateService());
		urlParts.append(File.separator);
		urlParts.append(appConfig.getVersionFile());
		remoteConfigUrl = urlParts.toString();
		
		return remoteConfigUrl;
	}

}
