package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rec_vehiculos database table.
 * 
 */
@Entity
@Table(name="rec_vehiculos")
@NamedQuery(name="RecVehiculo.findAll", query="SELECT r FROM RecVehiculo r")
public class RecVehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="veh_id", unique=true, nullable=false)
	private Integer vehId;

	@Column(name="veh_anio", length=5)
	private String vehAnio;

	@Column(name="veh_clave", length=10)
	private String vehClave;

	@Column(name="veh_color", length=20)
	private String vehColor;

	@Column(name="veh_estado")
	private Boolean vehEstado;

	@Column(name="veh_kilometraje")
	private Integer vehKilometraje;

	@Column(name="veh_marca", length=20)
	private String vehMarca;

	@Column(name="veh_motor", length=20)
	private String vehMotor;

	@Column(name="veh_nivel_combustible", length=2147483647)
	private String vehNivelCombustible;

	@Column(name="veh_numero_chasis", length=10)
	private String vehNumeroChasis;

	@Column(name="veh_placa", length=20)
	private String vehPlaca;

	//bi-directional many-to-one association to InvSalida
	@OneToMany(mappedBy="recVehiculo")
	private List<InvSalida> invSalidas;

	//bi-directional many-to-one association to RecRecepcionCabecera
	@OneToMany(mappedBy="recVehiculo")
	private List<RecRecepcionCabecera> recRecepcionCabeceras;

	//bi-directional many-to-one association to RecVehiculoExtra
	@OneToMany(mappedBy="recVehiculo")
	private List<RecVehiculoExtra> recVehiculoExtras;

	public RecVehiculo() {
	}

	public Integer getVehId() {
		return this.vehId;
	}

	public void setVehId(Integer vehId) {
		this.vehId = vehId;
	}

	public String getVehAnio() {
		return this.vehAnio;
	}

	public void setVehAnio(String vehAnio) {
		this.vehAnio = vehAnio;
	}

	public String getVehClave() {
		return this.vehClave;
	}

	public void setVehClave(String vehClave) {
		this.vehClave = vehClave;
	}

	public String getVehColor() {
		return this.vehColor;
	}

	public void setVehColor(String vehColor) {
		this.vehColor = vehColor;
	}

	public Boolean getVehEstado() {
		return this.vehEstado;
	}

	public void setVehEstado(Boolean vehEstado) {
		this.vehEstado = vehEstado;
	}

	public Integer getVehKilometraje() {
		return this.vehKilometraje;
	}

	public void setVehKilometraje(Integer vehKilometraje) {
		this.vehKilometraje = vehKilometraje;
	}

	public String getVehMarca() {
		return this.vehMarca;
	}

	public void setVehMarca(String vehMarca) {
		this.vehMarca = vehMarca;
	}

	public String getVehMotor() {
		return this.vehMotor;
	}

	public void setVehMotor(String vehMotor) {
		this.vehMotor = vehMotor;
	}

	public String getVehNivelCombustible() {
		return this.vehNivelCombustible;
	}

	public void setVehNivelCombustible(String vehNivelCombustible) {
		this.vehNivelCombustible = vehNivelCombustible;
	}

	public String getVehNumeroChasis() {
		return this.vehNumeroChasis;
	}

	public void setVehNumeroChasis(String vehNumeroChasis) {
		this.vehNumeroChasis = vehNumeroChasis;
	}

	public String getVehPlaca() {
		return this.vehPlaca;
	}

	public void setVehPlaca(String vehPlaca) {
		this.vehPlaca = vehPlaca;
	}

	public List<InvSalida> getInvSalidas() {
		return this.invSalidas;
	}

	public void setInvSalidas(List<InvSalida> invSalidas) {
		this.invSalidas = invSalidas;
	}

	public InvSalida addInvSalida(InvSalida invSalida) {
		getInvSalidas().add(invSalida);
		invSalida.setRecVehiculo(this);

		return invSalida;
	}

	public InvSalida removeInvSalida(InvSalida invSalida) {
		getInvSalidas().remove(invSalida);
		invSalida.setRecVehiculo(null);

		return invSalida;
	}

	public List<RecRecepcionCabecera> getRecRecepcionCabeceras() {
		return this.recRecepcionCabeceras;
	}

	public void setRecRecepcionCabeceras(List<RecRecepcionCabecera> recRecepcionCabeceras) {
		this.recRecepcionCabeceras = recRecepcionCabeceras;
	}

	public RecRecepcionCabecera addRecRecepcionCabecera(RecRecepcionCabecera recRecepcionCabecera) {
		getRecRecepcionCabeceras().add(recRecepcionCabecera);
		recRecepcionCabecera.setRecVehiculo(this);

		return recRecepcionCabecera;
	}

	public RecRecepcionCabecera removeRecRecepcionCabecera(RecRecepcionCabecera recRecepcionCabecera) {
		getRecRecepcionCabeceras().remove(recRecepcionCabecera);
		recRecepcionCabecera.setRecVehiculo(null);

		return recRecepcionCabecera;
	}

	public List<RecVehiculoExtra> getRecVehiculoExtras() {
		return this.recVehiculoExtras;
	}

	public void setRecVehiculoExtras(List<RecVehiculoExtra> recVehiculoExtras) {
		this.recVehiculoExtras = recVehiculoExtras;
	}

	public RecVehiculoExtra addRecVehiculoExtra(RecVehiculoExtra recVehiculoExtra) {
		getRecVehiculoExtras().add(recVehiculoExtra);
		recVehiculoExtra.setRecVehiculo(this);

		return recVehiculoExtra;
	}

	public RecVehiculoExtra removeRecVehiculoExtra(RecVehiculoExtra recVehiculoExtra) {
		getRecVehiculoExtras().remove(recVehiculoExtra);
		recVehiculoExtra.setRecVehiculo(null);

		return recVehiculoExtra;
	}

}