package media.databases;

public class ImageService extends DatabaseService {

	public ImageService() {
		table = getClass().getResourceAsStream("/databases/tables/images.csv");
	}

}
