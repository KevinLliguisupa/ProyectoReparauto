package minimarketdemo.controller.recepcion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.controller.seguridades.BeanSegLogin;
import minimarketdemo.model.core.entities.RecCliente;
import minimarketdemo.model.core.entities.RecRecepcionCabecera;
import minimarketdemo.model.core.entities.RecServicio;
import minimarketdemo.model.core.entities.RecVehiculo;
import minimarketdemo.model.core.utils.ModelUtil;
import minimarketdemo.model.recepcion.managers.ManagerRecepcion;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;




@Named
@SessionScoped
public class BeanRecepcion implements Serializable {

	@EJB
	private ManagerRecepcion mRecepcion;

	@Inject
	private BeanRecCotizacion beanCotizacion;

	private List<RecVehiculo> listaVehiculos;
	private List<RecServicio> listaServicios;
	private List<RecCliente> listaClientes;

	private RecRecepcionCabecera cabeceraRecepcion;
	private RecVehiculo vehiculo;
	private RecCliente cliente;
	private RecCliente nuevoCliente;
	private Date fechaRecepcion;
	private Time horaRecepcion;

	private int numeroRecepcion;
	private BigDecimal precioTotal;
	private BigDecimal abono;
	private BigDecimal saldo;

	@Inject
	private BeanSegLogin beanSegLogin;

	public BeanRecepcion() {

	}

	@PostConstruct
	public void inicializar() throws Exception {
		listaVehiculos = mRecepcion.findAllVehiculos();

//		if (beanCotizacion.getListaServiciosSeleccionados()==null) {
//			listaServicios=new ArrayList<RecServicio>();
//		}else {
//			listaServicios = beanCotizacion.getListaServiciosSeleccionados();
//		}

		listaServicios = beanCotizacion.getListaServiciosSeleccionados();
		cabeceraRecepcion = new RecRecepcionCabecera();

		cliente = new RecCliente();
		nuevoCliente = new RecCliente();
		vehiculo = new RecVehiculo();
		numeroRecepcion = mRecepcion.findNumeroRecepcion();

		Date hoy = new Date();
		fechaRecepcion = hoy;
		horaRecepcion = new java.sql.Time(hoy.getTime());
		abono = new BigDecimal(0);

		precioTotal = mRecepcion.calcularTotal(listaServicios);
		saldo = precioTotal.subtract(abono);
		cabeceraRecepcion.setRecCabFechaRecepcion(hoy);
		cabeceraRecepcion.setRecCabFechaEntrega(ModelUtil.addDays(hoy, +3));

	}

	public void actionListenerCargarClientes() {
		listaClientes = mRecepcion.findAllClientes();
	}

	public void actionListenerCargarVehiculos() {
		listaVehiculos = mRecepcion.findAllVehiculos();
	}

	public void actionListenerRetirarServicio(int servicioId) throws Exception {
		RecServicio servicioSeleccionado = mRecepcion.findServicioById(servicioId);
		listaServicios = mRecepcion.retirarServicio(listaServicios, servicioSeleccionado);
		precioTotal = mRecepcion.calcularTotal(listaServicios);
		saldo = precioTotal.subtract(abono);
	}

	public void actionListenerCalcularSaldo() {
		precioTotal = mRecepcion.calcularTotal(listaServicios);
		saldo = precioTotal.subtract(abono);
	}

	public void actionSeleccionarCliente(RecCliente clienteSeleccionado) {
		cliente = clienteSeleccionado;
	}

	public void actionSeleccionarVehiculo(RecVehiculo vehiculoSeleccionado) {
		vehiculo = vehiculoSeleccionado;
	}

	public void actionListenerCrearRecepcion() throws Exception {
		cabeceraRecepcion.setRecCabAbono(abono);
		cabeceraRecepcion.setRecCabFechaRecepcion(fechaRecepcion);
		cabeceraRecepcion.setRecCabTotal(precioTotal);
		cabeceraRecepcion.setRecCabHora(horaRecepcion);
		cabeceraRecepcion.setRecCabSaldo(saldo);
		cabeceraRecepcion.setRecVehiculo(vehiculo);
		cabeceraRecepcion.setRecCliente(cliente);
		mRecepcion.ingresarRecepcion(beanSegLogin.getLoginDTO(), cabeceraRecepcion, listaServicios);
		actionReporte(cabeceraRecepcion.getRecCabId());
		cabeceraRecepcion = new RecRecepcionCabecera();

		inicializar();
		listaServicios = new ArrayList<RecServicio>();
		precioTotal = mRecepcion.calcularTotal(listaServicios);
		saldo = precioTotal.subtract(abono);
		beanCotizacion.setListaServiciosSeleccionados(new ArrayList<RecServicio>());
		beanCotizacion.setPrecioTotalServicios(0);
		

	}
	
	private String actionReporte(int cab_id){
		 Map<String,Object> parametros=new HashMap<String,Object>();
		 parametros.put("rec_cab_id",cab_id);

		 FacesContext context=FacesContext.getCurrentInstance();
		 ServletContext servletContext=(ServletContext)context.getExternalContext().getContext();
		 String ruta=servletContext.getRealPath("recepcion/jefeTaller/comprobanteRecepcion.jasper");
		 System.out.println(ruta);
		 HttpServletResponse response=(HttpServletResponse)context.getExternalContext().getResponse();
		 response.addHeader("Content-disposition", "attachment;filename=comprobanteRecepcion.pdf");
		 response.setContentType("application/pdf");
		 try {
		 Class.forName("org.postgresql.Driver");
		 Connection connection = null;
		 connection = DriverManager.getConnection(
		 "jdbc:postgresql://localhost:5432/DBReparauto","postgres", "Scorpion246");
		 JasperPrint impresion=JasperFillManager.fillReport(ruta, parametros,connection);
		 JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());
		 context.getApplication().getStateManager().saveView ( context ) ;
		 System.out.println("reporte generado.");
		 context.responseComplete();
		 } catch (Exception e) {
		 JSFUtil.crearMensajeERROR(e.getMessage());
		 e.printStackTrace();
		 }            	
		 return "";
		 }


	public void actionListenerCrearCliente() throws Exception {
		mRecepcion.ingresarCliente(nuevoCliente);
		cliente = nuevoCliente;
		nuevoCliente = new RecCliente();
	}

	public List<RecServicio> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(List<RecServicio> listaServicios) {
		this.listaServicios = listaServicios;
	}

	public RecVehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(RecVehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public RecCliente getCliente() {
		return cliente;
	}

	public void setCliente(RecCliente cliente) {
		this.cliente = cliente;
	}

	public List<RecCliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<RecCliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<RecVehiculo> getListaVehiculos() {
		return listaVehiculos;
	}

	public void setListaVehiculos(List<RecVehiculo> listaVehiculos) {
		this.listaVehiculos = listaVehiculos;
	}

	public int getNumeroRecepcion() {
		return numeroRecepcion;
	}

	public void setNumeroRecepcion(int numeroRecepcion) {
		this.numeroRecepcion = numeroRecepcion;
	}

	public Time getHoraRecepcion() {
		return horaRecepcion;
	}

	public void setHoraRecepcion(Time horaRecepcion) {
		this.horaRecepcion = horaRecepcion;
	}

	public Date getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Date fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public BigDecimal getAbono() {
		return abono;
	}

	public void setAbono(BigDecimal abono) {
		this.abono = abono;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getFechaActualTexto() {
		Calendar hoy = Calendar.getInstance();

		String dia = "" + hoy.get(Calendar.MONTH) + 1;

		String fechatexto = hoy.get(Calendar.YEAR) + "/" + dia + "/" + hoy.get(Calendar.DAY_OF_MONTH);
		return fechatexto;
	}

	public BigDecimal getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(BigDecimal precioTotal) {
		this.precioTotal = precioTotal;
	}

	public RecRecepcionCabecera getCabeceraRecepcion() {
		return cabeceraRecepcion;
	}

	public void setCabeceraRecepcion(RecRecepcionCabecera cabeceraRecepcion) {
		this.cabeceraRecepcion = cabeceraRecepcion;
	}

	public RecCliente getNuevoCliente() {
		return nuevoCliente;
	}

	public void setNuevoCliente(RecCliente nuevoCliente) {
		this.nuevoCliente = nuevoCliente;
	}

}
