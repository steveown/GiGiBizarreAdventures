package gameState;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import manager.Content;
import manager.GameStateManager;
import manager.Keys;

public class MenuState extends GameState {

	private Image background;
	private BufferedImage zombieHand;
	private BufferedImage logo;
	
	private int currentOption = 0;
	private String[] options = {
			"NEW GAME",
			"QUIT"
	};

	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		background = Content.BACKGROUNDMENU;
		zombieHand = Content.ZOMBIEHAND[0][0];
		logo = Content.LOGO[0][0];
		}

	public void update() {
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(background, 0, 0, null);
		g.drawImage(logo, 170, 50, 400, 300, null);

		Content.drawString(g, options[0], 312 , 360);
		Content.drawString(g, options[1], 356, 386);
		
		if(currentOption == 0) g.drawImage(zombieHand, 242, 352, null);
		else if(currentOption == 1) g.drawImage(zombieHand, 286, 378, null);

	}

	public void handleInput() {
		if(Keys.isPressed(Keys.BAIXO) && currentOption == options.length - 1)
			currentOption = 0;
		else if(Keys.isPressed(Keys.BAIXO) && currentOption != options.length - 1)
			currentOption++;
		
		if(Keys.isPressed(Keys.CIMA) && currentOption == 0)
			currentOption = options.length - 1;
		else if(Keys.isPressed(Keys.CIMA) && currentOption != 0)
			currentOption--;
		if(Keys.isPressed(Keys.Z))
			selectOption();
	}
	
	public void selectOption(){
		if(currentOption == 0)
			gsm.setState(GameStateManager.NEWGAME);
		if(currentOption == 2)
			System.exit(0);
	}	
	
}
