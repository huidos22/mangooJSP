package com.huidos.mangooo.model;
/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the detalle_venta database table.
 * 
 */
@Embeddable
public class DetalleVentaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_gasto_corte", insertable=false, updatable=false)
	private short idGastoCorte;

	@Column(name="id_venta", insertable=false, updatable=false)
	private Integer idVenta;

	@Column(name="id_producto", insertable=false, updatable=false)
	private short idProducto;

	public DetalleVentaPK() {
	}
	public short getIdGastoCorte() {
		return this.idGastoCorte;
	}
	public void setIdGastoCorte(short idGastoCorte) {
		this.idGastoCorte = idGastoCorte;
	}
	public Integer getIdVenta() {
		return this.idVenta;
	}
	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}
	public short getIdProducto() {
		return this.idProducto;
	}
	public void setIdProducto(short idProducto) {
		this.idProducto = idProducto;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetalleVentaPK)) {
			return false;
		}
		DetalleVentaPK castOther = (DetalleVentaPK)other;
		return 
			(this.idGastoCorte == castOther.idGastoCorte)
			&& this.idVenta.equals(castOther.idVenta)
			&& (this.idProducto == castOther.idProducto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) this.idGastoCorte);
		hash = hash * prime + this.idVenta.hashCode();
		hash = hash * prime + ((int) this.idProducto);
		
		return hash;
	}
}