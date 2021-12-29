package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inv_salida database table.
 * 
 */
@Entity
@Table(name="inv_salida")
@NamedQuery(name="InvSalida.findAll", query="SELECT i FROM InvSalida i")
public class InvSalida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sal_id", unique=true, nullable=false)
	private Integer salId;

	@Temporal(TemporalType.DATE)
	@Column(name="sal_fecha")
	private Date salFecha;

	//bi-directional many-to-one association to InvMaterialSalida
	@OneToMany(mappedBy="invSalida")
	private List<InvMaterialSalida> invMaterialSalidas;

	//bi-directional many-to-one association to RecVehiculo
	@ManyToOne
	@JoinColumn(name="veh_id_rec_vehiculos")
	private RecVehiculo recVehiculo;

	//bi-directional many-to-one association to ThmEmpleado
	@ManyToOne
	@JoinColumn(name="id_thm_empleado_thm_empleado")
	private ThmEmpleado thmEmpleado;

	public InvSalida() {
	}

	public Integer getSalId() {
		return this.salId;
	}

	public void setSalId(Integer salId) {
		this.salId = salId;
	}

	public Date getSalFecha() {
		return this.salFecha;
	}

	public void setSalFecha(Date salFecha) {
		this.salFecha = salFecha;
	}

	public List<InvMaterialSalida> getInvMaterialSalidas() {
		return this.invMaterialSalidas;
	}

	public void setInvMaterialSalidas(List<InvMaterialSalida> invMaterialSalidas) {
		this.invMaterialSalidas = invMaterialSalidas;
	}

	public InvMaterialSalida addInvMaterialSalida(InvMaterialSalida invMaterialSalida) {
		getInvMaterialSalidas().add(invMaterialSalida);
		invMaterialSalida.setInvSalida(this);

		return invMaterialSalida;
	}

	public InvMaterialSalida removeInvMaterialSalida(InvMaterialSalida invMaterialSalida) {
		getInvMaterialSalidas().remove(invMaterialSalida);
		invMaterialSalida.setInvSalida(null);

		return invMaterialSalida;
	}

	public RecVehiculo getRecVehiculo() {
		return this.recVehiculo;
	}

	public void setRecVehiculo(RecVehiculo recVehiculo) {
		this.recVehiculo = recVehiculo;
	}

	public ThmEmpleado getThmEmpleado() {
		return this.thmEmpleado;
	}

	public void setThmEmpleado(ThmEmpleado thmEmpleado) {
		this.thmEmpleado = thmEmpleado;
	}

}