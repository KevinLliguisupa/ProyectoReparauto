package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inv_ingreso database table.
 * 
 */
@Entity
@Table(name="inv_ingreso")
@NamedQuery(name="InvIngreso.findAll", query="SELECT i FROM InvIngreso i")
public class InvIngreso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ing_id", unique=true, nullable=false)
	private Integer ingId;

	@Temporal(TemporalType.DATE)
	@Column(name="ing_fecha")
	private Date ingFecha;

	//bi-directional many-to-one association to InvProveedor
	@ManyToOne
	@JoinColumn(name="pro_id")
	private InvProveedor invProveedor;

	//bi-directional many-to-one association to InvMaterialIngreso
	@OneToMany(mappedBy="invIngreso")
	private List<InvMaterialIngreso> invMaterialIngresos;

	public InvIngreso() {
	}

	public Integer getIngId() {
		return this.ingId;
	}

	public void setIngId(Integer ingId) {
		this.ingId = ingId;
	}

	public Date getIngFecha() {
		return this.ingFecha;
	}

	public void setIngFecha(Date ingFecha) {
		this.ingFecha = ingFecha;
	}

	public InvProveedor getInvProveedor() {
		return this.invProveedor;
	}

	public void setInvProveedor(InvProveedor invProveedor) {
		this.invProveedor = invProveedor;
	}

	public List<InvMaterialIngreso> getInvMaterialIngresos() {
		return this.invMaterialIngresos;
	}

	public void setInvMaterialIngresos(List<InvMaterialIngreso> invMaterialIngresos) {
		this.invMaterialIngresos = invMaterialIngresos;
	}

	public InvMaterialIngreso addInvMaterialIngreso(InvMaterialIngreso invMaterialIngreso) {
		getInvMaterialIngresos().add(invMaterialIngreso);
		invMaterialIngreso.setInvIngreso(this);

		return invMaterialIngreso;
	}

	public InvMaterialIngreso removeInvMaterialIngreso(InvMaterialIngreso invMaterialIngreso) {
		getInvMaterialIngresos().remove(invMaterialIngreso);
		invMaterialIngreso.setInvIngreso(null);

		return invMaterialIngreso;
	}

}