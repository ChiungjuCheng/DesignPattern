package decorator;

public class Client {
	
	public static void main(String[] args) {
		
		// 原本舊功能
		System.out.println("====原本的舊功能 ====  ");
		DataSource file = new FileDataSource("D://test");
		file.write("This is a simple sentence. Ha! Ha! Ha !");
				
		System.out.println(file.read());
					
		
		//<--- Decorator---->
		System.out.println("====裝飾新功能==== ");
		DataSource file2 = new FileDataSource("D://test");
		
		// encoding
		DataSource encryptionDataSource = new EncryptionDecorator(file2);
		encryptionDataSource.write("This is a simple sentence. Ha! Ha! Ha !");
		
		System.out.println("目前檔案的樣子 : ");
		System.out.println(file2.read());
		
		// decoding
		System.out.println("Decoding : ");
		System.out.println(encryptionDataSource.read());
		
	}
}
