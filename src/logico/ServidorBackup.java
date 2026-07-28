package logico;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;

public class ServidorBackup extends Thread {

	private static String respaldos = "Respaldos" + File.separator;
	
	public static void main (String args[]) {
		
		File carpeta = new File(respaldos);
		if(carpeta.exists() == false) {
			carpeta.mkdirs();
		}
		
		ServerSocket sckServer = null;
		try {
			sckServer = new ServerSocket(7000);
		}catch(IOException e) {
			System.out.println("Comunicación rechazada.");
			System.exit(1);
		}
		
		while(true) {
			try {
				Socket sckCliente = sckServer.accept();
			
				new Thread() {
					public void run() {
						try	(Socket sckActual = sckCliente;
							DataInputStream entrada = new DataInputStream(sckActual.getInputStream())){
							
							boolean pasandoArchivos = true;
							
							while(pasandoArchivos) {
								try {
									String nombreArchivo = entrada.readUTF();
									long sizeArchivo = entrada.readLong();
									File archivoSalida = new File(respaldos+"Respaldo_"+LocalDate.now()+"_"+nombreArchivo);
									
									try (FileOutputStream salida = new FileOutputStream(archivoSalida)){
					                	int unByte = entrada.read();
					                	long sizeActual = 0;
					                	while(sizeActual < sizeArchivo && unByte != -1) {
					                		salida.write(unByte);
					                		sizeActual++;
					                		unByte = entrada.read();
					                		
					                	}
					                	System.out.println("Respaldo guardado: "+archivoSalida.getName());
					                }
								}catch (EOFException e) {
									pasandoArchivos = false;
								}
							}
						}catch(IOException e) {
							System.out.println("Error");
						}
					}
				}.start();
			}catch(IOException e) {
				System.out.println("Error");
			}
		}
	}
}
