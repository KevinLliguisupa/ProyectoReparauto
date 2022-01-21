package minimarketdemo.model.recepcion.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.auditoria.managers.ManagerAuditoria;
import minimarketdemo.model.core.entities.InvMaterial;
import minimarketdemo.model.core.entities.RecCliente;
import minimarketdemo.model.core.entities.RecVehiculo;
import minimarketdemo.model.core.entities.RecVehiculoExtra;
import minimarketdemo.model.core.managers.ManagerDAO;
import minimarketdemo.model.seguridades.dtos.LoginDTO;

/**
 * Session Bean implementation class ManagerRecJefeTaller
 */
@Stateless
@LocalBean
public class ManagerRecJefeTaller {

	@EJB
	private ManagerDAO mDao;
	
	@EJB
	private ManagerAuditoria mAuditoria;
	
    public ManagerRecJefeTaller() {
    }
    
    public List<RecVehiculo> findAllVehiculos(){
    	List<RecVehiculo> lista= mDao.findAll(RecVehiculo.class, "vehId",true);
    	List<RecVehiculo> listaVehiculo= new ArrayList<RecVehiculo>();
    	for(RecVehiculo ve: lista) {
    		if(ve.getVehEstado()) {
    			listaVehiculo.add(ve);
    		}
    		
    	}
    	return listaVehiculo;
    }
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
    public List<RecCliente> findAllClientes(){
    	return mDao.findWhere(RecCliente.class, "cli_estado=true", null);
    }
    public void ingresarCliente(RecCliente ClienteNuevo) throws Exception {
    	RecCliente Tmp=new RecCliente();
    	Tmp.setCliCedula(ClienteNuevo.getCliCedula());
    	Tmp.setCliCelular(ClienteNuevo.getCliCelular());
    	Tmp.setCliCorreo(ClienteNuevo.getCliCorreo());
    	Tmp.setCliDireccion(ClienteNuevo.getCliDireccion());
    	Tmp.setCliNombreApellido(ClienteNuevo.getCliNombreApellido());
    	Tmp.setCliTelefono(ClienteNuevo.getCliTelefono());
    	Tmp.setCliEstado(true);
    	mDao.insertar(Tmp);
    	ClienteNuevo=new RecCliente();
    }
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
