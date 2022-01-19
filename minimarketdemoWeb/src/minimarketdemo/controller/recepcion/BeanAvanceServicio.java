package minimarketdemo.controller.recepcion;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
	private RecRecepcionDetalle recDetalle;
	private int idrecDetalle;

	public BeanAvanceServicio() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {

		listaRecepcionCab = mAvanceServicios.findAllRecepcionCabecera();
		recCabecera = new RecRecepcionCabecera();
		idRecepcionCab = 0;
		
		listaRecepcionDet = mAvanceServicios.findAllRecepcionDetalle();
		recDetalle = new RecRecepcionDetalle();
		idrecDetalle= 0;
	}

	//Metodo de Busqueda de cabecera por Id
	
	public RecRecepcionCabecera ActionConsultarRecepcionCabId() throws Exception {
		if (mAvanceServicios.findRecepcionCabeceraById(idRecepcionCab) != null) {
			recCabecera = mAvanceServicios.findRecepcionCabeceraById(idRecepcionCab);
		}

		return recCabecera;
	}
	public RecRecepcionDetalle ActionConsultarRecepcionDetId() throws Exception {
		if (mAvanceServicios.findRecepcionDetallerById(idrecDetalle) != null) {
			recDetalle = mAvanceServicios.findRecepcionDetallerById(idrecDetalle);
		}

		return recDetalle;
	}
	//Getters and Setters 
	
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
	

}
