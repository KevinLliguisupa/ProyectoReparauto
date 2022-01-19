package minimarketdemo.controller.inventario;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.InvProveedor;
import minimarketdemo.model.core.entities.InvTipo;
import minimarketdemo.model.inventario.managers.ManagerGerente;
import minimarketdemo.model.inventario.managers.ManagerJefeTaller;

@Named
@SessionScoped
public class BeanInvProveedor implements Serializable {

	@EJB
	private ManagerGerente mGerente;
	private List<InvProveedor> listaProveedores;
	private InvProveedor proveedor;

	public BeanInvProveedor() {

	}

	@PostConstruct
	public void inicializar() {
		listaProveedores = mGerente.findAllProveedores();
		proveedor = new InvProveedor();
	}

	public void actionCreateProveedor() throws Exception {

		try {
			mGerente.createProveedores(proveedor);
			listaProveedores = mGerente.findAllProveedores();
			proveedor = new InvProveedor();
			JSFUtil.crearMensajeINFO("Proveedor creado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionfindProveedorByID(int id) throws Exception {

		try {
			proveedor = mGerente.findIdProveedores(id);
			JSFUtil.crearMensajeINFO("Proveedor creado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionUpdateProveedor() throws Exception {

		try {
			mGerente.updateProveedores(proveedor);
			listaProveedores = mGerente.findAllProveedores();
			proveedor = new InvProveedor();
			JSFUtil.crearMensajeINFO("Proveedor editado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}

	}
	
	
	public void actionDeleteProveedor(InvProveedor pro) throws Exception {
			mGerente.deleteProveedores(pro);
			listaProveedores = mGerente.findAllProveedores();
			JSFUtil.crearMensajeINFO("Proveedor Eliminado");
		
	}

	public List<InvProveedor> getListaProveedores() {
		return listaProveedores;
	}

	public void setListaProveedores(List<InvProveedor> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}

	public InvProveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(InvProveedor proveedor) {
		this.proveedor = proveedor;
	}

}