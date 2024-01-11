package coso;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.ManejaMensajesRecibidosServidor;
import modelo.SocketServidorTCP;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class VtnChatServidor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMensaje;
	private JTextField textField;
	private SocketServidorTCP servidor = null;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VtnChatServidor frame = new VtnChatServidor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VtnChatServidor() {
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldMensaje = new JTextField();
		textFieldMensaje.setBounds(84, 230, 165, 20);
		contentPane.add(textFieldMensaje);
		textFieldMensaje.setColumns(10);

		
		try {
			servidor = new SocketServidorTCP(49171);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(259, 229, 89, 23);
		contentPane.add(btnEnviar);

		
		textField = new JTextField();
		textField.setBounds(134, 13, 125, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(105, 16, 19, 14);
		contentPane.add(lblIp);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(84, 50, 254, 162);
		contentPane.add(scrollPane);
		
		JTextArea textAreaChat = new JTextArea();
		textAreaChat.setWrapStyleWord(true);
		scrollPane.setViewportView(textAreaChat);
		ManejaMensajesRecibidosServidor ma = new ManejaMensajesRecibidosServidor(servidor, textAreaChat);
		ma.start();
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				servidor.enviarMensajeDeTexto(textFieldMensaje.getText());
				textAreaChat.append("(Servidor)" + textFieldMensaje.getText()+"\n");
				textFieldMensaje.setText("");
				textFieldMensaje.setFocusable(true);
			}
		});

	}
}
