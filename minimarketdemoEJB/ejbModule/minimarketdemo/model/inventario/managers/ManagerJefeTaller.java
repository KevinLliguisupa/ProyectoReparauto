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
public class ManagerJefeTaller {
	@EJB
	private ManagerDAO mDao;
	
	@EJB
	private ManagerAuditoria mAuditoria;
	
	public ManagerJefeTaller() {

	}

	public List<InvMaterial> findAllMaterial() {
		return  mDao.findWhere(InvMaterial.class, "mat_estado=true", "mat_nombre ASC");
	}

	public void createMaterial(InvMaterial material, InvTipo selectTipo) throws Exception {
		InvMaterial newMaterial = new InvMaterial();
		
		newMaterial.setMatEstado(true);
		newMaterial.setMatExistencia(new BigDecimal(0));
		newMaterial.setMatNombre(material.getMatNombre());
		newMaterial.setMatPrecioVenta(material.getMatPrecioVenta());
		newMaterial.setMatUnidadMedida(material.getMatUnidadMedida());
		newMaterial.setMatImagen(material.getMatImagen());
		newMaterial.setInvTipo(selectTipo);
		
		mDao.insertar(newMaterial);
	}
	
	public void deleteMaterial(InvMaterial material) throws Exception {
		material.setMatEstado(false);
		mDao.actualizar(material);

	}
	

	// nuevo verificado
	public void ingresarCabeceraIngreso(LoginDTO loginDTO, InvProveedor proveedor) throws Exception {
		InvIngreso cabIngreso = new InvIngreso();
		cabIngreso.setIngFecha(new Date());
		cabIngreso.setInvProveedor(proveedor);
		mDao.insertar(cabIngreso);
		mAuditoria.mostrarLog(loginDTO, getClass(), "ingresarCabeceraIngreso", "Ingreso de material Usu."+loginDTO.getIdSegUsuario());
	}	
	
	public void ingresarMaterial(LoginDTO loginDTO, List<InvMaterial> listaMaterial, InvIngreso cabeceraIngreso) throws Exception {
		
		for (InvMaterial m : listaMaterial) {
			int idmateirla=m.getMatId();
			InvMaterialIngreso detalleIngreso = new InvMaterialIngreso();
			detalleIngreso.setInvIngreso(cabeceraIngreso);
			detalleIngreso.setMatIngEstado(true);
			detalleIngreso.setMatIngCantidad(m.getMatExistencia());
			detalleIngreso.setMatIngPrecioCompra(m.getMatPrecioVenta());
			detalleIngreso.setInvMaterial(m);
			InvMaterial materialAux = this.findMaterialId(m.getMatId());
			this.calcularSock(materialAux, m.getMatExistencia(), true);
			mDao.actualizar(materialAux);
			mDao.insertar(detalleIngreso);
			mAuditoria.mostrarLog(loginDTO, getClass(), "ingresarMaterial", "Ingreso "+detalleIngreso.getMatIngCantidad()+" "+
			detalleIngreso.getInvMaterial().getMatNombre()+" P.Compr. "+detalleIngreso.getMatIngPrecioCompra()+" Usu. "+loginDTO.getIdSegUsuario());
			
		}
		
	}

	// modificado
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
			mAuditoria.mostrarLog(loginDTO, getClass(), "Retiro Material", "Retiro "+m.getMatExistencia()+" "+
			m.getMatNombre()+" Usu. "+detalleSalida.getInvSalida().getThmEmpleado().getSegUsuario().getIdSegUsuario()+
			" Veh. "+detalleSalida.getInvSalida().getRecVehiculo().getVehId());
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

	public InvMaterial findMaterialId(int id) throws Exception {
		return (InvMaterial) mDao.findById(InvMaterial.class, id);
	}

	public List<InvTipo> findAllTipoMaterial() {
		return  mDao.findWhere(InvTipo.class, "tip_estado=true", "tip_nombre");
	}

	public InvTipo findTipoMaterialById(int id) throws Exception {
		mDao.findById(InvProveedor.class, id);
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
		return  mDao.findWhere(InvProveedor.class, "pro_estado=true", "pro_nombre ASC");
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
		return mDao.findAll(InvIngreso.class, "ingId", false);
	}
	
	public List<InvSalida> findAllSalidas() {
		return mDao.findAll(InvSalida.class, "salFecha", false);
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


	
	
	public void agregarMaterialSeleccion(List<InvMaterial> lista, InvMaterial material, int cantidad, BigDecimal precioCompra) {
		material.setMatExistencia(new BigDecimal(cantidad));
		material.setMatPrecioVenta(precioCompra);
		lista.add(material);
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
	
	public void ingresarCabeceraRetiro(LoginDTO loginDTO, RecVehiculo vehiculo, ThmEmpleado empleado) throws Exception {
		InvSalida cabSalida = new InvSalida();
		cabSalida.setSalFecha(new Date());
		cabSalida.setRecVehiculo(vehiculo);
		cabSalida.setThmEmpleado(empleado);
		mDao.insertar(cabSalida);
		mAuditoria.mostrarLog(loginDTO, getClass(), "ingresarCabeceraRetiro", "Retiro de material Usu. "+
		cabSalida.getThmEmpleado().getSegUsuario().getIdSegUsuario()+" "+" Veh. "+cabSalida.getRecVehiculo().getVehId());
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

	// Material
	public void updatematerial(InvMaterial material) throws Exception {
		if (material == null)
			throw new Exception("No se puede actualizar un dato null");
		try {
			mDao.actualizar(material);
		} catch (Exception e) {
			throw new Exception("No se pudo actualizar el dato: " + e.getMessage());
		}
	}

	public void añadirMaterialExistente(InvMaterial material, int cantidad) throws Exception {
		calcularSock(material, BigDecimal.valueOf(cantidad), true);
		updatematerial(material);
	}

	// Tipo Material
	public void CreateTipoMaterialExtra(InvTipo TipoMat) throws Exception {
		InvTipo NewTipoMat = new InvTipo();
		NewTipoMat.setTipEstado(true);
		NewTipoMat.setTipId(TipoMat.getTipId());
		NewTipoMat.setTipNombre(TipoMat.getTipNombre());
		mDao.insertar(NewTipoMat);
	}
}
