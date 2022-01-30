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

import com.sun.tools.javac.util.Log;

import minimarketdemo.model.auditoria.managers.ManagerAuditoria;
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
import minimarketdemo.model.seguridades.dtos.LoginDTO;

/**
 * Session Bean implementation class ManagerJefeTaller
 */
@Stateless
@LocalBean
public class ManagerInventario {
	@EJB
	private ManagerDAO mDao;

	@EJB
	private ManagerAuditoria mAuditoria;

	public ManagerInventario() {

	}

	// Metodos para Materiales
	public List<InvMaterial> findAllMaterial() {
		return mDao.findWhere(InvMaterial.class, "mat_estado=true", "mat_nombre ASC");
	}

	public void createMaterial(LoginDTO loginDTO, InvMaterial material, InvTipo selectTipo) throws Exception {
		InvMaterial newMaterial = new InvMaterial();

		newMaterial.setMatEstado(true);
		newMaterial.setMatExistencia(new BigDecimal(0));
		newMaterial.setMatNombre(material.getMatNombre());
		newMaterial.setMatPrecioVenta(material.getMatPrecioVenta());
		newMaterial.setMatUnidadMedida(material.getMatUnidadMedida());
		newMaterial.setMatImagen(material.getMatImagen());
		newMaterial.setInvTipo(selectTipo);

		mDao.insertar(newMaterial);
		mAuditoria.mostrarLog(loginDTO, getClass(), "createMaterial",
				"Ingreso de material ." + newMaterial.getMatNombre());
	}

	public void deleteMaterial(LoginDTO loginDTO, InvMaterial material) throws Exception {
		material.setMatEstado(false);
		mDao.actualizar(material);
		mAuditoria.mostrarLog(loginDTO, getClass(), "deleteMaterial",
				"Eliminacion de material: " + material.getMatNombre());
	}

	public InvMaterial findMaterialId(int id) throws Exception {
		return (InvMaterial) mDao.findById(InvMaterial.class, id);
	}

	public void updatematerial(LoginDTO loginDTO, InvMaterial material) throws Exception {
		if (material == null)
			throw new Exception("No se puede actualizar un dato null");
		try {
			mDao.actualizar(material);
			mAuditoria.mostrarLog(loginDTO, getClass(), "Actualizar material",
					"Actualizacion de material: " + material.getMatId());
		} catch (Exception e) {
			throw new Exception("No se pudo actualizar el dato: " + e.getMessage());
		}
	}

	public void retirarMaterialExistente(LoginDTO loginDTO, InvMaterial material, int cantidad) throws Exception {
		calcularSock(material, BigDecimal.valueOf(cantidad), false);
		updatematerial(loginDTO, material);
		mAuditoria.mostrarLog(loginDTO, getClass(), "Retirar material",
				"Retiro de material: " + material.getMatId() + " cantidad: " + cantidad);
	}

	// Metodos para Categorias de material

	public List<InvTipo> findAllTipoMaterial() {
		return mDao.findWhere(InvTipo.class, "tip_estado=true", "tip_nombre");
	}

	public InvTipo findTipoMaterialById(int id) throws Exception {
		mDao.findById(InvProveedor.class, id);
		return (InvTipo) mDao.findById(InvTipo.class, id);
	}

	public void createTipoMaterial(LoginDTO loginDTO, InvTipo tipoMaterial) throws Exception {
		tipoMaterial.setTipEstado(true);
		mDao.insertar(tipoMaterial);
		mAuditoria.mostrarLog(loginDTO, getClass(), "createTipoMaterial",
				"Crear tipo de material: " + tipoMaterial.getTipNombre());
	}

	public void deleteTipoMaterial(LoginDTO loginDTO, InvTipo tipoMaterial) throws Exception {
		tipoMaterial.setTipEstado(false);
		mDao.actualizar(tipoMaterial);
		mAuditoria.mostrarLog(loginDTO, getClass(), "deleteTipoMaterial",
				"Eliminacion de tipo de material: " + tipoMaterial.getTipId());
	}

	public void updateTipoMaterial(LoginDTO loginDTO, InvTipo tipoMaterial) throws Exception {
		mDao.actualizar(tipoMaterial);
		mAuditoria.mostrarLog(loginDTO, getClass(), "updateTipoMaterial",
				"Actualiacion tipo de material: " + tipoMaterial.getTipId());
	}

	public void CreateTipoMaterialExtra(InvTipo TipoMat) throws Exception {
		InvTipo NewTipoMat = new InvTipo();
		NewTipoMat.setTipEstado(true);
		NewTipoMat.setTipId(TipoMat.getTipId());
		NewTipoMat.setTipNombre(TipoMat.getTipNombre());
		mDao.insertar(NewTipoMat);
	}

	// Metodos para proveedores
	public List<InvProveedor> findAllProveedores() {
		List<InvProveedor> lista = mDao.findAll(InvProveedor.class);
		List<InvProveedor> nueva = new ArrayList<InvProveedor>();
		for (InvProveedor l : lista) {
			if (l.getProEstado()) {
				nueva.add(l);
			}
		}
		return nueva;
	}

	public void createProveedores(LoginDTO loginDTO, InvProveedor Proveedor) throws Exception {
		InvProveedor NewProv = new InvProveedor();
		NewProv.setProCorreo(Proveedor.getProCorreo());
		NewProv.setProEstado(true);
		NewProv.setProId(Proveedor.getProId());
		NewProv.setProNombre(Proveedor.getProNombre());
		NewProv.setProTelefono(Proveedor.getProTelefono());
		mDao.insertar(NewProv);
		mAuditoria.mostrarLog(loginDTO, getClass(), "createProveedores",
				"Proveedor ingresado: " + Proveedor.getProNombre());
	}

	public void updateProveedores(LoginDTO loginDTO, InvProveedor Proveedor) throws Exception {
		mDao.actualizar(Proveedor);
		mAuditoria.mostrarLog(loginDTO, getClass(), "updateProveedores",
				"Proveedor actulizado: " + Proveedor.getProId());
	}

	public void deleteProveedores(LoginDTO loginDTO, InvProveedor Proveedor) throws Exception {
		Proveedor.setProEstado(false);
		mDao.actualizar(Proveedor);
		mAuditoria.mostrarLog(loginDTO, getClass(), "deleteProveedores",
				"Proveedor eliminado: " + Proveedor.getProNombre());
	}

	public InvProveedor findIdProveedor(int id) throws Exception {
		return (InvProveedor) mDao.findById(InvProveedor.class, id);
	}

	public List<InvProveedor> findAllProveedor() {
		return mDao.findWhere(InvProveedor.class, "pro_estado=true", "pro_nombre ASC");
	}

	// Metodos para Ingresos
	public List<InvIngreso> findAllIngresos() {
		return mDao.findAll(InvIngreso.class, "ingId", false);
	}

	public InvIngreso findIngresoById(int id) throws Exception {
		return (InvIngreso) mDao.findById(InvIngreso.class, id);
	}

	public void ingresarCabeceraIngreso(LoginDTO loginDTO, InvProveedor proveedor) throws Exception {
		InvIngreso cabIngreso = new InvIngreso();
		cabIngreso.setIngFecha(new Date());
		cabIngreso.setInvProveedor(proveedor);
		mDao.insertar(cabIngreso);
		mAuditoria.mostrarLog(loginDTO, getClass(), "ingresarCabeceraIngreso",
				"Ingreso de material Usu." + loginDTO.getIdSegUsuario());
	}

	public void ingresarMaterial(LoginDTO loginDTO, List<InvMaterial> listaMaterial, InvIngreso cabeceraIngreso)
			throws Exception {

		for (InvMaterial m : listaMaterial) {
			int idmateirla = m.getMatId();
			InvMaterialIngreso detalleIngreso = new InvMaterialIngreso();
			detalleIngreso.setInvIngreso(cabeceraIngreso);
			detalleIngreso.setMatIngEstado(true);
			detalleIngreso.setMatIngCantidad(m.getMatExistencia());
			// Hay que cambiar precio venta por compra
			detalleIngreso.setMatIngPrecioCompra(m.getMatPrecioVenta());
			detalleIngreso.setInvMaterial(m);
			InvMaterial materialAux = this.findMaterialId(m.getMatId());
			this.calcularSock(materialAux, m.getMatExistencia(), true);
			mDao.actualizar(materialAux);
			mDao.insertar(detalleIngreso);
			mAuditoria.mostrarLog(loginDTO, getClass(), "ingresarMaterial",
					"Ingreso " + detalleIngreso.getMatIngCantidad() + " "
							+ detalleIngreso.getInvMaterial().getMatNombre() + " P.Compr. "
							+ detalleIngreso.getMatIngPrecioCompra() + " Usu. " + loginDTO.getIdSegUsuario());

		}

	}

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

	public void deleteDetalleIngreso(LoginDTO loginDTO, InvMaterialIngreso detalle) throws Exception {
		detalle.setMatIngEstado(false);
		mDao.actualizar(detalle);

		mAuditoria.mostrarLog(loginDTO, getClass(), "deleteDetalleIngreso",
				"Eliminacion de detalle de ingreso: " + detalle.getMatEntId());
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

	// Metodos para retiros

	public List<InvSalida> findAllSalidas() {
		return mDao.findAll(InvSalida.class, "salId", false);
	}

	public InvSalida findSalidaById(int id) throws Exception {
		return (InvSalida) mDao.findById(InvSalida.class, id);
	}

	public void retirarMaterial(LoginDTO loginDTO, List<InvMaterial> lista, InvSalida cabeceraSalida) throws Exception {
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
			mAuditoria.mostrarLog(loginDTO, getClass(), "Retiro Material",
					"Retiro " + m.getMatExistencia() + " " + m.getMatNombre() + " Usu. "
							+ detalleSalida.getInvSalida().getThmEmpleado().getSegUsuario().getIdSegUsuario() + " Veh. "
							+ detalleSalida.getInvSalida().getRecVehiculo().getVehId());
		}

	}

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

	public void deleteDetalleSalida(LoginDTO loginDTO, InvMaterialSalida detalle) throws Exception {
		detalle.setMatSalEstado(false);
		mDao.actualizar(detalle);

		mAuditoria.mostrarLog(loginDTO, getClass(), "Eliminar detalle de salida",
				"Eliminacion de detalle salida: " + detalle.getMatSalId());
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

	public void calcularSock(InvMaterial material, BigDecimal cantidad, boolean ingreso) {
		if (ingreso) {
			material.setMatExistencia(material.getMatExistencia().add(cantidad));
		} else {
			material.setMatExistencia(material.getMatExistencia().subtract(cantidad));
		}
	}

	// Metodos para empleados
	public List<ThmEmpleado> findAllEmpleados() {
		return mDao.findAll(ThmEmpleado.class);
	}

	public ThmEmpleado findEmpleadosById(int id) throws Exception {
		return (ThmEmpleado) mDao.findById(ThmEmpleado.class, id);
	}

	// Metodos para vehiculos
	public List<RecVehiculo> findAllVehiculos() {
		return mDao.findWhere(RecVehiculo.class, "veh_estado=true", "veh_placa ASC");
	}

	public RecVehiculo findVehiculosById(int id) throws Exception {
		return (RecVehiculo) mDao.findById(RecVehiculo.class, id);
	}

	// Metodos para realizar selecciones

	public void agregarMaterialSeleccion(List<InvMaterial> lista, InvMaterial material, int cantidad,
			BigDecimal precioCompra) {
		material.setMatExistencia(new BigDecimal(cantidad));
		material.setMatPrecioVenta(precioCompra);
		lista.add(material);
	}

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

	public void agregarMaterialRetirar(List<InvMaterial> lista, InvMaterial material, int cantidad) {

		if (findMaterialByNameSeleccion(lista, material).getMatNombre() == null || lista.size() == 0) {
			material.setMatExistencia(new BigDecimal(cantidad));
			lista.add(material);

		} else {
			int indice = lista.indexOf(findMaterialByNameSeleccion(lista, material));
			lista.get(indice).setMatExistencia(lista.get(indice).getMatExistencia().add(new BigDecimal(cantidad)));
		}
	}

	public void ingresarCabeceraRetiro(LoginDTO loginDTO, RecVehiculo vehiculo, ThmEmpleado empleado) throws Exception {
		InvSalida cabSalida = new InvSalida();
		cabSalida.setSalFecha(new Date());
		cabSalida.setRecVehiculo(vehiculo);
		cabSalida.setThmEmpleado(empleado);
		mDao.insertar(cabSalida);
		mAuditoria.mostrarLog(loginDTO, getClass(), "ingresarCabeceraRetiro",
				"Retiro de material Usu. " + cabSalida.getThmEmpleado().getSegUsuario().getIdSegUsuario() + " "
						+ " Veh. " + cabSalida.getRecVehiculo().getVehId());
	}

	// Metodos para realizar Busquedas

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

}
