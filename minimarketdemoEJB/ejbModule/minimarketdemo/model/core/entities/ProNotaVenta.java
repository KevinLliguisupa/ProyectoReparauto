package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the pro_nota_venta database table.
 * 
 */
@Entity
@Table(name="pro_nota_venta")
@NamedQuery(name="ProNotaVenta.findAll", query="SELECT p FROM ProNotaVenta p")
public class ProNotaVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pro_not_id", unique=true, nullable=false)
	private Integer proNotId;

	@Temporal(TemporalType.DATE)
	@Column(name="pro_not_fecha")
	private Date proNotFecha;

	@Column(name="pro_not_total", precision=7, scale=2)
	private BigDecimal proNotTotal;

	//bi-directional many-to-one association to RecRecepcionCabecera
	@ManyToOne
	@JoinColumn(name="rec_cab_id_rec_recepcion_cabecera")
	private RecRecepcionCabecera recRecepcionCabecera;

	public ProNotaVenta() {
	}

	public Integer getProNotId() {
		return this.proNotId;
	}

	public void setProNotId(Integer proNotId) {
		this.proNotId = proNotId;
	}

	public Date getProNotFecha() {
		return this.proNotFecha;
	}

	public void setProNotFecha(Date proNotFecha) {
		this.proNotFecha = proNotFecha;
	}

	public BigDecimal getProNotTotal() {
		return this.proNotTotal;
	}

	public void setProNotTotal(BigDecimal proNotTotal) {
		this.proNotTotal = proNotTotal;
	}

	public RecRecepcionCabecera getRecRecepcionCabecera() {
		return this.recRecepcionCabecera;
	}

	public void setRecRecepcionCabecera(RecRecepcionCabecera recRecepcionCabecera) {
		this.recRecepcionCabecera = recRecepcionCabecera;
	}

}