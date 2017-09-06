package com.huidos.mangooo.model;
/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the detalle_venta database table.
 * 
 */
@Entity
@Table(name="detalle_venta")
@NamedQuery(name="DetalleVenta.findAll", query="SELECT d FROM DetalleVenta d")
public class DetalleVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleVentaPK id;

	private short cajas;

	@Column(name="forma_pago")
	private String formaPago;

	private double kilos;

	@Column(name="precio_kilo")
	private BigDecimal precioKilo;

	@Column(name="ret_prod")
	private BigDecimal retProd;


	public DetalleVenta() {
	}

	public DetalleVentaPK getId() {
		return this.id;
	}

	public void setId(DetalleVentaPK id) {
		this.id = id;
	}

	public short getCajas() {
		return this.cajas;
	}

	public void setCajas(short cajas) {
		this.cajas = cajas;
	}

	public String getFormaPago() {
		return this.formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public double getKilos() {
		return this.kilos;
	}

	public void setKilos(double kilos) {
		this.kilos = kilos;
	}

	public BigDecimal getPrecioKilo() {
		return this.precioKilo;
	}

	public void setPrecioKilo(BigDecimal precioKilo) {
		this.precioKilo = precioKilo;
	}

	public BigDecimal getRetProd() {
		return this.retProd;
	}

	public void setRetProd(BigDecimal retProd) {
		this.retProd = retProd;
	}



}