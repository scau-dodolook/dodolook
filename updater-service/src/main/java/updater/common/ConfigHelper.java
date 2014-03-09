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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

import updater.model.AppConfig;
import updater.model.Command;
import updater.model.UpdaterConfig;

import com.thoughtworks.xstream.XStream;

public class ConfigHelper {
	private static final String configFilePath = "E:/Java Workspace/app.updaterService/src/test/resources/UpdaterConfig.xml";
	private static String configFileMD5 = "";
	private static UpdaterConfig config;

	public UpdaterConfig getUpdaterConfig() {
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

	public void UpdateAppVersion(String appCode, String version) {
		UpdaterConfig updaterConfig = getUpdaterConfig();

		for (AppConfig appCfg : updaterConfig.getAppConfigs()) {
			if (StringUtils.equals(appCfg.getAppCode(), appCode)
					&& !StringUtils.equals(version, appCfg.getLocalVersion())) {
				appCfg.setLocalVersion(version);

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

	private XStream getConfigXStream() {
		XStream xStream = new XStream();

		xStream.alias("cmdCommand", Command.class);
		xStream.alias("app", AppConfig.class);
		xStream.alias("appUpdaterConfig", UpdaterConfig.class);

		return xStream;
	}
}
