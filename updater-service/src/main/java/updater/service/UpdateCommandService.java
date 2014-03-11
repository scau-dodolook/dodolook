/**
 * 
 */
package updater.service;

import org.apache.commons.lang.StringUtils;

import updater.common.CommandExecHelper;
import updater.model.Command;

/**
 * @author wangtao
 *
 */
public class UpdateCommandService {
	public Command updateCommand;
	CommandExecHelper commandExecHelper;
	
	public UpdateCommandService(Command updateCommand){ 
		commandExecHelper = new CommandExecHelper();
	}
	
	/**
	 * 执行更新操作请的预设命令
	 */
	public void execCommandBeforUpdate(){
		commandExecHelper.execCommand(getBeforUpdateCommands());
	}
	
	/**
	 * 执行更新操作后的预设命令
	 */
	public void execCommandAfterUpdate(){
		commandExecHelper.execCommand(getAfterUpdateCommands());
	}
	
	/**
	 * 获取更新操作前要执行的命令
	 * 
	 * @return
	 */
	private String[] getBeforUpdateCommands() {
		String[] commands = null;
 
		String beforCommand = updateCommand.getBeforUpdate();
		if (StringUtils.isNotEmpty(beforCommand))
			commands = StringUtils.split(beforCommand, ",");

		return commands;
	}

	/**
	 * 获取更新结束后要执行的命令
	 * @return
	 */
	private String[] getAfterUpdateCommands() {
		String[] commands = null;
 
		String afterCommand = updateCommand.getAfterUpdate();
		if (StringUtils.isNotEmpty(afterCommand))
			commands = StringUtils.split(afterCommand, ",");

		return commands;
	}
}
