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
import minimarketdemo.model.core.entities.InvMaterial;
import minimarketdemo.model.core.entities.InvTipo;
import minimarketdemo.model.inventario.managers.ManagerJefeTaller;

@Named
@SessionScoped
public class BeanInvMaterial implements Serializable {

	@EJB
	private ManagerJefeTaller mJefeTaller;
	private List<InvMaterial> listaMateriales;
	private List<InvMaterial> listaMaterialesElegidos;
	private InvMaterial material;
	
	
	private List<InvTipo> listaTipos;
	private int idTipoMaterial;
	private InvTipo tipo;

	@Inject
	private BeanSegLogin beanSegLogin;
	
	public BeanInvMaterial() {

	}

	@PostConstruct
	public void inicializar() {
		listaMateriales = mJefeTaller.findAllMaterial();
		listaTipos=mJefeTaller.findAllTipoMaterial();
		listaMaterialesElegidos = new ArrayList<InvMaterial>();
		material= new InvMaterial();
		tipo= new InvTipo();
		idTipoMaterial=0;
	}
	
	

	public void actionListenerInsertarMaterial() {
		try {
			this.tipo = mJefeTaller.findTipoMaterialById(this.idTipoMaterial);
			mJefeTaller.createMaterial(this.material, this.tipo);
			listaMateriales = mJefeTaller.findAllMaterial();
			idTipoMaterial = 0;
			material = new InvMaterial();
			tipo= new InvTipo();
			JSFUtil.crearMensajeINFO("Material Creado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerDeleteMaterial(InvMaterial material) throws Exception {
		try {
			mJefeTaller.deleteMaterial(material);
			listaMateriales = mJefeTaller.findAllMaterial();
			material = new InvMaterial();
			tipo= new InvTipo();
			JSFUtil.crearMensajeINFO("Material eliminado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void actionListenerCargarMaterial(InvMaterial selectMaterial) {
		material = selectMaterial;
	}
	
	public void actionListenerGuardarEdicionMaterial() {
		try {
			InvTipo selectTipo = mJefeTaller.findTipoMaterialById(this.idTipoMaterial);
			idTipoMaterial = 0;
			material.setInvTipo(selectTipo);
			mJefeTaller.updatematerial(material);
			material = new InvMaterial();
			tipo= new InvTipo();
			idTipoMaterial = 0;
			JSFUtil.crearMensajeINFO("Material editado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}

	}

	
	
	

	public List<InvMaterial> getListaMateriales() {
		return listaMateriales;
	}

	public void setListaMateriales(List<InvMaterial> listaMateriales) {
		this.listaMateriales = listaMateriales;
	}

	public List<InvMaterial> getListaMaterialesElegidos() {
		return listaMaterialesElegidos;
	}

	public void setListaMaterialesElegidos(List<InvMaterial> listaMaterialesElegidos) {
		this.listaMaterialesElegidos = listaMaterialesElegidos;
	}

	public InvMaterial getMaterial() {
		return material;
	}

	public void setMaterial(InvMaterial material) {
		this.material = material;
	}

	public int getIdTipoMaterial() {
		return idTipoMaterial;
	}

	public void setIdTipoMaterial(int idTipoMaterial) {
		this.idTipoMaterial = idTipoMaterial;
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
