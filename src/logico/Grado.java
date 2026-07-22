package logico;

import java.util.ArrayList;

public class Grado extends Persona {
	
private static final long serialVersionUID = 1L;
	
	private String universidad;
	private String carrera;
	private String tituloUniversitario;
	
	public Grado(String id, String cedula, String nombre, String telefono, String correo, boolean empleado,
			ArrayList<Solicitud> solicitudes, String tiempoDisponible, boolean tieneLicencia, char sexo, String ciudad,
			String universidad, String carrera, String tituloUniversitario) {
		super(id, cedula, nombre, telefono, correo, empleado, solicitudes, tiempoDisponible, tieneLicencia, sexo,
				ciudad);
		this.universidad = universidad;
		this.carrera = carrera;
		this.tituloUniversitario = tituloUniversitario;
	}

	
	
	

	public String getUniversidad() {
		return universidad;
	}

	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getTituloUniversitario() {
		return tituloUniversitario;
	}

	public void setTituloUniversitario(String tituloUniversitario) {
		this.tituloUniversitario = tituloUniversitario;
	}
}
