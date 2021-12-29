package minimarketdemo.model.inventario.managers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.core.entities.InvMaterial;
import minimarketdemo.model.core.entities.InvProveedor;
import minimarketdemo.model.core.entities.RecVehiculo;
import minimarketdemo.model.core.entities.ThmEmpleado;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerJefeTaller
 */
@Stateless
@LocalBean
public class ManagerJefeTaller {

    private ManagerDAO mDao;
    public ManagerJefeTaller() {
       
    }
    
    public List<InvMaterial> findAllMaterial(){
    	return mDao.findAll(InvMaterial.class);
    }
    
    public void deleteMaterial(InvMaterial material) {
    	
    }
    public void retirarMaterial(InvMaterial material,int cantidad, int vehiculo_id, int empleado_id) {
    	
    }
    public void ingresarMaterial(InvMaterial material,InvProveedor proveedor) {
    	
    }
    
    public void calcularSock(InvMaterial material,int cantidad, boolean ingreso) {
    	
    }
    
    public void updateMaterial(InvMaterial material) {
    	
    }
    
    public InvMaterial findMaterialId(int id) {
    	
    }
    public List<InvTipo> findAllTipoMaterial() {
    	
    }
    public InvTipo findTipoMaterialById(int id) {
    	
    }
    public InvProveedor findIdProveedor(int id) {
    	
    }
    public List<InvProveedor> findAllProveedor() {
    	
    }
    public  List<RecVehiculo> findAllVehiculos(){
    	
    }
    public RecVehiculo findVehiculosById(int id) {
    	
    }
    public List<ThmEmpleado>findAllEmpleados() {
    	
    }
    public ThmEmpleado findEmpleadosById(int id) {
    	
    }
    

}
