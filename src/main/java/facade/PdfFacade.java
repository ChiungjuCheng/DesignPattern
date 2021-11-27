package facade;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.itextpdf.kernel.color.DeviceGray;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

public class PdfFacade {
	
	void createPdf(String path) {
		File file = new File(path);
		file.getParentFile().mkdirs();
	}

	void manipulatePdf(String path, List<String> headers, List<Map<String, String>> body) throws FileNotFoundException {
		
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
