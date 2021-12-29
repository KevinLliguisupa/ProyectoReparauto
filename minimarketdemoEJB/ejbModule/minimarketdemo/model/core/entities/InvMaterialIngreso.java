package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the inv_material_ingreso database table.
 * 
 */
@Entity
@Table(name="inv_material_ingreso")
@NamedQuery(name="InvMaterialIngreso.findAll", query="SELECT i FROM InvMaterialIngreso i")
public class InvMaterialIngreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mat_ent_id", unique=true, nullable=false)
	private Integer matEntId;

	@Column(name="mat_ing_cantidad", precision=6)
	private BigDecimal matIngCantidad;

	@Column(name="mat_ing_precio_compra", precision=7, scale=2)
	private BigDecimal matIngPrecioCompra;

	@Column(name="\"mat_ing-estado\"")
	private Boolean matIng_estado;

	//bi-directional many-to-one association to InvIngreso
	@ManyToOne
	@JoinColumn(name="ing_id")
	private InvIngreso invIngreso;

	//bi-directional many-to-one association to InvMaterial
	@ManyToOne
	@JoinColumn(name="mat_id")
	private InvMaterial invMaterial;

	public InvMaterialIngreso() {
	}

	public Integer getMatEntId() {
		return this.matEntId;
	}

	public void setMatEntId(Integer matEntId) {
		this.matEntId = matEntId;
	}

	public BigDecimal getMatIngCantidad() {
		return this.matIngCantidad;
	}

	public void setMatIngCantidad(BigDecimal matIngCantidad) {
		this.matIngCantidad = matIngCantidad;
	}

	public BigDecimal getMatIngPrecioCompra() {
		return this.matIngPrecioCompra;
	}

	public void setMatIngPrecioCompra(BigDecimal matIngPrecioCompra) {
		this.matIngPrecioCompra = matIngPrecioCompra;
	}

	public Boolean getMatIng_estado() {
		return this.matIng_estado;
	}

	public void setMatIng_estado(Boolean matIng_estado) {
		this.matIng_estado = matIng_estado;
	}

	public InvIngreso getInvIngreso() {
		return this.invIngreso;
	}

	public void setInvIngreso(InvIngreso invIngreso) {
		this.invIngreso = invIngreso;
	}

	public InvMaterial getInvMaterial() {
		return this.invMaterial;
	}

	public void setInvMaterial(InvMaterial invMaterial) {
		this.invMaterial = invMaterial;
	}

}