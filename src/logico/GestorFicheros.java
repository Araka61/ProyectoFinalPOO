package logico;

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


	public static void guardarDatosID() throws IOException {
		FileOutputStream f = new FileOutputStream(idFile);
		ObjectOutputStream oos = new ObjectOutputStream(f);

		oos.writeInt(BolsaEmpleo.generadorIdPersona);
		oos.writeInt(BolsaEmpleo.generadorIdEmpresa);
		oos.writeInt(BolsaEmpleo.generadorIdOferta);
		oos.writeInt(BolsaEmpleo.generadorIdSolicitud);

		BolsaEmpleo bolsa = BolsaEmpleo.getInstancia();

		oos.writeInt(bolsa.getLasPersonas().size());
		for (Persona persona : bolsa.getLasPersonas()) {
			oos.writeObject(persona);
		}

		oos.close();
	}

	public static void cargarDatosID() throws IOException, ClassNotFoundException {
		FileInputStream f = new FileInputStream(idFile);
		ObjectInputStream ois = new ObjectInputStream(f);

		BolsaEmpleo.generadorIdPersona = ois.readInt();
		BolsaEmpleo.generadorIdEmpresa = ois.readInt();
		BolsaEmpleo.generadorIdOferta = ois.readInt();
		BolsaEmpleo.generadorIdSolicitud = ois.readInt();

		BolsaEmpleo bolsa = BolsaEmpleo.getInstancia();
		bolsa.getLasPersonas().clear(); 

		int sizePersonas = ois.readInt();
		for (int i = 0; i < sizePersonas; i++) {
			Persona a = (Persona) ois.readObject();
			bolsa.getLasPersonas().add(a);
		}

		ois.close();
	}
}