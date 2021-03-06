package minimarketdemo.controller.recepcion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import minimarketdemo.model.core.entities.InvMaterial;
import minimarketdemo.model.core.entities.RecServicio;
import minimarketdemo.model.recepcion.managers.ManagerRecepcion;

@Named
@SessionScoped
public class BeanRecCotizacion implements Serializable {

	@EJB
	private ManagerRecepcion mRecepcion;
	
	@Inject
	private BeanRecepcion bRecepcion;
	
	private List<RecServicio> listaServicios;
	private List<RecServicio> listaServiciosSeleccionados;
	private int idServicio;
	private double precioTotalServicios;

	public BeanRecCotizacion() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
		listaServicios = mRecepcion.findAllServicios();
		listaServiciosSeleccionados = new ArrayList<RecServicio>();
		precioTotalServicios = 0;
	}

	public void actionAgregarServicioSeleccion() throws Exception {
		mRecepcion.agregarServiciosSeleccion(listaServiciosSeleccionados, idServicio);
		precioTotalServicios=mRecepcion.calcularPrecioServiciosSeleccionados(listaServiciosSeleccionados);
	}

	public void actionEliminarSeleccionServicio(int id) {
		mRecepcion.deleteSeleccionServicio(listaServiciosSeleccionados, id);
		precioTotalServicios=mRecepcion.calcularPrecioServiciosSeleccionados(listaServiciosSeleccionados);
	}

	public void actionElegirSeleccionServicio(int id) {
		idServicio = id;
	}

	public void actionActualizarSeleccionServicio() {
		precioTotalServicios=mRecepcion.calcularPrecioServiciosSeleccionados(listaServiciosSeleccionados);
		idServicio = 0;
	}
	
	public void actionEliminarListaServicios() {
		precioTotalServicios=0;
		listaServiciosSeleccionados= new ArrayList<RecServicio>();
	}
	
	public String actionGenerarOrden() throws Exception {
//		bRecepcion.inicializar();
		bRecepcion.setListaServicios(listaServiciosSeleccionados);
		return "recepcion?faces-redirect=true";
		
	}

	public List<RecServicio> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(List<RecServicio> listaServicios) {
		this.listaServicios = listaServicios;
	}

	public List<RecServicio> getListaServiciosSeleccionados() {
		return listaServiciosSeleccionados;
	}

	public void setListaServiciosSeleccionados(List<RecServicio> listaServiciosSeleccionados) {
		this.listaServiciosSeleccionados = listaServiciosSeleccionados;
	}

	public int getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public double getPrecioTotalServicios() {
		return precioTotalServicios;
	}

	public void setPrecioTotalServicios(double precioTotalServicios) {
		this.precioTotalServicios = precioTotalServicios;
	}

}
