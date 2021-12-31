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
import minimarketdemo.model.core.entities.RecVehiculo;
import minimarketdemo.model.core.entities.ThmEmpleado;
import minimarketdemo.model.core.utils.ModelUtil;
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
		idMaterial=0;
		idProveedor=0;
		id_vehiculos=0;
		// Formato de la fecha
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// obtener la fecha de ayer:
		fechaInicio = sdf.parse("2000-01-01");
		// obtener la fecha de hoy:
		fechaFin = new Date();
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
		if(listaConsulta.size()!=0) {
			for (InvIngreso consultas : listaConsulta) {
				for (InvIngreso fechas : ingresosxFecha) {
					if (consultas.getIngId() == fechas.getIngId()) {
						respuesta.add(consultas);
					}
				}
			}
		}else {
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
		if(listaConsulta.size()!=0) {
			for (InvSalida consultas : listaConsulta) {
				for (InvSalida fechas : salidasxFecha) {
					if (consultas.getSalId() == fechas.getSalId()) {
						respuesta.add(consultas);
					}
				}
			}
		}else {
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

	//Getters and Setters
	
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

}
