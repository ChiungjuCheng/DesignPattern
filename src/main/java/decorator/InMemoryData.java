package decorator;

import java.util.HashMap;
import java.util.Map;

/**
 * 假資料
 * @author user
 *
 */
public class InMemoryData {

	public static Map<String, String> files = new HashMap<>();

	public static String read(String path) {
		return files.get(path);
	}

	public static void write(String path, String content) {
		files.put(path, content);
	}

}
