package modelo;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class ManejaMensajesRecibidosCliente extends Thread{
	private SocketClienteTCP cliente;
	private JTextArea textArea;
	
	public ManejaMensajesRecibidosCliente(SocketClienteTCP cliente, JTextArea textArea) {
		this.cliente = cliente;
		this.textArea = textArea;
	}

	@Override
	public void run() {
		try {
			this.cliente.start();
			this.cliente.abrirCanalesDeTexto();
			while(true) {
				actualizarTextArea(textArea, cliente);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(textArea, "No hay conxion con el servidor.");
//			e.printStackTrace();
		}
	}
	private void actualizarTextArea(JTextArea textArea, SocketClienteTCP cliente) throws IOException {
		String[] mensaje = cliente.leerMensajesDeTexto();
		textArea.append("("+ mensaje[0]+") " + mensaje[1] +"\n");
	}
}
