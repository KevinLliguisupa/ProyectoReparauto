package minimarketdemo.model.recepcion.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.auditoria.managers.ManagerAuditoria;
import minimarketdemo.model.core.entities.RecVehiculo;
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
	
	private ManagerAuditoria mAuditoria;
    public ManagerRecJefeTaller() {
    	mAuditoria= new ManagerAuditoria();
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
    public void actualizarVehiculo(RecVehiculo vehiculo, LoginDTO loginDto) throws Exception {
    	mDao.actualizar(vehiculo);
    	mAuditoria.mostrarLog(loginDto, getClass(), "actualizarVehiculo", "Actualizadion de Vehiculo de placa"+vehiculo.getVehPlaca());
    }
    public void eliminarVehiculo(RecVehiculo vehiculo, LoginDTO loginDto) throws Exception {
    	vehiculo.setVehEstado(false);
    	mDao.actualizar(vehiculo);
    	mAuditoria.mostrarLog(loginDto, getClass(), "eliminarVehiculo", "Eliminacion de Vehiculo loginDto"+vehiculo.getVehPlaca());
    }
}
