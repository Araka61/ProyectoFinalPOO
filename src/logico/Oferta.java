package logico;

import java.util.ArrayList;

public class Oferta extends Empleo{
	private static final long serialVersionUID = 1L;
	
	private String idEmpresa;
	private String descripcionTrabajo;
	private float salario;
	private float coincidencia;
	private int cantPuesto;
	private boolean soloespecif;
	private ArrayList<String>idSolicitudesRechazadas;

	public Oferta(String id, String tipoTrabajo, String titulo, String tecnico, String habilidad, String tiempoTrabajo,
			int experienciaLaboral, char sexo, String provincia, boolean licenciaDeConducir,
			boolean dispuestoAMudarse, boolean activo, String idEmpresa, String descripcionTrabajo,
			float salario, float coincidencia, int cantPuesto, boolean soloespecif) {
		super(id, tipoTrabajo, titulo, tecnico, habilidad, tiempoTrabajo, experienciaLaboral, sexo, provincia,
				licenciaDeConducir, dispuestoAMudarse, activo);
		this.idEmpresa = idEmpresa;
		this.descripcionTrabajo = descripcionTrabajo;
		this.salario = salario;
		this.coincidencia = coincidencia;
		this.cantPuesto = cantPuesto;
		this.soloespecif = soloespecif;
		this.idSolicitudesRechazadas = new ArrayList<>();
	}

	public String getDescripcionTrabajo() {
		return descripcionTrabajo;
	}

	public void setDescripcionTrabajo(String descripcionTrabajo) {
		this.descripcionTrabajo = descripcionTrabajo;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public float getCoincidencia() {
		return coincidencia;
	}

	public void setCoincidencia(float coincidencia) {
		this.coincidencia = coincidencia;
	}

	public int getCantPuesto() {
		return cantPuesto;
	}

	public void setCantPuesto(int cantPuesto) {
		this.cantPuesto = cantPuesto;
	}

	public boolean isSoloespecif() {
		return soloespecif;
	}

	public void setSoloespecif(boolean soloespecif) {
		this.soloespecif = soloespecif;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}
	
	public ArrayList<String> getIdSolicitudesRechazadas() {
		return idSolicitudesRechazadas;
	}
	
	public void setIdSolicitudesRechazadas(ArrayList<String> idSolicitudesRechazadas) {
		this.idSolicitudesRechazadas = idSolicitudesRechazadas;
	}
	
	public void rechazarSolicitud(Solicitud solicitud) {
		String idSolicitud = solicitud.getId();
		idSolicitudesRechazadas.add(idSolicitud);
	}
}
