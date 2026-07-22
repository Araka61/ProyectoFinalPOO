package logico;

import java.util.ArrayList;


public class BolsaEmpleo {
	private ArrayList<Persona> lasPersonas;
	private ArrayList<Empresa> lasEmpresas;
	private ArrayList<Oferta> lasOfertas;
	private ArrayList<Solicitud> lasSolicitudes;
	private ArrayList<Usuario> losUsuarios;
 
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
		losUsuarios = new ArrayList<>();
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
	
	public ArrayList<Usuario> getLosUsuarios() {
		return losUsuarios;
	}
	
	//            Registro  
	 
		public void registrarPersona(Persona nueva, Usuario user) {
			lasPersonas.add(nueva);
			losUsuarios.add(user);
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
				if (nivel.equalsIgnoreCase("Tecnico") && lasPersonas.get(i) instanceof Tecnico)
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
		
		public Usuario getUsuarioPorUserName (String username){
			
		Usuario resultado = null;
		int i = 0;
		while (i < losUsuarios.size()){
			if (username.equals(losUsuarios.get(i).getUserName()))
				resultado = losUsuarios.get(i);
			i++;
		}
			return resultado;
		}
		
		//         Algoritmo de macheo
		
		public ArrayList<String> PorcentajeCoincidencia(Oferta ofertaEmpresa) {
			int i = 0;
			int puntos = 0;
			ArrayList<String> candidatosIdeales = new ArrayList<>();			
			while (i < lasSolicitudes.size()) {
				Solicitud solicitudCandidato = lasSolicitudes.get(i);			
				if (solicitudCandidato.isActivo() && !(buscarPersona(solicitudCandidato.getId()).isEmpleado())) {
					puntos = calcularPuntosCoincidencia(solicitudCandidato, ofertaEmpresa);
					if (puntos >= ofertaEmpresa.getCoincidencia())
						candidatosIdeales.add(solicitudCandidato.getId());
				}
				i++;
			}
			return candidatosIdeales;
		}

		public int calcularPuntosCoincidencia(Solicitud solicitudCandidato, Oferta ofertaEmpresa) {
			int puntos = 0;
			puntos += compararDisponibilidadYTipo(solicitudCandidato, ofertaEmpresa);
			puntos += compararExperienciaYSalario(solicitudCandidato, ofertaEmpresa);
			puntos += compararDatosPersonales(solicitudCandidato, ofertaEmpresa);
			puntos += compararResidencia(solicitudCandidato, ofertaEmpresa);
			if (!aptitudSolicitud(solicitudCandidato, ofertaEmpresa))
				puntos = -1;
			return puntos;
		}

		private int compararDisponibilidadYTipo(Solicitud solicitudCandidato, Oferta ofertaEmpresa) {
			int puntos = 0;
			if (solicitudCandidato.getTiempoTrabajo().equalsIgnoreCase(ofertaEmpresa.getTiempoTrabajo()))
				puntos += 10;
			if (solicitudCandidato.getTipoTrabajo().equalsIgnoreCase(String.valueOf(ofertaEmpresa.getTipoTrabajo())))
				puntos += 10;
			return puntos;
		}

		private int compararExperienciaYSalario(Solicitud solicitudCandidato, Oferta ofertaEmpresa) {
			int puntos = 0;
			if (solicitudCandidato.getExperienciaLaboral() >= ofertaEmpresa.getExperienciaLaboral())
				puntos += 10;
			if (solicitudCandidato.getRangoMinSalario() <= ofertaEmpresa.getRangoMaxSalario()
					&& solicitudCandidato.getRangoMaxSalario() >= ofertaEmpresa.getRangoMinSalario())
				puntos += 10;
			return puntos;
		}

		private int compararDatosPersonales(Solicitud solicitudCandidato, Oferta ofertaEmpresa) {
			int puntos = 0;
			if (solicitudCandidato.getSexo() == ofertaEmpresa.getSexo())
				puntos += 10;
			if (!ofertaEmpresa.isLicenciaDeConducir())
				puntos +=10;
			else if (solicitudCandidato.isLicenciaDeConducir())
				puntos+=10;
			if (!ofertaEmpresa.isDispuestoAMudarse())
				puntos += 10;
			else if (solicitudCandidato.isDispuestoAMudarse())
				puntos += 10;
			return puntos;
		}

		private int compararResidencia(Solicitud solicitudCandidato, Oferta ofertaEmpresa) {
			int puntos = 0;
			if (solicitudCandidato.getResidencia().equalsIgnoreCase(ofertaEmpresa.getProvincia()))
				puntos += 10;
			return puntos;
		}
		
		
		
		//      Comprobaciones
		
		public boolean login (String username,String password){
			boolean resp = false;
			Usuario aux = getUsuarioPorUserName(username);
		    if (aux != null)
		    {
		    	if(aux.getPassword().equals (password))
		    		resp = true;
		    }	
			return resp;
		}
		
		private boolean aptitudSolicitud (Solicitud solicitudCandidato,Oferta ofertaEmpresa){
			boolean comp = false;
				
			return comp;
			
		}
		public boolean existeUsuario (String username) {
			boolean comp = false;
			if (getUsuarioPorUserName(username) != null)
				comp = true;
			return comp;
		}
}
