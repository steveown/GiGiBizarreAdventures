package gameState;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Random;

import manager.Content;
import manager.GameStateManager;

public class DadoState extends GameState {

	private Random rand;

	private int numDado;

	private Image dado1;
	private Image dado2;
	private Image dado3;
	private Image dado4;
	private Image dado5;
	private Image dado6;

	private int ticks;

	public DadoState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {

		handleInput();

		ticks = 0;

		rand  = new Random();

		dado1 = Content.DADO1;
		dado2 = Content.DADO2;
		dado3 = Content.DADO3;
		dado4 = Content.DADO4;
		dado5 = Content.DADO5;
		dado6 = Content.DADO6;

		numDado = rand.nextInt(6) + 1;


	}

	public void update() {
		ticks++;

		if(ticks == 100){

			if(numDado == 1 || numDado == 3 || numDado == 5){
				gsm.setDado(false);
				gsm.setState(GameStateManager.GAMEOVER);
			}
			else{
				
				PlayState.jogador.setHpAtual(PlayState.jogador.getHp());
				gsm.setDado(false);
			}
		}
	}



	public void draw(Graphics2D g) {

		if(numDado == 1)
			g.drawImage(dado1, 325, 217, null);

		else if(numDado == 2)
			g.drawImage(dado2, 325, 217, null);

		else if(numDado == 3)
			g.drawImage(dado3, 325, 217, null);

		else if(numDado == 4)
			g.drawImage(dado4, 325, 217, null);

		else if(numDado == 5)
			g.drawImage(dado5, 325, 217, null);

		else if(numDado == 6)
			g.drawImage(dado6, 325, 217, null);

	}

	public void handleInput() {

	}

}
