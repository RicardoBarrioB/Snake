package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.JPanel;


public class PanelSnake extends JPanel implements ActionListener {

	private int SCREEN_WIDTH;
	private  int SCREEN_HEIGHT;
	static final int UNIT_SIZE = 50;
	private int GAME_UNITS;
	static final int DELAY = 175;
	private int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 3;
	int manzanasComidas;
	int manzanasX;
	int manzanasY;
	char direccion = 'R';
	boolean running = false;
	Timer timer;
	Random random;
		
	public PanelSnake(int WIDTH, int HEIGHT) {
		this.SCREEN_HEIGHT = HEIGHT;
		this.SCREEN_WIDTH = WIDTH;
		this.GAME_UNITS = (SCREEN_WIDTH / UNIT_SIZE) * (SCREEN_HEIGHT / UNIT_SIZE);
		this.x = new int[GAME_UNITS];
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.GRAY);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		InicioJuego();
	}
	
	
	public void InicioJuego() {
		ManzanasN();
		running = true;
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dibujo(g);
	}
	
	public void Dibujo (Graphics g) {
		if(running) {
			g.setColor(Color.white);
			g.fillOval(manzanasX, manzanasY, UNIT_SIZE, UNIT_SIZE);
			for(int i = 0; i < bodyParts; i++) {
				if(i == 0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}else {
					g.setColor(new Color(45, 180, 0));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			g.setColor(Color.white);
			g.setFont(new Font("mv boli", Font.BOLD, 40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Puntaje:_" + manzanasComidas, (SCREEN_WIDTH - metrics.stringWidth("Puntaje:_" + manzanasComidas))/2, g.getFont().getSize());
		}else {
			finJuego(g);
		}
	}
	
	public void ManzanasN() {
		 manzanasX = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
		 manzanasY = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;
		
	}
	
	public void movimientos() {
		for(int i = bodyParts-1; i > 0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		switch (direccion) {
		case 'U': y[0] = y[0] - UNIT_SIZE; break;
		case 'D': y[0] = y[0] + UNIT_SIZE; break;
		case 'L': x[0] = x[0] - UNIT_SIZE; break;
		case 'R': x[0] = x[0] + UNIT_SIZE; break;
		}
	}
	
	public void checkManzanas() {
		if(x[0] == manzanasX && y[0] == manzanasY) {
			bodyParts++;
			manzanasComidas++;
			ManzanasN();
		}
	}
	
	public void checkChoques() {
		for(int i = bodyParts; i > 0; i--) {
			if(x[0] == x[i] && y[0] == y[i]) {
				running = false;
			}
		}
		 if (x[0] < 0 || x[0] >= SCREEN_WIDTH || y[0] < 0 || y[0] >= SCREEN_HEIGHT) {
		        running = false;
		    }
//		
		if(!running){
			timer.stop();
		}
	}
	
	private void finJuego(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("mv boli", Font.BOLD, 40));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Has perdido:_" + manzanasComidas, (SCREEN_WIDTH - metrics2.stringWidth("Has perdido:_" + manzanasComidas))/2, g.getFont().getSize());
	}
	
	public void actionPerformed(ActionEvent e) {
		if(running) {
			movimientos();
			checkManzanas();
			checkChoques();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT: 
				if(direccion!= 'L'){
					direccion = 'R';
				}
				break;
				
			case KeyEvent.VK_LEFT: 
				if(direccion!= 'R'){
					direccion = 'L';
				}
				break;
				
			case KeyEvent.VK_UP: 
				if(direccion!= 'D'){
					direccion = 'U';
				}
				break;	
				
			case KeyEvent.VK_DOWN: 
				if(direccion!= 'U'){
					direccion = 'D';
				}
				break;
			}
		}
	}

}




















