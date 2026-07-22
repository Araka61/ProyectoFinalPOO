package logico;

import java.util.ArrayList;

public class Tecnico extends Persona {
	private static final long serialVersionUID = 1L;
	
	private String instituto;
	private String diplomaTecnico;
	private String especialidad;
	
	public Tecnico(String id, String cedula, String nombre, String telefono, String correo, boolean empleado,
			ArrayList<Solicitud> solicitudes, String tiempoDisponible, float salarioMin, float salarioMax,
			boolean tieneLicencia, char sexo, boolean disMudar, String ciudad, int annosExperiencia, String instituto,
			String diplomaTecnico, String especialidad) {
		super(id, cedula, nombre, telefono, correo, empleado, solicitudes, tiempoDisponible, salarioMin, salarioMax,
				tieneLicencia, sexo, disMudar, ciudad, annosExperiencia);
		this.instituto = instituto;
		this.diplomaTecnico = diplomaTecnico;
		this.especialidad = especialidad;
	}

	public String getInstituto() {
		return instituto;
	}

	public void setInstituto(String instituto) {
		this.instituto = instituto;
	}

	public String getDiplomaTecnico() {
		return diplomaTecnico;
	}

	public void setDiplomaTecnico(String diplomaTecnico) {
		this.diplomaTecnico = diplomaTecnico;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
}
