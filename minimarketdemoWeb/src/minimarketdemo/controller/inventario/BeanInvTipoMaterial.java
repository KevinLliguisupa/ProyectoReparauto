package minimarketdemo.controller.inventario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.controller.seguridades.BeanSegLogin;
import minimarketdemo.model.core.entities.InvTipo;
import minimarketdemo.model.inventario.managers.ManagerInventario;

@Named
@SessionScoped
public class BeanInvTipoMaterial implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerInventario mJefeTaller;
	private List<InvTipo> listaTipos;
	private InvTipo tipo;
	@Inject
	private BeanSegLogin beanLogin;
	public BeanInvTipoMaterial() {

	}

	@PostConstruct
	public void inicializar() {
		listaTipos = mJefeTaller.findAllTipoMaterial();
		tipo = new InvTipo();
	}

	public void actionInsertarTipo() throws Exception {

		try {
			mJefeTaller.createTipoMaterial(beanLogin.getLoginDTO() ,tipo);
			listaTipos = mJefeTaller.findAllTipoMaterial();
			tipo = new InvTipo();
			JSFUtil.crearMensajeINFO("Tipo creado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionActuaizarTipo() throws Exception {

		try {
			mJefeTaller.updateTipoMaterial(beanLogin.getLoginDTO() ,tipo);
			listaTipos = mJefeTaller.findAllTipoMaterial();
			tipo = new InvTipo();
			JSFUtil.crearMensajeINFO("Tipo actualizado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionEliminarTipo(int id) throws Exception {

		try {
			tipo = mJefeTaller.findTipoMaterialById(id);
			mJefeTaller.deleteTipoMaterial(beanLogin.getLoginDTO() ,tipo);
			listaTipos = mJefeTaller.findAllTipoMaterial();
			tipo = new InvTipo();
			JSFUtil.crearMensajeINFO("Tipo eliminado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionSeleccionarTipo(int id) throws Exception {
		tipo = mJefeTaller.findTipoMaterialById(id);
	}

	public List<InvTipo> getListaTipos() {
		return listaTipos;
	}

	public void setListaTipos(List<InvTipo> listaTipos) {
		this.listaTipos = listaTipos;
	}

	public InvTipo getTipo() {
		return tipo;
	}

	public void setTipo(InvTipo tipo) {
		this.tipo = tipo;
	}

}
