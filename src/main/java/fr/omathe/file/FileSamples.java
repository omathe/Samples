package fr.omathe.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileSamples {

	/**
	 * Browses a files tree
	 * The root file is included in the returned stream
	 * @param directory - The root directory
	 * @return A stream of Path
	 * @throws IOException
	 */
	Stream<Path> browseTree(final String directory) throws IOException {

		return Files.walk(Paths.get(directory));
	}

	/**
	 * Returns the bytes of a file
	 * @param file - The file
	 * @return An array of bytes
	 */
	byte[] getFileBytes(final File file) {

		final Path path = file.toPath();
		byte[] content = null;
		try {
			content = Files.readAllBytes(path);
		} catch (final IOException e) {
		}
		return content;
	}

	/**
	 * Creates a file on the root of the class path
	 * @throws IOException
	 */
	void createFileOnClassPath() throws IOException {

		final String basePathOfClass = FileSamples.class.getProtectionDomain().getCodeSource().getLocation().getFile();
		final File file = new File(basePathOfClass + "toto.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
	}

}
