package fr.omathe.csv;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public interface CsvManager {

	String SEPARATOR = ",";
	String COLUMN_REGEX = "^[a-zA-Z_$][a-zA-Z_$0-9]*$";
	String CHARSET = "UTF-8";
	String NULL_VALUE = "NULL";

	/**
	 * Load an object from a CSV file
	 * @throws CsvException
	 */
	static List<?> load(Path path, Class<?> type) throws CsvException {

		if (path == null) {
			throw new IllegalArgumentException("The parameter 'path' must be defined");
		}
		if (type == null) {
			throw new IllegalArgumentException("The parameter 'type' must be defined");
		}
		List<Object> objects = new ArrayList<Object>();

		try {
			List<String> lines = Files.readAllLines(path);

			if (lines != null && !lines.isEmpty()) {
				// header
				String header = lines.get(0);
				List<String> columns = CsvManager.parseHeader(header);

				// retrieve the records
				for (String line : lines.subList(1, lines.size())) { // skip the header
					// line
					List<String> values = parseLine(line);
					Map<String, String> map = CsvManager.buildMap(columns, values);

					Object object = ObjectBuilder.build(Player.class, map);
					objects.add(object);
				}
			}
		} catch (Exception e) {
			throw new CsvException("Failed to load objects (" + e.getMessage() + ")");
		}
		return objects;
	}

	/**
	 * Extract the columns from the header
	 * 
	 * @param header : the header containing the columns separated by the SEPARATOR
	 * @return A list of columns
	 * @throws CsvException
	 */
	static List<String> parseHeader(String header) throws CsvException {

		List<String> list = new ArrayList<>();

		if (header != null && !header.isEmpty()) {
			String[] split = header.split(SEPARATOR);

			for (String column : split) {
				String trimed = column.trim();
				if (Pattern.compile(COLUMN_REGEX).matcher(trimed).matches()) {
					list.add(trimed);
				} else {
					throw new CsvException("Column '" + column + "' is invalid.");
				}
			}
		}
		return list;
	}

	/**
	 * Extract the values from a line
	 * 
	 * @param line : the line containing the values separated by the SEPARATOR
	 * @return A list of columns
	 * @throws CsvException
	 */
	static List<String> parseLine(String line) throws CsvException {

		List<String> list = new ArrayList<>();

		if (line != null && !line.isEmpty()) {
			String[] split = line.split(SEPARATOR);

			for (String value : split) {
				String trimed = value.trim();
				list.add(trimed.equals(NULL_VALUE) ? null : trimed);
			}
		}
		return list;
	}

	/**
	 * Build the map of column, value
	 * 
	 * @param columns - The columns extracted from the header
	 * @param values  - The values of one line
	 * @return A map of column, value
	 * @throws CsvException
	 */
	static Map<String, String> buildMap(List<String> columns, List<String> values) throws CsvException {

		Map<String, String> map = new HashMap<String, String>();

		if (columns != null && values != null) {
			if (columns.size() == values.size()) {

				IntStream.range(0, columns.size()).forEach(index -> {
					map.put(columns.get(index), values.get(index));
				});
			} else {
				throw new CsvException("The sizes of 'columns' and 'values' are different.");
			}
		}
		return map;
	}

}
