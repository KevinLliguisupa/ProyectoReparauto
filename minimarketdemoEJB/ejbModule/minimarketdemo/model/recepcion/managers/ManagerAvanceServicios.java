package minimarketdemo.model.recepcion.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.auditoria.managers.ManagerAuditoria;
import minimarketdemo.model.core.entities.InvMaterial;
import minimarketdemo.model.core.entities.RecCliente;
import minimarketdemo.model.core.entities.RecRecepcionCabecera;
import minimarketdemo.model.core.entities.RecRecepcionDetalle;
import minimarketdemo.model.core.entities.RecVehiculo;
import minimarketdemo.model.core.managers.ManagerDAO;
import minimarketdemo.model.seguridades.dtos.LoginDTO;

/**
 * Session Bean implementation class ManagerAvanceServicios
 */
@Stateless
@LocalBean
public class ManagerAvanceServicios {
	@EJB
	private ManagerDAO mDao;
	private ManagerAuditoria mAuditoria;
	
    public ManagerAvanceServicios() {
    	mAuditoria= new ManagerAuditoria();
    }
    
    public List<RecRecepcionCabecera> findAllRecepcionCabecera(){
    	List<RecRecepcionCabecera> lista= mDao.findAll(RecRecepcionCabecera.class, "recCabId",true);
    	List<RecRecepcionCabecera> listaRecCabe= new ArrayList<RecRecepcionCabecera>();
    	for(RecRecepcionCabecera rcab: lista) {
    		if(rcab.getRecCabEstado()) {
    			listaRecCabe.add(rcab);
    		}
    		
    	}
    	return listaRecCabe;
    }
    public List<RecRecepcionDetalle> findAllRecepcionDetalle(){
    	List<RecRecepcionDetalle> lista= mDao.findAll(RecRecepcionDetalle.class, "recDetId",true);
    	List<RecRecepcionDetalle> listaRecDet= new ArrayList<RecRecepcionDetalle>();
    	for(RecRecepcionDetalle rdet: lista) {
    		if(rdet.getRecDetEstado()) {
    			listaRecDet.add(rdet);
    		}
    		
    	}
    	return listaRecDet;
    }
    public RecRecepcionCabecera findRecepcionCabeceraById(int id) throws Exception{
    	return (RecRecepcionCabecera) mDao.findById(RecRecepcionCabecera.class, id);
    }
    public RecRecepcionDetalle findRecepcionDetallerById(int id) throws Exception{
    	return (RecRecepcionDetalle) mDao.findById(RecRecepcionDetalle.class, id);
    }
    
}
