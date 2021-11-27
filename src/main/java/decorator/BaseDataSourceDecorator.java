package decorator;

/**
 * Decorator
 * 
 * @author user
 *
 */
abstract public class BaseDataSourceDecorator implements DataSource {

	private DataSource dataSource;

	BaseDataSourceDecorator(DataSource source) {
		this.dataSource = source;
	}

	public String read() {
		return dataSource.read();
	}

	public void write(String content) {
		dataSource.write(content);
	}
}
