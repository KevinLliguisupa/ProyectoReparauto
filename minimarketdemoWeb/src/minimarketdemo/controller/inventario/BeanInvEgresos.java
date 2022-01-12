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
import minimarketdemo.model.core.entities.InvMaterial;
import minimarketdemo.model.core.entities.InvMaterialSalida;
import minimarketdemo.model.core.entities.InvSalida;
import minimarketdemo.model.core.entities.InvTipo;
import minimarketdemo.model.core.entities.RecVehiculo;
import minimarketdemo.model.core.entities.ThmEmpleado;
import minimarketdemo.model.inventario.managers.ManagerJefeTaller;

@Named
@SessionScoped
public class BeanInvEgresos implements Serializable {

	@EJB
	private ManagerJefeTaller mJefeTaller;

	private List<InvSalida> listaSalidas;
	private List<ThmEmpleado> listaEmpleados;
	private List<RecVehiculo> listaVehiculos;
	private List<InvMaterial> listaMatAux;
	private List<InvTipo> listaTipo;
	private List<InvMaterialSalida> detalleSalida;
	private List<InvMaterial> listaMateriales;

	private int id_vehiculos;
	private int id_empleados;
	private int idTipo;
	private int idMaterial;
	private int cantidadRetirar;

	private ThmEmpleado empleados;
	private RecVehiculo vehiculos;
	private InvSalida salida;
	private InvMaterial material;
	private Date fechaInicio;
	private Date fechaFin;

	@Inject
	private BeanSegLogin beanSegLogin;

	public BeanInvEgresos() {

	}

	@PostConstruct
	public void inicializar() throws ParseException {
		listaVehiculos = mJefeTaller.findAllVehiculos();
		listaEmpleados = mJefeTaller.findAllEmpleados();
		listaSalidas = mJefeTaller.findAllSalidas();
		listaMateriales = mJefeTaller.findAllMaterial();
		listaTipo = mJefeTaller.findAllTipoMaterial();

		idMaterial = 0;
		id_vehiculos = 0;
		material = new InvMaterial();
		material.setMatId(1);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// Fecha de inicio:
		fechaInicio = sdf.parse("2000-01-01");
		// Fecha de hoy:
		fechaFin = new Date();

	}

	// Here are all the ACTION methods

	public String actionCargarDetallesRetiro(int id) throws Exception {
		salida = mJefeTaller.findSalidaById(id);
		listaMatAux = mJefeTaller.findAllMaterial();
		material = new InvMaterial();
		detalleSalida = mJefeTaller.finAllDetalleSalidaByCabRetiro(salida);
		listaTipo = mJefeTaller.findAllTipoMaterial();
		listaMateriales = new ArrayList<InvMaterial>();
		cantidadRetirar = 0;
		return "detalleRetiro?faces-redirect=true";

	}

	public void actionConsultarSalidas() throws Exception {
		List<InvSalida> respuesta = new ArrayList<InvSalida>();
		List<InvSalida> listaConsulta = new ArrayList<InvSalida>();
		List<InvSalida> salidasxFecha;

		if (id_vehiculos != 0 && idMaterial != 0) {
			listaConsulta = mJefeTaller.findSalidasByMaterialVehiculo(idMaterial, id_vehiculos);
		} else {
			if (idMaterial != 0)
				listaConsulta = mJefeTaller.findSalidasByMaterial(idMaterial);
			if (id_vehiculos != 0)
				listaConsulta = mJefeTaller.findSalidasByVehiculo(id_vehiculos);
		}
		salidasxFecha = mJefeTaller.findSalidaByFecha(fechaInicio, fechaFin);
		if (listaConsulta.size() != 0) {
			for (InvSalida consultas : listaConsulta) {
				for (InvSalida fechas : salidasxFecha) {
					if (consultas.getSalId() == fechas.getSalId()) {
						respuesta.add(consultas);
					}
				}
			}
		} else {
			respuesta = salidasxFecha;
		}

		listaSalidas = respuesta;
		JSFUtil.crearMensajeINFO("Registros encontrados: " + listaSalidas.size());
	}

	public List<InvMaterialSalida> actionSeleccionarSalida(int idSalida) throws Exception {
		return mJefeTaller.findMaterialSalida(idSalida);
	}

	public BigDecimal actioncalcularValorTotalSalida(int idSalida) throws Exception {
		BigDecimal valorTotal = mJefeTaller.calcularValorTotalSalida(idSalida);
		return valorTotal;
	}

	public void actionLimpiar() throws ParseException {
		inicializar();
	}

	// Here are all the ACTIONLISTENER methods

	public void actionListenerIngresarCabeceraRetiro() {
		try {
			empleados = mJefeTaller.findEmpleadosById(id_empleados);
			vehiculos = mJefeTaller.findVehiculosById(id_vehiculos);
			mJefeTaller.ingresarCabeceraRetiro(beanSegLogin.getLoginDTO(), vehiculos, empleados);
			id_empleados = 0;
			id_vehiculos = 0;
			empleados = new ThmEmpleado();
			vehiculos = new RecVehiculo();
			listaSalidas = mJefeTaller.findAllSalidas();
			JSFUtil.crearMensajeINFO("Egreso creado correctamente.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerClasificarMateriales() throws Exception {
		listaMatAux = mJefeTaller.findAllMaterialesByTipo(idTipo);
	}

	public void actionListenerSeleccinarMaterialRetirar() throws Exception {
			material = mJefeTaller.findMaterialId(idMaterial);
			idMaterial = 0;
			mJefeTaller.agregarMaterialRetirar(listaMateriales, material, cantidadRetirar);
			JSFUtil.crearMensajeINFO("Materiales egresados correctamente.");
	}

	public void actionListenerRetirarMaterial(){
		try {
		mJefeTaller.retirarMaterial(beanSegLogin.getLoginDTO(),listaMateriales, salida);
		listaMateriales = new ArrayList<InvMaterial>();
		detalleSalida = mJefeTaller.finAllDetalleSalidaByCabRetiro(salida);
		listaMatAux = mJefeTaller.findAllMaterial();
		material = new InvMaterial();
		cantidadRetirar = 0;
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	// Getters and setters

	public void actionListenerDeleteSeleccionMaterial(InvMaterial material) throws Exception {
		mJefeTaller.eliminarSeleccionMaterial(listaMateriales, material);
	}

	public void actionListenerDatosActualizar(InvMaterial mat) throws Exception {
		material = mJefeTaller.findMaterialByNameSeleccion(listaMateriales, mat);
	}

	public void actionListenerDeleteDetalleSalida(InvMaterialSalida detalle) throws Exception {
		mJefeTaller.deleteDetalleSalida(detalle);
		detalleSalida = mJefeTaller.finAllDetalleSalidaByCabRetiro(salida);
	}

	public List<InvSalida> getListaSalidas() {
		return listaSalidas;
	}

	public void setListaSalidas(List<InvSalida> listaSalidas) {
		this.listaSalidas = listaSalidas;
	}

	public List<ThmEmpleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(List<ThmEmpleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	public List<RecVehiculo> getListaVehiculos() {
		return listaVehiculos;
	}

	public void setListaVehiculos(List<RecVehiculo> listaVehiculos) {
		this.listaVehiculos = listaVehiculos;
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

	public List<InvMaterialSalida> getDetalleSalida() {
		return detalleSalida;
	}

	public void setDetalleSalida(List<InvMaterialSalida> detalleSalida) {
		this.detalleSalida = detalleSalida;
	}

	public List<InvMaterial> getListaMateriales() {
		return listaMateriales;
	}

	public void setListaMateriales(List<InvMaterial> listaMateriales) {
		this.listaMateriales = listaMateriales;
	}

	public int getId_vehiculos() {
		return id_vehiculos;
	}

	public void setId_vehiculos(int id_vehiculos) {
		this.id_vehiculos = id_vehiculos;
	}

	public int getId_empleados() {
		return id_empleados;
	}

	public void setId_empleados(int id_empleados) {
		this.id_empleados = id_empleados;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public int getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(int idMaterial) {
		this.idMaterial = idMaterial;
	}

	public int getCantidadRetirar() {
		return cantidadRetirar;
	}

	public void setCantidadRetirar(int cantidadRetirar) {
		this.cantidadRetirar = cantidadRetirar;
	}

	public ThmEmpleado getEmpleados() {
		return empleados;
	}

	public void setEmpleados(ThmEmpleado empleados) {
		this.empleados = empleados;
	}

	public RecVehiculo getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(RecVehiculo vehiculos) {
		this.vehiculos = vehiculos;
	}

	public InvSalida getSalida() {
		return salida;
	}

	public void setSalida(InvSalida salida) {
		this.salida = salida;
	}

	public InvMaterial getMaterial() {
		return material;
	}

	public void setMaterial(InvMaterial material) {
		this.material = material;
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

}