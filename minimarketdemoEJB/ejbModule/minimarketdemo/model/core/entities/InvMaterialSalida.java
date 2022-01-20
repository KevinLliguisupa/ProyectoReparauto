package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the inv_material_salida database table.
 * 
 */
@Entity
@Table(name="inv_material_salida")
@NamedQuery(name="InvMaterialSalida.findAll", query="SELECT i FROM InvMaterialSalida i")
public class InvMaterialSalida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mat_sal_id", unique=true, nullable=false)
	private Integer matSalId;

	@Column(name="mat_sal_cantidad", precision=6)
	private BigDecimal matSalCantidad;

	@Column(name="mat_sal_estado")
	private Boolean matSalEstado;

	@Column(name="mat_sal_precio", precision=7, scale=2)
	private BigDecimal matSalPrecio;

	//bi-directional many-to-one association to InvMaterial
	@ManyToOne
	@JoinColumn(name="mat_id")
	private InvMaterial invMaterial;

	//bi-directional many-to-one association to InvSalida
	@ManyToOne
	@JoinColumn(name="sal_id")
	private InvSalida invSalida;

	public InvMaterialSalida() {
	}

	public Integer getMatSalId() {
		return this.matSalId;
	}

	public void setMatSalId(Integer matSalId) {
		this.matSalId = matSalId;
	}

	public BigDecimal getMatSalCantidad() {
		return this.matSalCantidad;
	}

	public void setMatSalCantidad(BigDecimal matSalCantidad) {
		this.matSalCantidad = matSalCantidad;
	}

	public Boolean getMatSalEstado() {
		return this.matSalEstado;
	}

	public void setMatSalEstado(Boolean matSalEstado) {
		this.matSalEstado = matSalEstado;
	}

	public BigDecimal getMatSalPrecio() {
		return this.matSalPrecio;
	}

	public void setMatSalPrecio(BigDecimal matSalPrecio) {
		this.matSalPrecio = matSalPrecio;
	}

	public InvMaterial getInvMaterial() {
		return this.invMaterial;
	}

	public void setInvMaterial(InvMaterial invMaterial) {
		this.invMaterial = invMaterial;
	}

	public InvSalida getInvSalida() {
		return this.invSalida;
	}

	public void setInvSalida(InvSalida invSalida) {
		this.invSalida = invSalida;
	}
	public BigDecimal getTotalxMaterial() {
		return this.matSalCantidad.multiply(this.matSalPrecio);
 
	}

}