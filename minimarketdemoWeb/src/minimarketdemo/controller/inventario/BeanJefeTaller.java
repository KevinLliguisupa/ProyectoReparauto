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
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.InvIngreso;
import minimarketdemo.model.core.entities.InvMaterial;
import minimarketdemo.model.core.entities.InvMaterialIngreso;
import minimarketdemo.model.core.entities.InvMaterialSalida;
import minimarketdemo.model.core.entities.InvProveedor;
import minimarketdemo.model.core.entities.InvSalida;
import minimarketdemo.model.core.entities.InvTipo;
import minimarketdemo.model.core.entities.RecVehiculo;
import minimarketdemo.model.core.entities.ThmEmpleado;
import minimarketdemo.model.inventario.managers.ManagerJefeTaller;

@Named
@SessionScoped
public class BeanJefeTaller implements Serializable {
	// private static final long serialVersionUID = 1L;
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
	private List<InvIngreso> listaIngresos;
	private List<InvSalida> listaSalidas;

	// nuevo
	private InvIngreso ingreso;
	// nuevo
	private InvSalida salida;
	// nuevo
	private List<InvMaterialSalida> detalleSalida;
	// nuevo
	private List<InvMaterialIngreso> detalleIngreso;
	// nuevo
	private List<InvMaterial> listaMatAux;

	private Date fechaInicio;
	private Date fechaFin;

	public List<InvMaterialIngreso> getDetalleIngreso() {
		return detalleIngreso;
	}

	public void setDetalleIngreso(List<InvMaterialIngreso> detalleIngreso) {
		this.detalleIngreso = detalleIngreso;
	}

	public InvIngreso getIngreso() {
		return ingreso;
	}

	public void setIngreso(InvIngreso ingreso) {
		this.ingreso = ingreso;
	}

	public BeanJefeTaller() {

	}

	@PostConstruct
	public void inicializar() throws ParseException {
		listaIngresos = mJefeTaller.findAllIngresos();
		listaProveedor = mJefeTaller.findAllProveedor();
		listaVehiculos = mJefeTaller.findAllVehiculos();
		listaEmpleados = mJefeTaller.findAllEmpleados();
		listaSalidas = mJefeTaller.findAllSalidas();
		listaMateriales = mJefeTaller.findAllMaterial();
		idMaterial = 0;
		idProveedor = 0;
		id_vehiculos = 0;
		// Formato de la fecha
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// obtener la fecha de ayer:
		fechaInicio = sdf.parse("2000-01-01");
		// obtener la fecha de hoy:
		fechaFin = new Date();
		listaMateriales = new ArrayList<InvMaterial>();
		material = new InvMaterial();
		material.setMatId(1);

	}

	public List<InvSalida> getListaSalidas() {
		return listaSalidas;
	}

	public void setListaSalidas(List<InvSalida> listaSalidas) {
		this.listaSalidas = listaSalidas;
	}

	public List<InvMaterial> getListaMateriales() {
		return mJefeTaller.findAllMaterial();
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

	public InvMaterial getNuevoMaterial() {
		return nuevoMaterial;
	}

	public void setNuevoMaterial(InvMaterial nuevoMaterial) {
		this.nuevoMaterial = nuevoMaterial;
	}

	public InvMaterial getMaterial() {
		return material;
	}

	public void setMaterial(InvMaterial material) {
		this.material = material;
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

	public ThmEmpleado getEmpleados() {
		return empleados;
	}

	public void setEmpleados(ThmEmpleado empleados) {
		this.empleados = empleados;
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

	public InvProveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(InvProveedor proveedor) {
		this.proveedor = proveedor;
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

	public RecVehiculo getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(RecVehiculo vehiculos) {
		this.vehiculos = vehiculos;
	}

	public List<InvTipo> getListaTipo() {
		return listaTipo;
	}

	public void setListaTipo(List<InvTipo> listaTipo) {
		this.listaTipo = listaTipo;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public InvTipo getNuevoTipo() {
		return nuevoTipo;
	}

	public void setNuevoTipo(InvTipo nuevoTipo) {
		this.nuevoTipo = nuevoTipo;
	}

	public InvTipo getTipo() {
		return tipo;
	}

	public InvSalida getSalida() {
		return salida;
	}

	public void setSalida(InvSalida salida) {
		this.salida = salida;
	}

	public List<InvMaterialSalida> getDetalleSalida() {
		return detalleSalida;
	}

	public void setDetalleSalida(List<InvMaterialSalida> detalleSalida) {
		this.detalleSalida = detalleSalida;
	}

	public void setTipo(InvTipo tipo) {
		this.tipo = tipo;
	}

	public int getCantidadRetirar() {
		return cantidadRetirar;
	}

	public List<InvIngreso> getListaIngresos() {
		return listaIngresos;
	}

	public void setListaIngresos(List<InvIngreso> listaIngresos) {
		this.listaIngresos = listaIngresos;
	}

	public void setCantidadRetirar(int cantidadRetirar) {
		this.cantidadRetirar = cantidadRetirar;
	}

	public void actionDeleteMaterial(InvMaterial material) throws Exception {
		mJefeTaller.deleteMaterial(material);
		detalleIngreso = mJefeTaller.findAllDetallesByCabIngreso(ingreso);
	}

	// nuevo verificado
	public void actionDeleteSeleccionMaterial(InvMaterial material) throws Exception {
		mJefeTaller.eliminarSeleccionMaterial(listaMateriales, material);
	}

	// modificado verificado
	public void actionIngresarMaterial() throws Exception {
		mJefeTaller.ingresarMaterial(listaMateriales, ingreso);
		detalleIngreso = mJefeTaller.findAllDetallesByCabIngreso(ingreso);
		nuevoMaterial = new InvMaterial();
		listaMateriales = new ArrayList<InvMaterial>();

	}

	// nuevo verificado
	public void actionSeleccinarMaterial() throws Exception {
		tipo = mJefeTaller.findTipoMaterialById(idTipo);
		nuevoMaterial.setInvTipo(tipo);
		mJefeTaller.agregarMaterialSeleccion(listaMateriales, nuevoMaterial);
		nuevoMaterial = new InvMaterial();

	}

	// nuevo
	public void actionSeleccinarMaterialRetirar() throws Exception {
		material = mJefeTaller.findMaterialId(idMaterial);
		mJefeTaller.agregarMaterialRetirar(listaMateriales, material, cantidadRetirar);

	}

	public List<InvMaterial> getListaMatAux() {
		return listaMatAux;
	}

	public void setListaMatAux(List<InvMaterial> listaMatAux) {
		this.listaMatAux = listaMatAux;
	}

	// nuevo verificado
	public void actionUpdateSeleccionMat() throws Exception {
		tipo = mJefeTaller.findTipoMaterialById(idTipo);
		material.setInvTipo(tipo);
	}

	public void actionUpdateMaterial() throws Exception {
		tipo = mJefeTaller.findTipoMaterialById(idTipo);
		material.setInvTipo(tipo);
		mJefeTaller.updateMaterial(material);
		detalleIngreso = mJefeTaller.findAllDetallesByCabIngreso(ingreso);

	}

	public void actionRetirarMaterial() throws Exception {
		mJefeTaller.retirarMaterial(listaMateriales, salida);
		listaMateriales = new ArrayList<InvMaterial>();
		detalleSalida = mJefeTaller.finAllDetalleSalidaByCabRetiro(salida);
		listaMatAux = mJefeTaller.findAllMaterial();
		material = new InvMaterial();
		cantidadRetirar = 0;
	}

	public void actionCreateTipoMaterial() {

	}

	public void actionDeleteTipoMaterial() {

	}

	public void actionUpdateTipoMaterial() {

	}

	// Nuevo verificado
	public void actionIngresarCabeceraIngreso() throws Exception {
		proveedor = mJefeTaller.findIdProveedor(idProveedor);
		mJefeTaller.ingresarCabeceraIngreso(proveedor);
		idProveedor = 0;
		proveedor = new InvProveedor();
		listaIngresos = mJefeTaller.findAllIngresos();
	}

	// Nuevo
	public void actionIngresarCabeceraRetiro() throws Exception {
		empleados = mJefeTaller.findEmpleadosById(id_empleados);
		vehiculos = mJefeTaller.findVehiculosById(id_vehiculos);
		mJefeTaller.ingresarCabeceraRetiro(vehiculos, empleados);
		id_empleados = 0;
		id_vehiculos = 0;
		empleados = new ThmEmpleado();
		vehiculos = new RecVehiculo();
		listaSalidas = mJefeTaller.findAllSalidas();
	}

	// Nuevo verificado
	public String actionCargarDetalles(int id) throws Exception {
		ingreso = mJefeTaller.findIngresoById(id);
		detalleIngreso = mJefeTaller.findAllDetallesByCabIngreso(ingreso);
		listaTipo = mJefeTaller.findAllTipoMaterial();
		nuevoMaterial = new InvMaterial();
		material = new InvMaterial();
		listaMateriales = new ArrayList<InvMaterial>();
		return "detalleIngreso?faces-redirect=true";

	}

	// nuevo verificado
	public void actionDatosActualizar(InvMaterial mat) throws Exception {
		material = mJefeTaller.findMaterialByNameSeleccion(listaMateriales, mat);
	}

	// Nuevo
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

	// nuevo verificado
	public void actionDeleteDetalleIngreso(InvMaterialIngreso detalle) throws Exception {
		mJefeTaller.deleteDetalleIngreso(detalle);
		detalleIngreso = mJefeTaller.findAllDetallesByCabIngreso(ingreso);
	}

	// nuevo
	public void actionDeleteDetalleSalida(InvMaterialSalida detalle) throws Exception {
		mJefeTaller.deleteDetalleSalida(detalle);
		detalleSalida = mJefeTaller.finAllDetalleSalidaByCabRetiro(salida);
	}

	// nuevo
	public void actionClasificarMateriales() throws Exception {
		listaMatAux = mJefeTaller.findAllMaterialesByTipo(idTipo);
	}

	public List<InvMaterialIngreso> actionSeleccionarIngreso(int idIngreso) throws Exception {
		return mJefeTaller.findMaterialIngreso(idIngreso);
	}

	public BigDecimal actioncalcularValorTotalIngreso(int idIngreso) throws Exception {
		BigDecimal valorTotal = mJefeTaller.calcularValorTotalIngreso(idIngreso);
		return valorTotal;
	}

	public void actionListenerConsultarIngresos() throws Exception {
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

	public void actionListenerConsultarSalidas() throws Exception {
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

	// MAterial Update
	public InvMaterial actionfindMaterialByID() throws Exception {
		if (mJefeTaller.findMaterialId(material.getMatId()) != null) {
			material = mJefeTaller.findMaterialId(material.getMatId());
		}
		return material;
	}

	public void actionUpdateMaterialExtra() throws Exception {
		mJefeTaller.updatematerial(material);
	}
}
