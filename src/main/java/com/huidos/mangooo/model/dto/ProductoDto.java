package com.huidos.mangooo.model.dto;
/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
public class ProductoDto {
	private Short idProducto;

	private String nombre;

	private String variedad;

	public ProductoDto() {
		super();
	}

	// Check if this is for New of Update
	public boolean isNew() {
		return (this.idProducto == null);
	}

	public Short getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Short idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getVariedad() {
		return variedad;
	}

	public void setVariedad(String variedad) {
		this.variedad = variedad;
	}

}
