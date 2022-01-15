package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the rec_recepcion_cabecera database table.
 * 
 */
@Entity
@Table(name="rec_recepcion_cabecera")
@NamedQuery(name="RecRecepcionCabecera.findAll", query="SELECT r FROM RecRecepcionCabecera r")
public class RecRecepcionCabecera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rec_cab_id", unique=true, nullable=false)
	private Integer recCabId;

	@Column(name="rec_cab_abono", precision=7, scale=2)
	private BigDecimal recCabAbono;

	@Column(name="rec_cab_estado")
	private Boolean recCabEstado;

	@Temporal(TemporalType.DATE)
	@Column(name="rec_cab_fecha_entrega")
	private Date recCabFechaEntrega;

	@Temporal(TemporalType.DATE)
	@Column(name="rec_cab_fecha_recepcion")
	private Date recCabFechaRecepcion;

	@Column(name="rec_cab_hora")
	private Time recCabHora;

	@Column(name="rec_cab_observacion", length=200)
	private String recCabObservacion;

	@Column(name="rec_cab_saldo", precision=7, scale=2)
	private BigDecimal recCabSaldo;

	@Column(name="rec_cab_terminado")
	private Boolean recCabTerminado;

	@Column(name="rec_cab_total", precision=7, scale=2)
	private BigDecimal recCabTotal;

	//bi-directional many-to-one association to ProNotaVenta
	@OneToMany(mappedBy="recRecepcionCabecera")
	private List<ProNotaVenta> proNotaVentas;

	//bi-directional many-to-one association to RecCliente
	@ManyToOne
	@JoinColumn(name="cli_id_rec_cliente")
	private RecCliente recCliente;

	//bi-directional many-to-one association to RecVehiculo
	@ManyToOne
	@JoinColumn(name="veh_id_rec_vehiculos")
	private RecVehiculo recVehiculo;

	//bi-directional many-to-one association to SegUsuario
	@ManyToOne
	@JoinColumn(name="id_seg_usuario_seg_usuario")
	private SegUsuario segUsuario;

	//bi-directional many-to-one association to RecRecepcionDetalle
	@OneToMany(mappedBy="recRecepcionCabecera")
	private List<RecRecepcionDetalle> recRecepcionDetalles;

	public RecRecepcionCabecera() {
	}

	public Integer getRecCabId() {
		return this.recCabId;
	}

	public void setRecCabId(Integer recCabId) {
		this.recCabId = recCabId;
	}

	public BigDecimal getRecCabAbono() {
		return this.recCabAbono;
	}

	public void setRecCabAbono(BigDecimal recCabAbono) {
		this.recCabAbono = recCabAbono;
	}

	public Boolean getRecCabEstado() {
		return this.recCabEstado;
	}

	public void setRecCabEstado(Boolean recCabEstado) {
		this.recCabEstado = recCabEstado;
	}

	public Date getRecCabFechaEntrega() {
		return this.recCabFechaEntrega;
	}

	public void setRecCabFechaEntrega(Date recCabFechaEntrega) {
		this.recCabFechaEntrega = recCabFechaEntrega;
	}

	public Date getRecCabFechaRecepcion() {
		return this.recCabFechaRecepcion;
	}

	public void setRecCabFechaRecepcion(Date recCabFechaRecepcion) {
		this.recCabFechaRecepcion = recCabFechaRecepcion;
	}

	public Time getRecCabHora() {
		return this.recCabHora;
	}

	public void setRecCabHora(Time recCabHora) {
		this.recCabHora = recCabHora;
	}

	public String getRecCabObservacion() {
		return this.recCabObservacion;
	}

	public void setRecCabObservacion(String recCabObservacion) {
		this.recCabObservacion = recCabObservacion;
	}

	public BigDecimal getRecCabSaldo() {
		return this.recCabSaldo;
	}

	public void setRecCabSaldo(BigDecimal recCabSaldo) {
		this.recCabSaldo = recCabSaldo;
	}

	public Boolean getRecCabTerminado() {
		return this.recCabTerminado;
	}

	public void setRecCabTerminado(Boolean recCabTerminado) {
		this.recCabTerminado = recCabTerminado;
	}

	public BigDecimal getRecCabTotal() {
		return this.recCabTotal;
	}

	public void setRecCabTotal(BigDecimal recCabTotal) {
		this.recCabTotal = recCabTotal;
	}

	public List<ProNotaVenta> getProNotaVentas() {
		return this.proNotaVentas;
	}

	public void setProNotaVentas(List<ProNotaVenta> proNotaVentas) {
		this.proNotaVentas = proNotaVentas;
	}

	public ProNotaVenta addProNotaVenta(ProNotaVenta proNotaVenta) {
		getProNotaVentas().add(proNotaVenta);
		proNotaVenta.setRecRecepcionCabecera(this);

		return proNotaVenta;
	}

	public ProNotaVenta removeProNotaVenta(ProNotaVenta proNotaVenta) {
		getProNotaVentas().remove(proNotaVenta);
		proNotaVenta.setRecRecepcionCabecera(null);

		return proNotaVenta;
	}

	public RecCliente getRecCliente() {
		return this.recCliente;
	}

	public void setRecCliente(RecCliente recCliente) {
		this.recCliente = recCliente;
	}

	public RecVehiculo getRecVehiculo() {
		return this.recVehiculo;
	}

	public void setRecVehiculo(RecVehiculo recVehiculo) {
		this.recVehiculo = recVehiculo;
	}

	public SegUsuario getSegUsuario() {
		return this.segUsuario;
	}

	public void setSegUsuario(SegUsuario segUsuario) {
		this.segUsuario = segUsuario;
	}

	public List<RecRecepcionDetalle> getRecRecepcionDetalles() {
		return this.recRecepcionDetalles;
	}

	public void setRecRecepcionDetalles(List<RecRecepcionDetalle> recRecepcionDetalles) {
		this.recRecepcionDetalles = recRecepcionDetalles;
	}

	public RecRecepcionDetalle addRecRecepcionDetalle(RecRecepcionDetalle recRecepcionDetalle) {
		getRecRecepcionDetalles().add(recRecepcionDetalle);
		recRecepcionDetalle.setRecRecepcionCabecera(this);

		return recRecepcionDetalle;
	}

	public RecRecepcionDetalle removeRecRecepcionDetalle(RecRecepcionDetalle recRecepcionDetalle) {
		getRecRecepcionDetalles().remove(recRecepcionDetalle);
		recRecepcionDetalle.setRecRecepcionCabecera(null);

		return recRecepcionDetalle;
	}

}