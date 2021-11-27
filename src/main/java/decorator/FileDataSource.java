package decorator;

/**
 * 原本的舊功能
 * @author user
 *
 */
public class FileDataSource implements DataSource {

	private String filePath;

	public FileDataSource(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String read() {
		return InMemoryData.read(filePath);
	}

	@Override
	public void write(String content) {
		InMemoryData.write(filePath,content);
	}

}
