package facade;

import java.io.File;

import com.itextpdf.kernel.color.DeviceGray;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

/**
 * https://www.tutorialspoint.com/itext/itext_adding_table.htm Adding a Table to a Pdf without Facade pattern
 * https://kb.itextpdf.com/home/it7kb/examples/cell-and-table-widths
 * @author user
 *
 */
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
