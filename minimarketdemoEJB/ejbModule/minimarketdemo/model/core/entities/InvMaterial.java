package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the inv_material database table.
 * 
 */
@Entity
@Table(name="inv_material")
@NamedQuery(name="InvMaterial.findAll", query="SELECT i FROM InvMaterial i")
public class InvMaterial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mat_id", unique=true, nullable=false)
	private Integer matId;

	@Column(name="mat_estado")
	private Boolean matEstado;

	@Column(name="mat_existencia", precision=6)
	private BigDecimal matExistencia;

	@Column(name="mat_imagen", length=2147483647)
	private String matImagen;

	@Column(name="mat_nombre", length=50)
	private String matNombre;

	@Column(name="mat_precio_venta", precision=7, scale=2)
	private BigDecimal matPrecioVenta;

	@Column(name="mat_unidad_medida", length=10)
	private String matUnidadMedida;

	//bi-directional many-to-one association to InvTipo
	@ManyToOne
	@JoinColumn(name="tip_id")
	private InvTipo invTipo;

	//bi-directional many-to-one association to InvMaterialIngreso
	@OneToMany(mappedBy="invMaterial")
	private List<InvMaterialIngreso> invMaterialIngresos;

	//bi-directional many-to-one association to InvMaterialSalida
	@OneToMany(mappedBy="invMaterial")
	private List<InvMaterialSalida> invMaterialSalidas;

	public InvMaterial() {
	}

	public Integer getMatId() {
		return this.matId;
	}

	public void setMatId(Integer matId) {
		this.matId = matId;
	}

	public Boolean getMatEstado() {
		return this.matEstado;
	}

	public void setMatEstado(Boolean matEstado) {
		this.matEstado = matEstado;
	}

	public BigDecimal getMatExistencia() {
		return this.matExistencia;
	}

	public void setMatExistencia(BigDecimal matExistencia) {
		this.matExistencia = matExistencia;
	}

	public String getMatImagen() {
		return this.matImagen;
	}

	public void setMatImagen(String matImagen) {
		this.matImagen = matImagen;
	}

	public String getMatNombre() {
		return this.matNombre;
	}

	public void setMatNombre(String matNombre) {
		this.matNombre = matNombre;
	}

	public BigDecimal getMatPrecioVenta() {
		return this.matPrecioVenta;
	}

	public void setMatPrecioVenta(BigDecimal matPrecioVenta) {
		this.matPrecioVenta = matPrecioVenta;
	}

	public String getMatUnidadMedida() {
		return this.matUnidadMedida;
	}

	public void setMatUnidadMedida(String matUnidadMedida) {
		this.matUnidadMedida = matUnidadMedida;
	}

	public InvTipo getInvTipo() {
		return this.invTipo;
	}

	public void setInvTipo(InvTipo invTipo) {
		this.invTipo = invTipo;
	}

	public List<InvMaterialIngreso> getInvMaterialIngresos() {
		return this.invMaterialIngresos;
	}

	public void setInvMaterialIngresos(List<InvMaterialIngreso> invMaterialIngresos) {
		this.invMaterialIngresos = invMaterialIngresos;
	}

	public InvMaterialIngreso addInvMaterialIngreso(InvMaterialIngreso invMaterialIngreso) {
		getInvMaterialIngresos().add(invMaterialIngreso);
		invMaterialIngreso.setInvMaterial(this);

		return invMaterialIngreso;
	}

	public InvMaterialIngreso removeInvMaterialIngreso(InvMaterialIngreso invMaterialIngreso) {
		getInvMaterialIngresos().remove(invMaterialIngreso);
		invMaterialIngreso.setInvMaterial(null);

		return invMaterialIngreso;
	}

	public List<InvMaterialSalida> getInvMaterialSalidas() {
		return this.invMaterialSalidas;
	}

	public void setInvMaterialSalidas(List<InvMaterialSalida> invMaterialSalidas) {
		this.invMaterialSalidas = invMaterialSalidas;
	}

	public InvMaterialSalida addInvMaterialSalida(InvMaterialSalida invMaterialSalida) {
		getInvMaterialSalidas().add(invMaterialSalida);
		invMaterialSalida.setInvMaterial(this);

		return invMaterialSalida;
	}

	public InvMaterialSalida removeInvMaterialSalida(InvMaterialSalida invMaterialSalida) {
		getInvMaterialSalidas().remove(invMaterialSalida);
		invMaterialSalida.setInvMaterial(null);

		return invMaterialSalida;
	}

}