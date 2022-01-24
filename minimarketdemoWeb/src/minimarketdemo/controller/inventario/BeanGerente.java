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
import minimarketdemo.model.core.entities.InvMaterialSalida;
import minimarketdemo.model.core.entities.InvProveedor;
import minimarketdemo.model.core.entities.InvSalida;
import minimarketdemo.model.core.entities.InvTipo;
import minimarketdemo.model.core.entities.RecServicio;
import minimarketdemo.model.core.entities.RecVehiculo;
import minimarketdemo.model.core.entities.ThmEmpleado;
import minimarketdemo.model.inventario.managers.ManagerGerente;

@Named
@SessionScoped
public class BeanGerente implements Serializable {

	@EJB
	private ManagerGerente mGerente;
	private List<InvIngreso> listaIngresos;
	private List<InvSalida> listaSalidas;
	private List<InvMaterial> listaMateriales;
	private int idMaterial;
	private List<ThmEmpleado> listaEmpleados;
	private int id_empleados;
	private List<InvProveedor> listaProveedor;
	private int idProveedor;
	private List<RecVehiculo> listaVehiculos;
	private int id_vehiculos;
	private Date fechaInicio;
	private Date fechaFin;
	private List<InvProveedor> listaProveedores;
	private InvProveedor proveedor;
	private InvProveedor nuevoProveedor;
	private int idServicios;
	private RecServicio servicio;
	private RecServicio nuevoServicio;
	private List<RecServicio> listaServicios;
	@Inject
	private BeanSegLogin beanLogin;
	public BeanGerente() {

	}

	@PostConstruct
	public void inicializar() throws ParseException {
		listaIngresos = mGerente.findAllIngresos();
		listaSalidas = mGerente.findAllSalidas();
		listaMateriales = mGerente.findAllMaterial();
		listaEmpleados = mGerente.findAllEmpleados();
		listaProveedor = mGerente.findAllProveedor();
		listaVehiculos = mGerente.findAllVehiculos();
		listaServicios = mGerente.findAllServicios();
		idServicios = 0;
		idMaterial = 0;
		idProveedor = 0;
		id_vehiculos = 0;
		// Formato de la fecha
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// obtener la fecha de ayer:
		fechaInicio = sdf.parse("2000-01-01");
		// obtener la fecha de hoy:
		fechaFin = new Date();
		listaProveedores = new ArrayList<InvProveedor>();
		listaServicios = new ArrayList<RecServicio>();
		proveedor = new InvProveedor();
		servicio = new RecServicio();
		nuevoProveedor = new InvProveedor();
		nuevoServicio = new RecServicio();
	}

	public List<InvMaterialIngreso> actionSeleccionarIngreso(int idIngreso) throws Exception {
		return mGerente.findMaterialIngreso(idIngreso);
	}

	public BigDecimal actioncalcularValorTotalIngreso(int idIngreso) throws Exception {
		BigDecimal valorTotal = mGerente.calcularValorTotalIngreso(idIngreso);
		return valorTotal;
	}

	public void actionListenerConsultarIngresos() throws Exception {
		List<InvIngreso> respuesta = new ArrayList<InvIngreso>();
		List<InvIngreso> listaConsulta = new ArrayList<InvIngreso>();
		List<InvIngreso> ingresosxFecha;

		if (idProveedor != 0 && idMaterial != 0) {
			listaConsulta = mGerente.findIngresosByMaterialProveedor(idMaterial, idProveedor);
		} else {
			if (idMaterial != 0)
				listaConsulta = mGerente.findIngresosByMaterial(idMaterial);
			if (idProveedor != 0)
				listaConsulta = mGerente.findIngresosByProveedor(idProveedor);
		}
		ingresosxFecha = mGerente.findIngresoByFecha(fechaInicio, fechaFin);
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

	public void actionListenerConsultarSalidas() throws Exception {
		List<InvSalida> respuesta = new ArrayList<InvSalida>();
		List<InvSalida> listaConsulta = new ArrayList<InvSalida>();
		List<InvSalida> salidasxFecha;

		if (id_vehiculos != 0 && idMaterial != 0) {
			listaConsulta = mGerente.findSalidasByMaterialVehiculo(idMaterial, id_vehiculos);
		} else {
			if (idMaterial != 0)
				listaConsulta = mGerente.findSalidasByMaterial(idMaterial);
			if (id_vehiculos != 0)
				listaConsulta = mGerente.findSalidasByVehiculo(id_vehiculos);
		}
		salidasxFecha = mGerente.findSalidaByFecha(fechaInicio, fechaFin);
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
		return mGerente.findMaterialSalida(idSalida);
	}

	public BigDecimal actioncalcularValorTotalSalida(int idSalida) throws Exception {
		BigDecimal valorTotal = mGerente.calcularValorTotalSalida(idSalida);
		return valorTotal;
	}

	public void actionListenerLimpiar() throws ParseException {
		inicializar();
	}

	// Getters and Setters
	public int getIdServicios() {
		return idServicios;
	}

	public void setIdServicios(int idServicios) {
		this.idServicios = idServicios;
	}
	public List<RecServicio> getListaServicios() {
		return listaServicios;
	}
	public void setListaServicios(List<RecServicio> listaServicios) {
		this.listaServicios = listaServicios;
	}
	public RecServicio getServicio() {
		return servicio;
	}
	public void setServicio(RecServicio servicio) {
		this.servicio = servicio;
	}
	public RecServicio getnuevoServicio() {
		return nuevoServicio;
	}
	public void setnuevoServicio(RecServicio nuevoServicio) {
		this.nuevoServicio = nuevoServicio;
	}

	public List<InvIngreso> getListaIngresos() {
		return listaIngresos;
	}

	public void setListaIngresos(List<InvIngreso> listaIngresos) {
		this.listaIngresos = listaIngresos;
	}

	public List<InvSalida> getListaSalidas() {
		return listaSalidas;
	}

	public void setListaSalidas(List<InvSalida> listaSalidas) {
		this.listaSalidas = listaSalidas;
	}

	public List<InvMaterial> getListaMateriales() {
		return listaMateriales;
	}

	public void setListaMateriales(List<InvMaterial> listaMateriales) {
		this.listaMateriales = listaMateriales;
	}

	public int getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(int idMaterial) {
		this.idMaterial = idMaterial;
	}

	public List<ThmEmpleado> getListaEmpleados() {
		return listaEmpleados;
	}

	public void setListaEmpleados(List<ThmEmpleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	public int getId_empleados() {
		return id_empleados;
	}

	public void setId_empleados(int id_empleados) {
		this.id_empleados = id_empleados;
	}

	public List<InvProveedor> getListaProveedor() {
		return listaProveedor;
	}

	public void setListaProveedor(List<InvProveedor> listaProveedor) {
		this.listaProveedor = listaProveedor;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public List<RecVehiculo> getListaVehiculos() {
		return listaVehiculos;
	}

	public void setListaVehiculos(List<RecVehiculo> listaVehiculos) {
		this.listaVehiculos = listaVehiculos;
	}

	public int getId_vehiculos() {
		return id_vehiculos;
	}

	public void setId_vehiculos(int id_vehiculos) {
		this.id_vehiculos = id_vehiculos;
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

	public InvProveedor getNuevoProveedor() {
		return nuevoProveedor;
	}

	public void setNuevoProveedor(InvProveedor nuevoProveedor) {
		this.nuevoProveedor = nuevoProveedor;
	}

	// Proveedores
	public InvProveedor actionfindProveedorByID(InvProveedor ProvID) throws Exception {
		if (mGerente.findIdProveedores(ProvID.getProId()) != null) {
			proveedor = mGerente.findIdProveedores(ProvID.getProId());
		}

		return proveedor;
	}

	public List<InvProveedor> actionfindAllproveedores() {
		return mGerente.findAllProveedores();
	}

	public void actionCreateProveedor() throws Exception {
		mGerente.createProveedores(beanLogin.getLoginDTO() ,nuevoProveedor);
	}

	public void actionUpdateProveedor() throws Exception {
		mGerente.updateProveedores(beanLogin.getLoginDTO() ,proveedor);
	}
	//Servicios
		public RecServicio actionfindServicioByID(RecServicio ServID) throws Exception {
			if (mGerente.findIdServicios(ServID.getRecSerId()) != null) {
				servicio = mGerente.findIdServicios(ServID.getRecSerId());
			}

			return servicio;
		}
		public List<RecServicio> actionfindAllServicios() {
			return mGerente.findAllServicios();
		}

		public void actionCreateServicio() throws Exception {
			mGerente.createServicios(nuevoServicio);
			nuevoServicio= new RecServicio();
			listaServicios = mGerente.findAllServicios();
		}

		public void actionUpdateServicio() throws Exception {
			mGerente.updateServicios(servicio);
		}
		public void actionListenerDeleteServicio(RecServicio servicio) throws Exception {
			try {
				mGerente.deleteServicio(servicio);
				listaServicios = mGerente.findAllServicios();
				servicio = new RecServicio();
				JSFUtil.crearMensajeINFO("Servicios eliminado correctamente.");
			} catch (Exception e) {
				JSFUtil.crearMensajeERROR(e.getMessage());
				e.printStackTrace();
			}

		}
}
