package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.ManejaMensajesRecibidosServidor;
import modelo.SocketServidorTCP;

public class VtnChatServidor extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldMensaje;
	private JTextField textField;
	private SocketServidorTCP servidor = null;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public VtnChatServidor() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBounds(0, 0, 450, 300);

		setLayout(null);

		textFieldMensaje = new JTextField();
		textFieldMensaje.setBounds(84, 230, 165, 20);
		add(textFieldMensaje);
		textFieldMensaje.setColumns(10);

		
		try {
			servidor = new SocketServidorTCP(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(259, 229, 89, 23);
		add(btnEnviar);

		
		textField = new JTextField();
		textField.setBounds(134, 13, 125, 20);
		add(textField);
		textField.setColumns(10);

		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(105, 16, 19, 14);
		add(lblIp);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(84, 50, 254, 162);
		add(scrollPane);
		
		JTextArea textAreaChat = new JTextArea();
		textAreaChat.setWrapStyleWord(true);
		scrollPane.setViewportView(textAreaChat);
		ManejaMensajesRecibidosServidor ma = new ManejaMensajesRecibidosServidor(servidor, textAreaChat);
		ma.start();
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				servidor.enviarMensajeDeTexto(textFieldMensaje.getText());
				textAreaChat.append(textFieldMensaje.getText()+"\n");
				textFieldMensaje.setText("");
				textFieldMensaje.setFocusable(true);
			}
		});

	}
	
}
