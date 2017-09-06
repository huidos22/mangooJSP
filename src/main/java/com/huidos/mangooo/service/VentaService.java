package com.huidos.mangooo.service;
import java.math.BigDecimal;

/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huidos.mangooo.model.DetalleVenta;
import com.huidos.mangooo.model.DetalleVentaPK;
import com.huidos.mangooo.model.Venta;
import com.huidos.mangooo.model.dto.VentaDto;
import com.huidos.mangooo.repository.DetalleVentaRepository;
import com.huidos.mangooo.repository.GastoCorteRepository;
import com.huidos.mangooo.repository.VentaRepository;
@Service
public class VentaService {
	@Autowired
	private DetalleVentaRepository detalleVentaRepository;
	@Autowired
	private GastoCorteRepository gastoCorteRepository;
	@Autowired
	private VentaRepository ventaRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	public Venta createVenta(VentaDto ventaDto) {
		
		
		Venta nuevaVenta =convertToModelVenta(ventaDto);
		
		DetalleVenta detalleVenta = convertToModeldetalleVenta(ventaDto);
		DetalleVentaPK detVentaPK = convertToModeldetalleVentaPK(ventaDto);
		nuevaVenta.setRetProd( new BigDecimal(detalleVenta.getKilos()* (ventaDto.getRetProd().doubleValue()/100)));
		ventaRepository.save(nuevaVenta);
		detVentaPK.setIdVenta(nuevaVenta.getIdVenta());
		detalleVenta.setId(detVentaPK);
		detVentaPK.setIdGastoCorte(gastoCorteRepository.findOne(ventaDto.getGastoCorteDto().getIdGastoCorte()).getIdGastoCorte());
		detalleVentaRepository.save(detalleVenta);
		//ventaRepository.save(nuevaVenta);
		
		return nuevaVenta;
	}
	
	private Venta convertToModelVenta(VentaDto ventaDto) {
		Venta venta = modelMapper.map(ventaDto, Venta.class);
		return venta;
	}
	private DetalleVenta convertToModeldetalleVenta(VentaDto ventaDto) {
		DetalleVenta detalleVenta = modelMapper.map(ventaDto, DetalleVenta.class);
		return detalleVenta;
	}
	private DetalleVentaPK convertToModeldetalleVentaPK(VentaDto ventaDto) {
		DetalleVentaPK detalleVentaPK = modelMapper.map(ventaDto, DetalleVentaPK.class);
		return detalleVentaPK;
	}
}
