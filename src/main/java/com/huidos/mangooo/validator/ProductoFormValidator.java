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

import com.huidos.mangooo.model.dto.ProductoDto;

public class ProductoFormValidator implements Validator {
	private static final String regexUserName = "^[A-Za-z\\s]+$";
	@Override
	public boolean supports(Class<?> clazz) {
		return ProductoDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductoDto productoDto = (ProductoDto) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idProducto", "NotEmpty.productoForm.idProducto");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.productoForm.nombre");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variedad", "NotEmpty.productoForm.variedad");
		
		if(!productoDto.getNombre().matches(regexUserName)){
			errors.rejectValue("nombre", "Pattern.productoForm.nombre");
			
		}
		if(!productoDto.getNombre().matches(regexUserName)){
			errors.rejectValue("variedad", "Pattern.productoForm.variedad");
			
		}
	}

}
