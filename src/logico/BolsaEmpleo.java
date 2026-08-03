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
	private ArrayList<String> areas;
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
		tiposEmpresa = new ArrayList<>();//----------------
		tiposEmpresa.add("Micro");
		tiposEmpresa.add("Pequeña");
		tiposEmpresa.add("Mediana");
		tiposEmpresa.add("Grande");
		tiposEmpresa.add("Zona Franca");
		tiposEmpresa.add("Hotel o Casino (mediano/pequeño)");
		tiposEmpresa.add("Hotel o Casino (Grande)");
		areas = new ArrayList<>();

		// Ingenieria

		areas.add("Ingenieria civil");
		areas.add("Ingenieria industrial");
		areas.add("Ingenieria electrica");
		areas.add("Ingenieria electronica");
		areas.add("Ingenieria mecanica");
		areas.add("Ingenieria quimica");
		areas.add("Ingenieria ambiental");
		areas.add("Ingenieria agronomica");
		areas.add("Ingenieria en telecomunicaciones");
		areas.add("Ingenieria naval");
		areas.add("Ingenieria de minas");
		areas.add("Ingenieria en alimentos");
		areas.add("Arquitectura");

		// Salu

		areas.add("Medicina general");
		areas.add("Paramedico / tecnico en emergencias");
		areas.add("Enfermeria");
		areas.add("Cardiologia");
		areas.add("Pediatria");
		areas.add("Ginecologia y obstetricia");
		areas.add("Odontologia");
		areas.add("Fisioterapia");
		areas.add("Psicologia clinica");
		areas.add("Nutricion y dietetica");
		areas.add("Farmacia");
		areas.add("Laboratorio clinico / bioanalisis");
		areas.add("Radiologia / tecnico en imagenes");
		areas.add("Anestesiologia");
		areas.add("Veterinaria");
		areas.add("Auxiliar de enfermeria");

		// Tecnologia / Informatica

		areas.add("Desarrollo de software");
		areas.add("Ingenieria en sistemas");
		areas.add("Gestion de redes");
		areas.add("Ciberseguridad");
		areas.add("Administracion de bases de datos");
		areas.add("Soporte tecnico / help desk");
		areas.add("Analisis de datos");
		areas.add("Devops / infraestructura cloud");
		areas.add("Qa / testing de software");
		areas.add("Diseño ux/ui");
		areas.add("Inteligencia artificial / machine learning");
		areas.add("Telecomunicaciones");

		// Oficios 

		areas.add("Plomeria");
		areas.add("Electricista");
		areas.add("Albañileria");
		areas.add("Carpinteria");
		areas.add("Soldadura");
		areas.add("Pintura (construccion)");
		areas.add("Mecanica automotriz");
		areas.add("Mecanica industrial");
		areas.add("Refrigeracion y aire acondicionado");
		areas.add("Herreria");
		areas.add("Operador de maquinaria pesada");
		areas.add("Instalador de paneles solares");
		areas.add("Techador");

		// Negocio

		areas.add("Administracion de empresas");
		areas.add("Marketing");
		areas.add("Contabilidad");
		areas.add("Recursos humanos");
		areas.add("Servicio al cliente");
		areas.add("Ventas");
		areas.add("Logistica y cadena de suministro");
		areas.add("Comercio exterior");
		areas.add("Finanzas");
		areas.add("Auditoria");
		areas.add("Gestion de proyectos");
		areas.add("Emprendimiento / negocios propios");

		// Diseño / Comunicacion / Arte
		
		areas.add("Diseño grafico");
		areas.add("Diseño industrial");
		areas.add("Fotografia");
		areas.add("Produccion audiovisual");
		areas.add("Periodismo");
		areas.add("Comunicacion social");
		areas.add("Publicidad");
		areas.add("Community manager");
		areas.add("Animacion digital");
		areas.add("Diseño de moda");

		// Educacion
		
		areas.add("Educacion primaria");
		areas.add("Educacion secundaria");
		areas.add("Profesor de matematicas");
		areas.add("Profesor de quimica");
		areas.add("Profesor de fisica");
		areas.add("Profesor de historia");
		areas.add("Profesor de español / literatura");
		areas.add("Profesor de idiomas");
		areas.add("Educacion especial");
		areas.add("Orientacion educativa (psicopedagogia)");
		areas.add("Educacion fisica");

		// Turismo / Hosteleria

		areas.add("Gastronomia / chef");
		areas.add("Bartender");
		areas.add("Hoteleria");
		areas.add("Guia turistico");
		areas.add("Recepcion de hotel");
		areas.add("Ama de llaves");
		areas.add("Aviacion (asistente de vuelo, tripulacion)");

		// Legal / Seguridad

		areas.add("Derecho");
		areas.add("Notaria");
		areas.add("Criminologia");
		areas.add("Seguridad privada");

		// Produccion / Industria

		areas.add("Operario de produccion");
		areas.add("Control de calidad");
		areas.add("Tecnico en mantenimiento industrial");
		areas.add("Almacen / inventario");
		areas.add("Textil / confeccion");
		areas.add("Agricultura / agropecuaria");
		areas.add("Pesca / acuicultura");

		// Otro

		areas.add("Otro");
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
	public ArrayList<String> getAreas() {
		return areas;
	}

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

	public void registrarOferta(String idEmpresaUsuario, String tipoTrabajo, String titulo, String tecnico, 
			String habilidad, String tiempoTrabajo, int experienciaLaboral, 
			char sexo, String provincia, boolean licencia, boolean dispuestoAMudarse,
			String descripcion, float salario, float coincidencia, int cantPuesto, boolean soloEspecif) {
		String[] partesId = idEmpresaUsuario.split("-");
		String idEmpresa = partesId[0];

		Empresa emp = buscarEmpresa(idEmpresa);
		if (emp != null) {
			String idOferta = "O" + generadorIdOferta;
			Oferta nueva = new Oferta(idOferta, tipoTrabajo, titulo, tecnico, habilidad, tiempoTrabajo, 
					experienciaLaboral, sexo, provincia, licencia, dispuestoAMudarse, 
					true, idEmpresa, descripcion, salario, coincidencia, cantPuesto, soloEspecif);

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

	public void modificarOferta(String idOferta, String tipo, String titulo, String tecnico, String habilidad,
			String tiempoTrabajo, int experiencia, char sexo, String provincia, boolean licRequerida,
			boolean dispMudarse, String descripcion, float sal, float coincidencia, int puestos, boolean solEspecif) {
		Oferta oferta = buscarOferta(idOferta);

		if(oferta != null) {
			oferta.setTipoTrabajo(tipo);
			oferta.setTitulo(titulo);
			oferta.setTecnico(tecnico);
			oferta.setHabilidad(habilidad);
			oferta.setTiempoTrabajo(tiempoTrabajo);
			oferta.setExperienciaLaboral(experiencia);
			oferta.setSexo(sexo);
			oferta.setProvincia(provincia);
			oferta.setLicenciaDeConducir(licRequerida);
			oferta.setDispuestoAMudarse(dispMudarse);
			oferta.setDescripcionTrabajo(descripcion);
			oferta.setSalario(sal);
			oferta.setCoincidencia(coincidencia);
			oferta.setCantPuesto(puestos);
			oferta.setSoloespecif(solEspecif);
		}
	}

	public Usuario getCookieUsuario() {
		return cookieUsuario;
	}

	public void setCookieUsuario(Usuario cookieUsuario) {
		this.cookieUsuario = cookieUsuario;
	}

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

	public Empresa getEmpresaPorEmpleado (Usuario empleado) {
		Empresa aux = null;
		String[] idEmpresa = empleado.getId().split("-");
		int i =0;
		while (i< lasEmpresas.size()) {
			if (lasEmpresas.get(i).getId().equals(idEmpresa[0])) {
				aux = lasEmpresas.get(i);
				return aux;
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

	public ArrayList<String> PorcentajeCoincidencia(Oferta ofertaEmpresa) {
		int i = 0;
		int puntos = 0;
		boolean solicitudRechazada;
		ArrayList<String> candidatosIdeales = new ArrayList<>();			
		while (i < lasSolicitudes.size()) {
			Solicitud solicitudCandidato = lasSolicitudes.get(i);			
			Persona p = buscarPersona(solicitudCandidato.getIdUsuario());
			solicitudRechazada = false;
			for(String idSolicitud : ofertaEmpresa.getIdSolicitudesRechazadas()) {
				if(solicitudCandidato.getId().equalsIgnoreCase(idSolicitud)) {
					solicitudRechazada = true;
				}
			}
			if (solicitudCandidato.isActivo() && p != null && !p.isEmpleado() && !solicitudRechazada) {
				puntos = calcularPuntosCoincidencia(solicitudCandidato, ofertaEmpresa);
				if (puntos >= ofertaEmpresa.getCoincidencia())
					candidatosIdeales.add(solicitudCandidato.getId());
			}
			i++;
		}
		return candidatosIdeales;
	}
	
	public ArrayList<Oferta> mejoresOfertas(Solicitud solicitud){
		ArrayList<Oferta> mejoresOfertas = new ArrayList<>();
		if (lasOfertas == null) {
	        return mejoresOfertas;
	    }
		int i = 0;
		int puntos = 0;
		boolean solicitudRechazada;
		while(i < lasOfertas.size()) {
			Oferta oferta = lasOfertas.get(i);
			if(oferta == null || !oferta.isActivo()) {
				i++;
				continue;
			}
			
			solicitudRechazada = false;
			if(oferta.getIdSolicitudesRechazadas() != null) {
				for(String idSolicitud : oferta.getIdSolicitudesRechazadas()) {
					if(solicitud.getId().equalsIgnoreCase(idSolicitud)) {
						solicitudRechazada = true;
						break;
					}
				}
			}
			if(oferta.isActivo() && !solicitudRechazada) {
				puntos = calcularPuntosCoincidencia(solicitud, oferta);
				if(puntos >= 50) {
					mejoresOfertas.add(oferta);
				}
			}
			i++;
		}
		return mejoresOfertas;
	}

	public int calcularPuntosCoincidencia(Solicitud solicitudCandidato, Oferta ofertaEmpresa) {
		int puntos = 0;
		if (!aptitudSolicitud(solicitudCandidato, ofertaEmpresa) || !solicitudCandidato.isActivo())
			puntos = -1;
		else {
		puntos += compararDisponibilidadYTipo(solicitudCandidato, ofertaEmpresa);
		puntos += compararExperienciaYSalario(solicitudCandidato, ofertaEmpresa);
		puntos += compararDatosPersonales(solicitudCandidato, ofertaEmpresa);
		puntos += compararResidencia(solicitudCandidato, ofertaEmpresa);
		}
		return puntos;
	}

	private int compararDisponibilidadYTipo(Solicitud solicitudCandidato, Oferta ofertaEmpresa) {
		int puntos = 0;
		if (solicitudCandidato.getTiempoTrabajo() != null &&solicitudCandidato.getTiempoTrabajo().equalsIgnoreCase(ofertaEmpresa.getTiempoTrabajo()))
			puntos += 15;
		if (solicitudCandidato.getTipoTrabajo() != null && solicitudCandidato.getTipoTrabajo().equalsIgnoreCase(String.valueOf(ofertaEmpresa.getTipoTrabajo())))
			puntos += 10;
		return puntos;
	}

	private int compararExperienciaYSalario(Solicitud solicitudCandidato, Oferta ofertaEmpresa) {
		int puntos = 0;
		if (solicitudCandidato.getExperienciaLaboral() >= ofertaEmpresa.getExperienciaLaboral())
			puntos += 15;
		if (solicitudCandidato.getRangoMinSalario() <= ofertaEmpresa.getSalario()
				&& solicitudCandidato.getRangoMaxSalario() >= ofertaEmpresa.getSalario())
			puntos += 20;
		return puntos;
	}

	private int compararDatosPersonales(Solicitud solicitudCandidato, Oferta ofertaEmpresa) {
		int puntos = 0;
		if (solicitudCandidato.getSexo() == ofertaEmpresa.getSexo())
			puntos += 5;
		if (!ofertaEmpresa.isLicenciaDeConducir() || solicitudCandidato.isLicenciaDeConducir())
			puntos += 10;
		if (!ofertaEmpresa.isDispuestoAMudarse() || solicitudCandidato.isDispuestoAMudarse())
			puntos += 10;
		return puntos;
	}

	private int compararResidencia(Solicitud solicitudCandidato, Oferta ofertaEmpresa) {
		int puntos = 0;
		if (solicitudCandidato.getProvincia().equalsIgnoreCase(ofertaEmpresa.getProvincia()))
			puntos += 15;
		else if (solicitudCandidato.isDispuestoAMudarse()){
			puntos += 8;
		}
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

	public Solicitud[] top3Candidatos(Oferta oferta){
		ArrayList<String> candidatosValidos = PorcentajeCoincidencia(oferta);
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
	public ArrayList<Oferta> misRecomentaciones (Solicitud miSolicitud) {
		ArrayList<Oferta> misResultados = new ArrayList<>();
		int i=0;
		int puntos = 0;
		while(i< lasOfertas.size()) {
			puntos = calcularPuntosCoincidencia(miSolicitud, lasOfertas.get(i));
			if (puntos >= lasOfertas.get(i).getCoincidencia())
				misResultados.add(lasOfertas.get(i));
			i++;
		}
		return misResultados;
	}
	
	public void registrarAdmin() {
		Usuario u = new Usuario("ADMIN","Admin@mail.com","Admin","123","Administrador");
		losUsuarios.add(u);
	}
}

