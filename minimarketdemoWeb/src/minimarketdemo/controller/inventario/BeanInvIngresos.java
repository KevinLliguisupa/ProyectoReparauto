package minimarketdemo.controller.inventario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.controller.seguridades.BeanSegLogin;
import minimarketdemo.model.core.entities.InvIngreso;
import minimarketdemo.model.core.entities.InvMaterial;
import minimarketdemo.model.core.entities.InvMaterialIngreso;
import minimarketdemo.model.core.entities.InvProveedor;
import minimarketdemo.model.core.entities.InvTipo;
import minimarketdemo.model.inventario.managers.ManagerJefeTaller;

@Named
@SessionScoped
public class BeanInvIngresos implements Serializable {

	@EJB
	private ManagerJefeTaller mJefeTaller;

	private List<InvMaterialIngreso> detalleIngreso;
	private List<InvMaterial> listaMateriales;
	private List<InvProveedor> listaProveedor;
	private List<InvIngreso> listaIngresos;
	private List<InvMaterial> listaMatAux;
	private List<InvTipo> listaTipo;

	private int cantidadIngresar;
	private int idProveedor;
	private int idMaterial;
	private int idTipo;

	private InvProveedor proveedor;
	private InvMaterial material;
	private InvIngreso ingreso;
	private BigDecimal precioCompra;
	private InvTipo tipo;
	private Date fechaInicio;
	private Date fechaFin;
	

	@Inject
	private BeanSegLogin beanSegLogin;

	public BeanInvIngresos() {

	}

	@PostConstruct
	public void inicializar() throws ParseException {
		listaIngresos = mJefeTaller.findAllIngresos();
		listaProveedor = mJefeTaller.findAllProveedor();
		listaMateriales = mJefeTaller.findAllMaterial();
		listaTipo = mJefeTaller.findAllTipoMaterial();

		idMaterial = 0;
		idProveedor = 0;
		precioCompra=new BigDecimal(0);
		material = new InvMaterial();
		tipo = new InvTipo();
		material.setMatId(1);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// Fecha de inicio:
		fechaInicio = sdf.parse("2000-01-01");
		// Fecha de hoy:
		fechaFin = new Date();
	}

	// Here are all the ACTION methods

	public void actionConsultarIngresos() throws Exception {
		List<InvIngreso> respuesta = new ArrayList<InvIngreso>();
		List<InvIngreso> listaConsulta = new ArrayList<InvIngreso>();
		List<InvIngreso> ingresosxFecha;

		if (idProveedor != 0 && idMaterial != 0) {
			listaConsulta = mJefeTaller.findIngresosByMaterialProveedor(idMaterial, idProveedor);
		} else {
			if (idMaterial != 0)
				listaConsulta = mJefeTaller.findIngresosByMaterial(idMaterial);
			if (idProveedor != 0)
				listaConsulta = mJefeTaller.findIngresosByProveedor(idProveedor);
		}
		ingresosxFecha = mJefeTaller.findIngresoByFecha(fechaInicio, fechaFin);
		if (listaConsulta.size() != 0) {
			for (InvIngreso consultas : listaConsulta) {
				for (InvIngreso fechas : ingresosxFecha) {
					if (consultas.getIngId() == fechas.getIngId()) {
						respuesta.add(consultas);
					}
				}
			}
		} else {
			respuesta = ingresosxFecha;
		}

		listaIngresos = respuesta;
		JSFUtil.crearMensajeINFO("Registros encontrados: " + listaIngresos.size());
	}

	public String actionCargarDetalles(int id) throws Exception {
		ingreso = mJefeTaller.findIngresoById(id);
		listaMatAux = mJefeTaller.findAllMaterial();
		detalleIngreso = mJefeTaller.findAllDetallesByCabIngreso(ingreso);
		listaTipo = mJefeTaller.findAllTipoMaterial();
		material = new InvMaterial();
		listaMateriales = new ArrayList<InvMaterial>();
		return "detalleIngreso?faces-redirect=true";

	}

	public BigDecimal actioncalcularValorTotalIngreso(int idIngreso) throws Exception {
		BigDecimal valorTotal = mJefeTaller.calcularValorTotalIngreso(idIngreso);
		return valorTotal;
	}

	public List<InvMaterialIngreso> actionSeleccionarIngreso(int idIngreso) throws Exception {
		return mJefeTaller.findMaterialIngreso(idIngreso);
	}

	public void actionLimpiar() throws ParseException {
		inicializar();
	}

	// Here are all the ACTIONLISTENER methods

	public void actionListenerIngresarMaterial() throws Exception {
		try {
		mJefeTaller.ingresarMaterial(beanSegLogin.getLoginDTO(), listaMateriales, ingreso);
		listaMateriales = new ArrayList<InvMaterial>();
		detalleIngreso = mJefeTaller.findAllDetallesByCabIngreso(ingreso);
		listaMatAux = mJefeTaller.findAllMaterial();
		material = new InvMaterial();
		cantidadIngresar = 0;
		JSFUtil.crearMensajeINFO("Materiales ingresados correctamente");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerIngresarCabeceraIngreso() throws Exception {
		if (idProveedor != 0) {
			proveedor = mJefeTaller.findIdProveedor(idProveedor);
			mJefeTaller.ingresarCabeceraIngreso(beanSegLogin.getLoginDTO(), proveedor);
			idProveedor = 0;
			proveedor = new InvProveedor();
			listaIngresos = mJefeTaller.findAllIngresos();
			JSFUtil.crearMensajeINFO("Ingreso creado correctamente.");
		} else {
			JSFUtil.crearMensajeERROR("Seleccione un proveedor");
		}

	}

	public void actionListenerSeleccionarMaterial() throws Exception {
		if(precioCompra.compareTo(precioCompra.ZERO)!=0 && idMaterial!=0) {
			material = mJefeTaller.findMaterialId(idMaterial);
			mJefeTaller.agregarMaterialSeleccion(listaMateriales, material, cantidadIngresar, precioCompra);
			cantidadIngresar=0;
			precioCompra=new BigDecimal(0);
			idMaterial = 0;
			listaMatAux=mJefeTaller.findAllMaterial();
			JSFUtil.crearMensajeINFO("Material seleccionado.");
		}else {
			JSFUtil.crearMensajeERROR("Porfavor ingrese un precio de compra");
		}

	}

	public void actionListenerUpdateSeleccionMat() throws Exception {
		tipo = mJefeTaller.findTipoMaterialById(idTipo);
		material.setInvTipo(tipo);
	}

	public void actionListenerDeleteDetalleIngreso(InvMaterialIngreso detalle) throws Exception {
		mJefeTaller.deleteDetalleIngreso(detalle);
		detalleIngreso = mJefeTaller.findAllDetallesByCabIngreso(ingreso);
	}

	public void actionListenerClasificarMateriales() throws Exception {
		if(idTipo!=0) {
			listaMatAux = mJefeTaller.findAllMaterialesByTipo(idTipo);
			JSFUtil.crearMensajeINFO("Datos de la categoria cargados.");
		}else {
			JSFUtil.crearMensajeERROR("Seleccione una categoria.");
		}
	}

	public void actionListenerDeleteSeleccionMaterial(InvMaterial material) throws Exception {
		mJefeTaller.eliminarSeleccionMaterial(listaMateriales, material);
	}

	public void actionListenerDatosActualizar(InvMaterial mat) throws Exception {
		material = mJefeTaller.findMaterialByNameSeleccion(listaMateriales, mat);
	}

	// Getters and setters

	public List<InvMaterialIngreso> getDetalleIngreso() {
		return detalleIngreso;
	}

	public void setDetalleIngreso(List<InvMaterialIngreso> detalleIngreso) {
		this.detalleIngreso = detalleIngreso;
	}

	public List<InvMaterial> getListaMateriales() {
		return listaMateriales;
	}

	public void setListaMateriales(List<InvMaterial> listaMateriales) {
		this.listaMateriales = listaMateriales;
	}

	public List<InvProveedor> getListaProveedor() {
		return listaProveedor;
	}

	public void setListaProveedor(List<InvProveedor> listaProveedor) {
		this.listaProveedor = listaProveedor;
	}

	public List<InvIngreso> getListaIngresos() {
		return listaIngresos;
	}

	public void setListaIngresos(List<InvIngreso> listaIngresos) {
		this.listaIngresos = listaIngresos;
	}

	public List<InvMaterial> getListaMatAux() {
		return listaMatAux;
	}

	public void setListaMatAux(List<InvMaterial> listaMatAux) {
		this.listaMatAux = listaMatAux;
	}

	public List<InvTipo> getListaTipo() {
		return listaTipo;
	}

	public void setListaTipo(List<InvTipo> listaTipo) {
		this.listaTipo = listaTipo;
	}

	public int getCantidadIngresar() {
		return cantidadIngresar;
	}

	public void setCantidadIngresar(int cantidadIngresar) {
		this.cantidadIngresar = cantidadIngresar;
	}

	public int getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(int idMaterial) {
		this.idMaterial = idMaterial;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public InvProveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(InvProveedor proveedor) {
		this.proveedor = proveedor;
	}

	public InvMaterial getMaterial() {
		return material;
	}

	public void setMaterial(InvMaterial material) {
		this.material = material;
	}

	public InvIngreso getIngreso() {
		return ingreso;
	}

	public void setIngreso(InvIngreso ingreso) {
		this.ingreso = ingreso;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public InvTipo getTipo() {
		return tipo;
	}

	public void setTipo(InvTipo tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(BigDecimal precioCompra) {
		this.precioCompra = precioCompra;
	}
	

}
