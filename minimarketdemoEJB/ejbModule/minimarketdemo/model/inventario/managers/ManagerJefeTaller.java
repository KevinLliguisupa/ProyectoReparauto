package minimarketdemo.model.inventario.managers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.core.entities.InvIngreso;
import minimarketdemo.model.core.entities.InvMaterial;
import minimarketdemo.model.core.entities.InvMaterialIngreso;
import minimarketdemo.model.core.entities.InvMaterialSalida;
import minimarketdemo.model.core.entities.InvProveedor;
import minimarketdemo.model.core.entities.InvSalida;
import minimarketdemo.model.core.entities.InvTipo;
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

	public List<InvMaterial> findAllMaterial() {
		return mDao.findWhere(InvMaterial.class, "o.mat_estado=true", null);
	}

	public void deleteMaterial(InvMaterial material) throws Exception {
		material.setMatEstado(false);
		mDao.actualizar(material);

	}

	public void retirarMaterial(InvMaterial material, int cantidad, int vehiculo_id, int empleado_id) throws Exception {

		InvSalida cabeceraSalida = new InvSalida();
		cabeceraSalida.setSalFecha(new Date());
		RecVehiculo vehiculo = (RecVehiculo) mDao.findById(RecVehiculo.class, vehiculo_id);
		cabeceraSalida.setRecVehiculo(vehiculo);
		ThmEmpleado empleado = (ThmEmpleado) mDao.findById(ThmEmpleado.class, empleado_id);
		cabeceraSalida.setThmEmpleado(empleado);
		mDao.insertar(cabeceraSalida);
		cabeceraSalida = (InvSalida) mDao.findAll(InvSalida.class).get((mDao.findAll(InvSalida.class).size() - 1));

		InvMaterialSalida detalleSalida = new InvMaterialSalida();
		detalleSalida.setInvSalida(cabeceraSalida);
		detalleSalida.setMatSalCantidad(new BigDecimal(cantidad));
		detalleSalida.setMatSalPrecio(material.getMatPrecioVenta());
		detalleSalida.setMatSalEstado(true);
		detalleSalida.setInvMaterial(material);
		calcularSock(material, cantidad, false);
		mDao.actualizar(material);
		mDao.insertar(detalleSalida);

	}

	public void ingresarMaterial(InvMaterial material, InvProveedor proveedor) throws Exception {
		InvIngreso cabIngreso = new InvIngreso();
		cabIngreso.setIngFecha(new Date());
		cabIngreso.setInvProveedor(proveedor);
		mDao.insertar(cabIngreso);
		material.setMatEstado(true);
		mDao.insertar(material);
		material= (InvMaterial) mDao.findAll(InvMaterial.class).get((mDao.findAll(InvMaterial.class).size()-1));
		cabIngreso = (InvIngreso) mDao.findAll(InvIngreso.class).get((mDao.findAll(InvIngreso.class).size() - 1));
		InvMaterialIngreso detalleIngreso= new InvMaterialIngreso();
		detalleIngreso.setMatIngCantidad(material.getMatExistencia());
		detalleIngreso.setMatIngPrecioCompra(material.getMatPrecioVenta());
		detalleIngreso.setMatIng_estado(true);
		detalleIngreso.setInvIngreso(cabIngreso);
		detalleIngreso.setInvMaterial(material);
		mDao.insertar(detalleIngreso);
	}

	public void calcularSock(InvMaterial material, int cantidad, boolean ingreso) {
		if(ingreso) {
			material.setMatExistencia(material.getMatExistencia().add(new BigDecimal(cantidad)));
		}else {
			material.setMatExistencia(material.getMatExistencia().subtract(new BigDecimal(cantidad)));
		}
	}

	public void updateMaterial(InvMaterial material) throws Exception {
		mDao.actualizar(material);
	}

	public InvMaterial findMaterialId(int id) throws Exception {
		return (InvMaterial) mDao.findById(InvMaterial.class, id);
	}

	public List<InvTipo> findAllTipoMaterial() {
		return mDao.findAll(InvTipo.class);
	}

	public InvTipo findTipoMaterialById(int id) throws Exception {
		return (InvTipo) mDao.findById(InvTipo.class, id);
	}

	public void createTipoMaterial(InvTipo tipoMaterial) throws Exception {
		mDao.insertar(tipoMaterial);
	}

	public void deleteTipoMaterial(InvTipo tipoMaterial) throws Exception {
		tipoMaterial.setTipEstado(false);
		mDao.actualizar(tipoMaterial);
	}
	public void updateTipoMaterial(InvTipo tipoMaterial) throws Exception {
		mDao.actualizar(tipoMaterial);
	}
	public InvProveedor findIdProveedor(int id) throws Exception {
		return (InvProveedor) mDao.findById(InvProveedor.class, id);
	}

	public List<InvProveedor> findAllProveedor() {
		return mDao.findAll(InvProveedor.class);
	}

	public List<RecVehiculo> findAllVehiculos() {
		return mDao.findAll(RecVehiculo.class);
	}

	public RecVehiculo findVehiculosById(int id) throws Exception {
		return (RecVehiculo) mDao.findById(RecVehiculo.class, id);
	}

	public List<ThmEmpleado> findAllEmpleados() {
		return mDao.findAll(ThmEmpleado.class);
	}

	public ThmEmpleado findEmpleadosById(int id) throws Exception {
		return (ThmEmpleado) mDao.findById(ThmEmpleado.class, id);
	}

}
