package minimarketdemo.controller.servicio;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.RecServicio;
import minimarketdemo.model.servicio.managers.ManagerServicio;

@Named
@SessionScoped
public class BeanServicio implements Serializable {
	@EJB
	private ManagerServicio mServicio;
	
	private int idServicios;
	private RecServicio servicio;
	private RecServicio nuevoServicio;
	private List<RecServicio> listaServicios;

	public BeanServicio() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void inicializar() throws ParseException {
		listaServicios = mServicio.findAllServicios();
		idServicios = 0;
		listaServicios = new ArrayList<RecServicio>();
		nuevoServicio = new RecServicio();
		servicio = new RecServicio();
	}

	
	public RecServicio actionfindServicioByID(RecServicio ServID) throws Exception {
		if (mServicio.findIdServicios(ServID.getRecSerId()) != null) {
			servicio = mServicio.findIdServicios(ServID.getRecSerId());
		}

		return servicio;
	}
	public List<RecServicio> actionfindAllServicios() {
		return mServicio.findAllServicios();
	}

	public void actionCreateServicio() throws Exception {
		mServicio.createServicios(nuevoServicio);
		nuevoServicio= new RecServicio();
		listaServicios = mServicio.findAllServicios();
	}

	public void actionUpdateServicio() throws Exception {
		mServicio.updateServicios(servicio);
	}
	public void actionListenerDeleteServicio(RecServicio servicio) throws Exception {
		try {
			mServicio.deleteServicio(servicio);
			listaServicios = mServicio.findAllServicios();
			servicio = new RecServicio();
			JSFUtil.crearMensajeINFO("Servicios eliminado correctamente.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}

	}

	public int getIdServicios() {
		return idServicios;
	}

	public void setIdServicios(int idServicios) {
		this.idServicios = idServicios;
	}

	public RecServicio getServicio() {
		return servicio;
	}

	public void setServicio(RecServicio servicio) {
		this.servicio = servicio;
	}

	public RecServicio getNuevoServicio() {
		return nuevoServicio;
	}

	public void setNuevoServicio(RecServicio nuevoServicio) {
		this.nuevoServicio = nuevoServicio;
	}

	public List<RecServicio> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(List<RecServicio> listaServicios) {
		this.listaServicios = listaServicios;
	}
	
	
	
}
