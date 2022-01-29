package minimarketdemo.model.recepcion.managers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import minimarketdemo.model.auditoria.managers.ManagerAuditoria;
import minimarketdemo.model.core.entities.InvMaterial;
import minimarketdemo.model.core.entities.InvSalida;
import minimarketdemo.model.core.entities.RecCliente;
import minimarketdemo.model.core.entities.RecRecepcionCabecera;
import minimarketdemo.model.core.entities.RecRecepcionDetalle;
import minimarketdemo.model.core.entities.RecServicio;
import minimarketdemo.model.core.entities.RecVehiculo;
import minimarketdemo.model.core.entities.RecVehiculoExtra;
import minimarketdemo.model.core.entities.SegUsuario;
import minimarketdemo.model.core.entities.ThmEmpleado;
import minimarketdemo.model.core.managers.ManagerDAO;
import minimarketdemo.model.seguridades.dtos.LoginDTO;

/**
 * Session Bean implementation class ManagerRecepcion
 */
@Stateless
@LocalBean
public class ManagerRecepcion {

	@EJB
	private ManagerDAO mDao;

	@EJB
	private ManagerAuditoria mAuditoria;

	@PersistenceContext
	private EntityManager em;

	public ManagerRecepcion() {
		// TODO Auto-generated constructor stub
	}

	public List<RecServicio> findAllServicios() {
		return mDao.findWhere(RecServicio.class, "rec_ser_estado=true", "rec_ser_nombre ASC");
	}

	public List<RecCliente> findAllClientes() {
		return mDao.findWhere(RecCliente.class, "cli_estado=true", "cli_nombre_apellido ASC");
	}

	public List<RecVehiculo> findAllVehiculos() {
		return mDao.findWhere(RecVehiculo.class, "veh_estado=true", "veh_placa ASC");
	}

	public int findNumeroRecepcion() throws Exception {
		// return mDao.obtenerValorMax(RecRecepcionCabecera.class, "recCabId");

		Query q;
		String sentenciaSQL;
		int valorMax;
		try {
			sentenciaSQL = "SELECT MAX(o.recCabId) FROM RecRecepcionCabecera o";
			q = em.createQuery(sentenciaSQL);
			valorMax = (Integer) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al obtener valor maximo" + e.getMessage());
		}
		return valorMax+1;
	}

	public BigDecimal calcularTotal(List<RecServicio> serviciosCotizacion) {
		BigDecimal total = new BigDecimal(0);
		for (RecServicio servicio : serviciosCotizacion) {
			total = total.add(servicio.getRecSerPrecio());
		}
		return total;
	}

	public List<RecServicio> retirarServicio(List<RecServicio> serviciosCotizacion, RecServicio servicioSeleccionado) {
		List<RecServicio> lista = new ArrayList<RecServicio>();
		for (int i = 0; i < serviciosCotizacion.size(); i++) {
			if (serviciosCotizacion.get(i).getRecSerId() != servicioSeleccionado.getRecSerId()) {
				lista.add(serviciosCotizacion.get(i));
			}

		}
		return lista;
	}

	public RecServicio findServicioById(int id) throws Exception {
		return (RecServicio) mDao.findById(RecServicio.class, id);
	}

	public void ingresarRecepcion(LoginDTO loginDTO, RecRecepcionCabecera cabeceraRecepcion,
			List<RecServicio> serviciosCotizacion) throws Exception {
		cabeceraRecepcion.setRecCabEstado(true);
		cabeceraRecepcion.setRecCabTerminado(false);
		SegUsuario usuario = new SegUsuario();
		usuario.setIdSegUsuario(loginDTO.getIdSegUsuario());
		cabeceraRecepcion.setSegUsuario(usuario);
		;
		mDao.insertar(cabeceraRecepcion);
		mAuditoria.mostrarLog(loginDTO, getClass(), "ingresarRecepcion",
				"Recepcion de vehiculo Usu. " + cabeceraRecepcion.getSegUsuario().getIdSegUsuario() + " " + " Veh. "
						+ cabeceraRecepcion.getRecVehiculo().getVehPlaca());

		for (RecServicio servicio : serviciosCotizacion) {
			RecRecepcionDetalle detalle = new RecRecepcionDetalle();
			detalle.setRecDetPrecioFinal(servicio.getRecSerPrecio());
			detalle.setRecDetConcluido(false);
			detalle.setRecDetServicioExtra(false);
			detalle.setRecDetEstado(true);
			detalle.setRecDetHorasEmpleadas(0);
			detalle.setRecRecepcionCabecera(cabeceraRecepcion);
			detalle.setRecServicio(servicio);
			mDao.insertar(detalle);
			mAuditoria.mostrarLog(loginDTO, getClass(), "ingresarRecepcion",
					"Servicio a vehiculo Ser. " + detalle.getRecServicio().getRecSerId() + " " + " Rec."
							+ detalle.getRecRecepcionCabecera().getRecCabId());
		}

	}

	public void ingresarCliente(RecCliente clienteNuevo) throws Exception {
		clienteNuevo.setCliEstado(true);
		mDao.insertar(clienteNuevo);
	}
	
	//Cotizacion
	
//	public List<RecServicio> findAllServicios() {
//		return mDao.findWhere(RecServicio.class, "o.recSerEstado=true", null);
//	}

//	public RecServicio findServicioById(int id) throws Exception {
//		return (RecServicio) mDao.findById(RecServicio.class, id);
//	}

	public void agregarServiciosSeleccion(List<RecServicio> listaServicios, int idServicio) throws Exception {
		RecServicio servicio = this.findServicioById(idServicio);
		if (listaServicios.size() == 0) {
			listaServicios.add(servicio);
		} else {
			Boolean verificarExistenciaServicio=false;
			for (RecServicio ser : listaServicios) {
				if(ser.getRecSerId().equals(servicio.getRecSerId())) {
					verificarExistenciaServicio=true;
					break;
				}
			}
			if(verificarExistenciaServicio==false) {
				listaServicios.add(servicio);
			}
			
		}

	}

	public void deleteSeleccionServicio(List<RecServicio> listaServicios, int idServicio) {
		int i = 0;
		for (RecServicio ser : listaServicios) {
			if (ser.getRecSerId().equals(idServicio)) {
				listaServicios.remove(i);
				break;
			}
			i++;
		}
	}
	
	public double calcularPrecioServiciosSeleccionados(List<RecServicio> listaServicios) {
		double total=0;
		for(RecServicio servicio: listaServicios) {
			total=total+servicio.getRecSerPrecio().doubleValue();
		}
		return total;
	}
	
	//Servicios y vehiculos
	
//    public List<RecVehiculo> findAllVehiculos(){
//    	List<RecVehiculo> lista= mDao.findAll(RecVehiculo.class, "vehId",true);
//    	List<RecVehiculo> listaVehiculo= new ArrayList<RecVehiculo>();
//    	for(RecVehiculo ve: lista) {
//    		if(ve.getVehEstado()) {
//    			listaVehiculo.add(ve);
//    		}
//    		
//    	}
//    	return listaVehiculo;
//    }
    public RecVehiculo findVehiculoById(int id) throws Exception{
    	return (RecVehiculo) mDao.findById(RecVehiculo.class, id);
    }
    
    public void insertarVehiculo(RecVehiculo vehiculo, LoginDTO loginDto) throws Exception {
    	vehiculo.setVehEstado(true);
    	mDao.insertar(vehiculo);
    	mAuditoria.mostrarLog(loginDto, this.getClass(), "insertarVehiculo", "Registro de Vehiculo de placa"+vehiculo.getVehPlaca());
    }
    
    public void insertarVehiculo(RecVehiculo vehiculo, RecVehiculoExtra extras, LoginDTO loginDto) throws Exception {
    	vehiculo.setVehEstado(true);
    	extras.setVehExtEstado(true);
    	extras.setRecVehiculo(vehiculo);
    	mDao.insertar(vehiculo);
    	
    	mAuditoria.mostrarLog(loginDto, this.getClass(), "insertarVehiculo", "Registro de Vehiculo de placa"+vehiculo.getVehPlaca());
    	mDao.insertar(extras);
    	mAuditoria.mostrarLog(loginDto, this.getClass(), "insertarVehiculo", "Registro de extras de Vehiculo de placa"+vehiculo.getVehPlaca());
    }
    
    public void actualizarVehiculo(RecVehiculo vehiculo, LoginDTO loginDto) throws Exception {
    	mDao.actualizar(vehiculo);
    	mAuditoria.mostrarLog(loginDto, getClass(), "actualizarVehiculo", "Actualizadion de Vehiculo de placa"+vehiculo.getVehPlaca());
    }
    public void eliminarVehiculo(RecVehiculo vehiculo, LoginDTO loginDto) throws Exception {
    	vehiculo.setVehEstado(false);
    	mDao.actualizar(vehiculo);
    	mAuditoria.mostrarLog(loginDto, getClass(), "eliminarVehiculo", "Eliminacion de Vehiculo loginDto"+vehiculo.getVehPlaca());
    }
    
    //Crud Clientes
//    public List<RecCliente> findAllClientes(){
//    	return mDao.findWhere(RecCliente.class, "cli_estado=true", null);
//    }
//    public void ingresarCliente(RecCliente ClienteNuevo) throws Exception {
//    	RecCliente Tmp=new RecCliente();
//    	Tmp.setCliCedula(ClienteNuevo.getCliCedula());
//    	Tmp.setCliCelular(ClienteNuevo.getCliCelular());
//    	Tmp.setCliCorreo(ClienteNuevo.getCliCorreo());
//    	Tmp.setCliDireccion(ClienteNuevo.getCliDireccion());
//    	Tmp.setCliNombreApellido(ClienteNuevo.getCliNombreApellido());
//    	Tmp.setCliTelefono(ClienteNuevo.getCliTelefono());
//    	Tmp.setCliEstado(true);
//    	mDao.insertar(Tmp);
//    	ClienteNuevo=new RecCliente();
//    }
    public void eliminarCliente(RecCliente ClienteEliminado) throws Exception {
    	ClienteEliminado.setCliEstado(false);
    	mDao.actualizar(ClienteEliminado);
    }
    public void actualizarCliente(RecCliente ClienteUpdate) throws Exception {
    	mDao.actualizar(ClienteUpdate);
    }
    public RecCliente findClienteByID(RecCliente CliBusqueda) throws Exception {
    	return (RecCliente) mDao.findById(RecCliente.class, CliBusqueda.getCliId());
    }

}
