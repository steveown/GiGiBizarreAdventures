package gameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;
import manager.GameStateManager;
import manager.Keys;

public class IntroState extends GameState {

	private BufferedImage logo;
	
	private int alpha;
	private int ticks;
	
	private final int FADE_IN = 60;
	private final int LENGTH = 60;
	private final int FADE_OUT = 60;
	
	public IntroState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		ticks = 0;
		try{
			logo = ImageIO.read(getClass().getResourceAsStream("/menu/gigilovers.png"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void update() {
		handleInput();
		ticks++;
		if(ticks < FADE_IN){
			alpha = (int) (255 - 255 *(1.0 * ticks / FADE_IN));
			if(alpha < 0) alpha = 0;
		}
		if(ticks > FADE_IN + LENGTH){
			alpha = (int) (255 * (1.0 * ticks - FADE_IN - LENGTH) / FADE_OUT);
			if(alpha > 255) alpha = 255;
		}
		if(ticks > FADE_IN + LENGTH + FADE_OUT){
			gsm.setState(GameStateManager.MENU);
		}
		
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.PINK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.drawImage(logo, 0, 0, 800 ,600 , null);
		g.setColor(new Color(0, 0, 0, alpha));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
	}

	public void handleInput() {
		if(Keys.isPressed(Keys.Z))
			gsm.setState(GameStateManager.MENU);
	}
	
}
