package minimarketdemo.controller.inventario;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.model.core.entities.InvMaterial;
import minimarketdemo.model.core.entities.InvProveedor;
import minimarketdemo.model.core.entities.InvTipo;
import minimarketdemo.model.core.entities.RecVehiculo;
import minimarketdemo.model.core.entities.ThmEmpleado;
import minimarketdemo.model.inventario.managers.ManagerJefeTaller;

@Named
@SessionScoped
public class BeanJefeTaller implements Serializable {

	@EJB
	private ManagerJefeTaller mJefeTaller;
	private List<InvMaterial> listaMateriales;
	private int idMaterial;
	private InvMaterial nuevoMaterial;
	private InvMaterial material;
	private List<ThmEmpleado> listaEmpleados;
	private int id_empleados;
	private ThmEmpleado empleados;
	private List<InvProveedor> listaProveedor;
	private int idProveedor;
	private InvProveedor proveedor;
	private List<RecVehiculo> listaVehiculos;
	private int id_vehiculos;
	private RecVehiculo vehiculos;
	private List<InvTipo> listaTipo;
	private int idTipo;
	private InvTipo nuevoTipo;
	private InvTipo tipo;
	private int cantidadRetirar;
	
	
	public BeanJefeTaller() {
		
	}
	@PostConstruct
	public void inicializar() {
		
	}
	
	public void actionDeleteMaterial() {
		
	}
	public void actionIngresarMaterial() {
		
	}
	public void actionUpdateMaterial() {
		
	}
	public void actionRetirarMaterial() {
		
	}
	public void actionCreateTipoMaterial() {
		
	}
	public void actionDeleteTipoMaterial() {
		
	}
	public void actionUpdateTipoMaterial() {
		
	}
}
