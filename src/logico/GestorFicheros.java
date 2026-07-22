package logico;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GestorFicheros {
	
	private static String idFile = "IdGenerador.dat";
	private static String usuariosFile = "Usuraio.dat";
	private static String personasFile = "Personas.dat";
	private static String empresasFile = "Empresa.dat";
	private static String ofertasFile = "Ofertas.dat";
	private static String solicitudesFile = "Solicitudes.dat";


	public static boolean guardarDatosID() throws IOException {
		try (FileOutputStream f = new FileOutputStream(idFile);
			ObjectOutputStream oos = new ObjectOutputStream(f)) {

			oos.writeInt(BolsaEmpleo.generadorIdPersona);
			oos.writeInt(BolsaEmpleo.generadorIdEmpresa);
			oos.writeInt(BolsaEmpleo.generadorIdOferta);
			oos.writeInt(BolsaEmpleo.generadorIdSolicitud);
			
			return true;
		}catch (IOException e) {
			return false;
		}
	}

	public static boolean cargarDatosID() throws IOException, ClassNotFoundException {
		try (FileInputStream f = new FileInputStream(idFile);
			ObjectInputStream ois = new ObjectInputStream(f)) {

			BolsaEmpleo.generadorIdPersona = ois.readInt();
			BolsaEmpleo.generadorIdEmpresa = ois.readInt();
			BolsaEmpleo.generadorIdOferta = ois.readInt();
			BolsaEmpleo.generadorIdSolicitud = ois.readInt();
			
			return true;
		}catch (IOException e) {
			return false;
		}
	}
	
	public static boolean guardarDatosUsuarios() throws IOException {
		try (FileOutputStream f = new FileOutputStream(usuariosFile);
			ObjectOutputStream oos = new ObjectOutputStream(f)) {

			BolsaEmpleo bolsa = BolsaEmpleo.getInstancia();
	
			oos.writeInt(bolsa.getLosUsuarios().size());
			for (Usuario usuario : bolsa.getLosUsuarios()) {
				oos.writeObject(usuario);
			}
			
			return true;
		}catch (IOException e) {
			return false;
		}
	}
	
	public static boolean cargarDatosUsuarios() throws IOException, ClassNotFoundException {
		try (FileInputStream f = new FileInputStream(usuariosFile);
			ObjectInputStream ois = new ObjectInputStream(f)) {

			BolsaEmpleo bolsa = BolsaEmpleo.getInstancia();
			bolsa.getLosUsuarios().clear(); 
			
			int sizeUsuarios = ois.readInt();
			for (int i = 0; i < sizeUsuarios; i++) {
	            Usuario u = (Usuario) ois.readObject();
	            bolsa.getLosUsuarios().add(u);
	        }
			
			return true;
		}catch (IOException e) {
			return false;
		}
	}
	
	public static boolean guardarDatosPersonas() throws IOException {
		try (FileOutputStream f = new FileOutputStream(personasFile);
			ObjectOutputStream oos = new ObjectOutputStream(f)) {

			BolsaEmpleo bolsa = BolsaEmpleo.getInstancia();
	
			oos.writeInt(bolsa.getLasPersonas().size());
			for (Persona persona : bolsa.getLasPersonas()) {
				oos.writeObject(persona);
			}
			
			return true;
		}catch (IOException e) {
			return false;
		}
	}
	
	public static boolean cargarDatosPersonas() throws IOException, ClassNotFoundException {
		try (FileInputStream f = new FileInputStream(personasFile);
			ObjectInputStream ois = new ObjectInputStream(f)) {

			BolsaEmpleo bolsa = BolsaEmpleo.getInstancia();
			bolsa.getLasPersonas().clear(); 
			
			int sizePersonas = ois.readInt();
			for (int i = 0; i < sizePersonas; i++) {
	            Persona p = (Persona) ois.readObject();
	            bolsa.getLasPersonas().add(p);
	        }
			
			return true;
		}catch (IOException e) {
			return false;
		}
	}
	
	public static boolean guardarDatosEmpresas() throws IOException {
		try (FileOutputStream f = new FileOutputStream(empresasFile);
			ObjectOutputStream oos = new ObjectOutputStream(f)) {

			BolsaEmpleo bolsa = BolsaEmpleo.getInstancia();
	
			oos.writeInt(bolsa.getLasEmpresas().size());
			for (Empresa empresa : bolsa.getLasEmpresas()) {
				oos.writeObject(empresa);
			}
			
			return true;
		}catch (IOException e){
			return false;
		}
	}
	
	public static boolean cargarDatosEmpresa() throws IOException, ClassNotFoundException {
		try (FileInputStream f = new FileInputStream(empresasFile);
		ObjectInputStream ois = new ObjectInputStream(f)) {

			BolsaEmpleo bolsa = BolsaEmpleo.getInstancia();
			bolsa.getLasEmpresas().clear(); 
			
			int sizeEmpresas = ois.readInt();
			for (int i = 0; i < sizeEmpresas; i++) {
	            Empresa e = (Empresa) ois.readObject();
	            bolsa.getLasEmpresas().add(e);
			}
			
			return true;
		}catch (IOException e) {
			return false;
		}
	}
	
	public static boolean guardarDatosOfertas() throws IOException {
		try (FileOutputStream f = new FileOutputStream(ofertasFile);
		ObjectOutputStream oos = new ObjectOutputStream(f)) {

			BolsaEmpleo bolsa = BolsaEmpleo.getInstancia();
	
			oos.writeInt(bolsa.getLasOfertas().size());
			for (Oferta oferta : bolsa.getLasOfertas()) {
				oos.writeObject(oferta);
			}
			
			return true;
		}catch (IOException e) {
			return false;
		}

	}
	
	public static boolean cargarDatosOfertas() throws IOException, ClassNotFoundException {
		try (FileInputStream f = new FileInputStream(ofertasFile);
		ObjectInputStream ois = new ObjectInputStream(f)) {

			BolsaEmpleo bolsa = BolsaEmpleo.getInstancia();
			bolsa.getLasOfertas().clear(); 
			
			int sizeOfertas = ois.readInt();
			for (int i = 0; i < sizeOfertas; i++) {
	            Oferta o = (Oferta) ois.readObject();
	            bolsa.getLasOfertas().add(o);
	        }

			return true;
		}catch (IOException e) {
			return false;
		}
	}
	
	public static boolean guardarDatosSolicitudes() throws IOException {
		try (FileOutputStream f = new FileOutputStream(solicitudesFile);
		ObjectOutputStream oos = new ObjectOutputStream(f)) {

			BolsaEmpleo bolsa = BolsaEmpleo.getInstancia();
	
			oos.writeInt(bolsa.getLasSolicitudes().size());
			for (Solicitud solicitud : bolsa.getLasSolicitudes()) {
				oos.writeObject(solicitud);
			}
			
			return true;
		}catch (IOException e) {
			return false;
		}
		
	}
	
	public static boolean cargarDatosSolicitudes() throws IOException, ClassNotFoundException {
		try (FileInputStream f = new FileInputStream(solicitudesFile);
		ObjectInputStream ois = new ObjectInputStream(f)) {

			BolsaEmpleo bolsa = BolsaEmpleo.getInstancia();
			bolsa.getLasSolicitudes().clear(); 
			
			int sizeSolicitudes = ois.readInt();
			for (int i = 0; i < sizeSolicitudes; i++) {
	            Solicitud s = (Solicitud) ois.readObject();
	            bolsa.getLasSolicitudes().add(s);
	        }
			
			return true;
		}catch (IOException e) {
			return false;
		}
	}
}