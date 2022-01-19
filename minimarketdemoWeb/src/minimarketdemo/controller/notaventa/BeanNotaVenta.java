package minimarketdemo.controller.notaventa;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.InvIngreso;
import minimarketdemo.model.core.entities.InvMaterial;
import minimarketdemo.model.core.entities.InvMaterialIngreso;
import minimarketdemo.model.core.entities.InvMaterialSalida;
import minimarketdemo.model.core.entities.InvProveedor;
import minimarketdemo.model.core.entities.InvSalida;
import minimarketdemo.model.core.entities.InvTipo;
import minimarketdemo.model.core.entities.RecRecepcionCabecera;
import minimarketdemo.model.core.entities.RecRecepcionDetalle;
import minimarketdemo.model.core.entities.RecServicio;
import minimarketdemo.model.core.entities.RecVehiculo;
import minimarketdemo.model.core.entities.ThmEmpleado;
import minimarketdemo.model.inventario.managers.ManagerGerente;
import minimarketdemo.model.notaventa.managers.managerNotaVenta;

@Named
@SessionScoped
public class BeanNotaVenta implements Serializable {

	@EJB
	private managerNotaVenta mNotaVenta;
	private List<RecRecepcionCabecera> Cabeceras;
	private RecRecepcionCabecera CabeceraSeleccionada;
	private List<RecRecepcionDetalle> DetallesCabecera;
	private RecRecepcionDetalle DetalleSelect;
	public BeanNotaVenta() {

	}

	@PostConstruct
	public void inicializar() throws ParseException {
		Cabeceras=new ArrayList<RecRecepcionCabecera>();
		DetallesCabecera=new ArrayList<RecRecepcionDetalle>();
		CabeceraSeleccionada = new RecRecepcionCabecera();
	}
	
	//Métodos
	public void EstablecerCabecera(RecRecepcionCabecera cab) {
		this.CabeceraSeleccionada=cab;
	}
	public void ActualizarDetalle() throws Exception {
		mNotaVenta.actualizarDetalle(this.DetalleSelect,this.CabeceraSeleccionada);
	}
	public void EstablecerDetalle(RecRecepcionDetalle Detalle) {
		this.DetalleSelect=Detalle;
	}
	//Getters y Setters
	public List<RecRecepcionCabecera> getCabeceras() {
		return mNotaVenta.findCabecerasActivas();
	}

	public void setCabeceras(List<RecRecepcionCabecera> cabeceras) {
		Cabeceras = cabeceras;
	}

	public RecRecepcionCabecera getCabeceraSeleccionada() {
		return CabeceraSeleccionada;
	}

	public void setCabeceraSeleccionada(RecRecepcionCabecera cabeceraSeleccionada) {
		CabeceraSeleccionada = cabeceraSeleccionada;
	}

	public List<RecRecepcionDetalle> getDetallesCabecera() {
		return mNotaVenta.findDetallesByIDCabecera(CabeceraSeleccionada);
	}

	public void setDetallesCabecera(List<RecRecepcionDetalle> detallesCabecera) {
		DetallesCabecera = detallesCabecera;
	}

	public RecRecepcionDetalle getDetalleSelect() {
		return DetalleSelect;
	}

	public void setDetalleSelect(RecRecepcionDetalle detalleSelect) {
		DetalleSelect = detalleSelect;
	}
	
}