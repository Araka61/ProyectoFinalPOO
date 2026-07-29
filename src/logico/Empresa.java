package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String rnc;
	private String representante;
	private String tipo;
	private String claveDeSeguridad;
	private ArrayList<Oferta> lasOfertas;
	private ArrayList<Usuario> reprecentantes;
	private String nombre;

	public Empresa() {
		lasOfertas = new ArrayList<>();
		reprecentantes = new ArrayList<>();
	}

	public Empresa(String id,String nombre, String rnc, String representante, String tipo,String claveDeSeguridad) {
		this.id = id;
		this.rnc = rnc;
		this.nombre = nombre;
		this.representante = representante;
		this.tipo = tipo;
		this.claveDeSeguridad = claveDeSeguridad;
		lasOfertas = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRnc() {
		return rnc;
	}

	public void setRnc(String rnc) {
		this.rnc = rnc;
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public ArrayList<Oferta> getLasOfertas() {
		return lasOfertas;
	}

	public void publicarOferta(Oferta nueva) {
		lasOfertas.add(nueva);
	}

	public boolean eliminarOferta(Oferta eliminar) {
		return lasOfertas.remove(eliminar);
	}

	public int cantidadOfertas() {
		return lasOfertas.size();
	}

	public ArrayList<Usuario> getReprecentantes() {
		return reprecentantes;
	}

	public String getClaveDeSeguridad() {
		return claveDeSeguridad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}