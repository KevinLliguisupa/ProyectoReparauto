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
import minimarketdemo.model.core.entities.RecVehiculo;
import minimarketdemo.model.recepcion.managers.ManagerRecJefeTaller;
import minimarketdemo.model.seguridades.dtos.LoginDTO;

@Named
@SessionScoped
public class BeanRecJefeTaller implements Serializable {
	@Inject
	private BeanSegLogin beanSegLogin;
	@EJB
	private ManagerRecJefeTaller mJefeTaller;
	private List<RecVehiculo> listaRecVehiculos;
	private Boolean actualizarVehiculo;
	private RecVehiculo vehiculo;
	private int idVehiculo;

	public BeanRecJefeTaller() {

	}

	@PostConstruct
	public void inicializar() {
		listaRecVehiculos = mJefeTaller.findAllVehiculos();
		vehiculo = new RecVehiculo();
		actualizarVehiculo = false;
	}

	public String actionRegistrarVehiculo() throws Exception {

		if (actualizarVehiculo) {
			mJefeTaller.actualizarVehiculo(vehiculo, beanSegLogin.getLoginDTO());
			listaRecVehiculos = mJefeTaller.findAllVehiculos();
			vehiculo = new RecVehiculo();
			actualizarVehiculo = false;

		} else {
			mJefeTaller.insertarVehiculo(vehiculo,beanSegLogin.getLoginDTO());
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
