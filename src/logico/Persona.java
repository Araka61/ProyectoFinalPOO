package logico;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Persona implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected String id;
	protected String cedula;
	protected String nombre;
	protected String telefono;
	protected String correo;
	protected boolean empleado;
	protected ArrayList<Solicitud> solicitudes;
	protected boolean tieneLicencia;
	protected char sexo;
	protected String ciudad;
	
	public Persona(String id, String cedula, String nombre, String telefono, String correo, boolean empleado,
			ArrayList<Solicitud> solicitudes, String tiempoDisponible,
			boolean tieneLicencia, char sexo, String ciudad) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
		this.empleado = empleado;
		this.solicitudes = solicitudes;
		this.tieneLicencia = tieneLicencia;
		this.sexo = sexo;
		this.ciudad = ciudad;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public boolean isEmpleado() {
		return empleado;
	}

	public void setEmpleado(boolean empleado) {
		this.empleado = empleado;
	}

	public ArrayList<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(ArrayList<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public boolean isTieneLicencia() {
		return tieneLicencia;
	}

	public void setTieneLicencia(boolean tieneLicencia) {
		this.tieneLicencia = tieneLicencia;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getId() {
		return id;
	}
}
