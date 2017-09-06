package com.huidos.mangooo.controller;
import java.math.BigDecimal;
/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import com.huidos.mangooo.model.GastoCorte;
import com.huidos.mangooo.model.dto.GastoCorteDto;
import com.huidos.mangooo.repository.GastoCorteRepository;
import com.huidos.mangooo.repository.MunicipioRepository;
import com.huidos.mangooo.service.EstadosService;
import com.huidos.mangooo.validator.GastoCorteFormValidator;

@Controller
public class GastoCorteController {

	@Autowired
	private EstadosService estadoService;
	@Autowired
	private GastoCorteRepository gastoCorteRepository;
	@Autowired
	private GastoCorteFormValidator gastoCorteFormValidator;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private MunicipioRepository municipioRepository;
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		  binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.setValidator(gastoCorteFormValidator);
	}

	// list page
	@RequestMapping(value = "/gastoCorte", method = RequestMethod.GET)
	public String showAllGastoCortes(Model model) {

		model.addAttribute("gastoCorte", gastoCorteRepository.findAll());
		return "gastoCorte/list";

	}

	// save or update gastoCorte
	@RequestMapping(value = "/gastoCorte", method = RequestMethod.POST)
	public String saveOrUpdateGastoCorte(@ModelAttribute("gastoCorteForm") @Validated GastoCorteDto gastoCorteDto,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			// populateDefaultModel(model);
			return "gastoCorte/gastoCorteForm";
		} else {
			
			GastoCorte gastoCorte = convertToEntity(gastoCorteDto);
			if(null!=gastoCorte.getGastoCortePercent()){
			gastoCorte.setGastoCortePercent(new BigDecimal(gastoCorte.getGastoCortePercent().doubleValue()/100));
			}else{
				gastoCorte.setGastoCortePercent(new BigDecimal(0d));
			}
			gastoCorteRepository.save(gastoCorte);
			
			redirectAttributes.addFlashAttribute("css", "success");
			if (gastoCorteDto.isNew()) {
				redirectAttributes.addFlashAttribute("msg", "GastoCorte agregado correctamente!");
			} else {
				redirectAttributes.addFlashAttribute("msg", "GastoCorte actualizado correctamente!");
			}
			

			// POST/REDIRECT/GET
			return "redirect:/gastoCorte/" + gastoCorte.getIdGastoCorte();

		}
	}

	// show add gastoCorte form
	@RequestMapping(value = "/gastoCorte/add", method = RequestMethod.GET)
	public String showAddGastoCorteForm(Model model) {

		GastoCorteDto gastoCortedto = new GastoCorteDto();
		// set default value
		model.addAttribute("estadosList", estadoService.getEstados());
		
		model.addAttribute("gastoCorteForm", gastoCortedto);
		return "gastoCorte/gastoCorteForm";

	}

	// show update form
	@RequestMapping(value = "/gastoCorte/{id}/update", method = RequestMethod.GET)
	public String showUpdateGastoCorteForm(@PathVariable("id") Short id, Model model) {

		GastoCorte gastoCorte = gastoCorteRepository.findOne(id);
		model.addAttribute("estadosList", estadoService.getEstados());
		model.addAttribute("municipiosList", municipioRepository.findAllByEstado(gastoCorte.getEstado()));
		model.addAttribute("gastoCorteForm", convertToDto(gastoCorte));
		
		
		return "gastoCorte/gastoCorteForm";

	}

	// delete gastoCorte
	@RequestMapping(value = "/gastoCorte/{id}/delete", method = RequestMethod.GET)
	public String deleteGastoCorte(@PathVariable("id") Short id, final RedirectAttributes redirectAttributes) {

		gastoCorteRepository.delete(id);

		redirectAttributes.addFlashAttribute("css", "success");
		redirectAttributes.addFlashAttribute("msg", "GastoCorte fue eliminado!");

		return "redirect:/gastoCorte";
	}

	// show gastoCorte
	@RequestMapping(value = "/gastoCorte/{id}", method = RequestMethod.GET)
	public String showGastoCorte(@PathVariable("id") Short id, Model model) {

		GastoCorte gastoCorte = gastoCorteRepository.findOne(id);
		if (gastoCorte == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "GastoCorte no encontrado");
		}
		model.addAttribute("gastoCorte", convertToDto(gastoCorte));
		return "gastoCorte/show";

	}

	private GastoCorteDto convertToDto(GastoCorte gastoCorte) {
		GastoCorteDto gastoCorteDto = modelMapper.map(gastoCorte, GastoCorteDto.class);
		return gastoCorteDto;
	}

	private GastoCorte convertToEntity(GastoCorteDto gastoCorteDto) {
		GastoCorte gastoCorte = modelMapper.map(gastoCorteDto, GastoCorte.class);

		if (gastoCorteDto.getIdGastoCorte() != null) {
			GastoCorte oldGastoCorteDto = gastoCorteRepository.findOne(gastoCorteDto.getIdGastoCorte());
			return oldGastoCorteDto;
		}
		return gastoCorte;
	}
}
