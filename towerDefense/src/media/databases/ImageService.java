package media.databases;

import java.io.File;

public class ImageService extends DatabaseService {

	public ImageService() {
		table = new File("src/media/databases/tables/images.csv");
	}

}
