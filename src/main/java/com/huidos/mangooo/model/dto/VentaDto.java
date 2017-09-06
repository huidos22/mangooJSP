package com.huidos.mangooo.model.dto;
/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import java.math.BigDecimal;
import java.util.Date;

import com.huidos.mangooo.model.Estado;
import com.huidos.mangooo.model.Municipio;

public class VentaDto {
	private Integer idVenta;
	private Date fecha;
	private String observacion;
	private BigDecimal retProd;
	private ClienteDto cliente;
	private UsuarioDto usuario;
	private short idProducto;
	private short cajas;
	private String formaPago;
	private Estado estado;
	private Date fechaGastoCorte;
	private GastoCorteDto gastoCorteDto;
	private Short idGastoCorte;
	private Municipio municipio;
	private BigDecimal precioKilo;
	private double kilos;
	
	public VentaDto() {
		super();
	}
	public VentaDto(Integer idVenta, Date fecha, String observacion, BigDecimal retProd, ClienteDto cliente,
			UsuarioDto usuario, short idProducto, short cajas, String formaPago, Estado estado, Date fechaGastoCorte,
			BigDecimal gastoCortePercent, Short idGastoCorte, Municipio municipio, BigDecimal precioKilo,
			double kilos) {
		super();
		this.idVenta = idVenta;
		this.fecha = fecha;
		this.observacion = observacion;
		this.retProd = retProd;
		this.cliente = cliente;
		this.usuario = usuario;
		this.idProducto = idProducto;
		this.cajas = cajas;
		this.formaPago = formaPago;
		this.estado = estado;
		this.fechaGastoCorte = fechaGastoCorte;
		this.idGastoCorte = idGastoCorte;
		this.municipio = municipio;
		this.precioKilo = precioKilo;
		this.kilos = kilos;
	}
	public Integer getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public BigDecimal getRetProd() {
		return retProd;
	}
	public void setRetProd(BigDecimal retProd) {
		this.retProd = retProd;
	}
	public ClienteDto getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}
	public UsuarioDto getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}
	public short getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(short idProducto) {
		this.idProducto = idProducto;
	}
	public short getCajas() {
		return cajas;
	}
	public void setCajas(short cajas) {
		this.cajas = cajas;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Date getFechaGastoCorte() {
		return fechaGastoCorte;
	}
	public void setFechaGastoCorte(Date fechaGastoCorte) {
		this.fechaGastoCorte = fechaGastoCorte;
	}
	
	public Short getIdGastoCorte() {
		return idGastoCorte;
	}
	public void setIdGastoCorte(Short idGastoCorte) {
		this.idGastoCorte = idGastoCorte;
	}
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	public BigDecimal getPrecioKilo() {
		return precioKilo;
	}
	public void setPrecioKilo(BigDecimal precioKilo) {
		this.precioKilo = precioKilo;
	}
	public double getKilos() {
		return kilos;
	}
	public void setKilos(double kilos) {
		this.kilos = kilos;
	}
	public GastoCorteDto getGastoCorteDto() {
		return gastoCorteDto;
	}
	public void setGastoCorteDto(GastoCorteDto gastoCorteDto) {
		this.gastoCorteDto = gastoCorteDto;
	}

	
}
