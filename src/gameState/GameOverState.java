package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import manager.GameStateManager;

public class GameOverState extends GameState {
	
	private int ticks;
	Font font;
	FontMetrics fm;
	
	public GameOverState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		font = new Font("SF Pixelate", Font.PLAIN, 60);
	}

	public void update() {
		ticks++;
		
		if(ticks == 100){
			System.exit(0);
		}
	}

	public void draw(Graphics2D g) {
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 600);
		
		g.setColor(Color.WHITE);
		g.setFont(font);
		fm = g.getFontMetrics();
		g.drawString("VOCÊ PERDEU",(800 - fm.stringWidth("VOCÊ PERDEU")) / 2, 200);
		
	}

	public void handleInput() {
		
	}

}
