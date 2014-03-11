/**
 * 
 */
package updater.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang.ArrayUtils;

/**
 * @author wangtao
 *
 */
public class CommandExecHelper {
	public void execCommand(String[] commands){
		if (ArrayUtils.isEmpty(commands))
			return;
		
		for(String command : commands){
			this.execCommand(command);
		}
	}
	
	public void execCommand(String command){
		Runtime rt = Runtime.getRuntime();
		try {
			Process pcs = rt.exec(command);
			BufferedReader br = new BufferedReader(new InputStreamReader(pcs.getInputStream(),"gb2312"));
			
			String line = new String();
			while((line = br.readLine()) != null){
				System.out.println(line);
			}
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
}
