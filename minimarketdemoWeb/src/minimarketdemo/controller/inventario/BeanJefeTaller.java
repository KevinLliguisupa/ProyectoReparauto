package minimarketdemo.controller.inventario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

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
	//private static final long serialVersionUID = 1L;
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
	public void inicializar() {
		listaIngresos = mJefeTaller.findAllIngresos();
		listaProveedor = mJefeTaller.findAllProveedor();
		listaVehiculos = mJefeTaller.findAllVehiculos();
		listaEmpleados = mJefeTaller.findAllEmpleados();
		listaSalidas = mJefeTaller.findAllSalidas();

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
}
