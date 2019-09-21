package media.databases;

import java.io.File;

public class SoundService extends DatabaseService {

	public SoundService() {
		table = new File("src/media/databases/tables/music.csv");
	}

}
