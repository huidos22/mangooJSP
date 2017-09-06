package com.huidos.mangooo.validator;
/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.huidos.mangooo.model.dto.ClienteDto;

public class ClienteFormValidator implements Validator {

	private String RFC_REGEX = "[A-Z,Ñ,&]{3,4}[0-9]{2}[0-1][0-9][0-3][0-9][A-Z,0-9]?[A-Z,0-9]?[0-9,A-Z]?";
	@Override
	public boolean supports(Class<?> clazz) {
		return ClienteDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ClienteDto cliente = (ClienteDto)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "direccion", "NotEmpty.clienteForm.direccion");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.clienteForm.nombre");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rfc", "NotEmpty.clienteForm.rfc");
		
		if(!cliente.getRfc().matches(RFC_REGEX)){
			errors.rejectValue("rfc", "Pattern.clienteForm.rfc");
		}
		if(cliente.getObservacion() != null && cliente.getObservacion().length() > 100){
			errors.rejectValue("observacion", "Length.clienteForm.observacion");
		}

	}

}
