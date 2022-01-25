package minimarketdemo.controller.recepcion;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.validator.constraints.Length;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.controller.seguridades.BeanSegLogin;
import minimarketdemo.model.core.entities.RecRecepcionCabecera;
import minimarketdemo.model.core.entities.RecRecepcionDetalle;
import minimarketdemo.model.core.entities.RecServicio;
import minimarketdemo.model.core.entities.RecVehiculo;
import minimarketdemo.model.recepcion.managers.ManagerAvanceServicios;
import minimarketdemo.model.recepcion.managers.ManagerRecJefeTaller;

@Named
@SessionScoped
public class BeanAvanceServicio implements Serializable {

	@Inject
	private BeanSegLogin beanSegLogin;
	@EJB

	private ManagerAvanceServicios mAvanceServicios;
	private List<RecRecepcionCabecera> listaRecepcionCab;
	private RecRecepcionCabecera recCabecera;
	private int idRecepcionCab;

	private List<RecRecepcionDetalle> listaRecepcionDet;
	private List<RecRecepcionDetalle> listaAuxiliar1;
	private List<RecRecepcionDetalle> listaAuxiliar2;
	private RecRecepcionDetalle recDetalle;
	private int idrecDetalle;
	private String servicioConcluido;
	private int idEmpleado;

	private Boolean estadoConcluidoCab;
	private Boolean estadoConcluidoDet;
	private int horasEstimadas;

	public BeanAvanceServicio() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() throws Exception {

		listaRecepcionCab = mAvanceServicios.findAllRecepcionCabecera();
		listaRecepcionDet = mAvanceServicios.findAllRecepcionDetalle();
		recCabecera = new RecRecepcionCabecera();
		idRecepcionCab = 0;

		recDetalle = new RecRecepcionDetalle();
		estadoConcluidoCab = false;
		estadoConcluidoDet = false;
		horasEstimadas = 0;

	}

	// Metodo de Busqueda de cabecera por Id

	public RecRecepcionCabecera ActionConsultarRecepcionCabId() throws Exception {
		if (mAvanceServicios.findRecepcionCabeceraById(idRecepcionCab) != null) {
			recCabecera = mAvanceServicios.findRecepcionCabeceraById(idRecepcionCab);
		}
		listaRecepcionDet = mAvanceServicios.findRecDetalleByIdCabecera(idRecepcionCab);
		return recCabecera;
	}

	public RecRecepcionDetalle ActionConsultarRecepcionDetId() throws Exception {
		if (mAvanceServicios.findRecepcionDetallerById(idrecDetalle) != null) {
			recDetalle = mAvanceServicios.findRecepcionDetallerById(idrecDetalle);

		}

		return recDetalle;
	}

	// Metodo que cambia de estado al servicio detalle
	public String estadoServicioConcluido(int idDetalle) throws Exception {
		recDetalle = mAvanceServicios.findRecepcionDetallerById(idDetalle);
		if (recDetalle.getRecDetConcluido())
			return "Finalizado";
		return "Pendiente";
	}

	// Metodo que cambia de estado al servicio cabecera - BORRAR 
	public String estadoServicioConcluidoCabecera() throws Exception {
		if (recCabecera == null) {
			return "Pendiente";
		} else {
			recCabecera = mAvanceServicios.findRecepcionCabeceraById(idRecepcionCab);
			if (recCabecera.getRecCabTerminado())
				return "Finalizado";
			return "Pendiente";
		}
	}

	// Metodo para calcular los servicios detalle finalizados
	public String actionActualizarEstadoCabecera() throws Exception {
		if (recCabecera == null) {
			return "";
		} else {
			listaAuxiliar1 = mAvanceServicios.findRecDetalleByIdCabecera(idRecepcionCab);
			listaAuxiliar2 = mAvanceServicios.findRecDetalleByEstadoAndId(idRecepcionCab);
			if (listaAuxiliar2.size() == listaAuxiliar1.size()) {
				return "Finalizado";
			}
			return "Pendiente";
		}
	}

	// Metodo actualizar servicios DETALLE
	public void actionActualizarRecDetalle(int idDetalle) throws Exception {
		recDetalle = mAvanceServicios.findRecepcionDetallerById(idDetalle);
		recDetalle.setRecDetHorasEmpleadas(recDetalle.getRecDetHorasEmpleadas()+horasEstimadas);
		recDetalle.getThmEmpleado().setIdThmEmpleado(idEmpleado);
		//recDetalle.setRecDetConcluido(true);
		mAvanceServicios.actualizarRecDetalle(recDetalle);
		recDetalle = new RecRecepcionDetalle();
		horasEstimadas = 0;
		idEmpleado=0;
	}

	// Getters and Setters

	public List<RecRecepcionCabecera> getListaRecepcionCab() {
		return listaRecepcionCab;
	}

	public void setListaRecepcionCab(List<RecRecepcionCabecera> listaRecepcionCab) {
		this.listaRecepcionCab = listaRecepcionCab;
	}

	public RecRecepcionCabecera getRecCabecera() {
		return recCabecera;
	}

	public void setRecCabecera(RecRecepcionCabecera recCabecera) {
		this.recCabecera = recCabecera;
	}

	public int getIdRecepcionCab() {
		return idRecepcionCab;
	}

	public void setIdRecepcionCab(int idRecepcionCab) {
		this.idRecepcionCab = idRecepcionCab;
	}

	public List<RecRecepcionDetalle> getListaRecepcionDet() {
		return listaRecepcionDet;
	}

	public void setListaRecepcionDet(List<RecRecepcionDetalle> listaRecepcionDet) {
		this.listaRecepcionDet = listaRecepcionDet;
	}

	public RecRecepcionDetalle getRecDetalle() {
		return recDetalle;
	}

	public void setRecDetalle(RecRecepcionDetalle recDetalle) {
		this.recDetalle = recDetalle;
	}

	public int getIdrecDetalle() {
		return idrecDetalle;
	}

	public void setIdrecDetalle(int idrecDetalle) {
		this.idrecDetalle = idrecDetalle;
	}

	public String getServicioConcluido() {
		return servicioConcluido;
	}

	public void setServicioConcluido(String servicioConcluido) {
		this.servicioConcluido = servicioConcluido;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Boolean getEstadoConcluidoCab() {
		return estadoConcluidoCab;
	}

	public void setEstadoConcluidoCab(Boolean estadoConcluidoCab) {
		this.estadoConcluidoCab = estadoConcluidoCab;
	}

	public Boolean getEstadoConcluidoDet() {
		return estadoConcluidoDet;
	}

	public void setEstadoConcluidoDet(Boolean estadoConcluidoDet) {
		this.estadoConcluidoDet = estadoConcluidoDet;
	}

	public int getHorasEstimadas() {
		return horasEstimadas;
	}

	public void setHorasEstimadas(int horasEstimadas) {
		this.horasEstimadas = horasEstimadas;
	}

	public List<RecRecepcionDetalle> getListaAuxiliar1() {
		return listaAuxiliar1;
	}

	public void setListaAuxiliar1(List<RecRecepcionDetalle> listaAuxiliar1) {
		this.listaAuxiliar1 = listaAuxiliar1;
	}

	public List<RecRecepcionDetalle> getListaAuxiliar2() {
		return listaAuxiliar2;
	}

	public void setListaAuxiliar2(List<RecRecepcionDetalle> listaAuxiliar2) {
		this.listaAuxiliar2 = listaAuxiliar2;
	}

}
