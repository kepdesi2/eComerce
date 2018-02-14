package org.o7planning.springmvcshoppingcart.view;

import java.text.DateFormat;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.o7planning.springmvcshoppingcart.model.CartInfo;
import org.o7planning.springmvcshoppingcart.model.CartLineInfo;
import org.o7planning.springmvcshoppingcart.model.CustomerInfo;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ItextPdfView extends AbstractITextPdfView {

    private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.SHORT);

    protected void buildPdfDocument(Map<String, Object> model,
                                    Document document, PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
        
        CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("myCart");
        
        
        PdfPTable table = new PdfPTable(4);
        table.setWidths(new int[]{10, 30, 30, 30});

        
        table.addCell("nombre");
        table.addCell("Mail");
        table.addCell("Telefono");
        table.addCell("direccion");
        
        table.addCell(String.valueOf(cartInfo.getCustomerInfo().getName()));
        table.addCell(String.valueOf(cartInfo.getCustomerInfo().getEmail()));
        table.addCell(String.valueOf(cartInfo.getCustomerInfo().getPhone()));
        table.addCell(String.valueOf(cartInfo.getCustomerInfo().getAddress()));
        
        table.setSpacingAfter(10);
        
        document.add(table);
        
        PdfPTable table2 = new PdfPTable(4);
        table2.setWidths(new int[]{10, 30, 30, 30});
        table2.addCell("Nombre");
        table2.addCell("Precio");
        table2.addCell("Cantidad");
        table2.addCell("Total");
        
        for (CartLineInfo course : cartInfo.getCartLines()){
        	table2.addCell(String.valueOf(course.getProductInfo().getName()));
        	table2.addCell(String.valueOf(course.getProductInfo().getPrice()));
        	table2.addCell(String.valueOf(course.getQuantity()));
        	table2.addCell(String.valueOf(course.getAmount()));
        }
        document.add(table2);
        
    }

}