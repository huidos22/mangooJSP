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
import java.util.Date;


/**
 * The persistent class for the gasto_corte database table.
 * 
 */
@Entity
@Table(name="gasto_corte")
@NamedQuery(name="GastoCorte.findAll", query="SELECT g FROM GastoCorte g")
public class GastoCorte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_gasto_corte" ,nullable=false, unique=true)
	private Short idGastoCorte;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_gasto_corte")
	private Date fechaGastoCorte;

	@Column(name="gasto_corte_percent")
	private BigDecimal gastoCortePercent;

	//uni-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private Estado estado;

	//uni-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name="id_municipio")
	private Municipio municipio;

	public GastoCorte() {
	}

	public Short getIdGastoCorte() {
		return this.idGastoCorte;
	}

	public void setIdGastoCorte(Short idGastoCorte) {
		this.idGastoCorte = idGastoCorte;
	}

	public Date getFechaGastoCorte() {
		return this.fechaGastoCorte;
	}

	public void setFechaGastoCorte(Date fechaGastoCorte) {
		this.fechaGastoCorte = fechaGastoCorte;
	}

	public BigDecimal getGastoCortePercent() {
		return this.gastoCortePercent;
	}

	public void setGastoCortePercent(BigDecimal gastoCortePercent) {
		this.gastoCortePercent = gastoCortePercent;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

}