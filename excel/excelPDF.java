package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class excelpdf{
	private FileInputStream Fileinputstream = null;
	private FileOutputStream Fileoutputstream = null;
	private XSSFWorkbook Xssfworkbook = null;
	private XSSFSheet Xssfsheet = null;
	private Iterator<Row> Iterator = null;
	private Document Document = null;
	private PdfPTable PDFtable = null;
	private PdfPCell PDFcell = null;
	private File OldPath = null;
	private File NewPath = null;
	
	/**
	 * @param 
	 * @throws IOException, DocumentException
	 * @return 
	 * @see (.jar) commons-collection4 4.1, itextpdf 5.3.4, xmlbeans 3.0.1, poi 3.16, poi-ooxml 3.16, ooxml-schemas 1.4 
	 */
	@SuppressWarnings("deprecation")
	void pdf(String oldPath, String newPath) throws IOException, DocumentException, Exception {
		this.OldPath = new File(oldPath);
		this.NewPath = new File(newPath);
		
		try{
			Fileinputstream = new FileInputStream(this.OldPath);
			Fileoutputstream = new FileOutputStream(this.NewPath);
			Xssfworkbook  = new XSSFWorkbook(Fileinputstream);
			Xssfsheet = Xssfworkbook .getSheetAt(0);
			Iterator = Xssfsheet.iterator();
			
			Document = new Document();
			PdfWriter.getInstance(Document, Fileoutputstream);
			Document.open();
			
			PDFtable = new PdfPTable(6);
			
			//loop
			while(Iterator.hasNext()) {
				Row row = Iterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch(cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						PDFcell = new PdfPCell(new Phrase(cell.getStringCellValue()));
						PDFtable.addCell(PDFcell);
						break;
					case Cell.CELL_TYPE_BLANK:
						PDFcell = new PdfPCell(new Phrase(cell.getStringCellValue()));
						PDFtable.addCell(PDFcell);
						break;
					case Cell.CELL_TYPE_FORMULA:
						PDFcell = new PdfPCell(new Phrase(cell.getStringCellValue()));
						PDFtable.addCell(PDFcell);
						break;
					}//switch
				}//while2
			}//while1
			Document.add(PDFtable);
			
		} catch (IOException e) {
			System.out.println("IOException - excelpdf.pdf");
		} catch (DocumentException e) {
			System.out.println("DocumentException - excelpdf.pdf");
		} catch (Exception e) {
			System.out.println("Exception - excelpdf.pdf");
		} finally {
			Document.close();
			Fileinputstream.close();
			Fileoutputstream.close();
		}//finally
	}//void
	
}