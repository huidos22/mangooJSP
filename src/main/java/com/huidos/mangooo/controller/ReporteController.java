package com.huidos.mangooo.controller;
/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.huidos.mangooo.repository.ClienteRepository;
import com.huidos.mangooo.repository.ProductoRepository;
import com.huidos.mangooo.service.reports.ReporteService;
@Controller
public class ReporteController {
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private ReporteService reporteService;
	
	@RequestMapping(value = "/clientesAll.xls", method = RequestMethod.GET)
	public ModelAndView downloadExcel(Model model) {
		ModelAndView mvxls = new  ModelAndView("excelView");
		mvxls.addObject("clientes", clienteRepository.findAll());
		mvxls.addObject("reportName","ClientesReport");
        return mvxls;
	}
	@RequestMapping(value = "/allProductos.xls", method = RequestMethod.GET)
	public ModelAndView downloadAllProductosExcel(Model model) {
		ModelAndView mvxls = new  ModelAndView("excelView");
		mvxls.addObject("productos", productoRepository.findAll());
		mvxls.addObject("reportName","productosReport");
        return mvxls;
	}
	@RequestMapping(value = "/reportes", method = RequestMethod.GET)
	public String showAllUsuarios(Model model) {
		return "reportes/reportesHomePage";
	}
	@RequestMapping(value = "/originalReporte.xls", method = RequestMethod.GET)
	public ModelAndView downloadReporteOriginalExcel(Model model) {
		ModelAndView mvxls = new  ModelAndView("excelView");
		mvxls.addObject("reportBase",reporteService.getOriginalReporte());
		mvxls.addObject("reportName","reporteOriginal");
        return mvxls;
	}
}
