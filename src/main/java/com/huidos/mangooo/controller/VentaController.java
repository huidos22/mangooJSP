package com.huidos.mangooo.controller;

/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.huidos.mangooo.model.Cliente;
import com.huidos.mangooo.model.DetalleVenta;
import com.huidos.mangooo.model.Estado;
import com.huidos.mangooo.model.FormaDePago;
import com.huidos.mangooo.model.GastoCorte;
import com.huidos.mangooo.model.Municipio;
import com.huidos.mangooo.model.Producto;
import com.huidos.mangooo.model.Usuario;
import com.huidos.mangooo.model.Venta;
import com.huidos.mangooo.model.dto.ClienteDto;
import com.huidos.mangooo.model.dto.UsuarioDto;
import com.huidos.mangooo.model.dto.VentaDto;
import com.huidos.mangooo.repository.ClienteRepository;
import com.huidos.mangooo.repository.DetalleVentaRepository;
import com.huidos.mangooo.repository.GastoCorteRepository;
import com.huidos.mangooo.repository.MunicipioRepository;
import com.huidos.mangooo.repository.ProductoRepository;
import com.huidos.mangooo.repository.VentaRepository;
import com.huidos.mangooo.service.EstadosService;
import com.huidos.mangooo.service.VentaService;
import com.huidos.mangooo.validator.VentaFormValidator;

@SessionAttributes("usuarioObjSession")
@Controller
public class VentaController {

	@Autowired
	private EstadosService estadoService;
	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private MunicipioRepository municipioRepository;
	@Autowired
	private VentaRepository ventaRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private VentaFormValidator ventaFormValidator;
	@Autowired
	private VentaService ventaService;
	@Autowired
	private GastoCorteRepository gastoCorteRepository;
	@Autowired
	private DetalleVentaRepository detalleVentaRepository;

	@InitBinder(value = { "ventaForm" })
	protected void initBinder(WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.setValidator(ventaFormValidator);
	}

	@RequestMapping(value = "/ventas/add", method = RequestMethod.GET)
	public String showAddVentaForm(HttpServletRequest request, Model model) {
		/**
		 * usuario.id retProdList es generada por el estado y por los kilos x
		 * una consta formaPagoList municipiosList estadosList productosList
		 * clientesList
		 **/
		VentaDto ventaDto = new VentaDto();
		Usuario userSession = (Usuario) request.getSession().getAttribute("usuarioObjSession");
		UsuarioDto usersessionDto = convertToDto(userSession);
		ventaDto.setUsuario(usersessionDto);
		ventaDto.setFechaGastoCorte(new Date());

		Map<FormaDePago, String> formaPagoList = getFormaDePago();
		Map<Short, String> mapProductos = getProductos();
		Map<Integer, String> mapClientes = getClientes();
		Iterable<GastoCorte> gastoCorteList = gastoCorteRepository.findAll();

		model.addAttribute("ventaForm", ventaDto);
		model.addAttribute("formaPagoList", formaPagoList);
		model.addAttribute("estadosList", estadoService.getEstados());
		model.addAttribute("productosList", mapProductos);
		model.addAttribute("clientesList", mapClientes);
		model.addAttribute("gastoCorteList", gastoCorteList);

		return "ventas/addVenta";
	}

	private Map<Integer, String> getClientes() {
		Map<Integer, String> mapClientes = new HashMap<Integer, String>();
		Iterable<Cliente> clientes = clienteRepository.findAll();
		for (Cliente cliente : clientes) {
			mapClientes.put(cliente.getIdCliente(), cliente.getNombre());
		}
		return mapClientes;
	}

	private Map<Short, String> getProductos() {
		Map<Short, String> mapProductos = new HashMap<Short, String>();
		Iterable<Producto> productos = productoRepository.findAll();
		for (Producto producto : productos) {
			mapProductos.put(producto.getIdProducto(), producto.getNombre() + " - " + producto.getVariedad());
		}
		return mapProductos;
	}

	private Map<FormaDePago, String> getFormaDePago() {
		Map<FormaDePago, String> formasDePago = new LinkedHashMap<FormaDePago, String>(2);
		formasDePago.put(FormaDePago.EFECTIVO, StringUtils.capitalize(FormaDePago.EFECTIVO.name().toLowerCase()));
		formasDePago.put(FormaDePago.CHEQUE, StringUtils.capitalize(FormaDePago.CHEQUE.name().toLowerCase()));
		formasDePago.put(FormaDePago.TRANSFERENCIA,
				StringUtils.capitalize(FormaDePago.TRANSFERENCIA.name().toLowerCase()));
		return formasDePago;
	}

	@RequestMapping(value = "/municipiosList.json")
	@ResponseStatus(code = HttpStatus.OK)
	public @ResponseBody List<Municipio> municipioList(@ModelAttribute("ventaForm") VentaDto ventaDto,
			@RequestParam(value = "idEstado", required = true) Short idEstado, ModelMap modelMap) {
		Estado estado = new Estado();
		estado.setIdEstado(idEstado);
		List<Municipio> municipios = municipioRepository.findAllByEstado(estado);
		return municipios;
	}

	@RequestMapping(value = "/gastoCorteDtosList.json")
	@ResponseStatus(code = HttpStatus.OK)
	public @ResponseBody List<GastoCorte> gastoCorteByMpioList(@ModelAttribute("ventaForm") VentaDto ventaDto,
			@RequestParam(value = "idEstado", required = true) Short idEstado,
			@RequestParam(value = "idMunicipio", required = true) Short idMunicipio, ModelMap modelMap) {
		Estado estado = new Estado();
		estado.setIdEstado(idEstado);
		List<Municipio> municipios = municipioRepository.findAllByEstado(estado);
		List<GastoCorte> lstGastoCorte = gastoCorteRepository.findAllByMunicipio(
				municipios.stream().filter(mpio -> (mpio.getIdMunicipio() == idMunicipio)).findFirst());
		return lstGastoCorte;
	}

	public static <T> List<T> toList(final Iterable<T> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
	}

	@RequestMapping(value = "/ventasxcliente", method = { RequestMethod.GET, RequestMethod.POST })
	public String showVentasxCliente(HttpServletRequest request, Model model,
			@ModelAttribute("ventaForm") VentaDto ventaDto) {
		String returnedView = "";
		Usuario userSession = (Usuario) request.getSession().getAttribute("usuarioObjSession");
		UsuarioDto usersessionDto = convertToDto(userSession);
		ventaDto.setUsuario(usersessionDto);
		ventaDto.setFechaGastoCorte(new Date());
		Map<Integer, String> mapClientes = getClientes();
		model.addAttribute("clientesList", mapClientes);
		ClienteDto clienteDto = ventaDto.getCliente();
		if (null != clienteDto && null != clienteDto.getIdCliente()) {
			Cliente cliente = convertClienteToEntity(clienteDto);

			List<Venta> ventasxCliente = ventaRepository.findAllByCliente(cliente);
			Iterable<DetalleVenta> detalleVentas = detalleVentaRepository.findAll();
			List<DetalleVenta> lstDetalleVentas = new ArrayList<>();
			if (!ventasxCliente.isEmpty()) {
				List<DetalleVenta> lstDetVent = new ArrayList<>();
				for (Venta venta : ventasxCliente) {
					lstDetVent = toList(detalleVentas);

					DetalleVenta detalleVenta = lstDetVent.stream()
							.filter(detVentPK -> detVentPK.getId().getIdVenta() == venta.getIdVenta()).findFirst()
							.get();
					lstDetalleVentas.add(detalleVenta);
				}
			}
			model.addAttribute("cliente", cliente);
			model.addAttribute("lstVentasxCliente", ventasxCliente);
			model.addAttribute("lstDetalleVentas", lstDetalleVentas);
		}

		returnedView = "/ventas/ventasxcliente";
		return returnedView;
	}

	@RequestMapping(value = "/ventasxproducto", method = { RequestMethod.GET, RequestMethod.POST })
	public String showVentasxProducto(HttpServletRequest request, Model model,
			@ModelAttribute("ventaForm") VentaDto ventaDto) {
		String returnedView = "";
		Usuario userSession = (Usuario) request.getSession().getAttribute("usuarioObjSession");
		UsuarioDto usersessionDto = convertToDto(userSession);
		ventaDto.setUsuario(usersessionDto);
		Map<Short, String> mapProductos = getProductos();
		model.addAttribute("productos", mapProductos);
		Producto producto = productoRepository.findOne(ventaDto.getIdProducto());
		if (null != producto) {

			Iterable<DetalleVenta> detalleVentas = detalleVentaRepository.findAll();
			List<DetalleVenta> lstDetalleVentas = new ArrayList<>();
			List<Venta> ventasxProducto= new ArrayList<>();
			if (!toList(detalleVentas).isEmpty()) {
				lstDetalleVentas = toList(detalleVentas).stream()
						.filter(detVentPK -> detVentPK.getId().getIdProducto() == producto.getIdProducto())
						.collect(Collectors.toList());
				for(DetalleVenta detv : lstDetalleVentas){
					ventasxProducto.add(ventaRepository.findOne(detv.getId().getIdVenta()));
				}
			}
			model.addAttribute("producto", producto);
			model.addAttribute("lstVentasxProducto", ventasxProducto);
			model.addAttribute("lstDetalleVentas", lstDetalleVentas);
		}

		returnedView = "/ventas/ventasxproducto";
		return returnedView;
	}

	private Cliente convertClienteToEntity(ClienteDto clienteDto) {
		Cliente cliente = modelMapper.map(clienteDto, Cliente.class);

		if (clienteDto.getIdCliente() != null) {
			Cliente oldClienteDto = clienteRepository.findOne(clienteDto.getIdCliente());
			return oldClienteDto;
		}
		return cliente;
	}

	@RequestMapping(value = "/ventas", method = RequestMethod.POST)
	public String AddVenta(HttpServletRequest request, @ModelAttribute("ventaForm") @Validated VentaDto ventaDto,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		String returnedView = "";
		if (!result.hasErrors()) {
			ventaService.createVenta(ventaDto);
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Venta Agregada satisfactoriamente");
			ventaDto = new VentaDto();

		} else {
			redirectAttributes.addFlashAttribute("css", "warning");
			redirectAttributes.addFlashAttribute("msg", "Verifique");
		}

		Usuario userSession = (Usuario) request.getSession().getAttribute("usuarioObjSession");
		UsuarioDto usersessionDto = convertToDto(userSession);
		ventaDto.setUsuario(usersessionDto);
		ventaDto.setFechaGastoCorte(new Date());
		Map<FormaDePago, String> formaPagoList = getFormaDePago();
		Map<Short, String> mapProductos = getProductos();
		Map<Integer, String> mapClientes = getClientes();
		Iterable<GastoCorte> gastoCorteList = gastoCorteRepository.findAll();
		model.addAttribute("ventaForm", ventaDto);
		model.addAttribute("formaPagoList", formaPagoList);
		model.addAttribute("estadosList", estadoService.getEstados());
		model.addAttribute("productosList", mapProductos);
		model.addAttribute("clientesList", mapClientes);
		model.addAttribute("gastoCorteList", gastoCorteList);

		returnedView = "/ventas/addVenta";
		return returnedView;
	}

	private UsuarioDto convertToDto(Usuario user) {
		UsuarioDto userDto = modelMapper.map(user, UsuarioDto.class);
		return userDto;
	}

}
