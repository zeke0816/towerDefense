package exceptions;

/**
 * Class used to identify an exception for a resource not found in the database
 * @author zeke0816
 *
 */
public class DatabaseException extends Exception {

	public DatabaseException(String str) {
		super(str);
	}

}
