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
import minimarketdemo.model.core.entities.RecServicio;
import minimarketdemo.model.core.entities.RecVehiculo;
import minimarketdemo.model.core.entities.RecVehiculoExtra;
import minimarketdemo.model.recepcion.managers.ManagerRecepcion;

@Named
@SessionScoped
public class BeanRecVehiculo implements Serializable {

	@Inject
	private BeanSegLogin beanSegLogin;
	@EJB
	private ManagerRecepcion mRecepcion;
	
	private List<RecVehiculo> listaRecVehiculos;
	private Boolean actualizarVehiculo;
	private RecVehiculo vehiculo;
	private RecVehiculoExtra extras;
	private RecVehiculoExtra extrasActualizacion;
	private int idVehiculo;
	
	@Inject
	private BeanRecepcion bRecepcion;

	public BeanRecVehiculo() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
		listaRecVehiculos = mRecepcion.findAllVehiculos();
		vehiculo = new RecVehiculo();
		actualizarVehiculo = false;
		idVehiculo = 0;
		extras= new RecVehiculoExtra();
		
	}

	public String actionRegistrarVehiculo() throws Exception {

		if (actualizarVehiculo) {
			mRecepcion.actualizarVehiculo(vehiculo, beanSegLogin.getLoginDTO());
			listaRecVehiculos = mRecepcion.findAllVehiculos();
			vehiculo = new RecVehiculo();
			actualizarVehiculo = false;

		} else {
			mRecepcion.insertarVehiculo(vehiculo, beanSegLogin.getLoginDTO());
			listaRecVehiculos = mRecepcion.findAllVehiculos();
			vehiculo = new RecVehiculo();

		}
		return "administrarVehiculos?faces-redirect=true";

	}
	
	public String actionRegistarVehiculoRecepcion() throws Exception {
//		if (actualizarVehiculo) {
//			mJefeTaller.actualizarVehiculo(vehiculo, beanSegLogin.getLoginDTO());
//			listaRecVehiculos = mJefeTaller.findAllVehiculos();
//			vehiculo = new RecVehiculo();
//			actualizarVehiculo = false;
//
//		} else {
			mRecepcion.insertarVehiculo(vehiculo, extras, beanSegLogin.getLoginDTO());
			bRecepcion.setVehiculo(vehiculo);
			listaRecVehiculos = mRecepcion.findAllVehiculos();
			bRecepcion.setListaVehiculos(listaRecVehiculos);
			extras= new RecVehiculoExtra();
			vehiculo = new RecVehiculo();

		//}
			return "recepcion?faces-redirect=true";

	}

	public void actionEliminarVehiculo(int id) throws Exception {
		vehiculo = mRecepcion.findVehiculoById(id);
		mRecepcion.eliminarVehiculo(vehiculo, beanSegLogin.getLoginDTO());
		listaRecVehiculos = mRecepcion.findAllVehiculos();
		vehiculo = new RecVehiculo();
		JSFUtil.crearMensajeINFO("Vehiculo eliminado.");

	}
	public RecVehiculo ActionConsultarVehiculoById() throws Exception {
		if (mRecepcion.findVehiculoById(idVehiculo) != null) {
			vehiculo = mRecepcion.findVehiculoById(idVehiculo);
		}

		return vehiculo;
	}

	public String actionCargarDetallesVehiculo(int id) throws Exception {
		vehiculo = mRecepcion.findVehiculoById(id);
		actualizarVehiculo = true;
		return "ingresarVehiculo?faces-redirect=true";
	}

	public List<RecVehiculo> getListaRecVehiculos() {
		return listaRecVehiculos;
	}

	public void setListaRecVehiculos(List<RecVehiculo> listaRecVehiculos) {
		this.listaRecVehiculos = listaRecVehiculos;
	}

	public RecVehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(RecVehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public int getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public RecVehiculoExtra getExtras() {
		return extras;
	}

	public void setExtras(RecVehiculoExtra extras) {
		this.extras = extras;
	}


	
	
}
