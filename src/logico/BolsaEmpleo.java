package logico;

import java.util.ArrayList;
import java.util.Arrays;

public class BolsaEmpleo {
	private ArrayList<Persona> lasPersonas;
	private ArrayList<Empresa> lasEmpresas;
	private ArrayList<Oferta> lasOfertas;
	private ArrayList<Solicitud> lasSolicitudes;
	private ArrayList<Usuario> losUsuarios;
	private ArrayList<String> ciudades;
	private ArrayList<String> tiposEmpresa;
	public  Usuario cookieUsuario;

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
		ciudades = new ArrayList<>();
		ciudades.add("Santo Domingo");
        ciudades.add("Santiago de los Caballeros");
        ciudades.add("San Francisco de Macorís");
        ciudades.add("La Vega");
        ciudades.add("Puerto Plata");
        ciudades.add("San Pedro de Macorís");
        ciudades.add("La Romana");
        ciudades.add("San Cristóbal");
        ciudades.add("Higüey");
        ciudades.add("Moca");
        ciudades.add("Bonao");
        ciudades.add("Baní");
        ciudades.add("San Juan de la Maguana");
        ciudades.add("Barahona");
        ciudades.add("Azua");
        tiposEmpresa = new ArrayList<>();
        tiposEmpresa.add("Micro");
        tiposEmpresa.add("Pequña");
        tiposEmpresa.add("Mediana");
        tiposEmpresa.add("Grande");
        
		cookieUsuario = null;
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
	public ArrayList<String> getCiudades() {
		return ciudades;
	}
	
	public ArrayList<String> getTiposEmpresa() {
		return tiposEmpresa;
	}


	//            Registro  

	
	public void registrarPersonaGrado(String cedula, String nombre, String telefono, String correo, 
            String tiempoDisponible, boolean tieneLicencia, char sexo, String ciudad,
            String universidad, String carrera, String tituloUniversitario,
            String username, String password, String rol) {

		String id = "P" + generadorIdPersona;
		Persona nueva = new Grado(id, cedula, nombre, telefono, correo, false, new ArrayList<>(), 
				tiempoDisponible, tieneLicencia, sexo, ciudad, 
				universidad, carrera, tituloUniversitario);

		completarRegistroPersona(nueva, correo, username, password, rol);
	}

	public void registrarPersonaTecnico(String cedula, String nombre, String telefono, String correo, 
			String tiempoDisponible, boolean tieneLicencia, char sexo, String ciudad,
			String instituto, String diplomaTecnico, String especialidad,
			String username, String password, String rol) {

		String id = "P" + generadorIdPersona;
		Persona nueva = new Tecnico(id, cedula, nombre, telefono, correo, false, new ArrayList<>(), 
				tiempoDisponible, tieneLicencia, sexo, ciudad, 
				instituto, diplomaTecnico, especialidad);

		completarRegistroPersona(nueva, correo, username, password, rol);
	}

	public void registrarPersonaTrabajador(String cedula, String nombre, String telefono, String correo, 
			String tiempoDisponible, boolean tieneLicencia, char sexo, String ciudad,
			String oficio, String username, String password, String rol) {

		String id = "P" + generadorIdPersona;
		Persona nueva = new Trabajador(id, cedula, nombre, telefono, correo, false, new ArrayList<>(), 
				tiempoDisponible, tieneLicencia, sexo, ciudad, oficio);

		completarRegistroPersona(nueva, correo, username, password, rol);
	}

	private void completarRegistroPersona(Persona nueva, String correo, String username, String password, String rol) {
		Usuario usuario = new Usuario(nueva.getId(), correo, username, password, rol);
		lasPersonas.add(nueva);
		losUsuarios.add(usuario);
		generadorIdPersona++;
	}
	
	public Empresa registrarEmpresa(String nombre, String rnc, String representante, String tipo, String claveDeSeguridad) {
	    String id = "E" + generadorIdEmpresa;
	    Empresa nueva = new Empresa(id, nombre, rnc, representante, tipo, claveDeSeguridad);
	    lasEmpresas.add(nueva);
	    generadorIdEmpresa++;
	    return nueva;
	}

	public void registrarOferta(String idEmpresa, String tipoTrabajo, String titulo, String tecnico, 
            String habilidad, String tiempoTrabajo, int experienciaLaboral, 
            char sexo, String provincia, boolean licencia, boolean dispuestoAMudarse, 
            String descripcion, float salario, float coincidencia, int cantPuesto, boolean soloEspecif) {

		Empresa emp = buscarEmpresa(idEmpresa);
		if (emp != null) {
			String idOferta = "O" + generadorIdOferta;
			Oferta nueva = new Oferta(idOferta, tipoTrabajo, titulo, tecnico, habilidad, tiempoTrabajo, 
                 experienciaLaboral, sexo, provincia, licencia, dispuestoAMudarse, 
                 true, descripcion, salario, coincidencia, cantPuesto, soloEspecif);

			emp.publicarOferta(nueva);
			lasOfertas.add(nueva);
			generadorIdOferta++;
		}
	}
	
	public void registrarSolicitud(String idUsuario, String tipo, String tituloCarrera, String diplomaTecnico,
	        String habilidadOficio, String tiempoTrabajo, int experiencia, char sexo, String provincia,
	        boolean tieneLicencia, boolean dispuestoAMudarse, float minSal, float maxSal) {
	    
	    String idSolicitud = "S" + generadorIdSolicitud;
	    boolean activo = true;

	    Solicitud nueva = new Solicitud(
	        idSolicitud, 
	        tipo, 
	        tituloCarrera, 
	        diplomaTecnico, 
	        habilidadOficio, 
	        tiempoTrabajo,
	        experiencia, 
	        sexo, 
	        provincia, 
	        tieneLicencia, 
	        dispuestoAMudarse, 
	        activo, 
	        idUsuario, 
	        minSal, 
	        maxSal
	    );

	    lasSolicitudes.add(nueva);
	    Persona persona = buscarPersona(idUsuario);
	    if (persona != null) {
	        if (persona.getSolicitudes() == null) {
	            persona.setSolicitudes(new ArrayList<>());
	        }
	        persona.getSolicitudes().add(nueva);
	    }

	    generadorIdSolicitud++;
	}
	public void registrarUsuarioEmpres(Usuario nuevo) {
		losUsuarios.add(nuevo);
	}
	public void modificarSolicitud(String idSolicitud, String tipo, String tituloCarrera, String diplomaTecnico,
	        String habilidadOficio, String tiempoTrabajo, int experiencia, char sexo, String provincia,
	        boolean tieneLicencia, boolean dispuestoAMudarse, float minSal, float maxSal) {
	    
	    Solicitud solicitud = buscarSolicitud(idSolicitud);
	    if (solicitud != null) {
	        solicitud.setTipoTrabajo(tipo);
	        solicitud.setTitulo(tituloCarrera);
	        solicitud.setTecnico(diplomaTecnico);
	        solicitud.setHabilidad(habilidadOficio);
	        solicitud.setTiempoTrabajo(tiempoTrabajo);
	        solicitud.setExperienciaLaboral(experiencia);
	        solicitud.setSexo(sexo);
	        solicitud.setProvincia(provincia);
	        solicitud.setLicenciaDeConducir(tieneLicencia);
	        solicitud.setDispuestoAMudarse(dispuestoAMudarse);
	        solicitud.setRangoMinSalario(minSal);
	        solicitud.setRangoMaxSalario(maxSal);
	    }
	}

	public Usuario getCookieUsuario() {
		return cookieUsuario;
	}

	public void setCookieUsuario(Usuario cookieUsuario) {
		this.cookieUsuario = cookieUsuario;
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
	public Empresa getEmpresaNombre(String nombre){
		Empresa aux = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < lasEmpresas.size()) {
			if (lasEmpresas.get(i).getNombre().equalsIgnoreCase(nombre)) {
				aux = lasEmpresas.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}
	
	public Empresa getEmpresaRNC(String rnc){
		Empresa aux = null;
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < lasEmpresas.size()) {
			if (lasEmpresas.get(i).getRnc().equals(rnc)) {
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

	public Usuario getUsuarioPorCorreo(String Correo) {
		Usuario aux = null;
		for (int i=0; i<losUsuarios.size();i++)
		{
			if (losUsuarios.get(i).getEmail().equals(Correo))
				aux = losUsuarios.get(i);
		}
		return aux;

	}

	//         Algoritmo de macheo

	public ArrayList<String> PorcentajeCoincidencia(Oferta ofertaEmpresa) {
		int i = 0;
		int puntos = 0;
		ArrayList<String> candidatosIdeales = new ArrayList<>();			
		while (i < lasSolicitudes.size()) {
			Solicitud solicitudCandidato = lasSolicitudes.get(i);			
			Persona p = buscarPersona(solicitudCandidato.getIdUsuario());
			if (solicitudCandidato.isActivo() && p != null && !p.isEmpleado()) {
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
		if (!aptitudSolicitud(solicitudCandidato, ofertaEmpresa) || solicitudCandidato.isActivo())
			puntos = -1;
		else {
			puntos += 40;
		}
		return puntos;
	}

	private int compararDisponibilidadYTipo(Solicitud solicitudCandidato, Oferta ofertaEmpresa) {
		int puntos = 0;
		if (solicitudCandidato.getTiempoTrabajo().equalsIgnoreCase(ofertaEmpresa.getTiempoTrabajo()))
			puntos += 5;
		if (solicitudCandidato.getTipoTrabajo().equalsIgnoreCase(String.valueOf(ofertaEmpresa.getTipoTrabajo())))
			puntos += 5;
		return puntos;
	}

	private int compararExperienciaYSalario(Solicitud solicitudCandidato, Oferta ofertaEmpresa) {
		int puntos = 0;
		if (solicitudCandidato.getExperienciaLaboral() >= ofertaEmpresa.getExperienciaLaboral())
			puntos += 8;
		if (solicitudCandidato.getRangoMinSalario() <= ofertaEmpresa.getSalario()
				&& solicitudCandidato.getRangoMaxSalario() >= ofertaEmpresa.getSalario())
			puntos += 5;
		return puntos;
	}

	private int compararDatosPersonales(Solicitud solicitudCandidato, Oferta ofertaEmpresa) {
		int puntos = 0;
		if (solicitudCandidato.getSexo() == ofertaEmpresa.getSexo())
			puntos += 2;
		if (!ofertaEmpresa.isLicenciaDeConducir() || solicitudCandidato.isLicenciaDeConducir())
			puntos += 10;
		if (!ofertaEmpresa.isDispuestoAMudarse() || solicitudCandidato.isDispuestoAMudarse())
			puntos += 15;
		return puntos;
	}

	private int compararResidencia(Solicitud solicitudCandidato, Oferta ofertaEmpresa) {
		int puntos = 0;
		if (solicitudCandidato.getProvincia().equalsIgnoreCase(ofertaEmpresa.getProvincia()))
			puntos += 10;
		return puntos;
	}

	private boolean aptitudSolicitud(Solicitud solicitudCandidato, Oferta ofertaEmpresa) {
		boolean comp = true;

		if (!ofertaEmpresa.getTitulo().equalsIgnoreCase("n/a") && !ofertaEmpresa.getTitulo().trim().isEmpty()) {
			if (solicitudCandidato.getTitulo().equalsIgnoreCase("n/a") || 
					!areaRelacionadaTitulo(ofertaEmpresa.getTitulo(), solicitudCandidato.getTitulo())) {
				comp = false;
			}
		}

		if (comp && !ofertaEmpresa.getTecnico().equalsIgnoreCase("n/a") && !ofertaEmpresa.getTecnico().trim().isEmpty()) {
			if (solicitudCandidato.getTecnico().equalsIgnoreCase("n/a") || 
					!areaRelacionadaTitulo(ofertaEmpresa.getTecnico(), solicitudCandidato.getTecnico())) {
				comp = false;
			}
		}

		if (comp && !ofertaEmpresa.getHabilidad().equalsIgnoreCase("n/a") && !ofertaEmpresa.getHabilidad().trim().isEmpty()) {
			if (solicitudCandidato.getHabilidad().equalsIgnoreCase("n/a") || 
					!areaRelacionadaTitulo(ofertaEmpresa.getHabilidad(), solicitudCandidato.getHabilidad())) {
				comp = false;
			}
		}

		return comp;
	}

	private boolean areaRelacionadaTitulo(String reqOferta, String areaCandidato) {
		if (reqOferta == null || areaCandidato == null) {
			return false;
		}

		String req = reqOferta.trim().toLowerCase();
		String cand = areaCandidato.trim().toLowerCase();

		return req.equalsIgnoreCase(cand) || cand.contains(req) || req.contains(cand);
	}

	//      Comprobaciones

	public boolean login (String username,String password){
		boolean resp = false;
		Usuario aux = getUsuarioPorUserName(username);
		if (aux != null)
		{
			if(aux.getPassword().equals (password)) {
				resp = true;
				cookieUsuario = aux;
			}
		}	 
		return resp;
	}

	public boolean existeUsuario (String username) {
		boolean comp = false;
		if (getUsuarioPorUserName(username) != null)
			comp = true;
		return comp;
	}

	public boolean claveCorrecta (String clave,Empresa empresa){
		if (empresa.getClaveDeSeguridad().equals(clave))
			return true;
		return false;	
	}
	
	public Solicitud[] top3Candidatos(ArrayList<String> candidatosValidos, Oferta oferta){
		Solicitud top3Candidatos[] = new Solicitud[3];
		int puntosTop3Candidatos[] = new int[3];
 		Solicitud candidatoActual = null;
		int puntosCandidato = 0;
		for(String idCandidato : candidatosValidos) {
			candidatoActual = buscarSolicitud(idCandidato);
			puntosCandidato = calcularPuntosCoincidencia(candidatoActual, oferta);
			
			if(puntosCandidato >= puntosTop3Candidatos[0]) {
				top3Candidatos[2] = top3Candidatos[1];
				puntosTop3Candidatos[2] = puntosTop3Candidatos[1];
				top3Candidatos[1] = top3Candidatos[0];
				puntosTop3Candidatos[1] = puntosTop3Candidatos[0];
				top3Candidatos[0] = candidatoActual;
				puntosTop3Candidatos[0] = puntosCandidato;
			}else if(puntosCandidato >= puntosTop3Candidatos[1]) {
				top3Candidatos[2] = top3Candidatos[1];
				puntosTop3Candidatos[2] = puntosTop3Candidatos[1];
				top3Candidatos[1] = candidatoActual;
				puntosTop3Candidatos[1] = puntosCandidato;
			}else if(puntosCandidato >= puntosTop3Candidatos[2]) {
				top3Candidatos[2] = candidatoActual;
				puntosTop3Candidatos[2] = puntosCandidato;
			}
		}
		return top3Candidatos;
	}
}
