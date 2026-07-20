package logico;

import java.util.ArrayList;

public class Grado extends Persona {
	private String universidad;
	private String carrera;
	private String tituloUniversitario;
	
	public Grado(String id, String cedula, String nombre, String telefono, String correo, boolean empleado,
			ArrayList<Solicitud> solicitudes, String tiempoDisponible, float salarioMin, float salarioMax,
			boolean tieneLicencia, String sexo, boolean disMudar, String ciudad, int annosExperiencia,
			String universidad, String carrera, String tituloUniversitario) {
		super(id, cedula, nombre, telefono, correo, empleado, solicitudes, tiempoDisponible, salarioMin, salarioMax,
				tieneLicencia, sexo, disMudar, ciudad, annosExperiencia);
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
