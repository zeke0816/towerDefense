package media.databases;

public class SoundService extends DatabaseService {

	public SoundService() {
		database = "src/media/databases/tables/music.csv";
	}

	@Override
	public String getPath(String id) {
		return null;
	}

}
