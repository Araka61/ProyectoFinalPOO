package logico;

import java.util.ArrayList;

public class Tecnico extends Persona {
	
	private static final long serialVersionUID = 1L;
	
	private String instituto;
	private String diplomaTecnico;
	private String especialidad;
	
	public Tecnico(String id, String cedula, String nombre, String telefono, String correo, boolean empleado,
			ArrayList<Solicitud> solicitudes, String tiempoDisponible, boolean tieneLicencia, char sexo, String ciudad,
			String instituto, String diplomaTecnico, String especialidad) {
		super(id, cedula, nombre, telefono, correo, empleado, solicitudes, tiempoDisponible, tieneLicencia, sexo,
				ciudad);
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
