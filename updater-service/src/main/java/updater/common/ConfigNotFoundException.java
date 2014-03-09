/**
 * 
 */
package updater.common;

/**
 * @author wangtao
 *
 */
public class ConfigNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	/* (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "updater config file not found!";
	}
}
