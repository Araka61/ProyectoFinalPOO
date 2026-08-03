package logico;

import java.util.Random;

public class InyectarDatosPrueba {

	public static void inyectar() {
		BolsaEmpleo bolsa = BolsaEmpleo.getInstancia();
		Random rnd = new Random();

		// ==================== ARREGLOS DE DATOS DE PRUEBA ====================

		// Nombres de personas (mucho más variados)
		String[] nombres = {
				"Carlos Perez", "Maria Gomez", "Juan Rodriguez", "Ana Martinez", "Luis Fernandez",
				"Laura Lopez", "Jose Garcia", "Carmen Sanchez", "Pedro Ramirez", "Sofia Torres",
				"Miguel Angel Reyes", "Yolanda Mercedes Diaz", "Rafael Antonio Nunez", "Katherine Rosario", "Franklin Ureña",
				"Yesenia Paulino", "Julio Cesar Mejia", "Alba Iris Peña", "Ramon Antonio Cabrera", "Elizabeth Guzman",
				"Wilkin Manuel Castillo", "Yohanna Beltre", "Argenis De la Cruz", "Mercedes Altagracia Vargas", "Hector Manuel Suero",
				"Ruth Esther Abreu", "Nelson Rafael Feliz", "Ana Yris Batista", "Domingo Antonio Polanco", "Cristina Marte",
				"Danilo Alberto Rosario", "Xiomara Encarnacion", "Bienvenido Almonte", "Rosa Iris Tavarez", "Fausto Manuel Concepcion",
				"Ivelisse Contreras", "Federico Antonio Jimenez", "Yaquelin De Jesus", "Gregorio Antonio Nolasco", "Altagracia Espinal"
		};

		// Ciudades (según catálogo de la aplicación)
		String[] ciudades = {
				"Santo Domingo", "Santiago de los Caballeros", "San Francisco de Macorís", "La Vega", "Puerto Plata",
				"San Pedro de Macorís", "La Romana", "San Cristóbal", "Higüey", "Moca",
				"Bonao", "Baní", "San Juan de la Maguana", "Barahona", "Azua"
		};

		// Tipos de empresa (según catálogo de la aplicación)
		String[] tiposEmpresaNombres = {
				"Micro", "Pequeña", "Mediana", "Grande", "Zona Franca",
				"Hotel o Casino (mediano/pequeño)", "Hotel o Casino (Grande)"
		};

		String[] tiposTrabajo = {"Presencial", "Remoto", "Hibrido"};
		String[] tiemposTrabajo = {"Tiempo Completo", "Medio Tiempo", "Por Proyecto"};
		char[] sexos = {'M', 'F', 'N'}; // N = N/A o Indistinto

		// Áreas profesionales (para personas de Grado / ofertas y solicitudes "titulo")
		String[] areasProfesionales = {
				"Ingenieria civil", "Ingenieria industrial", "Ingenieria electrica", "Ingenieria electronica",
				"Ingenieria mecanica", "Ingenieria quimica", "Ingenieria ambiental", "Ingenieria agronomica",
				"Ingenieria en telecomunicaciones", "Ingenieria naval", "Ingenieria de minas", "Ingenieria en alimentos",
				"Arquitectura", "Medicina general", "Enfermeria", "Cardiologia", "Pediatria",
				"Ginecologia y obstetricia", "Odontologia", "Fisioterapia", "Psicologia clinica",
				"Nutricion y dietetica", "Farmacia", "Laboratorio clinico / bioanalisis", "Ingenieria en sistemas",
				"Administracion de empresas", "Marketing", "Contabilidad", "Recursos humanos",
				"Logistica y cadena de suministro", "Finanzas", "Auditoria", "Gestion de proyectos",
				"Derecho", "Notaria", "Criminologia", "Educacion primaria", "Educacion secundaria",
				"Profesor de matematicas", "Profesor de idiomas", "Diseño grafico", "Periodismo",
				"Comunicacion social"
		};

		// Áreas técnicas (para personas Técnico / ofertas y solicitudes "tecnico")
		String[] areasTecnicas = {
				"Desarrollo de software", "Ingenieria en sistemas", "Gestion de redes", "Ciberseguridad",
				"Administracion de bases de datos", "Soporte tecnico / help desk", "Analisis de datos",
				"Devops / infraestructura cloud", "Qa / testing de software", "Diseño ux/ui",
				"Inteligencia artificial / machine learning", "Telecomunicaciones",
				"Paramedico / tecnico en emergencias", "Radiologia / tecnico en imagenes", "Auxiliar de enfermeria",
				"Tecnico en mantenimiento industrial", "Control de calidad"
		};

		// Áreas de oficio (para personas Trabajador / ofertas y solicitudes "habilidad")
		String[] areasOficios = {
				"Plomeria", "Electricista", "Albañileria", "Carpinteria", "Soldadura",
				"Pintura (construccion)", "Mecanica automotriz", "Mecanica industrial",
				"Refrigeracion y aire acondicionado", "Herreria", "Operador de maquinaria pesada",
				"Instalador de paneles solares", "Techador", "Gastronomia / chef", "Bartender",
				"Hoteleria", "Recepcion de hotel", "Ama de llaves", "Almacen / inventario",
				"Textil / confeccion", "Agricultura / agropecuaria", "Seguridad privada"
		};

		// Universidades / institutos para las personas de Grado
		String[] universidades = {
				"PUCMM", "UASD", "INTEC", "UNPHU", "O&M", "UNIBE", "UTESA", "UCSD"
		};

		// Institutos técnicos para las personas de Técnico
		String[] institutosTecnicos = {
				"ITLA", "ITSC", "IPL", "INFOTEP", "Instituto Tecnico Loyola"
		};

		// Nombres de empresas reales/variados (en vez de "Empresa Tech 1", "Empresa Tech 2"...)
		String[] nombresEmpresas = {
				"Distribuidora Hermanos Peña SRL", "Constructora Vega & Asociados", "Zona Franca Industrial del Cibao",
				"Hotel Riu Bavaro Resort & Spa", "Grupo Ramos Supermercados", "Industrias Nacionales del Plastico",
				"Textiles del Caribe SA", "Soluciones Tecnologicas Quisqueya", "Agropecuaria Los Almacigos",
				"Banco Multiple Progreso", "Farmacias Carol", "Constructora Hazoury",
				"Grupo Rica", "Cerveceria Nacional Dominicana", "Compañia Dominicana de Telecomunicaciones",
				"Servicios Logisticos del Este", "Aluminios y Vidrios del Caribe", "Manufacturas Ideal SA",
				"Consultores Empresariales Asociados", "Transporte y Carga Duarte", "Ferreteria Americana",
				"Grupo Hotelero Bahia Principe", "Autopartes y Servicios Rodriguez", "Ingenieria y Proyectos del Cibao",
				"Software Solutions RD", "Exportadora de Cacao y Cafe Sanchez", "Clinica y Centro Medico Abreu",
				"Publicidad Creativa Digital", "Zona Franca Multimodal Caucedo", "Grupo Financiero Popular",
				"Restaurantes El Conuco", "Inmobiliaria Costa Verde", "Textil Dominicana Export SA",
				"Call Center Solutions Caribbean", "Energia Renovable del Cibao", "Grupo Leon Jimenes",
				"Distribuidora de Alimentos La Sirena", "Constructora e Inmobiliaria Ginco", "Aeropuertos Dominicanos Siglo XXI",
				"Casino y Hotel Jaragua"
		};

		// ==================== 1. INYECTAR EMPRESAS (Y 1 USUARIO REPRESENTANTE POR EMPRESA) ====================
		int totalEmpresas = 40;
		System.out.println("Inyectando " + totalEmpresas + " empresas y sus usuarios representantes...");
		for (int i = 1; i <= totalEmpresas; i++) {
			String tipoEmpresa = tiposEmpresaNombres[rnd.nextInt(tiposEmpresaNombres.length)];
			String nombreEmpresa = nombresEmpresas[(i - 1) % nombresEmpresas.length];
			// Si superamos el catálogo de nombres, añadimos un sufijo para mantener unicidad
			if (i > nombresEmpresas.length) {
				nombreEmpresa = nombreEmpresa + " " + (((i - 1) / nombresEmpresas.length) + 1);
			}

			// Registrar empresa
			Empresa nuevaEmpresa = bolsa.registrarEmpresa(
					nombreEmpresa,
					"1010" + rnd.nextInt(90000) + i,
					nombres[rnd.nextInt(nombres.length)],
					tipoEmpresa,
					"admin123"
			);

			// Crear usuario de empresa con el formato: IDEmpresa-IdPropio (ej: E1-1)
			String idUsuarioEmpresa = nuevaEmpresa.getId() + "-1";
			Usuario usuarioEmpresa = new Usuario(
					idUsuarioEmpresa,
					"contacto@empresa" + i + ".com",
					"empresa" + i,
					"admin123",
					"Admin"
			);

			bolsa.registrarUsuarioEmpres(usuarioEmpresa);
			nuevaEmpresa.nuevoReprecentante(usuarioEmpresa);
		}

		// ==================== 2. INYECTAR PERSONAS (20 Grado, 20 Tecnicos, 20 Trabajadores) CON SUS USUARIOS ====================
		int totalPersonas = 60;
		System.out.println("Inyectando " + totalPersonas + " personas (Usuarios / Candidatos)...");
		for (int i = 1; i <= totalPersonas; i++) {
			String nombre = nombres[rnd.nextInt(nombres.length)] + " " + i;
			String cedula = "402-000000" + i + "-1";
			String telefono = "809-555-0" + String.format("%03d", i);
			String correo = "candidato" + i + "@mail.com";
			String ciudad = ciudades[rnd.nextInt(ciudades.length)];
			char sexo = sexos[rnd.nextInt(2)]; // M o F
			boolean licencia = rnd.nextBoolean();
			String username = "user" + i;

			// Alternar entre los 3 tipos de persona
			if (i <= 20) {
				// Grado
				String universidad = universidades[rnd.nextInt(universidades.length)];
				String area = areasProfesionales[rnd.nextInt(areasProfesionales.length)];
				bolsa.registrarPersonaGrado(cedula, nombre, telefono, correo, tiemposTrabajo[rnd.nextInt(tiemposTrabajo.length)], licencia, sexo, ciudad,
						universidad, area, area, username, "123", "Candidato");
			} else if (i <= 40) {
				// Tecnico
				String instituto = institutosTecnicos[rnd.nextInt(institutosTecnicos.length)];
				String area = areasTecnicas[rnd.nextInt(areasTecnicas.length)];
				bolsa.registrarPersonaTecnico(cedula, nombre, telefono, correo, tiemposTrabajo[rnd.nextInt(tiemposTrabajo.length)], licencia, sexo, ciudad,
						instituto, area, area, username, "123", "Candidato");
			} else {
				// Trabajador (Oficio)
				String oficio = areasOficios[rnd.nextInt(areasOficios.length)];
				bolsa.registrarPersonaTrabajador(cedula, nombre, telefono, correo, tiemposTrabajo[rnd.nextInt(tiemposTrabajo.length)], licencia, sexo, ciudad,
						oficio, username, "123", "Candidato");
			}
		}

		// ==================== 3. INYECTAR OFERTAS DE EMPLEO ====================
		int totalOfertas = 500;
		System.out.println("Inyectando " + totalOfertas + " Ofertas...");

		// Plantillas de descripcion segun el contexto de la vacante, para evitar texto generico
		String[] plantillasDescripcionArea = {
				"Empresa %s busca %s con experiencia comprobada para fortalecer su equipo en %s. Se ofrece estabilidad, buen ambiente laboral y oportunidades de crecimiento.",
				"%s esta en la busqueda de un(a) %s para unirse a su equipo, con sede en %s. Ideal para personas proactivas, responsables y con deseos de desarrollarse profesionalmente.",
				"%s requiere %s para cubrir vacante disponible en %s. Ofrecemos capacitacion continua, buen clima laboral y posibilidad de crecimiento dentro de la empresa.",
				"%s busca personal con formacion o experiencia como %s para una oportunidad en %s. Buscamos compromiso, puntualidad y ganas de aportar al equipo.",
				"%s amplia su equipo de trabajo y solicita %s para su sucursal en %s. Se valorara la iniciativa, el trabajo en equipo y la orientacion a resultados.",
				"Oportunidad laboral en %s: se necesita %s para desempeñarse en %s. Ambiente dinamico, con posibilidad de desarrollo a mediano plazo.",
				"%s esta reclutando %s para su operacion en %s. Se ofrece contrato segun modalidad, prestaciones de ley y buen ambiente de trabajo."
		};

		for (int i = 1; i <= totalOfertas; i++) {
			// Seleccionar un ID de usuario de empresa al azar
			int indexEmpresa = rnd.nextInt(totalEmpresas) + 1;
			String idEmpresaUsuario = "E" + indexEmpresa + "-1";
			String nombreEmpresaOferta = nombresEmpresas[(indexEmpresa - 1) % nombresEmpresas.length];

			String tipo = tiposTrabajo[rnd.nextInt(tiposTrabajo.length)];
			String tiempo = tiemposTrabajo[rnd.nextInt(tiemposTrabajo.length)];
			String ciudad = ciudades[rnd.nextInt(ciudades.length)];
			char sexoReq = sexos[rnd.nextInt(sexos.length)];
			float salario = 20000f + rnd.nextInt(80000); // Entre 20k y 100k

			// Alternamos el tipo de vacante, tomando el area de los catalogos completos
			String titulo = "n/a", tecnico = "n/a", habilidad = "n/a";
			String areaSeleccionada;
			if (i % 3 == 0) {
				areaSeleccionada = areasProfesionales[rnd.nextInt(areasProfesionales.length)];
				titulo = areaSeleccionada;
			} else if (i % 3 == 1) {
				areaSeleccionada = areasTecnicas[rnd.nextInt(areasTecnicas.length)];
				tecnico = areaSeleccionada;
			} else {
				areaSeleccionada = areasOficios[rnd.nextInt(areasOficios.length)];
				habilidad = areaSeleccionada;
			}

			// Construir una descripcion contextual en vez de un texto generico
			String plantilla = plantillasDescripcionArea[rnd.nextInt(plantillasDescripcionArea.length)];
			String descripcion = String.format(plantilla, nombreEmpresaOferta, areaSeleccionada, ciudad);

			bolsa.registrarOferta(
					idEmpresaUsuario, tipo, titulo, tecnico, habilidad, tiempo,
					rnd.nextInt(5), // 0 a 4 años de experiencia
					sexoReq, ciudad, rnd.nextBoolean(), rnd.nextBoolean(),
					descripcion,
					salario,
					50f, // Coincidencia mínima requerida
					rnd.nextInt(5) + 1, // Cantidad de puestos (1 a 5)
					false
			);
		}

		// ==================== 4. INYECTAR SOLICITUDES DE EMPLEO ====================
		int totalSolicitudes = 1000;
		System.out.println("Inyectando " + totalSolicitudes + " Solicitudes...");
		for (int i = 1; i <= totalSolicitudes; i++) {
			// Seleccionar un ID de usuario/persona al azar
			int indexPersona = rnd.nextInt(totalPersonas) + 1;
			String idUsuario = "P" + indexPersona;

			String tipo = tiposTrabajo[rnd.nextInt(tiposTrabajo.length)];
			String tiempo = tiemposTrabajo[rnd.nextInt(tiemposTrabajo.length)];
			String ciudad = ciudades[rnd.nextInt(ciudades.length)];
			char sexoReq = sexos[rnd.nextInt(2)];

			// Rango salarial esperado
			float minSal = 15000f + rnd.nextInt(30000);
			float maxSal = minSal + 20000f + rnd.nextInt(40000);

			// Alternamos el tipo de vacante que buscan
			String titulo = "n/a", tecnico = "n/a", habilidad = "n/a";
			if (i % 3 == 0) titulo = areasProfesionales[rnd.nextInt(areasProfesionales.length)];
			else if (i % 3 == 1) tecnico = areasTecnicas[rnd.nextInt(areasTecnicas.length)];
			else habilidad = areasOficios[rnd.nextInt(areasOficios.length)];

			bolsa.registrarSolicitud(
					idUsuario, tipo, titulo, tecnico, habilidad, tiempo,
					rnd.nextInt(5), // experiencia
					sexoReq, ciudad, rnd.nextBoolean(), rnd.nextBoolean(),
					minSal, maxSal
			);
		}

		System.out.println("--- INYECCION DE DATOS COMPLETADA CON EXITO ---");
		System.out.println("Total Personas (Candidatos): " + bolsa.getLasPersonas().size());
		System.out.println("Total Empresas: " + bolsa.getLasEmpresas().size());
		System.out.println("Total Ofertas: " + bolsa.getLasOfertas().size());
		System.out.println("Total Solicitudes: " + bolsa.getLasSolicitudes().size());
		System.out.println("Total Usuarios de Login: " + bolsa.getLosUsuarios().size() + " (" + totalPersonas + " candidatos + " + totalEmpresas + " empresas)");
		GestorFicheros.guardarDatosFicheros();
	}

	// Main para pruebas rápidas aisladas (Opcional)
	public static void main(String[] args) {
		InyectarDatosPrueba.inyectar();
	}
}