package com.huidos.mangooo.validator;
/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.huidos.mangooo.model.dto.GastoCorteDto;

public class GastoCorteFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return GastoCorteDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		GastoCorteDto gastoCorte = (GastoCorteDto) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaGastoCorte", "NotEmpty.gastoCorteForm.fechaGastoCorte");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "estado", "NotEmpty.gastoCorteForm.estado");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "municipio", "NotEmpty.gastoCorteForm.municipio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gastoCortePercent", "NotEmpty.gastoCorteForm.gastoCortePercent");
		
		if(gastoCorte.getFechaGastoCorte()==null){
			errors.rejectValue("fechaGastoCorte", "NotEmpty.gastoCorteForm.fechaGastoCorte");
		}
		if(gastoCorte.getFechaGastoCorte().getTime()>new Date().getTime()){
			errors.rejectValue("fechaGastoCorte", "NotGreatherThan.gastoCorteForm.fechaGastoCorte");
		}
		
		if(gastoCorte.getEstado().getIdEstado()<=0){
			errors.rejectValue("estado", "NotEmpty.gastoCorteForm.estado");
		}
		if(gastoCorte.getMunicipio().getIdMunicipio()<=0){
			errors.rejectValue("municipio", "NotEmpty.gastoCorteForm.municipio");
		}
		if(gastoCorte.getGastoCortePercent()== null ||gastoCorte.getGastoCortePercent().doubleValue()<=0){
			errors.rejectValue("gastoCortePercent", "NotEmpty.gastoCorteForm.gastoCortePercent");
		}
	}

}
