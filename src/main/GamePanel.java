package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import manager.GameStateManager;
import manager.Keys;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {

	// Dimen��es
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	//Game loop
	private Thread thread;
	private boolean running;
	private final int FPS = 30;
	private final int TARGET_TIME = 1000/ FPS;

	// coisas pra desenhar
	private BufferedImage image;
	private Graphics2D g;

	//Game state manager
	private GameStateManager gsm;

	public GamePanel(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}

	public void addNotify(){
		super.addNotify();
		if(thread == null){
			addKeyListener(this);
			thread = new Thread(this);
			thread.start();
		}
	}
	// Run new thread
	public void run(){

		init();

		long start;
		long elapsed;
		long wait;

		// Game loop
		while(running){
			start = System.nanoTime();
			update();
			draw();
			drawToScreen();

			elapsed = System.nanoTime() - start;

			wait = TARGET_TIME - elapsed / 1000000;

			if(wait < 0)
				wait = TARGET_TIME;

			try{
				Thread.sleep(wait);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

	}

	private void init(){
		running = true;
		image = new BufferedImage(WIDTH, HEIGHT, 1);
		g = (Graphics2D) image.getGraphics();
		gsm = new GameStateManager();
	}

	private void update(){
		gsm.update();
		Keys.update();
	}

	// Desenha o jogo
	private void draw(){
		gsm.draw(g);
	}

	// copy buffer to screen
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}

	// Key Event
	public void keyTyped(KeyEvent key){}
	public void keyPressed(KeyEvent key){
		Keys.keySet(key.getKeyCode(), true);
	}
	public void keyReleased(KeyEvent key){
		Keys.keySet(key.getKeyCode(), false);
	}

}
