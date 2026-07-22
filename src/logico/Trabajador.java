package logico;

import java.util.ArrayList;

public class Trabajador extends Persona {
	
private static final long serialVersionUID = 1L;
	
	private String oficio;
	
	public Trabajador(String id, String cedula, String nombre, String telefono, String correo, boolean empleado,
			ArrayList<Solicitud> solicitudes, String tiempoDisponible, boolean tieneLicencia, char sexo, String ciudad,
			String oficio) {
		super(id, cedula, nombre, telefono, correo, empleado, solicitudes, tiempoDisponible, tieneLicencia, sexo,
				ciudad);
		this.oficio = oficio;
	}

	

	public String getOficio() {
		return oficio;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}
}
