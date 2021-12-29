package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the inv_tipo database table.
 * 
 */
@Entity
@Table(name="inv_tipo")
@NamedQuery(name="InvTipo.findAll", query="SELECT i FROM InvTipo i")
public class InvTipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tip_id", unique=true, nullable=false)
	private Integer tipId;

	@Column(name="tip_estado")
	private Boolean tipEstado;

	@Column(name="tip_nombre", length=25)
	private String tipNombre;

	//bi-directional many-to-one association to InvMaterial
	@OneToMany(mappedBy="invTipo")
	private List<InvMaterial> invMaterials;

	public InvTipo() {
	}

	public Integer getTipId() {
		return this.tipId;
	}

	public void setTipId(Integer tipId) {
		this.tipId = tipId;
	}

	public Boolean getTipEstado() {
		return this.tipEstado;
	}

	public void setTipEstado(Boolean tipEstado) {
		this.tipEstado = tipEstado;
	}

	public String getTipNombre() {
		return this.tipNombre;
	}

	public void setTipNombre(String tipNombre) {
		this.tipNombre = tipNombre;
	}

	public List<InvMaterial> getInvMaterials() {
		return this.invMaterials;
	}

	public void setInvMaterials(List<InvMaterial> invMaterials) {
		this.invMaterials = invMaterials;
	}

	public InvMaterial addInvMaterial(InvMaterial invMaterial) {
		getInvMaterials().add(invMaterial);
		invMaterial.setInvTipo(this);

		return invMaterial;
	}

	public InvMaterial removeInvMaterial(InvMaterial invMaterial) {
		getInvMaterials().remove(invMaterial);
		invMaterial.setInvTipo(null);

		return invMaterial;
	}

}