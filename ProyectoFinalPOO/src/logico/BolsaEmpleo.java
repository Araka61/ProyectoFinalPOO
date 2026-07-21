package logico;

import java.util.ArrayList;

public class BolsaEmpleo {
	
	private ArrayList<Persona> lasPersonas;
	private ArrayList<Empresa> lasEmpresas;
	private ArrayList<Oferta> lasOfertas;
	private ArrayList<Solicitud> lasSolicitudes;
 
	private static BolsaEmpleo controlador= null;
 
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
 
	public static BolsaEmpleo getInstancia() {
		if (controlador == null)
			controlador = new BolsaEmpleo();
		return controlador;
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
	
	//            Registro  
	 
		public void registrarPersona(Persona nueva) {
			lasPersonas.add(nueva);
			generadorIdPersona++;
		}
		public void registrarEmpresa(Empresa nueva) {
			lasEmpresas.add(nueva);
			generadorIdEmpresa++;
		}
	 
		public void registrarOferta(Empresa empresa, Oferta nueva) {
			empresa.publicarOferta(nueva);
			lasOfertas.add(nueva);
			generadorIdOferta++;
		}
		public void registrarSolicitud(Solicitud nueva) {
			lasSolicitudes.add(nueva);
			generadorIdSolicitud++;
		}
		//               Busqueda
	 
		public Persona buscarPersona(String id) {
			
			Persona aux = null;
			boolean encontrado = false;
			int i = 0;
			
			while (!encontrado && i < lasPersonas.size()) {
				if (lasPersonas.get(i).getId().equalsIgnoreCase(id)) {
					aux = lasPersonas.get(i);
					encontrado = true;
				}
				i++;
			}
			return aux;
		}
	
		public Empresa buscarEmpresa(String id) {
			
			Empresa aux = null;
			boolean encontrado = false;
			int i = 0;
			
			while (!encontrado && i < lasEmpresas.size()) {
				if (lasEmpresas.get(i).getId().equalsIgnoreCase(id)) {
					aux = lasEmpresas.get(i);
					encontrado = true;
				}
				i++;
			}
			return aux;
		}
		
		public Solicitud buscarSolicitud(String id) {
			Solicitud aux = null;
			boolean encontrado = false;
			int i = 0;
			while (!encontrado && i < lasSolicitudes.size()) {
				if (lasSolicitudes.get(i).getId().equalsIgnoreCase(id)) {
					aux = lasSolicitudes.get(i);
					encontrado = true;
				}
				i++;
			}
			return aux;
		}
	 
		public Oferta buscarOferta(String id) { 
			
			Oferta aux = null;
			boolean encontrado = false;
			int i = 0;
			
			while (!encontrado && i < lasOfertas.size()) {
				if (lasOfertas.get(i).getId().equalsIgnoreCase(id)) {
					aux = lasOfertas.get(i);
					encontrado = true;
				}
				i++;
			}
			return aux;
		}
		
		public ArrayList<Persona> getPersonasDisponibles() {
			
			ArrayList<Persona> resultado = new ArrayList<>();
			int i = 0;
			while (i < lasPersonas.size()) {
				if (!lasPersonas.get(i).isEmpleado())
					resultado.add(lasPersonas.get(i));
				i++;
			}
			
			return resultado;
		}
		
		public ArrayList<Persona> getPersonasPorNivel(String nivel) {		
			ArrayList<Persona> resultado = new ArrayList<>();
			int i = 0;
			while (i < lasPersonas.size()) {
				if (nivel.equalsIgnoreCase("Tecnico") && lasPersonas.get(i) instanceof Tecnico))
					resultado.add(lasPersonas.get(i));	
				else if (nivel.equalsIgnoreCase("Grado") && lasPersonas.get(i) instanceof Grado) {
					resultado.add(lasPersonas.get(i));
				}
				else if (nivel.equalsIgnoreCase("Trabajador") && lasPersonas.get(i) instanceof Trabajador) {
					resultado.add(lasPersonas.get(i));
				}
				i++;
			}	
			return resultado;
		}
		//         Algoritmo de macheo

}
