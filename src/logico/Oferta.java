package logico;

import java.time.LocalDate;

public class Oferta {
	
	private String id;
	private char tipoTrabajo;
	private String titulo;
	private String tecnico;
	private String habilidad;
	private String descripcionTrabajo;
	private String tiempoTrabajo;
	private LocalDate horaOferta;
	private float experienciaLaboral;
	private float rangoMinSalario;
	private float rangoMaxSalario;
	private char sexo;
	private String provincia;
	private boolean licenciaDeConducir;
	private boolean dispuestoAMudarse;
	private float coincidencia;
	private int cantPuesto;
	private int minCoincidencia;
	private boolean activo;
	
	public Oferta(String id, char tipoTrabajo, String titulo, String tecnico, String habilidad,
			String descripcionTrabajo, String tiempoTrabajo, float experienciaLaboral, float rangoMinSalario,
			float rangoMaxSalario, char sexo, String provincia, boolean licenciaDeConducir, boolean dispuestoAMudarse,
			float coincidencia, int cantPuesto, int minCoincidencia, boolean activo) {
		super();
		this.id = id;
		this.tipoTrabajo = tipoTrabajo;
		this.titulo = titulo;
		this.tecnico = tecnico;
		this.habilidad = habilidad;
		this.descripcionTrabajo = descripcionTrabajo;
		this.tiempoTrabajo = tiempoTrabajo;
		this.horaOferta = LocalDate.now();
		this.experienciaLaboral = experienciaLaboral;
		this.rangoMinSalario = rangoMinSalario;
		this.rangoMaxSalario = rangoMaxSalario;
		this.sexo = sexo;
		this.provincia = provincia;
		this.licenciaDeConducir = licenciaDeConducir;
		this.dispuestoAMudarse = dispuestoAMudarse;
		this.coincidencia = coincidencia;
		this.cantPuesto = cantPuesto;
		this.setMinCoincidencia(minCoincidencia);
		this.activo = activo;
	}

	public char getTipoTrabajo() {
		return tipoTrabajo;
	}

	public void setTipoTrabajo(char tipoTrabajo) {
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

	public String getDescripcionTrabajo() {
		return descripcionTrabajo;
	}

	public void setDescripcionTrabajo(String descripcionTrabajo) {
		this.descripcionTrabajo = descripcionTrabajo;
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

	public float getRangoMinSalario() {
		return rangoMinSalario;
	}

	public void setRangoMinSalario(float rangoMinSalario) {
		this.rangoMinSalario = rangoMinSalario;
	}

	public float getRangoMaxSalario() {
		return rangoMaxSalario;
	}

	public void setRangoMaxSalario(float rangoMaxSalario) {
		this.rangoMaxSalario = rangoMaxSalario;
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

	public boolean isActivo() {
		return activo;
	}

	public void setDisponible(boolean activo) {
		this.activo = activo;
	}

	public String getId() {
		return id;
	}

	public int getMinCoincidencia() {
		return minCoincidencia;
	}

	public void setMinCoincidencia(int minCoincidencia) {
		this.minCoincidencia = minCoincidencia;
	}
	
	
}
