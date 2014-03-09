/**
 * 
 */
package updater.model;

/**
 * 执行更新操作时执行的命令
 * 
 * @author wangtao 
 */
public class Command {
	private String beforUpdate;
	private String afterUpdate;
	/**
	 * @return the beforUpdate
	 */
	public String getBeforUpdate() {
		return beforUpdate;
	}
	/**
	 * @param beforUpdate the beforUpdate to set
	 */
	public void setBeforUpdate(String beforUpdate) {
		this.beforUpdate = beforUpdate;
	}
	/**
	 * @return the afterUpdate
	 */
	public String getAfterUpdate() {
		return afterUpdate;
	}
	/**
	 * @param afterUpdate the afterUpdate to set
	 */
	public void setAfterUpdate(String afterUpdate) {
		this.afterUpdate = afterUpdate;
	}
}