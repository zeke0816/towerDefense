package media.databases;

public class SoundService extends DatabaseService {

	public SoundService() {
		table = getClass().getResourceAsStream("/databases/tables/sounds.csv");
	}

}
