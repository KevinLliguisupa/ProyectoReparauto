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
import minimarketdemo.model.recepcion.managers.ManagerRecJefeTaller;

@Named
@SessionScoped
public class BeanRecVehiculo implements Serializable {

	@Inject
	private BeanSegLogin beanSegLogin;
	@EJB
	private ManagerRecJefeTaller mJefeTaller;
	private List<RecVehiculo> listaRecVehiculos;
	private Boolean actualizarVehiculo;
	private RecVehiculo vehiculo;
	private int idVehiculo;
	
	

	public BeanRecVehiculo() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializar() {
		listaRecVehiculos = mJefeTaller.findAllVehiculos();
		vehiculo = new RecVehiculo();
		actualizarVehiculo = false;
		idVehiculo = 0;
		
	}

	public String actionRegistrarVehiculo() throws Exception {

		if (actualizarVehiculo) {
			mJefeTaller.actualizarVehiculo(vehiculo, beanSegLogin.getLoginDTO());
			listaRecVehiculos = mJefeTaller.findAllVehiculos();
			vehiculo = new RecVehiculo();
			actualizarVehiculo = false;

		} else {
			mJefeTaller.insertarVehiculo(vehiculo, beanSegLogin.getLoginDTO());
			listaRecVehiculos = mJefeTaller.findAllVehiculos();
			vehiculo = new RecVehiculo();

		}
		return "administrarVehiculos?faces-redirect=true";

	}

	public void actionEliminarVehiculo(int id) throws Exception {
		vehiculo = mJefeTaller.findVehiculoById(id);
		mJefeTaller.eliminarVehiculo(vehiculo, beanSegLogin.getLoginDTO());
		listaRecVehiculos = mJefeTaller.findAllVehiculos();
		vehiculo = new RecVehiculo();
		JSFUtil.crearMensajeINFO("Vehiculo eliminado.");

	}
	public RecVehiculo ActionConsultarVehiculoById() throws Exception {
		if (mJefeTaller.findVehiculoById(idVehiculo) != null) {
			vehiculo = mJefeTaller.findVehiculoById(idVehiculo);
		}

		return vehiculo;
	}

	public String actionCargarDetallesVehiculo(int id) throws Exception {
		vehiculo = mJefeTaller.findVehiculoById(id);
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


	
	
}
