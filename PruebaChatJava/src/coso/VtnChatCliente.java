package coso;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.ManejaMensajesRecibidosCliente;
import modelo.ManejaMensajesRecibidosServidor;
import modelo.SocketClienteTCP;
import modelo.SocketServidorTCP;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class VtnChatCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMensaje;
	private JTextField textField;
	private SocketClienteTCP cliente = null;
	private ManejaMensajesRecibidosCliente ma = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VtnChatCliente frame = new VtnChatCliente();
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
	public VtnChatCliente() {
		setTitle("Cliente");
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

		
		

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setEnabled(false);
		btnEnviar.setBounds(259, 229, 89, 23);
		contentPane.add(btnEnviar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(83, 39, 265, 183);
		contentPane.add(scrollPane);
		
		JTextArea textAreaChat = new JTextArea();
		textAreaChat.setWrapStyleWord(true);
		scrollPane.setViewportView(textAreaChat);

		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.enviarMensajeDeTexto(textFieldMensaje.getText());
				textAreaChat.append("(Cliente)" + textFieldMensaje.getText()+"\n");
				textFieldMensaje.setText("");
				textFieldMensaje.setFocusable(true);
			}
		});
		
		textField = new JTextField();
		textField.setBounds(134, 13, 125, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(105, 16, 19, 14);
		contentPane.add(lblIp);

		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente = new SocketClienteTCP(textField.getText(), 49171);
				btnEnviar.setEnabled(true);
				ma = new ManejaMensajesRecibidosCliente(cliente, textAreaChat);
				ma.start();
			}
		});
		btnConectar.setBounds(284, 12, 89, 23);
		contentPane.add(btnConectar);
		
		
		

	}
}
