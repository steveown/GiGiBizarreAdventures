package gameState;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import classes.Animation;
import manager.Content;
import manager.GameStateManager;
import manager.Keys;

public class NewGameState extends GameState {

	// Sprites
	private BufferedImage[][] humanoSprites;
	private BufferedImage[][] vampiroSprites;
	private BufferedImage[][] elfoSprites;
	private BufferedImage[][] zumbiSprites;
	private BufferedImage[][] venusianoSprites;
	private BufferedImage[][] marcianoSprites;

	private BufferedImage background;
	private BufferedImage zombieHand;

	// Anima��o
	private Animation animation;

	private int currentOption = 0;
	private String[] options = {
			"HUMANO",
			"MARCIANO",
			"VENUSIANO",
			"VAMPIRO",
			"ELFO",
			"ZUMBI"
	};

	// Anima��o
	private boolean animationVerif;

	// Classes
	private final int HUMANO = 0;
	private final int MARCIANO = 1;
	private final int VENUSIANO = 2;
	private final int VAMPIRO = 3;
	private final int ELFO = 4;
	private final int ZUMBI = 5;

	public static int staticOption;


	public NewGameState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {
		animation = new Animation();
		background = Content.BACKGROUNDNEWGAME[0][0];
		zombieHand = Content.ZOMBIEHAND[0][0];
		humanoSprites = Content.HUMANO;
		vampiroSprites = Content.VAMPIRO;
		elfoSprites = Content.ELFO;
		zumbiSprites = Content.ZUMBI;
		venusianoSprites = Content.VENUSIANO;
		marcianoSprites = Content.MARCIANO;
		animation.setFrames(humanoSprites[0]);
		animation.setDelay(10);
	}

	public void update() {
		handleInput();
		animation.update();
	}

	public void draw(Graphics2D g) {

		g.drawImage(background, 0, 0, null);

		Content.drawString(g, options[0], 334 , 350);
		Content.drawString(g, options[1], 312, 376);
		Content.drawString(g, options[2], 301, 402);
		Content.drawString(g, options[3], 323, 432);
		Content.drawString(g, options[4], 356, 458);
		Content.drawString(g, options[5], 345, 484);

		if(currentOption == this.HUMANO){
			g.drawImage(zombieHand, 264, 342, null);
			if(!animationVerif){
				animation.setFrames(humanoSprites[0]);
				animation.setDelay(5);
				animationVerif = true;
			}
		}
		else if(currentOption == this.MARCIANO){
			g.drawImage(zombieHand, 242, 368, null);
			if(!animationVerif){
				animation.setFrames(marcianoSprites[0]);
				animation.setDelay(5);
				animationVerif = true;
			}
		}
		else if(currentOption == this.VENUSIANO){
			g.drawImage(zombieHand, 231, 394, null);
			if(!animationVerif){
				animation.setFrames(venusianoSprites[0]);
				animation.setDelay(5);
				animationVerif = true;
			}
		}
		else if(currentOption == this.VAMPIRO){
			g.drawImage(zombieHand, 253, 424, null);
			if(!animationVerif){
				animation.setFrames(vampiroSprites[0]);
				animation.setDelay(5);
				animationVerif = true;
			}
		}
		else if(currentOption == this.ELFO){
			g.drawImage(zombieHand, 286, 450, null);
			if(!animationVerif){
				animation.setFrames(elfoSprites[0]);
				animation.setDelay(5);
				animationVerif = true;
			}
		}
		else if(currentOption == this.ZUMBI){
			g.drawImage(zombieHand, 275, 476, null);
			if(!animationVerif){
				animation.setFrames(zumbiSprites[0]);
				animation.setDelay(5);
				animationVerif = true;
			}
		}

		g.drawImage(animation.getImage(), 370, 200, null);

		staticOption = currentOption;
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
		if(Keys.isPressed(Keys.CIMA) || Keys.isPressed(Keys.BAIXO))
			animationVerif = false;
		if(Keys.isPressed(Keys.ESC))
			gsm.setState(GameStateManager.MENU);
		if(Keys.isPressed(Keys.Z)){
			gsm.setState(GameStateManager.PLAY);

		}

	}

}
