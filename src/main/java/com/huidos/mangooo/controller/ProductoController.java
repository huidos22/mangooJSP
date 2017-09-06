package com.huidos.mangooo.controller;
/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.huidos.mangooo.model.DetalleVentaPK;
import com.huidos.mangooo.model.Producto;
import com.huidos.mangooo.model.dto.ProductoDto;
import com.huidos.mangooo.repository.DetalleVentaRepository;
import com.huidos.mangooo.repository.ProductoRepository;
import com.huidos.mangooo.validator.ProductoFormValidator;

@Controller
public class ProductoController {

	
	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private DetalleVentaRepository detalleVentaRepository;
	@Autowired
	private ProductoFormValidator productoFormValidator;
	@Autowired
	private ModelMapper modelMapper;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(productoFormValidator);
	}

	// list page
	@RequestMapping(value = "/productos", method = RequestMethod.GET)
	public String showAllProductos(Model model) {
		model.addAttribute("productos", productoRepository.findAll());
		return "productos/list";

	}

	// save or update product
	@RequestMapping(value = "/productos", method = RequestMethod.POST)
	public String saveOrUpdateProducto(@ModelAttribute("productoForm") @Validated ProductoDto productoDto,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			// populateDefaultModel(model);
			return "productos/productoForm";
		} else {
			
			Producto producto = convertToEntity(productoDto);
			productoRepository.save(producto);
			
			redirectAttributes.addFlashAttribute("css", "success");
			if (productoDto.isNew()) {
				redirectAttributes.addFlashAttribute("msg", "Producto agregado correctamente!");
			} else {
				redirectAttributes.addFlashAttribute("msg", "Producto actualizado correctamente!");
			}
			// POST/REDIRECT/GET
			return "redirect:/productos/" + producto.getIdProducto();

		}
	}

	// show add producto form
	@RequestMapping(value = "/productos/add", method = RequestMethod.GET)
	public String showAddProductoForm(Model model) {

		ProductoDto productodto = new ProductoDto();
		// set default value
		model.addAttribute("productoForm", productodto);
		return "productos/productoForm";

	}

	// show update form
	@RequestMapping(value = "/productos/{id}/update", method = RequestMethod.GET)
	public String showUpdateProductoForm(@PathVariable("id") Short id, Model model) {
		Producto producto = productoRepository.findOne(id);
		model.addAttribute("productoForm", convertToDto(producto));
		return "productos/productoForm";

	}

	// delete producto
	@RequestMapping(value = "/productos/{id}/delete", method = RequestMethod.GET)
	public String deleteProducto(@PathVariable("id") Short id, final RedirectAttributes redirectAttributes) {

		DetalleVentaPK detalleVentaPK = new DetalleVentaPK();
		detalleVentaPK.setIdProducto(id);
		detalleVentaRepository.findAllById(detalleVentaPK);
		productoRepository.delete(id);

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "Producto fue eliminado!");

		return "redirect:/productos";
	}

	// show producto
	@RequestMapping(value = "/productos/{id}", method = RequestMethod.GET)
	public String showProducto(@PathVariable("id") Short id, Model model) {

		Producto producto = productoRepository.findOne(id);
		if (producto == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Producto no encontrado");
		}
		model.addAttribute("producto", convertToDto(producto));
		return "productos/show";

	}

	private ProductoDto convertToDto(Producto producto) {
		ProductoDto productoDto = modelMapper.map(producto, ProductoDto.class);
		return productoDto;
	}

	private Producto convertToEntity(ProductoDto productoDto) {
		Producto producto = modelMapper.map(productoDto, Producto.class);

		if (productoDto.getIdProducto() != null) {
			Producto oldProductoDto = productoRepository.findOne(productoDto.getIdProducto());
			return oldProductoDto;
		}
		return producto;
	}
}
