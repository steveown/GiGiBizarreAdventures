package gameState;

import java.awt.Graphics2D;
import java.awt.Image;

import manager.Content;
import manager.GameStateManager;

public class PassarState extends GameState {

	private Image backgroundGigi = Content.GIGINASESTRELAS;;
	private int ticks;

	public PassarState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		ticks = 0;
	}

	public void update() {
		ticks++;

		if(ticks == 100){
			gsm.setPassar(false);
		}
	}

	public void draw(Graphics2D g) {
		g.drawImage(backgroundGigi, 0, 0, null);
	}

	public void handleInput() {

	}

}
