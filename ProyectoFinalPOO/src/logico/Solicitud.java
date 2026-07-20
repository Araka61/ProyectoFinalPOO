package logico;

public class Solicitud {

	private String id;
	private char tipoTrabajo;
	private String titulo;
	private String tecnico;
	private String habilidad;
	private char tiempoTrabajo;
	private float experienciaLaboral;
	private float rangoMinSalario;
	private float rangoMaxSalario;
	private char sexo;
	private String residencia;
	private boolean licenciaDeConducir;
	private boolean dispuestoAMudarse;
	private boolean activo;
	
	public Solicitud(String id, char tipoTrabajo, String titulo, String tecnico, String habilidad, char tiempoTrabajo,
			float experienciaLaboral, float rangoMinSalario, float rangoMaxSalario, char sexo, String residencia,
			boolean licenciaDeConducir, boolean dispuestoAMudarse, boolean activo) {
		super();
		this.id = id;
		this.tipoTrabajo = tipoTrabajo;
		this.titulo = titulo;
		this.tecnico = tecnico;
		this.habilidad = habilidad;
		this.tiempoTrabajo = tiempoTrabajo;
		this.experienciaLaboral = experienciaLaboral;
		this.rangoMinSalario = rangoMinSalario;
		this.rangoMaxSalario = rangoMaxSalario;
		this.sexo = sexo;
		this.residencia = residencia;
		this.licenciaDeConducir = licenciaDeConducir;
		this.dispuestoAMudarse = dispuestoAMudarse;
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

	public char getTiempoTrabajo() {
		return tiempoTrabajo;
	}

	public void setTiempoTrabajo(char tiempoTrabajo) {
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

	public String getResidencia() {
		return residencia;
	}

	public void setResidencia(String residencia) {
		this.residencia = residencia;
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

	public String getId() {
		return id;
	}
	
	//Commit de prueba
	
}
