package media.databases;

public abstract class DatabaseService {
	
	protected String database;
	
	public abstract String getPath(String id);
	
}
