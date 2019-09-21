package media.databases;

public class MusicService extends DatabaseService {

	public MusicService() {
		database = "src/media/databases/tables/music.csv";
	}

	@Override
	public String getPath(String id) {
		return null;
	}

}
