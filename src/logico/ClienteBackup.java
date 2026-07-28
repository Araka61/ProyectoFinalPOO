package logico;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteBackup {
	
	public static void enviarRespaldo(String[] listaArchivos) {
		
		try (Socket sckCliente = new Socket("127.0.0.1",7000);
			DataOutputStream salida = new DataOutputStream(sckCliente.getOutputStream())) {
			
			for (String rutaArchivo : listaArchivos) {
				File archivo = new File(rutaArchivo);
				
				if(archivo.exists() == true) {
					try (FileInputStream entradaArchivo = new FileInputStream(archivo)){
						salida.writeUTF(archivo.getName());
						salida.writeLong(archivo.length());
			            salida.flush();
			            
			            long sizeArchivo = archivo.length();
			            long sizeActual = 0;
			            int unByte;
			            
			            while(sizeActual < sizeArchivo && (unByte = entradaArchivo.read()) != -1) {
			            	salida.write(unByte);
			            	sizeActual++;
			            }
			            salida.flush();
			            System.out.println("Respaldo: "+archivo.getName()+" ha sido guardado");
					}catch (IOException e) {
						System.out.println("Error al leer el archivo: "+archivo.getName());
					}
				}else {
					System.out.println("El archivo: "+rutaArchivo+" no existe");
				}
			}
			
		}catch(IOException e) {
			System.out.println("Error de conexión");
		}
	}
	
	public static void main(String[] args) {
		String[] losArchivos = {"IdGenerador.dat","Usuraio.dat","Personas.dat","Empresa.dat","Ofertas.dat","Solicitudes.dat"};
		enviarRespaldo(losArchivos);
	}
}
