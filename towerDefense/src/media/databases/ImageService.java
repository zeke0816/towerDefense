package media.databases;

public class ImageService extends DatabaseService {

	public ImageService() {
		database = "src/media/databases/tables/images.csv";
	}

	@Override
	public String getPath(String id) {
		return null;
	}

}
