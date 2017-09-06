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

import com.huidos.mangooo.model.dto.VentaDto;

public class VentaFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return VentaDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		VentaDto venta =  (VentaDto) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cajas", "NotEmpty.ventaForm.cajas");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cliente", "NotEmpty.ventaForm.cliente");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "estado", "NotEmpty.ventaForm.estado");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fecha", "NotEmpty.ventaForm.fecha");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fechaGastoCorte", "NotEmpty.ventaForm.fechaGastoCorte");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cajas", "NotEmpty.ventaForm.cajas");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gastoCorteDto", "NotEmpty.ventaForm.gastoCortePercent");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kilos", "NotEmpty.ventaForm.kilos");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "municipio", "NotEmpty.ventaForm.municipio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "precioKilo", "NotEmpty.ventaForm.precioKilo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idProducto", "NotEmpty.ventaForm.producto");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "formaPago", "NotEmpty.ventaForm.formaPago");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idGastoCorte", "NotEmpty.ventaForm.idGastoCorte");
		
		
		if(venta.getFecha() == null ){
			errors.rejectValue("fecha", "NotEmpty.ventaForm.fecha");
		}
		if(venta.getCliente().getIdCliente() == null || venta.getCliente().getIdCliente()<=0){
			errors.rejectValue("cliente", "NotEmpty.ventaForm.cliente");
		}
		if( venta.getIdProducto()<=0){
			errors.rejectValue("idProducto", "NotEmpty.ventaForm.producto");
		}
		if(venta.getEstado().getIdEstado() == null || venta.getEstado().getIdEstado()<=0){
			errors.rejectValue("estado", "NotEmpty.ventaForm.estado");
		}
		if(venta.getMunicipio().getIdMunicipio() == null || venta.getMunicipio().getIdMunicipio()<=0){
			errors.rejectValue("municipio", "NotEmpty.ventaForm.municipio");
		}
		
		if(venta.getCajas()<=0){
			errors.rejectValue("cajas", "NotEmpty.ventaForm.cajas");
		}
		if(venta.getKilos()<=0){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kilos", "NotEmpty.ventaForm.kilos");
		}
		/*if(venta.getIdGastoCorte()<=0){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idGastoCorte", "NotEmpty.ventaForm.idGastoCorte");
		}*/
		
		
		
		

	}

}
