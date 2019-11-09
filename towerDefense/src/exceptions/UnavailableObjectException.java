package exceptions;

/**
 * Class used to identify an exception for a warrior that has not been selected yet
 * @author zeke0816
 *
 */
public class UnavailableObjectException extends Exception {

	public UnavailableObjectException(String str) {
		super(str);
	}
	
}
