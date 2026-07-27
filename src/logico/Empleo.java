package logico;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Empleo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String tipoTrabajo;
	private String titulo;
	private String tecnico;
	private String habilidad;
	private String tiempoTrabajo;
	private LocalDate hora;
	private float experienciaLaboral;
	private char sexo;
	private String provincia;
	private boolean licenciaDeConducir;
	private boolean dispuestoAMudarse;
	private boolean activo;
	
	public Empleo(String id, String tipoTrabajo, String titulo, String tecnico, String habilidad, String tiempoTrabajo,
			float experienciaLaboral, char sexo, String provincia, boolean licenciaDeConducir,
			boolean dispuestoAMudarse, boolean activo) {
		super();
		this.id = id;
		this.tipoTrabajo = tipoTrabajo;
		this.titulo = titulo;
		this.tecnico = tecnico;
		this.habilidad = habilidad;
		this.tiempoTrabajo = tiempoTrabajo;
		this.hora = LocalDate.now();
		this.experienciaLaboral = experienciaLaboral;
		this.sexo = sexo;
		this.provincia = provincia;
		this.licenciaDeConducir = licenciaDeConducir;
		this.dispuestoAMudarse = dispuestoAMudarse;
		this.activo = activo;
	}

	public String getTipoTrabajo() {
		return tipoTrabajo;
	}

	public void setTipoTrabajo(String tipoTrabajo) {
		this.tipoTrabajo = tipoTrabajo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTecnico() {
		return tecnico;
	}

	public void setTecnico(String tecnico) {
		this.tecnico = tecnico;
	}

	public String getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(String habilidad) {
		this.habilidad = habilidad;
	}

	public String getTiempoTrabajo() {
		return tiempoTrabajo;
	}

	public void setTiempoTrabajo(String tiempoTrabajo) {
		this.tiempoTrabajo = tiempoTrabajo;
	}

	public float getExperienciaLaboral() {
		return experienciaLaboral;
	}

	public void setExperienciaLaboral(float experienciaLaboral) {
		this.experienciaLaboral = experienciaLaboral;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public boolean isLicenciaDeConducir() {
		return licenciaDeConducir;
	}

	public void setLicenciaDeConducir(boolean licenciaDeConducir) {
		this.licenciaDeConducir = licenciaDeConducir;
	}

	public boolean isDispuestoAMudarse() {
		return dispuestoAMudarse;
	}

	public void setDispuestoAMudarse(boolean dispuestoAMudarse) {
		this.dispuestoAMudarse = dispuestoAMudarse;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public LocalDate getHora() {
		return hora;
	}
}
