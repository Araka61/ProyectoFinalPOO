package logico;

import java.util.ArrayList;

public class Empresa {

	private String id;
	private String rnc;
	private String representante;
	private String tipo;
	private ArrayList<Oferta> lasOfertas;

	public Empresa() {
		lasOfertas = new ArrayList<>();
	}

	public Empresa(String id, String rnc, String representante, String tipo) {
		this.id = id;
		this.rnc = rnc;
		this.representante = representante;
		this.tipo = tipo;
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

}