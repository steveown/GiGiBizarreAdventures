package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import manager.Content;
import manager.GameStateManager;
import manager.Keys;

public class PauseState extends GameState {

	private Font font;
	private FontMetrics fm;
	
	public PauseState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		font = new Font("SF Pixelate", Font.PLAIN, 30);
	}

	public void update() {
		handleInput();
	}

	public void draw(Graphics2D g) {
		
		g.setFont(font);
		g.setColor(Color.pink);
		fm = g.getFontMetrics();
		g.drawString("Pausado", (800 - fm.stringWidth("Pausado")) / 2, 300);
		
		
		Content.drawString(g, "paused", 40, 30);

		Content.drawString(g, "arrow", 12, 76);
		Content.drawString(g, "keys", 16, 84);
		Content.drawString(g, ": move", 52, 80);

		Content.drawString(g, "z", 12, 96);
		Content.drawString(g, ": action", 52, 96);

	}

	public void handleInput() {
		if(Keys.isPressed(Keys.ESC))
			gsm.setPaused(false);

	}

}
