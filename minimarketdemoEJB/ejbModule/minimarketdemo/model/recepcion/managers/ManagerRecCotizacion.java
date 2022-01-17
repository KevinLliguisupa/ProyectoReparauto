package minimarketdemo.model.recepcion.managers;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.core.entities.RecServicio;
import minimarketdemo.model.core.managers.ManagerDAO;

/**
 * Session Bean implementation class ManagerRecCotizacion
 */
@Stateless
@LocalBean
public class ManagerRecCotizacion {

	@EJB
	private ManagerDAO mDao;

	public ManagerRecCotizacion() {
	}

	public List<RecServicio> findAllServicios() {
		return mDao.findWhere(RecServicio.class, "o.recSerEstado=true", null);
	}

	public RecServicio findServicioById(int id) throws Exception {
		return (RecServicio) mDao.findById(RecServicio.class, id);
	}

	public void agregarServiciosSeleccion(List<RecServicio> listaServicios, int idServicio) throws Exception {
		RecServicio servicio = this.findServicioById(idServicio);
		if (listaServicios.size() == 0) {
			listaServicios.add(servicio);
		} else {
			Boolean verificarExistenciaServicio=false;
			for (RecServicio ser : listaServicios) {
				if(ser.getRecSerId().equals(servicio.getRecSerId())) {
					verificarExistenciaServicio=true;
					break;
				}
			}
			if(verificarExistenciaServicio==false) {
				listaServicios.add(servicio);
			}
			
		}

	}

	public void deleteSeleccionServicio(List<RecServicio> listaServicios, int idServicio) {
		int i = 0;
		for (RecServicio ser : listaServicios) {
			if (ser.getRecSerId().equals(idServicio)) {
				listaServicios.remove(i);
				break;
			}
			i++;
		}
	}
	
	public double calcularPrecioServiciosSeleccionados(List<RecServicio> listaServicios) {
		double total=0;
		for(RecServicio servicio: listaServicios) {
			total=total+servicio.getRecSerPrecio().doubleValue();
		}
		return total;
	}


}
