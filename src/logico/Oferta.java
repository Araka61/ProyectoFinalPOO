package logico;

public class Oferta extends Empleo{
	private static final long serialVersionUID = 1L;
	
	private String descripcionTrabajo;
	private float experienciaLaboral;
	private float salario;
	private float coincidencia;
	private int cantPuesto;
	private boolean soloespecif;

	public Oferta(String id, String tipoTrabajo, String titulo, String tecnico, String habilidad, String tiempoTrabajo,
			float experienciaLaboral, char sexo, String provincia, boolean licenciaDeConducir,
			boolean dispuestoAMudarse, boolean activo, String descripcionTrabajo, float experienciaLaboral2,
			float salario, float coincidencia, int cantPuesto, boolean soloespecif) {
		super(id, tipoTrabajo, titulo, tecnico, habilidad, tiempoTrabajo, experienciaLaboral, sexo, provincia,
				licenciaDeConducir, dispuestoAMudarse, activo);
		this.descripcionTrabajo = descripcionTrabajo;
		experienciaLaboral = experienciaLaboral2;
		this.salario = salario;
		this.coincidencia = coincidencia;
		this.cantPuesto = cantPuesto;
		this.soloespecif = soloespecif;
	}

	public String getDescripcionTrabajo() {
		return descripcionTrabajo;
	}

	public void setDescripcionTrabajo(String descripcionTrabajo) {
		this.descripcionTrabajo = descripcionTrabajo;
	}

	public float getExperienciaLaboral() {
		return experienciaLaboral;
	}

	public void setExperienciaLaboral(float experienciaLaboral) {
		this.experienciaLaboral = experienciaLaboral;
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
}
