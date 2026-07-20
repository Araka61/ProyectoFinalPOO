package logico;

public class Solicitud {

	private String id;
	private char tipoTrabajo;
	private String titulo;
	private String tecnico;
	private String habilidad;
	private char tiempoTrabajo;
	private float rangoMinSalario;
	private float rangoMaxSalario;
	private boolean licenciaDeConducir;
	private char sexo;
	private boolean dispuestoAMudarse;
	private String residencia;
	private String experienciaLaboral;
	
	public Solicitud(String id, char tipoTrabajo, String titulo, String tecnico, String habilidad, char tiempoTrabajo,
			float rangoMinSalario, float rangoMaxSalario, boolean licenciaDeConducir, char sexo,
			boolean dispuestoAMudarse, String residencia, String experienciaLaboral) {
		super();
		this.id = id;
		this.tipoTrabajo = tipoTrabajo;
		this.titulo = titulo;
		this.tecnico = tecnico;
		this.habilidad = habilidad;
		this.tiempoTrabajo = tiempoTrabajo;
		this.rangoMinSalario = rangoMinSalario;
		this.rangoMaxSalario = rangoMaxSalario;
		this.licenciaDeConducir = licenciaDeConducir;
		this.sexo = sexo;
		this.dispuestoAMudarse = dispuestoAMudarse;
		this.residencia = residencia;
		this.experienciaLaboral = experienciaLaboral;
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

	public char getTiempoTrabajo() {
		return tiempoTrabajo;
	}

	public void setTiempoTrabajo(char tiempoTrabajo) {
		this.tiempoTrabajo = tiempoTrabajo;
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

	public boolean isLicenciaDeConducir() {
		return licenciaDeConducir;
	}

	public void setLicenciaDeConducir(boolean licenciaDeConducir) {
		this.licenciaDeConducir = licenciaDeConducir;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public boolean isDispuestoAMudarse() {
		return dispuestoAMudarse;
	}

	public void setDispuestoAMudarse(boolean dispuestoAMudarse) {
		this.dispuestoAMudarse = dispuestoAMudarse;
	}

	public String getResidencia() {
		return residencia;
	}

	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}

	public String getExperienciaLaboral() {
		return experienciaLaboral;
	}

	public void setExperienciaLaboral(String experienciaLaboral) {
		this.experienciaLaboral = experienciaLaboral;
	}

	public String getId() {
		return id;
	}
	
	
}
