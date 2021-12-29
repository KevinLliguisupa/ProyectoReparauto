package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rec_vehiculo_extras database table.
 * 
 */
@Entity
@Table(name="rec_vehiculo_extras")
@NamedQuery(name="RecVehiculoExtra.findAll", query="SELECT r FROM RecVehiculoExtra r")
public class RecVehiculoExtra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="veh_ext_id", unique=true, nullable=false)
	private Integer vehExtId;

	@Column(name="veh_ext_adicionales", length=200)
	private String vehExtAdicionales;

	@Column(name="veh_ext_antena")
	private Boolean vehExtAntena;

	@Column(name="veh_ext_bateria")
	private Boolean vehExtBateria;

	@Column(name="veh_ext_cables_corriente")
	private Boolean vehExtCablesCorriente;

	@Column(name="veh_ext_control_alarma")
	private Boolean vehExtControlAlarma;

	@Column(name="veh_ext_encendor")
	private Boolean vehExtEncendor;

	@Column(name="veh_ext_estado")
	private Boolean vehExtEstado;

	@Column(name="veh_ext_extintor")
	private Boolean vehExtExtintor;

	@Column(name="veh_ext_herramientas_basicas")
	private Boolean vehExtHerramientasBasicas;

	@Column(name="veh_ext_llanta_emergencia")
	private Boolean vehExtLlantaEmergencia;

	@Column(name="veh_ext_llave_ruedas")
	private Boolean vehExtLlaveRuedas;

	@Column(name="veh_ext_llavero")
	private Boolean vehExtLlavero;

	@Column(name="veh_ext_mascarilla_radio")
	private Boolean vehExtMascarillaRadio;

	@Column(name="veh_ext_matricula_documento")
	private Boolean vehExtMatriculaDocumento;

	@Column(name="veh_ext_num_limpia_parabrisas")
	private Integer vehExtNumLimpiaParabrisas;

	@Column(name="veh_ext_num_retrovisores")
	private Integer vehExtNumRetrovisores;

	@Column(name="veh_ext_num_ruedas")
	private float vehExtNumRuedas;

	@Column(name="veh_ext_radio")
	private Boolean vehExtRadio;

	@Column(name="veh_ext_tapacubos")
	private Integer vehExtTapacubos;

	@Column(name="veh_ext_triangulos")
	private Boolean vehExtTriangulos;

	@Column(name="veh_ext_usb")
	private Boolean vehExtUsb;

	//bi-directional many-to-one association to RecVehiculo
	@ManyToOne
	@JoinColumn(name="veh_id_rec_vehiculos")
	private RecVehiculo recVehiculo;

	public RecVehiculoExtra() {
	}

	public Integer getVehExtId() {
		return this.vehExtId;
	}

	public void setVehExtId(Integer vehExtId) {
		this.vehExtId = vehExtId;
	}

	public String getVehExtAdicionales() {
		return this.vehExtAdicionales;
	}

	public void setVehExtAdicionales(String vehExtAdicionales) {
		this.vehExtAdicionales = vehExtAdicionales;
	}

	public Boolean getVehExtAntena() {
		return this.vehExtAntena;
	}

	public void setVehExtAntena(Boolean vehExtAntena) {
		this.vehExtAntena = vehExtAntena;
	}

	public Boolean getVehExtBateria() {
		return this.vehExtBateria;
	}

	public void setVehExtBateria(Boolean vehExtBateria) {
		this.vehExtBateria = vehExtBateria;
	}

	public Boolean getVehExtCablesCorriente() {
		return this.vehExtCablesCorriente;
	}

	public void setVehExtCablesCorriente(Boolean vehExtCablesCorriente) {
		this.vehExtCablesCorriente = vehExtCablesCorriente;
	}

	public Boolean getVehExtControlAlarma() {
		return this.vehExtControlAlarma;
	}

	public void setVehExtControlAlarma(Boolean vehExtControlAlarma) {
		this.vehExtControlAlarma = vehExtControlAlarma;
	}

	public Boolean getVehExtEncendor() {
		return this.vehExtEncendor;
	}

	public void setVehExtEncendor(Boolean vehExtEncendor) {
		this.vehExtEncendor = vehExtEncendor;
	}

	public Boolean getVehExtEstado() {
		return this.vehExtEstado;
	}

	public void setVehExtEstado(Boolean vehExtEstado) {
		this.vehExtEstado = vehExtEstado;
	}

	public Boolean getVehExtExtintor() {
		return this.vehExtExtintor;
	}

	public void setVehExtExtintor(Boolean vehExtExtintor) {
		this.vehExtExtintor = vehExtExtintor;
	}

	public Boolean getVehExtHerramientasBasicas() {
		return this.vehExtHerramientasBasicas;
	}

	public void setVehExtHerramientasBasicas(Boolean vehExtHerramientasBasicas) {
		this.vehExtHerramientasBasicas = vehExtHerramientasBasicas;
	}

	public Boolean getVehExtLlantaEmergencia() {
		return this.vehExtLlantaEmergencia;
	}

	public void setVehExtLlantaEmergencia(Boolean vehExtLlantaEmergencia) {
		this.vehExtLlantaEmergencia = vehExtLlantaEmergencia;
	}

	public Boolean getVehExtLlaveRuedas() {
		return this.vehExtLlaveRuedas;
	}

	public void setVehExtLlaveRuedas(Boolean vehExtLlaveRuedas) {
		this.vehExtLlaveRuedas = vehExtLlaveRuedas;
	}

	public Boolean getVehExtLlavero() {
		return this.vehExtLlavero;
	}

	public void setVehExtLlavero(Boolean vehExtLlavero) {
		this.vehExtLlavero = vehExtLlavero;
	}

	public Boolean getVehExtMascarillaRadio() {
		return this.vehExtMascarillaRadio;
	}

	public void setVehExtMascarillaRadio(Boolean vehExtMascarillaRadio) {
		this.vehExtMascarillaRadio = vehExtMascarillaRadio;
	}

	public Boolean getVehExtMatriculaDocumento() {
		return this.vehExtMatriculaDocumento;
	}

	public void setVehExtMatriculaDocumento(Boolean vehExtMatriculaDocumento) {
		this.vehExtMatriculaDocumento = vehExtMatriculaDocumento;
	}

	public Integer getVehExtNumLimpiaParabrisas() {
		return this.vehExtNumLimpiaParabrisas;
	}

	public void setVehExtNumLimpiaParabrisas(Integer vehExtNumLimpiaParabrisas) {
		this.vehExtNumLimpiaParabrisas = vehExtNumLimpiaParabrisas;
	}

	public Integer getVehExtNumRetrovisores() {
		return this.vehExtNumRetrovisores;
	}

	public void setVehExtNumRetrovisores(Integer vehExtNumRetrovisores) {
		this.vehExtNumRetrovisores = vehExtNumRetrovisores;
	}

	public float getVehExtNumRuedas() {
		return this.vehExtNumRuedas;
	}

	public void setVehExtNumRuedas(float vehExtNumRuedas) {
		this.vehExtNumRuedas = vehExtNumRuedas;
	}

	public Boolean getVehExtRadio() {
		return this.vehExtRadio;
	}

	public void setVehExtRadio(Boolean vehExtRadio) {
		this.vehExtRadio = vehExtRadio;
	}

	public Integer getVehExtTapacubos() {
		return this.vehExtTapacubos;
	}

	public void setVehExtTapacubos(Integer vehExtTapacubos) {
		this.vehExtTapacubos = vehExtTapacubos;
	}

	public Boolean getVehExtTriangulos() {
		return this.vehExtTriangulos;
	}

	public void setVehExtTriangulos(Boolean vehExtTriangulos) {
		this.vehExtTriangulos = vehExtTriangulos;
	}

	public Boolean getVehExtUsb() {
		return this.vehExtUsb;
	}

	public void setVehExtUsb(Boolean vehExtUsb) {
		this.vehExtUsb = vehExtUsb;
	}

	public RecVehiculo getRecVehiculo() {
		return this.recVehiculo;
	}

	public void setRecVehiculo(RecVehiculo recVehiculo) {
		this.recVehiculo = recVehiculo;
	}

}