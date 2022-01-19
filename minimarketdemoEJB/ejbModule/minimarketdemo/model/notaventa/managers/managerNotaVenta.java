package minimarketdemo.model.notaventa.managers;

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
import minimarketdemo.model.core.entities.RecRecepcionCabecera;
import minimarketdemo.model.core.entities.RecRecepcionDetalle;
import minimarketdemo.model.core.entities.RecServicio;
import minimarketdemo.model.core.entities.RecVehiculo;
import minimarketdemo.model.core.entities.ThmEmpleado;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerGerente
 */
@Stateless
@LocalBean
public class managerNotaVenta {

	@EJB
	private ManagerDAO mDao;

	public managerNotaVenta() {

	}
	public List<RecRecepcionCabecera> findCabecerasActivas(){
		return mDao.findWhere(RecRecepcionCabecera.class, "rec_cab_terminado=true and rec_cab_estado=true", null);
	}
	public List<RecRecepcionDetalle> findDetallesByIDCabecera(RecRecepcionCabecera Cabecera){
		return mDao.findWhere(RecRecepcionDetalle.class, "rec_cab_id_rec_recepcion_cabecera = "+Cabecera.getRecCabId(), null);
	}
	public void actualizarDetalle(RecRecepcionDetalle Detalle, RecRecepcionCabecera Cabecera) throws Exception {
		mDao.actualizar(Detalle);
		Cabecera.setRecCabTotal(ActualizarPrecioDetalle(Cabecera));
		mDao.actualizar(Cabecera);
	}
	public java.math.BigDecimal ActualizarPrecioDetalle(RecRecepcionCabecera Cabecera) {
		List<RecRecepcionDetalle> DetallesRecepcion = findDetallesByIDCabecera(Cabecera);
		double Sumatoria=0;
		for(int i=0; i<DetallesRecepcion.size();i++) {
			Sumatoria += DetallesRecepcion.get(i).getRecDetPrecioFinal().doubleValue();
		}
		return java.math.BigDecimal.valueOf(Sumatoria);
	}
}