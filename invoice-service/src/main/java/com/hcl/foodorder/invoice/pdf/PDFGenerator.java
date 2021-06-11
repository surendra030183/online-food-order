package com.hcl.foodorder.invoice.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hcl.foodorder.domain.order.Order;
import com.hcl.foodorder.domain.restaurant.MenuItem;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


@Service
public class PDFGenerator {

	private static final Logger logger = LoggerFactory.getLogger(PDFGenerator.class);

	@Value("${fileBaseLocation}")
	private String fileBaseLocation;

	/**
	 * Generate the Order PDF Invoice file and save it in the specified location.
	 * 
	 * @param order
	 * @throws DocumentException
	 * @throws IOException
	 */
	public void generatePDFReport(Order order) throws DocumentException, IOException {
		logger.info("Report has been started ");
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(fileBaseLocation + order.getOrderNumber() + ".pdf"));
		document.open();

		// Invoice Header
		Paragraph paragraph = new Paragraph("Invoice Order : " + order.getOrderNumber());
		paragraph.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph);

		// Customer Details
		paragraph = new Paragraph("Customer Details : " + order.getCustomerId());
		paragraph.setAlignment(Element.ALIGN_LEFT);
		document.add(paragraph);

		// Restaurant Details
		paragraph = new Paragraph("Restaurant Details : " + order.getRestaurantId());
		paragraph.setAlignment(Element.ALIGN_LEFT);
		document.add(paragraph);

		paragraph = new Paragraph("Ordered Items");
		paragraph.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph);

		// Order List
		PdfPTable table = new PdfPTable(4);
		// Table Header
		Stream.of("Item Name", "Price", "Quantity", "Total Price").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(1.5f);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});

		for (MenuItem item : order.getItemList()) {
			// Table Body
			table.addCell(item.getName() + " - " + item.getDescription());
			table.addCell(String.valueOf(item.getPrice()));
			table.addCell(String.valueOf(item.getQuantity()));
			table.addCell(String.valueOf(item.getQuantity() * item.getPrice()));
		}
		document.add(table);
		Double total = order.getItemList().stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();

		paragraph = new Paragraph("Total Cost : " + total);
		paragraph.setAlignment(Element.ALIGN_RIGHT);
		document.add(paragraph);

		double totalTax = (total / 100) * order.getTaxPercentage();
		paragraph = new Paragraph("Total Tax : (" + order.getTaxPercentage() + " % ) : " + totalTax);
		paragraph.setAlignment(Element.ALIGN_RIGHT);
		document.add(paragraph);

		paragraph = new Paragraph("Total Bill : " + (total + totalTax));
		paragraph.setAlignment(Element.ALIGN_RIGHT);
		document.add(paragraph);

		document.close();
		logger.info("Report has been generated ");
	}
}
