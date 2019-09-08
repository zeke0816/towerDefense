package exceptions;

/**
 * Class used to identify an exception for a cell that has already been taken
 * @author zeke0816
 *
 */
public class CellTakenException extends Exception {
	
	public CellTakenException(String str) {
		super(str);
	}
	
}
