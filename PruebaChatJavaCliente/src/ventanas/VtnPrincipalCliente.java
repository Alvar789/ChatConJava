package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;

public class VtnPrincipalCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VtnPrincipalCliente frame = new VtnPrincipalCliente();
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
	public VtnPrincipalCliente() {
		contentPane = new JPanel();
		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane.setLayout(new CardLayout(0, 0));
		cambiaPaneles(new VtnUsuarioCliente());
		setContentPane(contentPane);
	}
	public void cambiaPaneles(JPanel nuevoPanel) {
		contentPane.removeAll();
		contentPane.add(nuevoPanel);
		contentPane.repaint();
		contentPane.revalidate();
	}
}
