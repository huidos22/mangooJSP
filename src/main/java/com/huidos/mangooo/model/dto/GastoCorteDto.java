package com.huidos.mangooo.model.dto;
/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.huidos.mangooo.model.Estado;
import com.huidos.mangooo.model.Municipio;

public class GastoCorteDto {
	
	private Short idGastoCorte;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date fechaGastoCorte;
	private BigDecimal gastoCortePercent;
	private Estado estado;
	private Municipio municipio;

	// Check if this is for New of Update
	public boolean isNew() {
		return (this.idGastoCorte == null);
	}

	public Short getIdGastoCorte() {
		return idGastoCorte;
	}

	public void setIdGastoCorte(Short idGastoCorte) {
		this.idGastoCorte = idGastoCorte;
	}

	public Date getFechaGastoCorte() {
		return fechaGastoCorte;
	}

	public void setFechaGastoCorte(Date fechaGastoCorte) {
		this.fechaGastoCorte = fechaGastoCorte;
	}

	public BigDecimal getGastoCortePercent() {
		return gastoCortePercent;
	}

	public void setGastoCortePercent(BigDecimal gastoCortePercent) {
		this.gastoCortePercent = gastoCortePercent;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

}
