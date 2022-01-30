package minimarketdemo.model.servicio.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.auditoria.managers.ManagerAuditoria;
import minimarketdemo.model.core.entities.RecRecepcionCabecera;
import minimarketdemo.model.core.entities.RecRecepcionDetalle;
import minimarketdemo.model.core.entities.RecServicio;
import minimarketdemo.model.core.entities.ThmEmpleado;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerServicio
 */
@Stateless
@LocalBean
public class ManagerServicio {

	@EJB
	private ManagerDAO mDao;
	@EJB
	private ManagerAuditoria mAuditoria;
	
    public ManagerServicio() {
        // TODO Auto-generated constructor stub
    }
    
    public List<RecServicio> findAllServicios() {
		return  mDao.findWhere(RecServicio.class, "rec_ser_estado=true", "rec_ser_nombre ASC");
	}

	public RecServicio findIdServicios(int id) throws Exception {
		return (RecServicio) mDao.findById(RecServicio.class, id);
	}

	public void createServicios(RecServicio servicio) throws Exception {
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
	
	
	
	//Servicios
	
	public List<RecRecepcionCabecera> findAllRecepcionCabecera() {
		List<RecRecepcionCabecera> lista = mDao.findAll(RecRecepcionCabecera.class, "recCabId", true);
		List<RecRecepcionCabecera> listaRecCabe = new ArrayList<RecRecepcionCabecera>();
		for (RecRecepcionCabecera rcab : lista) {
			if (rcab.getRecCabEstado()) {
				listaRecCabe.add(rcab);
			}

		}
		return listaRecCabe;
	}

	public List<RecRecepcionDetalle> findAllRecepcionDetalle() {
		List<RecRecepcionDetalle> lista = mDao.findAll(RecRecepcionDetalle.class, "recDetId", true);
		List<RecRecepcionDetalle> listaRecDet = new ArrayList<RecRecepcionDetalle>();
		for (RecRecepcionDetalle rdet : lista) {
			if (rdet.getRecDetEstado()) {
				listaRecDet.add(rdet);
			}

		}
		return listaRecDet;
	}
	
	public List<ThmEmpleado> findAllEmpleados(){
		return mDao.findAll(ThmEmpleado.class);
	}

	// Metodo que devuelve recepecion detalle por id de cabecera
	public List<RecRecepcionDetalle> findRecDetalleByIdCabecera(int cabId) throws Exception {
		String consulta = "rec_cab_id_rec_recepcion_cabecera=" + cabId;
		List<RecRecepcionDetalle> listaRecDet = mDao.findWhere(RecRecepcionDetalle.class, consulta, "rec_det_id DESC");
		return listaRecDet;
	}
	public List<RecRecepcionDetalle> findRecDetalleByEstado() throws Exception {
		String consulta = "rec_det_concluido=" + true;
		List<RecRecepcionDetalle> listaRecDet = mDao.findWhere(RecRecepcionDetalle.class, consulta, "rec_det_id DESC");
		return listaRecDet;
	}
	public List<RecRecepcionDetalle> findRecDetalleByEstadoAndId(int cabId) throws Exception {
		String consulta1 = "rec_det_concluido=" + true;
		String consulta2 = "rec_cab_id_rec_recepcion_cabecera=" + cabId;
		List<RecRecepcionDetalle> listaRecDet = mDao.findDoubleWhere(RecRecepcionDetalle.class, consulta1,consulta2,"rec_det_id DESC");
		return listaRecDet;
	}

	public RecRecepcionCabecera findRecepcionCabeceraById(int id) throws Exception {
		return (RecRecepcionCabecera) mDao.findById(RecRecepcionCabecera.class, id);
	}
	
	public ThmEmpleado findEmpleadoById(int id) throws Exception {
		return (ThmEmpleado) mDao.findById(ThmEmpleado.class, id);
	}

	public RecRecepcionDetalle findRecepcionDetallerById(int id) throws Exception {
		return (RecRecepcionDetalle) mDao.findById(RecRecepcionDetalle.class, id);
	}
    public void actualizarRecDetalle(RecRecepcionDetalle detalleUpdate) throws Exception {
    	mDao.actualizar(detalleUpdate);
    }
}
