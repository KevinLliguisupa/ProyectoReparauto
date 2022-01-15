package minimarketdemo.model.inventario.managers;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.core.entities.InvIngreso;
import minimarketdemo.model.core.entities.InvMaterial;
import minimarketdemo.model.core.entities.InvMaterialIngreso;
import minimarketdemo.model.core.entities.InvMaterialSalida;
import minimarketdemo.model.core.entities.InvProveedor;
import minimarketdemo.model.core.entities.InvSalida;
import minimarketdemo.model.core.entities.RecServicio;
import minimarketdemo.model.core.entities.RecVehiculo;
import minimarketdemo.model.core.entities.ThmEmpleado;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerGerente
 */
@Stateless
@LocalBean
public class ManagerGerente {

	@EJB
	private ManagerDAO mDao;

	public ManagerGerente() {

	}

	public List<InvIngreso> findAllIngresos() {
		return mDao.findAll(InvIngreso.class, "ingFecha", false);
	}

	public List<InvSalida> findAllSalidas() {
		return mDao.findAll(InvSalida.class, "salFecha", false);
	}

	public List<InvMaterialIngreso> findMaterialIngreso(int id) throws Exception {
		String consulta = "ing_id=" + id;
		return mDao.findWhere(InvMaterialIngreso.class, consulta, "mat_ing_cantidad DESC");
	}

	public List<InvMaterialSalida> findMaterialSalida(int id) throws Exception {
		String consulta = "sal_id=" + id;
		return mDao.findWhere(InvMaterialSalida.class, consulta, "mat_sal_cantidad DESC");
	}

	public List<InvIngreso> findIngresosByProveedor(int proId) throws Exception {
		String consulta = "pro_id=" + proId;
		return mDao.findWhere(InvIngreso.class, consulta, "ing_fecha DESC");
	}

	public List<InvSalida> findSalidasByVehiculo(int vehId) throws Exception {
		String consulta = "veh_id_rec_vehiculos=" + vehId;
		return mDao.findWhere(InvSalida.class, consulta, "sal_fecha DESC");
	}

	public List<InvIngreso> findIngresosByMaterial(int matId) throws Exception {
		String consulta = "mat_id=" + matId;
		List<InvMaterialIngreso> listaMatIng = mDao.findWhere(InvMaterialIngreso.class, consulta, "ing_id DESC");
		List<InvIngreso> listaIngresos = new ArrayList<InvIngreso>();
		for (InvMaterialIngreso matIng : listaMatIng) {
			listaIngresos.add(matIng.getInvIngreso());
		}

		return listaIngresos;
	}

	public List<InvIngreso> findIngresosByMaterialProveedor(int matId, int proId) throws Exception {
		String consulta = "mat_id=" + matId;
		List<InvMaterialIngreso> listaMatIng = mDao.findWhere(InvMaterialIngreso.class, consulta, "ing_id DESC");
		List<InvIngreso> listaIngresos = new ArrayList<InvIngreso>();
		for (InvMaterialIngreso matIng : listaMatIng) {
			if (matIng.getInvIngreso().getInvProveedor().getProId() == proId)
				listaIngresos.add(matIng.getInvIngreso());
		}

		return listaIngresos;
	}

	public List<InvIngreso> findIngresoByFecha(Date fechaInicio, Date fechaFin) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Timestamp finicio = new Timestamp(fechaInicio.getTime());
		Timestamp fFin = new Timestamp(fechaFin.getTime());
		String consulta = "ing_fecha between '" + finicio + "' and '" + fFin + "'";
		return mDao.findWhere(InvIngreso.class, consulta, "ing_fecha DESC");
	}

	public List<InvSalida> findSalidaByFecha(Date fechaInicio, Date fechaFin) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Timestamp finicio = new Timestamp(fechaInicio.getTime());
		Timestamp fFin = new Timestamp(fechaFin.getTime());
		String consulta = "sal_fecha between '" + finicio + "' and '" + fFin + "'";
		return mDao.findWhere(InvSalida.class, consulta, "sal_fecha DESC");
	}

	public List<InvSalida> findSalidasByMaterial(int matId) throws Exception {
		String consulta = "mat_id=" + matId;
		List<InvMaterialSalida> listaMatSal = mDao.findWhere(InvMaterialSalida.class, consulta, "sal_id DESC");
		List<InvSalida> listaSalidas = new ArrayList<InvSalida>();
		for (InvMaterialSalida matSal : listaMatSal) {
			listaSalidas.add(matSal.getInvSalida());
		}

		return listaSalidas;
	}

	public BigDecimal calcularValorTotalIngreso(int id) throws Exception {
		BigDecimal valorTotal = new BigDecimal(0);
		String consulta = "ing_id=" + id;
		List<InvMaterialIngreso> listaregistros = mDao.findWhere(InvMaterialIngreso.class, consulta, null);
		for (InvMaterialIngreso registro : listaregistros) {
			valorTotal = valorTotal.add(registro.getTotalxMaterial());
		}

		return valorTotal;
	}

	public List<InvSalida> findSalidasByMaterialVehiculo(int matId, int vehid) throws Exception {
		String consulta = "mat_id=" + matId;
		List<InvMaterialSalida> listaMatSal = mDao.findWhere(InvMaterialSalida.class, consulta, "sal_id DESC");
		List<InvSalida> listaSalidas = new ArrayList<InvSalida>();
		for (InvMaterialSalida matSal : listaMatSal) {
			if (matSal.getInvSalida().getRecVehiculo().getVehId() == vehid)
				listaSalidas.add(matSal.getInvSalida());
		}

		return listaSalidas;
	}

	public BigDecimal calcularValorTotalSalida(int id) throws Exception {
		BigDecimal valorTotal = new BigDecimal(0);
		String consulta = "sal_id=" + id;
		List<InvMaterialSalida> listaregistros = mDao.findWhere(InvMaterialSalida.class, consulta, null);
		for (InvMaterialSalida registro : listaregistros) {
			valorTotal = valorTotal.add(registro.getTotalxMaterial());
		}

		return valorTotal;
	}
	//Metodos CrudServicios
		//************************************************************************************
		public List<RecServicio> findAllServicios() {
			return  mDao.findWhere(RecServicio.class, "rec_ser_estado=true", "rec_ser_nombre ASC");
		}

		public RecServicio findIdServicios(int id) throws Exception {
			return (RecServicio) mDao.findById(RecServicio.class, id);
		}

		public void createServicios(RecServicio servicio) throws Exception {
			//RecServicio NewServ = new RecServicio();
			//NewServ.setRecSerId(findAllServicios().size() + 1);
			//NewServ.setRecSerNombre(Servicio.getRecSerNombre());
			//NewServ.setRecSerPrecio(Servicio.getRecSerPrecio());
			servicio.setRecSerEstado(true);
			mDao.insertar(servicio);
		}

		public void deleteServicio(RecServicio  Servicio) throws Exception {
			Servicio.setRecSerEstado(false);
			mDao.actualizar(Servicio);
		}

		public void updateServicios(RecServicio  Servicio) throws Exception {
			mDao.actualizar(Servicio);
		}

		public RecServicio findIdServicio(int id) throws Exception {
			return (RecServicio) mDao.findById(RecServicio.class, id);
		}
	// Metodo CrudProveedores
	public List<InvProveedor> findAllProveedores() {
		return mDao.findAll(InvProveedor.class);
	}

	public InvProveedor findIdProveedores(int id) throws Exception {
		return (InvProveedor) mDao.findById(InvProveedor.class, id);
	}

	public void createProveedores(InvProveedor Proveedor) throws Exception {
		InvProveedor NewProv = new InvProveedor();
		NewProv.setProCorreo(Proveedor.getProCorreo());
		NewProv.setProEstado(true);
		NewProv.setProId(Proveedor.getProId());
		NewProv.setProNombre(Proveedor.getProNombre());
		NewProv.setProTelefono(Proveedor.getProTelefono());
		mDao.insertar(NewProv);
	}

	public void deleteProveedores(InvProveedor Proveedor) throws Exception {
		Proveedor.setProEstado(false);
		mDao.actualizar(Proveedor);
	}

	public void CreateProveedores(InvProveedor Proveedor) throws Exception {
		InvProveedor NewProv = new InvProveedor();
		NewProv.setProCorreo(Proveedor.getProCorreo());
		NewProv.setProNombre(Proveedor.getProNombre());
		NewProv.setProEstado(true);
		NewProv.setProTelefono(Proveedor.getProTelefono());
		NewProv.setProId(findAllProveedores().size() + 1);
		mDao.insertar(Proveedor);
	}

	public void updateProveedores(InvProveedor Proveedor) throws Exception {
		mDao.actualizar(Proveedor);
	}
	
	

	public List<InvMaterial> findAllMaterial() {
		return mDao.findWhere(InvMaterial.class, "mat_estado=true", null);
	}

	public InvMaterial findMaterialId(int id) throws Exception {
		return (InvMaterial) mDao.findById(InvMaterial.class, id);
	}

	public List<InvProveedor> findAllProveedor() {
		return mDao.findAll(InvProveedor.class);
	}

	public InvProveedor findIdProveedor(int id) throws Exception {
		return (InvProveedor) mDao.findById(InvProveedor.class, id);
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
