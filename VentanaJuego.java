package snake;

import javax.swing.JFrame;

public class VentanaJuego extends JFrame {	
	
	public static PanelJuego panel = new PanelJuego();
	
	VentanaJuego() {
		this.setSize(1000, 800);
		setTitle("Snake");
		setLocationRelativeTo(null);
		this.getContentPane().add(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
