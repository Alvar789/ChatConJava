package modelo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServidorTCP {
	private ServerSocket servidorSocket;
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	
	// Objetos espec�ficos para el env�o y recepci�n de cadenas de caracteres
	private InputStreamReader isr;
	private BufferedReader br;
	private PrintWriter pw;
	
	public SocketServidorTCP (int puerto) throws IOException{
		servidorSocket = new ServerSocket(puerto);
	}
	
	public void start() throws IOException{
		System.out.println("(Servidor) Esperando conexiones...");
		// Queda a la espera de peticiones del ServerSocket del constructor
		socket = servidorSocket.accept();
		is = socket.getInputStream();
		os = socket.getOutputStream();
		
		System.out.println("(Servidor) Conexi�n establecida ;)");
	}
	
	public void stop() throws IOException{
		System.out.println("(Servidor) Cerrando conexiones...");
		is.close();
		os.close();
		socket.close();
		servidorSocket.close();
		
		System.out.println("(Servidor) Conexiones cerradas ;)");
	}
	
	public void abrirCanalesDeTexto(){
		System.out.println("(Servidor) Abriendo canales de texto...");
		// Canales de lectura
		isr = new InputStreamReader(is);
		br = new BufferedReader(isr);
		
		// Canales de escritura
		pw = new PrintWriter(os, true);
		
		System.out.println("(Servidor) Canales de texto abiertos ;)");
	}

	public void cerrarCanalesDeTexto() throws IOException{
		System.out.println("(Servidor) Cerrando canales de texto...");
		// Canales de lectura
		br.close();
		isr.close();
		
		// Canal de escritura
		pw.close();
		
		System.out.println("(Servidor) Canales de texto cerrados ;)");
	}
	
	public String[] leerMensajesDeTexto() throws IOException{
		System.out.println("(Servidor) Leyando mensaje...");
		String[] mensaje = br.readLine().split(";");  //"(Cliente) " + br.readLine();
		
		System.out.println("(Servidor) Mensaje leido ;)");
		
		return mensaje;
	}
	
	public void enviarMensajeDeTexto(String mensaje){
		System.out.println("(Servidor) Enviando mensaje...");
		pw.println(NombreUsuario.getNombre() + ";" + mensaje);
		System.out.println("(Servidor) Mensaje enviado ;)");
	}
	
//	public String iniciarServidor(SocketServidorTCP servidor) {
//		String mensajeRecibido = "vacio";
//		try{
//			SocketServidorTCP servidor = new SocketServidorTCP(49171);
//			servidor.start();
//			servidor.abrirCanalesDeTexto();
//			// Recepci�n del mensaje del cliente
//			mensajeRecibido = servidor.leerMensajesDeTexto();
//			System.out.println("(Servidor) Mensaje recibido: " + mensajeRecibido);
//			
//			// Envio del mensaje al cliente
//			servidor.enviarMensajeDeTexto("Mensaje enviado desde el servidor");
//			servidor.cerrarCanalesDeTexto();
//			servidor.stop();
//		}catch(IOException e){
//			e.printStackTrace();
//		}	
//		return mensajeRecibido;
//	}
}
