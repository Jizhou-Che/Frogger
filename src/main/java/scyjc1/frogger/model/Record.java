package scyjc1.frogger.model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

/**
 * The object for facilitating operations on the leaderboard file.
 */
public class Record {
	private List<String> records;
	private Path path = Paths.get(".data/leaderboard.csv");
	private File dataDirectory = new File(".data");
	private File leaderboardFile = new File(dataDirectory, "leaderboard.csv");

	/**
	 * Initialises with a read from the leaderboard file.
	 *
	 * @throws IOException if an error occurred.
	 */
	public Record() throws IOException {
		records = read();
	}

	/**
	 * Gets the size of the records.
	 *
	 * @return the size of the records as an integer.
	 */
	public int size() {
		return records.size();
	}

	/**
	 * Adds a record.
	 *
	 * @param score the score as an integer.
	 * @param date  the date in a specific format as a {@link String}.
	 * @param name  the name of the player as a {@link String}.
	 */
	public void add(int score, String date, String name) {
		records.add(score + "," + date + "," + name);
	}

	/**
	 * Sorts the records according to the score in descending order.
	 */
	public void sort() {
		records.sort(Comparator.comparing(s -> Integer.parseInt(s.substring(0, s.indexOf(','))), Comparator.reverseOrder()));
	}

	/**
	 * Gets the score of a specified record.
	 *
	 * @param id the id of the record as an integer. Must be within the range.
	 * @return the score of the specified record as an integer.
	 */
	public int getScore(int id) {
		return Integer.parseInt(records.get(id).split(",", 3)[0]);
	}

	/**
	 * Gets the date of a specified record.
	 *
	 * @param id the id of the record as an integer. Must be within the range.
	 * @return the date of the specified record as a {@link String}.
	 */
	public String getDate(int id) {
		return records.get(id).split(",", 3)[1];
	}

	/**
	 * Gets the name of a specified record.
	 *
	 * @param id the id of the record as an integer. Must be within the range.
	 * @return the name of the specified record as a {@link String}.
	 */
	public String getName(int id) {
		return records.get(id).split(",", 3)[2];
	}

	/**
	 * Writes the record to the leaderboard file.
	 *
	 * @throws IOException if the file cannot be written to.
	 */
	public void write() throws IOException {
		if (size() >= 10) {
			Files.write(path, records.subList(0, 10));
		} else {
			Files.write(path, records.subList(0, size()));
		}
	}

	/**
	 * Reads from the leaderboard file.
	 * Creates the file if it does not exist.
	 *
	 * @return all lines in the leaderboard file as a {@link List}.
	 * @throws IOException if the directory or file cannot be created.
	 */
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
