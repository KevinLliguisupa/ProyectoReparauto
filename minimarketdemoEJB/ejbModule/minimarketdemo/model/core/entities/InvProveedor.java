package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the inv_proveedor database table.
 *
 */
@Entity
@Table(name = "inv_proveedor")
@NamedQuery(name = "InvProveedor.findAll", query = "SELECT i FROM InvProveedor i")
public class InvProveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pro_id", unique = true, nullable = false)
	private Integer proId;

	@Column(name = "pro_correo", length = 30)
	private String proCorreo;

	@Column(name = "pro_estado")
	private Boolean proEstado;

	@Column(name = "pro_nombre", length = 50)
	private String proNombre;

	@Column(name = "pro_telefono", length = 10)
	private String proTelefono;

	// bi-directional many-to-one association to InvIngreso
	@OneToMany(mappedBy = "invProveedor")
	private List<InvIngreso> invIngresos;

	public InvProveedor() {
	}

	public Integer getProId() {
		return this.proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public String getProCorreo() {
		return this.proCorreo;
	}

	public void setProCorreo(String proCorreo) {
		this.proCorreo = proCorreo;
	}

	public Boolean getProEstado() {
		return this.proEstado;
	}

	public void setProEstado(Boolean proEstado) {
		this.proEstado = proEstado;
	}

	public String getProNombre() {
		return this.proNombre;
	}

	public void setProNombre(String proNombre) {
		this.proNombre = proNombre;
	}

	public String getProTelefono() {
		return this.proTelefono;
	}

	public void setProTelefono(String proTelefono) {
		this.proTelefono = proTelefono;
	}

	public List<InvIngreso> getInvIngresos() {
		return this.invIngresos;
	}

	public void setInvIngresos(List<InvIngreso> invIngresos) {
		this.invIngresos = invIngresos;
	}

	public InvIngreso addInvIngreso(InvIngreso invIngreso) {
		getInvIngresos().add(invIngreso);
		invIngreso.setInvProveedor(this);

		return invIngreso;
	}

	public InvIngreso removeInvIngreso(InvIngreso invIngreso) {
		getInvIngresos().remove(invIngreso);
		invIngreso.setInvProveedor(null);

		return invIngreso;
	}

}