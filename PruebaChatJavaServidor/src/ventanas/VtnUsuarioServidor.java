package ventanas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.NombreUsuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.CardLayout;

public class VtnUsuarioServidor extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public VtnUsuarioServidor(VtnPrincipalServidor ventana) {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, "name_2396405768686400");
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre de Usuario");
		lblNewLabel.setBounds(149, 66, 91, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(154, 112, 86, 20);
		textField.setColumns(10);
		panel.add(textField);
		
		JButton btnAcceder = new JButton("Acceder");
		btnAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().isEmpty()) {
					NombreUsuario.setNombre(textField.getText());
					cambiaPaneles(new VtnChatServidor(), panel);
				}
				cambiaPaneles(new VtnChatServidor(), panel);
			}
		});
		btnAcceder.setBounds(169, 160, 71, 23);
		panel.add(btnAcceder);

	}
	public void cambiaPaneles(JPanel nuevoPanel, JPanel este) {
		este.removeAll();
		
		este.add(nuevoPanel);
//		nuevoPanel.setBounds(100, 100, 450, 300);
		este.repaint();
		este.revalidate();
	}
}
