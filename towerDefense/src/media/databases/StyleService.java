package media.databases;

import java.io.File;

public class StyleService extends DatabaseService {

	public StyleService() {
		table = new File("src/media/databases/tables/styles.csv");
	}

}
