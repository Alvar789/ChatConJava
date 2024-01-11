package ventanas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import modelo.NombreUsuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

public class VtnUsuarioCliente extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public VtnUsuarioCliente() {
		setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, "name_2399604085116900");
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre de Usuario");
		lblNewLabel.setBounds(160, 98, 91, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(165, 150, 86, 20);
		textField.setColumns(10);
		panel.add(textField);
		
		JButton btnAcceder = new JButton("Acceder");
		btnAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().isEmpty()) {
					NombreUsuario.setNombre(textField.getText());
					cambiaPaneles(new VtnChatCliente(), panel);
				}
			}
		});
		btnAcceder.setBounds(175, 206, 71, 23);
		panel.add(btnAcceder);

	}
	public void cambiaPaneles(JPanel nuevoPanel, JPanel este) {
		este.removeAll();
		este.add(nuevoPanel);
		este.repaint();
		este.revalidate();
		
		
	}
}
