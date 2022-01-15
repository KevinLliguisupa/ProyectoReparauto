package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the rec_recepcion_detalle database table.
 * 
 */
@Entity
@Table(name="rec_recepcion_detalle")
@NamedQuery(name="RecRecepcionDetalle.findAll", query="SELECT r FROM RecRecepcionDetalle r")
public class RecRecepcionDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rec_det_id", unique=true, nullable=false)
	private Integer recDetId;

	@Column(name="rec_det_concluido")
	private Boolean recDetConcluido;

	@Column(name="rec_det_estado")
	private Boolean recDetEstado;

	@Column(name="rec_det_observacion", length=100)
	private String recDetObservacion;

	@Column(name="rec_det_precio_final", precision=7, scale=2)
	private BigDecimal recDetPrecioFinal;

	@Column(name="rec_det_servicio_extra")
	private Boolean recDetServicioExtra;

	@Column(name="\"rec-det_horas_empleadas\"")
	private Integer rec_detHorasEmpleadas;

	//bi-directional many-to-one association to RecRecepcionCabecera
	@ManyToOne
	@JoinColumn(name="rec_cab_id_rec_recepcion_cabecera")
	private RecRecepcionCabecera recRecepcionCabecera;

	//bi-directional many-to-one association to RecServicio
	@ManyToOne
	@JoinColumn(name="rec_ser_id_rec_servicio")
	private RecServicio recServicio;

	//bi-directional many-to-one association to ThmEmpleado
	@ManyToOne
	@JoinColumn(name="id_thm_empleado_thm_empleado")
	private ThmEmpleado thmEmpleado;

	public RecRecepcionDetalle() {
	}

	public Integer getRecDetId() {
		return this.recDetId;
	}

	public void setRecDetId(Integer recDetId) {
		this.recDetId = recDetId;
	}

	public Boolean getRecDetConcluido() {
		return this.recDetConcluido;
	}

	public void setRecDetConcluido(Boolean recDetConcluido) {
		this.recDetConcluido = recDetConcluido;
	}

	public Boolean getRecDetEstado() {
		return this.recDetEstado;
	}

	public void setRecDetEstado(Boolean recDetEstado) {
		this.recDetEstado = recDetEstado;
	}

	public String getRecDetObservacion() {
		return this.recDetObservacion;
	}

	public void setRecDetObservacion(String recDetObservacion) {
		this.recDetObservacion = recDetObservacion;
	}

	public BigDecimal getRecDetPrecioFinal() {
		return this.recDetPrecioFinal;
	}

	public void setRecDetPrecioFinal(BigDecimal recDetPrecioFinal) {
		this.recDetPrecioFinal = recDetPrecioFinal;
	}

	public Boolean getRecDetServicioExtra() {
		return this.recDetServicioExtra;
	}

	public void setRecDetServicioExtra(Boolean recDetServicioExtra) {
		this.recDetServicioExtra = recDetServicioExtra;
	}

	public Integer getRec_detHorasEmpleadas() {
		return this.rec_detHorasEmpleadas;
	}

	public void setRec_detHorasEmpleadas(Integer rec_detHorasEmpleadas) {
		this.rec_detHorasEmpleadas = rec_detHorasEmpleadas;
	}

	public RecRecepcionCabecera getRecRecepcionCabecera() {
		return this.recRecepcionCabecera;
	}

	public void setRecRecepcionCabecera(RecRecepcionCabecera recRecepcionCabecera) {
		this.recRecepcionCabecera = recRecepcionCabecera;
	}

	public RecServicio getRecServicio() {
		return this.recServicio;
	}

	public void setRecServicio(RecServicio recServicio) {
		this.recServicio = recServicio;
	}

	public ThmEmpleado getThmEmpleado() {
		return this.thmEmpleado;
	}

	public void setThmEmpleado(ThmEmpleado thmEmpleado) {
		this.thmEmpleado = thmEmpleado;
	}

}