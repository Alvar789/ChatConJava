package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.BuscaIp;
import modelo.ManejaMensajesRecibidosCliente;
import modelo.SocketClienteTCP;

public class VtnChatCliente extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldMensaje;
	private JTextField textField;
	private SocketClienteTCP cliente = null;
	private ManejaMensajesRecibidosCliente ma = null;
	private JComboBox comboBox;
	private JScrollPane scrollPane;
	private BuscaIp bip = new BuscaIp();

	/**
	 * Create the panel.
	 */
	public VtnChatCliente() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBounds(0, 0, 450, 300);

		setLayout(null);

		textFieldMensaje = new JTextField();
		textFieldMensaje.setBounds(84, 230, 165, 20);
		add(textFieldMensaje);
		textFieldMensaje.setColumns(10);

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
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.enviarMensajeDeTexto(textFieldMensaje.getText());
				textAreaChat.append(textFieldMensaje.getText()+"\n");
				textFieldMensaje.setText("");
				textFieldMensaje.setFocusable(true);
			}
		});
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(138, 12, 141, 22);
		add(comboBox);
		
		rellenaIp(bip.buscadorIp());

		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente = new SocketClienteTCP(comboBox.getSelectedItem().toString(), 0);
				btnEnviar.setEnabled(true);
				ma = new ManejaMensajesRecibidosCliente(cliente, textAreaChat);
				ma.start();
			}
		});
		btnConectar.setBounds(284, 12, 89, 23);
		add(btnConectar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Buscando...");
				comboBox.removeAll();
				rellenaIp(bip.buscadorIp());
			}
		});
		btnBuscar.setBounds(10, 12, 89, 23);
		add(btnBuscar);
	}
	public void rellenaIp(ArrayList<String> arrLIp) {
		comboBox.addItem("localhost");
		for (String ip : arrLIp) {
			comboBox.addItem(ip);
		}
	}
}
