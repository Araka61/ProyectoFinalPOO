package logico;

import java.util.ArrayList;

public class Trabajador extends Persona {
	private String oficio;

	public Trabajador(String id, String cedula, String nombre, String telefono, String correo, boolean empleado,
			ArrayList<Solicitud> solicitudes, String tiempoDisponible, float salarioMin, float salarioMax,
			boolean tieneLicencia, String sexo, boolean disMudar, String ciudad, int annosExperiencia, String oficio) {
		super(id, cedula, nombre, telefono, correo, empleado, solicitudes, tiempoDisponible, salarioMin, salarioMax,
				tieneLicencia, sexo, disMudar, ciudad, annosExperiencia);
		this.oficio = oficio;
	}

	public String getOficio() {
		return oficio;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}
}
