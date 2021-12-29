package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the rec_servicio database table.
 * 
 */
@Entity
@Table(name="rec_servicio")
@NamedQuery(name="RecServicio.findAll", query="SELECT r FROM RecServicio r")
public class RecServicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rec_ser_id", unique=true, nullable=false)
	private Integer recSerId;

	@Column(name="rec_ser_estado")
	private Boolean recSerEstado;

	@Column(name="rec_ser_nombre", length=2147483647)
	private String recSerNombre;

	@Column(name="rec_ser_precio", precision=7, scale=2)
	private BigDecimal recSerPrecio;

	//bi-directional many-to-one association to RecRecepcionDetalle
	@OneToMany(mappedBy="recServicio")
	private List<RecRecepcionDetalle> recRecepcionDetalles;

	public RecServicio() {
	}

	public Integer getRecSerId() {
		return this.recSerId;
	}

	public void setRecSerId(Integer recSerId) {
		this.recSerId = recSerId;
	}

	public Boolean getRecSerEstado() {
		return this.recSerEstado;
	}

	public void setRecSerEstado(Boolean recSerEstado) {
		this.recSerEstado = recSerEstado;
	}

	public String getRecSerNombre() {
		return this.recSerNombre;
	}

	public void setRecSerNombre(String recSerNombre) {
		this.recSerNombre = recSerNombre;
	}

	public BigDecimal getRecSerPrecio() {
		return this.recSerPrecio;
	}

	public void setRecSerPrecio(BigDecimal recSerPrecio) {
		this.recSerPrecio = recSerPrecio;
	}

	public List<RecRecepcionDetalle> getRecRecepcionDetalles() {
		return this.recRecepcionDetalles;
	}

	public void setRecRecepcionDetalles(List<RecRecepcionDetalle> recRecepcionDetalles) {
		this.recRecepcionDetalles = recRecepcionDetalles;
	}

	public RecRecepcionDetalle addRecRecepcionDetalle(RecRecepcionDetalle recRecepcionDetalle) {
		getRecRecepcionDetalles().add(recRecepcionDetalle);
		recRecepcionDetalle.setRecServicio(this);

		return recRecepcionDetalle;
	}

	public RecRecepcionDetalle removeRecRecepcionDetalle(RecRecepcionDetalle recRecepcionDetalle) {
		getRecRecepcionDetalles().remove(recRecepcionDetalle);
		recRecepcionDetalle.setRecServicio(null);

		return recRecepcionDetalle;
	}

}