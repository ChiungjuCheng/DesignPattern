package decorator;

public interface DataSource {

	String read();
	
	void write(String content);
}
