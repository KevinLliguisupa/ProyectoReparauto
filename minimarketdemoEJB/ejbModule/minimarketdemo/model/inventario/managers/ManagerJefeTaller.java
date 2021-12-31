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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	@EJB
	private ManagerDAO mDao;

	public ManagerJefeTaller() {

	}

	public List<InvMaterial> findAllMaterial() {
		List<InvMaterial> lista = mDao.findAll(InvMaterial.class);
		List<InvMaterial> listaNueva = new ArrayList<InvMaterial>();
		for (InvMaterial mat : lista) {
			if (mat.getMatEstado()) {
				listaNueva.add(mat);
			}
		}
		return listaNueva;
	}

	public void deleteMaterial(InvMaterial material) throws Exception {
		material.setMatEstado(false);
		mDao.actualizar(material);

	}

	// nuevo verificado
	public void ingresarCabeceraIngreso(InvProveedor proveedor) throws Exception {
		InvIngreso cabIngreso = new InvIngreso();
		cabIngreso.setIngFecha(new Date());
		cabIngreso.setInvProveedor(proveedor);
		mDao.insertar(cabIngreso);
	}

	// modificado verificado
	public void ingresarMaterial(List<InvMaterial> listaMaterial, InvIngreso cabeceraIngreso) throws Exception {

		for (InvMaterial material : listaMaterial) {
			mDao.insertar(material);
			material = (InvMaterial) mDao.findAll(InvMaterial.class).get((mDao.findAll(InvMaterial.class).size() - 1));
			InvMaterialIngreso detalleIngreso = new InvMaterialIngreso();
			detalleIngreso.setMatIngCantidad(material.getMatExistencia());
			detalleIngreso.setMatIngPrecioCompra(material.getMatPrecioVenta());
			detalleIngreso.setMatIngEstado(true);
			detalleIngreso.setInvIngreso(cabeceraIngreso);
			detalleIngreso.setInvMaterial(material);
			mDao.insertar(detalleIngreso);
		}

	}

	// modificado
	public void retirarMaterial(List<InvMaterial> lista, InvSalida cabeceraSalida) throws Exception {

		for (InvMaterial m : lista) {
			InvMaterialSalida detalleSalida = new InvMaterialSalida();
			detalleSalida.setInvSalida(cabeceraSalida);
			detalleSalida.setMatSalEstado(true);
			detalleSalida.setMatSalCantidad(m.getMatExistencia());
			detalleSalida.setMatSalPrecio(m.getMatPrecioVenta());
			detalleSalida.setInvMaterial(m);
			InvMaterial materialAux = this.findMaterialId(m.getMatId());
			this.calcularSock(materialAux, m.getMatExistencia(), false);
			mDao.actualizar(materialAux);
			mDao.insertar(detalleSalida);
		}

	}

	public void calcularSock(InvMaterial material, BigDecimal cantidad, boolean ingreso) {
		if (ingreso) {
			material.setMatExistencia(material.getMatExistencia().add(cantidad));
		} else {
			material.setMatExistencia(material.getMatExistencia().subtract(cantidad));
		}
	}

	public void updateMaterial(InvMaterial material) throws Exception {
		mDao.actualizar(material);
	}

	// nuevo
	public void ingresarCabeceraRetiro(RecVehiculo vehiculo, ThmEmpleado empleado) throws Exception {
		InvSalida cabSalida = new InvSalida();
		cabSalida.setSalFecha(new Date());
		cabSalida.setRecVehiculo(vehiculo);
		cabSalida.setThmEmpleado(empleado);
		mDao.insertar(cabSalida);
	}

	public InvMaterial findMaterialId(int id) throws Exception {
		return (InvMaterial) mDao.findById(InvMaterial.class, id);
	}

	public List<InvTipo> findAllTipoMaterial() {
		List<InvTipo> lista = mDao.findAll(InvTipo.class);
		List<InvTipo> listaNueva = new ArrayList<InvTipo>();
		for (InvTipo mat : lista) {
			if (mat.getTipEstado()) {
				listaNueva.add(mat);
			}
		}
		return listaNueva;
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
		List<InvProveedor> lista = mDao.findAll(InvProveedor.class);
		List<InvProveedor> listaNueva = new ArrayList<InvProveedor>();
		for (InvProveedor mat : lista) {
			if (mat.getProEstado()) {
				listaNueva.add(mat);
			}
		}
		return listaNueva;
	}

	public List<RecVehiculo> findAllVehiculos() {
		List<RecVehiculo> lista = mDao.findAll(RecVehiculo.class);
		List<RecVehiculo> listaNueva = new ArrayList<RecVehiculo>();
		for (RecVehiculo mat : lista) {
			if (mat.getVehEstado()) {
				listaNueva.add(mat);
			}
		}
		return listaNueva;
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

	public List<InvIngreso> findAllIngresos() {
		return mDao.findAll(InvIngreso.class);
	}

	public List<InvSalida> findAllSalidas() {
		return mDao.findAll(InvSalida.class);
	}

	// nuevo verificado
	public InvIngreso findIngresoById(int id) throws Exception {
		return (InvIngreso) mDao.findById(InvIngreso.class, id);
	}

	// nuevo
	public InvSalida findSalidaById(int id) throws Exception {
		return (InvSalida) mDao.findById(InvSalida.class, id);
	}

	// nuevo verificado
	public List<InvMaterialIngreso> findAllDetallesByCabIngreso(InvIngreso cabeceraIngreso) {
		List<InvMaterialIngreso> listaIngresos = mDao.findAll(InvMaterialIngreso.class);
		List<InvMaterialIngreso> listaMateriales = new ArrayList<InvMaterialIngreso>();
		for (InvMaterialIngreso ingreso : listaIngresos) {
			if (ingreso.getInvIngreso().getIngId().equals(cabeceraIngreso.getIngId()) && ingreso.getMatIngEstado()) {
				listaMateriales.add(ingreso);
			}

		}
		return listaMateriales;
	}

	// nuevo
	public List<InvMaterialSalida> finAllDetalleSalidaByCabRetiro(InvSalida cabeceraSalida) {
		List<InvMaterialSalida> retiros = mDao.findAll(InvMaterialSalida.class);
		List<InvMaterialSalida> listaRetiros = new ArrayList<InvMaterialSalida>();
		for (InvMaterialSalida reti : retiros) {
			if (reti.getInvSalida().getSalId().equals(cabeceraSalida.getSalId()) && reti.getMatSalEstado()) {
				listaRetiros.add(reti);
			}

		}
		return listaRetiros;
	}

	// nuevo verificado
	public void agregarMaterialSeleccion(List<InvMaterial> lista, InvMaterial material) {
		lista.add(new InvMaterial());
		lista.get(lista.size() - 1).setMatNombre(material.getMatNombre());
		lista.get(lista.size() - 1).setMatPrecioVenta(material.getMatPrecioVenta());
		lista.get(lista.size() - 1).setMatEstado(true);
		lista.get(lista.size() - 1).setMatExistencia(material.getMatExistencia());
		lista.get(lista.size() - 1).setMatUnidadMedida(material.getMatUnidadMedida());
		lista.get(lista.size() - 1).setInvTipo(material.getInvTipo());
	}

	// nuevo verificado
	public void eliminarSeleccionMaterial(List<InvMaterial> lista, InvMaterial material) {
		int i = 0;
		for (InvMaterial m : lista) {
			if (material.getMatNombre().equals(m.getMatNombre())) {
				lista.remove(i);
				break;
			}
			i++;
		}
	}

	// nuevo verificado
	public InvMaterial findMaterialByNameSeleccion(List<InvMaterial> lista, InvMaterial material) {
		InvMaterial nuevo = new InvMaterial();
		int i = 0;
		for (InvMaterial m : lista) {
			if (material.getMatNombre().equals(m.getMatNombre())) {
				nuevo = m;
				break;
			}
			i++;
		}
		return nuevo;
	}

	// nuevo verificado
	public void deleteDetalleIngreso(InvMaterialIngreso detalle) throws Exception {
		detalle.setMatIngEstado(false);
		mDao.actualizar(detalle);
	}

	// nuevo
	public void deleteDetalleSalida(InvMaterialSalida detalle) throws Exception {
		detalle.setMatSalEstado(false);
		mDao.actualizar(detalle);
	}

	// nuevo
	public List<InvMaterial> findAllMaterialesByTipo(int id) {
		List<InvMaterial> materiales = this.findAllMaterial();
		List<InvMaterial> listaMateriales = new ArrayList<InvMaterial>();
		for (InvMaterial m : materiales) {
			if (m.getInvTipo().getTipId().equals(id)) {
				listaMateriales.add(m);
			}
		}
		return listaMateriales;
	}

	// nuevo
	public void agregarMaterialRetirar(List<InvMaterial> lista, InvMaterial material, int cantidad) {

		if (findMaterialByNameSeleccion(lista, material).getMatNombre() == null || lista.size() == 0) {
			material.setMatExistencia(new BigDecimal(cantidad));
			lista.add(material);

		} else {
			int indice = lista.indexOf(findMaterialByNameSeleccion(lista, material));
			lista.get(indice).setMatExistencia(lista.get(indice).getMatExistencia().add(new BigDecimal(cantidad)));
		}
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

}
