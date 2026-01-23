package utils;

import java.io.File;

public class FileUtils {

	public static void createDirectoryIfNotExists(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}

	}

}
