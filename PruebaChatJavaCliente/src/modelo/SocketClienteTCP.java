package modelo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClienteTCP {
	private String servidorIP;
	private int puertoServidor;
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	// Objetos espec�ficos para el env�o y recepci�n de cadenas de caracteres
	private InputStreamReader isr;
	private BufferedReader br;
	private PrintWriter pw;
	
	public SocketClienteTCP(String servidorIP, int puertoServidor){
		this.servidorIP = servidorIP;
		this.puertoServidor = puertoServidor;
	}
	
	public void start() throws UnknownHostException, IOException{
		System.out.println("(Cliente) Estableciendo conexi�n...");
		socket = new Socket(servidorIP, puertoServidor);
		os = socket.getOutputStream();
		is = socket.getInputStream();
		
		System.out.println("(Cliente) Conexi�n establecida ;)");
	}
	
	public void stop() throws IOException{
		System.out.println("(Cliente) Cerrando conexiones...");
		is.close();
		os.close();
		socket.close();
		
		System.out.println("(Cliente) Conexiones cerradas");
	}
	
	public void abrirCanalesDeTexto(){
		System.out.println("(Cliente) Abriendo canales de texto...");
		// Canales de lectura
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		
		// Canales de escritura
		pw = new PrintWriter(os, true);
		
		System.out.println("(Cliente) Canales de texto abiertos ;)");
	}

	public void cerrarCanalesDeTexto() throws IOException{
		System.out.println("(Cliente) Cerrando canales de texto...");
		// Canales de lectura
		br.close();
		isr.close();
		
		// Canal de escritura
		pw.close();
		
		System.out.println("(Cliente) Canales de texto cerrados ;)");
	}
	
	public String[] leerMensajesDeTexto() throws IOException{
		System.out.println("(Cliente) Leyando mensaje...");
		String[] mensaje = br.readLine().split(";");
		System.out.println("(Cliente) Mensaje leido ;)");
		
		return mensaje;
	}
	
	public void enviarMensajeDeTexto(String mensaje){
		System.out.println("(Cliente) Enviando mensaje...");
		pw.println(NombreUsuario.getNombre() + ";" + mensaje);
		System.out.println("(Cliente) Mensaje enviado ;)");
	}
	
//	public static void main(String[] args) {
//		SocketClienteTCP cliente = new SocketClienteTCP("localhost", 49171);
//		try{
//			cliente.start();
//			cliente.abrirCanalesDeTexto();
//			
//			// Envio del mensaje al servidor
//			cliente.enviarMensajeDeTexto("Mensaje enviado desde el cliente");
//			
//			// Recepci�n del mensaje del servidor
//			String mensajeRecibido = cliente.leerMensajesDeTexto();
//			System.out.println("(Cliente) Mensaje recibido: " + mensajeRecibido);
//			
//			cliente.cerrarCanalesDeTexto();
//			cliente.stop();
//		}catch(UnknownHostException uhe){
//			uhe.printStackTrace();
//		}catch(IOException e){
//			e.printStackTrace();
//		}
//	}
}
