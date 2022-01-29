package minimarketdemo.controller.recepcion;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import minimarketdemo.controller.seguridades.BeanSegLogin;
import minimarketdemo.model.core.entities.RecCliente;
import minimarketdemo.model.recepcion.managers.ManagerRecepcion;

@Named
@SessionScoped
public class BeanRecCliente implements Serializable{

	@Inject
	private BeanSegLogin beanSegLogin;
	@EJB
	private ManagerRecepcion mRecepcion;
	
	private List<RecCliente> listaClientes;
	private RecCliente NuevoCliente;
	private RecCliente Cliente;
	
	public BeanRecCliente() {
		// TODO Auto-generated constructor stub
	}
	@PostConstruct
	public void inicializar() {
		listaClientes=mRecepcion.findAllClientes();
		NuevoCliente=new RecCliente();
	}

	public void actionRegistrarCliente() throws Exception {
		mRecepcion.ingresarCliente(NuevoCliente);
		NuevoCliente=new RecCliente();
	}

	public void actionEliminarCliente(RecCliente Eliminado) throws Exception {
		mRecepcion.eliminarCliente(Eliminado);
	}
	public void actionActualizarCliente() throws Exception {
		mRecepcion.actualizarCliente(Cliente);
	}
	public void actionfindClienteByID(RecCliente Cliente) throws Exception {
		this.Cliente=mRecepcion.findClienteByID(Cliente);
	}
	public List<RecCliente> getListaClientes() {
		return mRecepcion.findAllClientes();
	}
	public void setListaClientes(List<RecCliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	public RecCliente getNuevoCliente() {
		return NuevoCliente;
	}
	public void setNuevoCliente(RecCliente nuevoCliente) {
		NuevoCliente = nuevoCliente;
	}
	public RecCliente getCliente() {
		return Cliente;
	}
	public void setCliente(RecCliente cliente) {
		Cliente = cliente;
	}
	
}
