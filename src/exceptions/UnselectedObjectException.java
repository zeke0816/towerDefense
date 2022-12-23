package exceptions;

/**
 * Class used to identify an exception for a warrior that has not been selected yet
 * @author zeke0816
 *
 */
public class UnselectedObjectException extends Exception {

	public UnselectedObjectException(String str) {
		super(str);
	}
	
}
