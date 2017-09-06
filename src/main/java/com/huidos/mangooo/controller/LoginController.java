package com.huidos.mangooo.controller;

/**
 * This class is a controller for the login page
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.huidos.mangooo.model.Usuario;
import com.huidos.mangooo.model.dto.UsuarioDto;
import com.huidos.mangooo.repository.ClienteRepository;
import com.huidos.mangooo.repository.GastoCorteRepository;
import com.huidos.mangooo.repository.ProductoRepository;
import com.huidos.mangooo.repository.VentaRepository;
import com.huidos.mangooo.service.reports.ReporteService;
import com.huidos.mangooo.service.reports.dto.ReporteOriginal;

@Controller
@SessionAttributes("usuarioObjSession")

public class LoginController {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private VentaRepository ventaRepository;
	@Autowired
	private GastoCorteRepository gastoCorteRepository;
	@Autowired
	private ReporteService reporteService;

	/**
	 * 
	 * @return goes to the welcomePage view
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("welcomePage");
		return model;
	}

	/**
	 * if the session expires this method is used
	 * 
	 * @return goes to the invalidSession page
	 */
	@RequestMapping(value = { "/invalidSession" }, method = RequestMethod.GET)
	public ModelAndView invalidSession() {
		ModelAndView model = new ModelAndView();
		model.setViewName("invalidSession");
		return model;
	}

	/**
	 * this controller set the information that will be included en the view
	 * homePage
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/homePage" }, method = RequestMethod.GET)
	public ModelAndView homePage(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		Usuario userSession = (Usuario) request.getSession().getAttribute("usuarioObjSession");
		UsuarioDto usersessionDto = convertToDto(userSession);
		List<ReporteOriginal> reporteSemanalVtas= reporteService.getVentasSemanal();
		reporteSemanalVtas.sort((left, right) -> left.getVariedades().compareTo( right.getVariedades()));
		model.setViewName("homePage");
		model.addObject("userSession", usersessionDto);
		model.addObject("totalProd", productoRepository.count());
		model.addObject("totalClients", clienteRepository.count());
		model.addObject("totalVentas", ventaRepository.count());
		model.addObject("totalGastoCorte", gastoCorteRepository.count());
		model.addObject("dashboardSemanalVentas", gastoCorteRepository.count());
		
		model.addObject("dashboardSemanalVentas", reporteSemanalVtas);
		model.addObject("fecha", new Date());

		return model;
	}

	/**
	 * this method is used by the spring security to validate the credentials of
	 * the user
	 * 
	 * @param error
	 * @param logout
	 * @return
	 */
	@RequestMapping(value = "/loginPage")
	public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid Credentials provided.");
		}

		if (logout != null) {
			model.addObject("message", "Logged out from JournalDEV successfully.");
		}

		model.setViewName("loginPage");
		return model;
	}

	/**
	 * this method is used to logout from the application invalidates the
	 * session then you should re login
	 * 
	 * @param request
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value = "/logout")
	public ModelAndView logoutMango(HttpServletRequest request) throws ServletException {
		HttpSession session = request.getSession();
		session.invalidate();
		ModelAndView model = new ModelAndView();
		model.setViewName("welcomePage");
		return model;
	}

	private UsuarioDto convertToDto(Usuario user) {
		UsuarioDto userDto = modelMapper.map(user, UsuarioDto.class);
		return userDto;
	}
}