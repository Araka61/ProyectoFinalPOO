package logico;

import java.util.ArrayList;

public class BolsaEmpleo {
	
	private ArrayList<Persona> lasPersonas;
	private ArrayList<Empresa> lasEmpresas;
	private ArrayList<Oferta> lasOfertas;
	private ArrayList<Solicitud> lasSolicitudes;
 
	private static Controladora controladora = null;
 
	public static int generadorIdPersona = 1;
	public static int generadorIdEmpresa = 1;
	public static int generadorIdOferta = 1;
	public static int generadorIdSolicitud = 1;
 
	private BolsaEmpleo() {
		lasPersonas = new ArrayList<>();
		lasEmpresas = new ArrayList<>();
		lasOfertas = new ArrayList<>();
		lasSolicitudes = new ArrayList<>();
	}
 
	public static Controladora getInstancia() {
		if (controladora == null)
			controladora = new Controladora();
		return controladora;
	}
 
	public ArrayList<Persona> getLasPersonas() {
		return lasPersonas;
	}
 
	public ArrayList<Empresa> getLasEmpresas() {
		return lasEmpresas;
	}
 
	public ArrayList<Oferta> getLasOfertas() {
		return lasOfertas;
	}
 
	public ArrayList<Solicitud> getLasSolicitudes() {
		return lasSolicitudes;
	}

}
