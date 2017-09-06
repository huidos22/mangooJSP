package com.huidos.mangooo.viewresolvers;

import java.util.Iterator;
import java.util.List;
/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.huidos.mangooo.model.Cliente;
import com.huidos.mangooo.model.Producto;
import com.huidos.mangooo.service.reports.dto.ReporteOriginal;

public class ExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// change the file name
		response.setHeader("Content-Disposition", "attachment; filename=\"reporteMangooo.xlsx\"");
		// create excel xls sheet
		Sheet sheet ;

		String reportName = model.get("reportName").toString();

		if (reportName.equalsIgnoreCase("ClientesReport")) {
			sheet = workbook.createSheet("Reporte de Clientes");
			// create header row
			@SuppressWarnings("unchecked")
			Iterable<Cliente> clientes = (Iterable<Cliente>) model.get("clientes");

			Row header = sheet.createRow(0);
			header.createCell(0).setCellValue("ID");
			header.createCell(1).setCellValue("nombre");
			header.createCell(2).setCellValue("direccion");
			header.createCell(3).setCellValue("rfc");

			// Create data cells
			int rowCount = 1;
			for (Cliente cliente : clientes) {
				Row clienteRow = sheet.createRow(rowCount++);
				clienteRow.createCell(0).setCellValue(cliente.getIdCliente());
				clienteRow.createCell(1).setCellValue(cliente.getNombre());
				clienteRow.createCell(2).setCellValue(cliente.getDireccion());
				clienteRow.createCell(3).setCellValue(cliente.getRfc());
			}

		} else if (reportName.equalsIgnoreCase("reporteOriginal")) {
			// create header row
			sheet = workbook.createSheet("Reporte de Ventas");
			Row header = sheet.createRow(0);
			header.createCell(0).setCellValue("Cheque A");
			header.createCell(1).setCellValue("Estado");
			header.createCell(2).setCellValue("Variedades");
			header.createCell(3).setCellValue("Total de Cajas");
			header.createCell(4).setCellValue("Total de Kilos");
			header.createCell(5).setCellValue("Kilos Promedio");
			header.createCell(6).setCellValue("Precio");
			header.createCell(7).setCellValue("Impuesto Fruta");
			header.createCell(8).setCellValue("Gasto Corte");
			header.createCell(9).setCellValue("Imp corte");
			header.createCell(10).setCellValue("Ret. Prod");
			header.createCell(11).setCellValue("Total Ch Fruta");
			header.createCell(12).setCellValue("Total");
			header.createCell(13).setCellValue("Forma de Pago");
			// Create data cells
			int rowCount = 1;
			@SuppressWarnings("unchecked")
			List<ReporteOriginal> lstRepOriginal = (List<ReporteOriginal>) model.get("reportBase");

			for (ReporteOriginal repOriginal : lstRepOriginal) {
				Row repOrigRow = sheet.createRow(rowCount++);
				repOrigRow.createCell(0).setCellValue(repOriginal.getChequeA());
				repOrigRow.createCell(1).setCellValue(repOriginal.getEstado());
				repOrigRow.createCell(2).setCellValue(repOriginal.getVariedades());
				repOrigRow.createCell(3).setCellValue(repOriginal.getTotalCajas());
				repOrigRow.createCell(4).setCellValue(repOriginal.getTotalKilos());
				repOrigRow.createCell(5).setCellValue(repOriginal.getKilosPrmoedio());
				repOrigRow.createCell(6).setCellValue(repOriginal.getPrecio());
				repOrigRow.createCell(7).setCellValue(repOriginal.getImpFruta());
				repOrigRow.createCell(8).setCellValue(repOriginal.getGastoCorte());
				repOrigRow.createCell(9).setCellValue(repOriginal.getImpGastoCorte());
				repOrigRow.createCell(10).setCellValue(repOriginal.getRetProd());
				repOrigRow.createCell(11).setCellValue(repOriginal.getTotalChFruta());
				repOrigRow.createCell(12).setCellValue(repOriginal.getTotal());
				repOrigRow.createCell(13).setCellValue(repOriginal.getFormaDePago());
			}
		} else if (reportName.equalsIgnoreCase("productosReport")) {
			sheet = workbook.createSheet("Reporte de Productos");
			// create header row
			Row header = sheet.createRow(0);
			header.createCell(0).setCellValue("Id Producto");
			header.createCell(1).setCellValue("nombre");
			header.createCell(2).setCellValue("Variedad");
			@SuppressWarnings("unchecked")
			Iterable<Producto> productos = (Iterable<Producto>) model.get("productos");
			int rowCount = 1;
			for(Producto producto:productos){
				Row rowProd = sheet.createRow(rowCount++);
				rowProd.createCell(0).setCellValue(producto.getIdProducto());
				rowProd.createCell(1).setCellValue(producto.getNombre());
				rowProd.createCell(2).setCellValue(producto.getVariedad());
			}
		}
	}
}
