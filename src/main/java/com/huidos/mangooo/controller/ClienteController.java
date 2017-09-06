package com.huidos.mangooo.controller;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.huidos.mangooo.model.Cliente;
import com.huidos.mangooo.model.dto.ClienteDto;
import com.huidos.mangooo.model.dto.JqGridDataDto;
import com.huidos.mangooo.model.dto.JqgridFilter;
import com.huidos.mangooo.model.dto.JqgridObjectMapper;
import com.huidos.mangooo.repository.ClienteRepository;
import com.huidos.mangooo.validator.ClienteFormValidator;
@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ClienteFormValidator clienteFormValidator;
	@Autowired
	private ModelMapper modelMapper;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(clienteFormValidator);
	}

	// list page
	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	public String showAllClientes(Model model) {

		model.addAttribute("clientes", clienteRepository.findAll());
		return "clientes/list";

	}

	// save or update client
	@RequestMapping(value = "/clientes", method = RequestMethod.POST)
	public String saveOrUpdateCliente(@ModelAttribute("clienteForm") @Validated ClienteDto clienteDto,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			// populateDefaultModel(model);
			return "clientes/clienteForm";
		} else {

			redirectAttributes.addFlashAttribute("css", "success");
			if (clienteDto.isNew()) {
				redirectAttributes.addFlashAttribute("msg", "Cliente added successfully!");
			} else {
				redirectAttributes.addFlashAttribute("msg", "Cliente updated successfully!");
			}
			Cliente cliente = convertToEntity(clienteDto);
			clienteRepository.save(cliente);

			// POST/REDIRECT/GET
			return "redirect:/clientes/" + clienteDto.getIdCliente();

		}
	}

	// show add cliente form
	@RequestMapping(value = "/clientes/add", method = RequestMethod.GET)
	public String showAddClienteForm(Model model) {

		ClienteDto clientedto = new ClienteDto();
		// set default value
		model.addAttribute("clienteForm", clientedto);
		return "clientes/clienteForm";

	}

	// show update form
	@RequestMapping(value = "/clientes/{id}/update", method = RequestMethod.GET)
	public String showUpdateClienteForm(@PathVariable("id") Integer id, Model model) {

		Cliente cliente = clienteRepository.findOne(id);
		model.addAttribute("clienteForm", convertToDto(cliente));
		return "clientes/clienteForm";

	}

	// delete cliente
	@RequestMapping(value = "/clientes/{id}/delete", method = RequestMethod.GET)
	public String deleteCliente(@PathVariable("id") Integer id, final RedirectAttributes redirectAttributes) {

		Cliente cliente = clienteRepository.findOne(id);
		if(cliente.getVentas().isEmpty()){
			clienteRepository.delete(id);

			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Cliente fue eliminado!");
		}else{
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "No puede eliminar un cliente con ventas registradas");
		}
		
		

		return "redirect:/clientes";
	}

	// show cliente
	@RequestMapping(value = "/clientes/{id}", method = RequestMethod.GET)
	public String showCliente(@PathVariable("id") Integer id, Model model) {

		Cliente cliente = clienteRepository.findOne(id);
		if (cliente == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Cliente no encontrado");
		}
		model.addAttribute("cliente", convertToDto(cliente));
		return "clientes/show";

	}

	private ClienteDto convertToDto(Cliente cliente) {
		ClienteDto clienteDto = modelMapper.map(cliente, ClienteDto.class);
		return clienteDto;
	}

	private Cliente convertToEntity(ClienteDto clienteDto) {
		Cliente cliente = modelMapper.map(clienteDto, Cliente.class);

		if (clienteDto.getIdCliente() != null) {
			Cliente oldClienteDto = clienteRepository.findOne(clienteDto.getIdCliente());
			return oldClienteDto;
		}
		return cliente;
	}
	
	@RequestMapping(value="/recordsClientes.json",method = RequestMethod.GET,produces="application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public @ResponseBody JqGridDataDto<ClienteDto> records(
    		@RequestParam("_search") Boolean search,
    		@RequestParam(value="filters", required=false) String filters,
    		@RequestParam(value="page", required=false) Integer page,
    		@RequestParam(value="rows", required=false) Integer rows,
    		@RequestParam(value="sidx", required=false) String sidx,
    		@RequestParam(value="sord", required=false) String sord) {

		Pageable pageRequest = new PageRequest(page-1, rows);
		if (search == true) {
			return getFilteredRecords(filters, pageRequest);
			
		} 
		Page<Cliente> clientes =clienteRepository.findAll(pageRequest);
		List<ClienteDto> clientesDto  = new ArrayList<>();
		for (Cliente cliente : clientes) {
			clientesDto.add(convertToDto(cliente));
		}
		
		JqGridDataDto<ClienteDto> response = new JqGridDataDto<ClienteDto>();
		response.setRows(clientesDto);
		response.setRecords((int) clientes.getTotalElements());
		response.setTotal((int) clientes.getTotalPages());
		response.setPage((int) clientes.getNumber()+1);
		
		return response;
	}
	/**
	 * Helper method for returning filtered records
	 */
	private JqGridDataDto<ClienteDto> getFilteredRecords(String filters, Pageable pageRequest) {
		String qUsername = null;
		String qFirstName = null;
		String qLastName = null;
		Integer qRole = null;
		
		JqgridFilter jqgridFilter = JqgridObjectMapper.map(filters);
		return null;
	}
}
