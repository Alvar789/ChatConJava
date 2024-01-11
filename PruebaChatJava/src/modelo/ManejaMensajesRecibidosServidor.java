package modelo;

import java.io.IOException;

import javax.swing.JTextArea;

public class ManejaMensajesRecibidosServidor extends Thread{
	private SocketServidorTCP servidor;
	private JTextArea textArea;
	
	public ManejaMensajesRecibidosServidor(SocketServidorTCP servidor, JTextArea textArea) {
		this.servidor = servidor;
		this.textArea = textArea;
	}

	@Override
	public void run() {
		try {
			this.servidor.start();
			this.servidor.abrirCanalesDeTexto();
			while(true) {
				actualizarTextArea(textArea, servidor);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void actualizarTextArea(JTextArea textArea, SocketServidorTCP servidor) throws IOException {
		textArea.append(servidor.leerMensajesDeTexto()+"\n");
	}
}
