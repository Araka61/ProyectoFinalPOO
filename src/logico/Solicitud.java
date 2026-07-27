package logico;

public class Solicitud extends Empleo{
	private static final long serialVersionUID = 1L;
	
	private String idUsuario;
	private float rangoMinSalario;
	private float rangoMaxSalario;
	
	public Solicitud(String id, String tipoTrabajo, String titulo, String tecnico, String habilidad,
			String tiempoTrabajo, float experienciaLaboral, char sexo, String provincia, boolean licenciaDeConducir,
			boolean dispuestoAMudarse, boolean activo, String idUsuario, float rangoMinSalario, float rangoMaxSalario) {
		super(id, tipoTrabajo, titulo, tecnico, habilidad, tiempoTrabajo, experienciaLaboral, sexo, provincia,
				licenciaDeConducir, dispuestoAMudarse, activo);
		this.idUsuario = idUsuario;
		this.rangoMinSalario = rangoMinSalario;
		this.rangoMaxSalario = rangoMaxSalario;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIdUsuario() {
		return idUsuario;
	}
}
