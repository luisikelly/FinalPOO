package ar.edu.unlu.juego.espionaje.vista.grafica;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;

public class Instrucciones extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Instrucciones frame = new Instrucciones();
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
	public Instrucciones() {
		setBounds(100, 100, 686, 513);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.EAST);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[][][][][][grow][][][]", "[][][][][][][grow][][grow][][][grow]"));

		JLabel lblEspionaje = new JLabel("       ESPIONAJE");
		lblEspionaje.setHorizontalAlignment(SwingConstants.CENTER);
		lblEspionaje.setForeground(Color.LIGHT_GRAY);
		lblEspionaje.setFont(new Font("Bloomer DEMO", Font.PLAIN, 54));
		panel.add(lblEspionaje, "cell 5 0");

		JLabel lblcmoJugar = new JLabel("                    \u00BFC\u00D3MO JUGAR?");
		lblcmoJugar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblcmoJugar.setForeground(Color.WHITE);
		lblcmoJugar.setFont(new Font("SUBSCRIBER", Font.PLAIN, 30));
		panel.add(lblcmoJugar, "cell 5 2");

	}

}
