package logico;

import java.util.ArrayList;

public abstract class Persona {
	protected String id;
	protected String cedula;
	protected String nombre;
	protected String telefono;
	protected String correo;
	protected boolean empleado;
	protected ArrayList<Solicitud> solicitudes;
	protected String tiempoDisponible;
	protected float salarioMin;
	protected float salarioMax;
	protected boolean tieneLicencia;
	protected char sexo;
	protected boolean disMudar;
	protected String ciudad;
	protected int annosExperiencia;
	
	public Persona(String id, String cedula, String nombre, String telefono, String correo, boolean empleado,
			ArrayList<Solicitud> solicitudes, String tiempoDisponible, float salarioMin, float salarioMax,
			boolean tieneLicencia, char sexo, boolean disMudar, String ciudad, int annosExperiencia) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
		this.empleado = empleado;
		this.solicitudes = solicitudes;
		this.tiempoDisponible = tiempoDisponible;
		this.salarioMin = salarioMin;
		this.salarioMax = salarioMax;
		this.tieneLicencia = tieneLicencia;
		this.sexo = sexo;
		this.disMudar = disMudar;
		this.ciudad = ciudad;
		this.annosExperiencia = annosExperiencia;
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

	public String getTiempoDisponible() {
		return tiempoDisponible;
	}

	public void setTiempoDisponible(String tiempoDisponible) {
		this.tiempoDisponible = tiempoDisponible;
	}

	public float getSalarioMin() {
		return salarioMin;
	}

	public void setSalarioMin(float salarioMin) {
		this.salarioMin = salarioMin;
	}

	public float getSalarioMax() {
		return salarioMax;
	}

	public void setSalarioMax(float salarioMax) {
		this.salarioMax = salarioMax;
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

	public boolean isDisMudar() {
		return disMudar;
	}

	public void setDisMudar(boolean disMudar) {
		this.disMudar = disMudar;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getAnnosExperiencia() {
		return annosExperiencia;
	}

	public void setAnnosExperiencia(int annosExperiencia) {
		this.annosExperiencia = annosExperiencia;
	}

	public String getId() {
		return id;
	}
}
