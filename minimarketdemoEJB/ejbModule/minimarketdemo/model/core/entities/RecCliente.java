package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rec_cliente database table.
 * 
 */
@Entity
@Table(name="rec_cliente")
@NamedQuery(name="RecCliente.findAll", query="SELECT r FROM RecCliente r")
public class RecCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cli_id", unique=true, nullable=false)
	private Integer cliId;

	@Column(name="cli_cedula", length=10)
	private String cliCedula;

	@Column(name="cli_celular", length=10)
	private String cliCelular;

	@Column(name="cli_correo", length=30)
	private String cliCorreo;

	@Column(name="cli_direccion", length=50)
	private String cliDireccion;

	@Column(name="cli_estado")
	private Boolean cliEstado;

	@Column(name="cli_nombre_apellido", length=600)
	private String cliNombreApellido;

	@Column(name="cli_telefono", length=10)
	private String cliTelefono;

	//bi-directional many-to-one association to RecRecepcionCabecera
	@OneToMany(mappedBy="recCliente")
	private List<RecRecepcionCabecera> recRecepcionCabeceras;

	public RecCliente() {
	}

	public Integer getCliId() {
		return this.cliId;
	}

	public void setCliId(Integer cliId) {
		this.cliId = cliId;
	}

	public String getCliCedula() {
		return this.cliCedula;
	}

	public void setCliCedula(String cliCedula) {
		this.cliCedula = cliCedula;
	}

	public String getCliCelular() {
		return this.cliCelular;
	}

	public void setCliCelular(String cliCelular) {
		this.cliCelular = cliCelular;
	}

	public String getCliCorreo() {
		return this.cliCorreo;
	}

	public void setCliCorreo(String cliCorreo) {
		this.cliCorreo = cliCorreo;
	}

	public String getCliDireccion() {
		return this.cliDireccion;
	}

	public void setCliDireccion(String cliDireccion) {
		this.cliDireccion = cliDireccion;
	}

	public Boolean getCliEstado() {
		return this.cliEstado;
	}

	public void setCliEstado(Boolean cliEstado) {
		this.cliEstado = cliEstado;
	}

	public String getCliNombreApellido() {
		return this.cliNombreApellido;
	}

	public void setCliNombreApellido(String cliNombreApellido) {
		this.cliNombreApellido = cliNombreApellido;
	}

	public String getCliTelefono() {
		return this.cliTelefono;
	}

	public void setCliTelefono(String cliTelefono) {
		this.cliTelefono = cliTelefono;
	}

	public List<RecRecepcionCabecera> getRecRecepcionCabeceras() {
		return this.recRecepcionCabeceras;
	}

	public void setRecRecepcionCabeceras(List<RecRecepcionCabecera> recRecepcionCabeceras) {
		this.recRecepcionCabeceras = recRecepcionCabeceras;
	}

	public RecRecepcionCabecera addRecRecepcionCabecera(RecRecepcionCabecera recRecepcionCabecera) {
		getRecRecepcionCabeceras().add(recRecepcionCabecera);
		recRecepcionCabecera.setRecCliente(this);

		return recRecepcionCabecera;
	}

	public RecRecepcionCabecera removeRecRecepcionCabecera(RecRecepcionCabecera recRecepcionCabecera) {
		getRecRecepcionCabeceras().remove(recRecepcionCabecera);
		recRecepcionCabecera.setRecCliente(null);

		return recRecepcionCabecera;
	}

}