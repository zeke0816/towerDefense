package exceptions;

/**
 * Class used to identify an exception for a warrior that has not been selected yet
 * @author zeke0816
 *
 */
public class UnselectedWarriorException extends Exception {

	public UnselectedWarriorException(String str) {
		super(str);
	}
	
}
