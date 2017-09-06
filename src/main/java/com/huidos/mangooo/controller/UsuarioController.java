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

import com.huidos.mangooo.model.Usuario;
import com.huidos.mangooo.model.dto.UsuarioDto;
import com.huidos.mangooo.repository.UsuarioRepository;
import com.huidos.mangooo.validator.UsuarioFormValidator;

//http://www.journaldev.com/3531/spring-mvc-hibernate-mysql-integration-crud-example-tutorial
//http://www.mkyong.com/spring-mvc/spring-mvc-form-handling-example/
//http://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
//http://krams915.blogspot.mx/2012/01/spring-mvc-31-jqgrid-and-spring-data_8761.html
@Controller
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioFormValidator usuarioFormValidator;
	@Autowired
	private ModelMapper modelMapper;

	// Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(usuarioFormValidator);
	}

	// list page
	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	public String showAllUsuarios(Model model) {
		model.addAttribute("usuarios", usuarioRepository.findAll());
		return "usuarios/list";
	}

	// save or update user
	@RequestMapping(value = "/usuarios", method = RequestMethod.POST)
	public String saveOrUpdateUsuario(@ModelAttribute("usuarioForm") @Validated UsuarioDto userDto,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "usuarios/usuarioForm";
		} else {
			redirectAttributes.addFlashAttribute("css", "success");
			Usuario user = convertToEntity(userDto);
			user = usuarioRepository.save(user);
			if (userDto.isNew()) {
				redirectAttributes.addFlashAttribute("msg", "Usuario agregado correctamente!");
			} else {
				redirectAttributes.addFlashAttribute("msg", "Usuario actualizado correctamente!");
			}
			return "redirect:/usuarios/" + user.getId();
		}
	}

	// show add user form
	@RequestMapping(value = "/usuarios/add", method = RequestMethod.GET)
	public String showAddUsuarioForm(Model model) {
		UsuarioDto userdto = new UsuarioDto();
		// set default value
		model.addAttribute("usuarioForm", userdto);
		return "usuarios/usuarioForm";
	}

	// show update form
	@RequestMapping(value = "/usuarios/{id}/update", method = RequestMethod.GET)
	public String showUpdateUsuarioForm(@PathVariable("id") Integer id, Model model) {
		Usuario user = usuarioRepository.findOne(id);
		model.addAttribute("usuarioForm", convertToDto(user));
		return "usuarios/usuarioForm";
	}

	// delete user
	@RequestMapping(value = "/usuarios/{id}/delete", method = RequestMethod.GET)
	public String deleteUsuario(@PathVariable("id") Integer id, final RedirectAttributes redirectAttributes) {
		if (usuarioRepository.findOne(id).getVentas().isEmpty()) {
			usuarioRepository.delete(id);
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Usuario fue eliminado!");
		} else {
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "No puede eliminar un usuario que ya realizo ventas");
		}

		return "redirect:/usuarios";
	}

	// show user
	@RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
	public String showUsuario(@PathVariable("id") Integer id, Model model) {
		Usuario user = usuarioRepository.findOne(id);
		if (user == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Usuario no encontrado");
		}
		model.addAttribute("usuario", convertToDto(user));
		return "usuarios/show";
	}

	private UsuarioDto convertToDto(Usuario user) {
		UsuarioDto userDto = modelMapper.map(user, UsuarioDto.class);
		return userDto;
	}

	private Usuario convertToEntity(UsuarioDto userDto) {
		Usuario usuario = modelMapper.map(userDto, Usuario.class);
		if (userDto.getId() != null) {
			Usuario oldUsuario = usuarioRepository.findByUserNameAndPassword(userDto.getUserName(),
					userDto.getPassword());
			return oldUsuario;
		}
		return usuario;
	}

}
