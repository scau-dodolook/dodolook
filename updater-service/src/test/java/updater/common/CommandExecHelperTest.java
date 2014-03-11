/**
 * 
 */
package updater.common;

import org.junit.Test;

/**
 * @author wangtao
 *
 */
public class CommandExecHelperTest {
	@Test
	public void commandExecText(){
		CommandExecHelper commandExecHelper = new CommandExecHelper();
		
		String[] commands = new String[]{"ping www.baidu.com"};
		commandExecHelper.execCommand(commands);
	}
}
