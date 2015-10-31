/** UnderflowException.java
 * @author Carter Buce | cmb9400 
 *
 * Version:
 *		$Id: UnderflowException.java,v 1.1 2015/10/26 20:54:34 cmb9400 Exp $
 *
 * Revisions:
 *		$Log: UnderflowException.java,v $
 *		Revision 1.1  2015/10/26 20:54:34  cmb9400
 *		wrote all methods
 *
 */

/**
 * Exception for removing an item from a heap
 * that does not contain any items
 */
public class UnderflowException extends Exception {
	
	/**
	 * Create the exception
	 * @param e the error message
	 */
	public UnderflowException(String e){
		super(e);
	}
}
