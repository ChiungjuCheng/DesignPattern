package facade;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacadeClient {
		
	public static void main(String[] args) {
		
		// Path
		String path = "D://test/simple_table.pdf";
		
		// header
		List<String> headers =  Arrays.asList("#","Key","Value");
		
		// body
		List<Map<String, String>> body = new ArrayList<>();
		Map<String, String> row1 = new HashMap<>();
		row1.put("#", "1");
		row1.put("Key", "key1");
		row1.put("Value", "Value1");	
		body.add(row1);
		
		PdfFacade pdfFacade = new PdfFacade();
		
		try {
			pdfFacade.manipulatePdf(path, headers, body);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
