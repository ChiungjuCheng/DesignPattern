# Facade 外觀模式
物件導向的程式語言會大量地使用到這個設計模式。他可以建立一個介面，讓使用者使用藉由該介面去使用到第三方的套件，避免使用者的程式碼和第三方套件有高耦合性。

外觀模式為子系統中的一組介面，提供一個一致的介面，此模式定義了一個高層介面，這個介面使得這一子系統更加容易使用。[DP]

# 問題
有一個產生pdf的第三方套件itextpdf，在使用上需要有以下步驟 : 
1. Creating a PdfWriter
2. Creating a PdfDocument object
3. Creating a Document object
4. Creating a table
5. Setting header
6. Setting body
7. Setting Document object
8. Closing Document object

第三方套件的物件和方法與自身邏輯的程式碼呈現耦合性，若是邏輯修改或是想改使用其他第三方套件產生pdf，就會需要大量動到原本的程式碼。

```java
public class NoFacadeClient {

	public static final String DEST = "D://test/simple_table.pdf";

	public static void main(String[] args) throws Exception {
		File file = new File(DEST);
		file.getParentFile().mkdirs();
		
		new NoFacadeClient().manipulatePdf(DEST);
	}

	protected void manipulatePdf(String dest) throws Exception {
		// Creating a PdfWriter and PdfDocument object   
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
		
		// Creating a Document object     
		Document doc = new Document(pdfDoc, PageSize.A4.rotate());

		// Creating a table
		float[] columnWidths = { 1, 5, 5 };
		Table table = new Table(UnitValue.createPercentArray(columnWidths));

		// header
		table.addHeaderCell(new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("#")));
		table.addHeaderCell(new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Key")));
		table.addHeaderCell(new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph("Value")));
	
		// body
		table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph("1")));
		table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph("key1")));
		table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph("value1")));

		// 設定doc
		doc.add(table);
		doc.close();
	}

}
```
因此需要將第三方套件相關物件和商業邏輯程式碼隔開來。

# UML
![facade](/picture/facade.png)

SubSystem負責初始化和使用第三方套件的物件或方法，Additional Facade可以直接給Facade使用或是給subsystem使用。

圖片來源  
https://refactoring.guru/design-patterns/facade

# Demo
將使用itextpdf的物件初始化的程式改寫在PdfFacade當中。
```java
public class PdfFacade {
	
	private void createPdf(String path) {
		File file = new File(path);
		file.getParentFile().mkdirs();
	}

	public void manipulatePdf(String path, List<String> headers, List<Map<String, String>> body) throws FileNotFoundException {
		
		createPdf(path);
		
		// Creating a PdfWriter and PdfDocument object
		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(path));

		// Creating a Document object
		Document doc = new Document(pdfDoc, PageSize.A4.rotate());

		// Creating a table
		float[] columnWidths = new float[headers.size()];
		for(int i = 0; i < columnWidths.length; i++) {
			columnWidths[i] = 5;
		}

		Table table = new Table(UnitValue.createPercentArray(columnWidths));

		// header
		List<Cell> headerList =  headers.stream()
                                        .map(header -> new Cell().setBackgroundColor(new DeviceGray(0.75f)).add(new Paragraph(header)))
										.collect(Collectors.toList());
			
		Cell[] headerArray = new Cell[headerList.size()];
		headerList.toArray(headerArray);
		
		for (Cell hfCell : headerArray) {
			table.addHeaderCell(hfCell);
		}
		
		// body
		body.stream()
			.forEach(rowMap -> headers.stream()
                                                    .forEach(header-> table.addCell(new Cell().setTextAlignment(TextAlignment.CENTER).add(new Paragraph(rowMap.get(header)))))
			);
		
		// 設定doc
		doc.add(table);
		doc.close();
	}
}
```
Client
```java
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
		e.printStackTrace();
	}
```


itext 參考  
https://www.tutorialspoint.com/itext/itext_adding_table.htm

# 解決
創建一個介面，將使用各種class的程式碼封裝起來，使用者只需要去使用該interface，不需要知道使用了第三方套件的哪個class檔案，且這也有另外的好處是，當第三方套件版本更新後，使用者不需要更改自己的程式碼，只需要開發者去修改interface的implement。

# 應用時機
* 在資料存取層、業務邏輯層和表示層之間建立
* 開發階段子系統越來越複雜，使用Facade提供一個簡單的使用介面
* 維護舊系統時，因舊系統已經難以維護和擴展，使用Facade與舊系統互動

另外補充:Adapter的不同的地方在於，facade會去定義一個全新的介面，並且使用第三方套件的多個物件來實做該介面，而adapter則是讓已經存在的介面變成可以讓使用者的程式碼可以做使用，且通常都是針對單一物件。