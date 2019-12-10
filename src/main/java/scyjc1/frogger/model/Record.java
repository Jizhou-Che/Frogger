package scyjc1.frogger.model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class Record {
	private List<String> records;
	private Path path = Paths.get(".data/leaderboard.csv");
	private File dataDirectory = new File(".data");
	private File leaderboardFile = new File(dataDirectory, "leaderboard.csv");

	public Record() throws IOException {
		records = read();
	}

	public int size() {
		return records.size();
	}

	public void add(int score, String date, String name) {
		records.add(score + "," + date + "," + name);
	}

	public void sort() {
		records.sort(Comparator.comparing(s -> Integer.parseInt(s.substring(0, s.indexOf(','))), Comparator.reverseOrder()));
	}

	public int getScore(int id) {
		return Integer.parseInt(records.get(id).split(",", 3)[0]);
	}

	public String getDate(int id) {
		return records.get(id).split(",", 3)[1];
	}

	public String getName(int id) {
		return records.get(id).split(",", 3)[2];
	}

	public void write() throws IOException {
		if (size() >= 10) {
			Files.write(path, records.subList(0, 10));
		} else {
			Files.write(path, records.subList(0, size()));
		}
	}

	private List<String> read() throws IOException {
		if (!dataDirectory.mkdir()) {
			if (!dataDirectory.exists()) {
				throw new IOException("Failed to create the data directory.");
			}
		}
		if (!leaderboardFile.createNewFile()) {
			if (!leaderboardFile.exists()) {
				throw new IOException("Failed to create the leaderboard file.");
			}
		}
		return Files.readAllLines(path, StandardCharsets.UTF_8);
	}
}
