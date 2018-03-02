package gameState;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import classes.Inimigo;
import hud.HudBattle;
import itens.Item;
import manager.Content;
import manager.GameStateManager;
import manager.Keys;

public class BattleState extends GameState {


	private Image background;
	private BufferedImage spriteJogador;
	private HudBattle hudBattle;

	private Random rand = new Random();

	private Inimigo inimigo;

	public int currentOption = 0;
	String[] options = {
			"atacar",
			"item",
			"fugir"
	};

	private int fuga;

	public BattleState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {

		if((PlayState.jogador.getx() == 2190 && PlayState.jogador.gety() == 510 && !PlayState.jogador.isLutou())){
			inimigo = new Inimigo(2);
			fuga = 5;
		}
		else if(PlayState.jogador.getx() == 2910 && PlayState.jogador.gety() == 1890 && !PlayState.jogador.isLutou()){
			inimigo = new Inimigo(2);
			fuga = 1;
		}
		else{
			// Geração do inimigo
			int aleatorio = rand.nextInt(2);
			inimigo = new Inimigo(aleatorio);
			fuga = 30;
		}
		currentOption = 0;

		hudBattle = new HudBattle(PlayState.jogador, inimigo); 

		if(PlayState.jogador.getMundo() != 2)
			background = Content.BACKGROUNDBATALHA;
		else background = Content.BACKGROUNBATALHAPOLUX;


	}

	public void update() {
		handleInput();

		hudBattle.update();

	}

	public void draw(Graphics2D g) {

		g.drawImage(background, 0, 0, 800, 600, null);
		g.drawImage(spriteJogador, 50, 200, null);

		hudBattle.draw(g);

		if(currentOption == 0) g.drawRect(76, 462, 190, 72);
		if(currentOption == 1) g.drawRect(324, 462, 190, 72);
		if(currentOption == 2) g.drawRect(553, 462, 190, 72);

	}

	public void handleInput() {

		if(Keys.isPressed(Keys.DIREITA) && currentOption == options.length - 1)
			currentOption = 0;
		else if(Keys.isPressed(Keys.DIREITA) && currentOption != options.length - 1)
			currentOption++;

		if(Keys.isPressed(Keys.ESQUERDA) && currentOption == 0)
			currentOption = options.length - 1;
		else if(Keys.isPressed(Keys.ESQUERDA) && currentOption != 0)
			currentOption--;

		if(Keys.isPressed(Keys.Z))
			selectOption();

	}

	private void selectOption() {

		if(currentOption == 0){

			ataque();
			ataqueInimigo();
			if(verificaMorte()){
				if((PlayState.jogador.getx() == 2190 && PlayState.jogador.gety() == 510 && !PlayState.jogador.isLutou()) || ((PlayState.jogador.getx() == 2910 && PlayState.jogador.gety() == 1890 && !PlayState.jogador.isLutou())))
					PlayState.jogador.setLutou(true);
				
				gsm.setMorreu(true);
				gsm.setBattle(false);
			}

		}

		else if(currentOption == 1){
			gsm.setItem(true);
		}

		else if(currentOption == 2){

			Random rand = new Random();

			if(rand.nextInt(100) < fuga){
				if((PlayState.jogador.getx() == 2190 && PlayState.jogador.gety() == 510 && !PlayState.jogador.isLutou()) || ((PlayState.jogador.getx() == 2910 && PlayState.jogador.gety() == 1890 && !PlayState.jogador.isLutou())))
					PlayState.jogador.setLutou(true);
				gsm.setBattle(false);
			}
			
			else{
				ataqueInimigo();
				if(verificaMorte()){
					gsm.setMorreu(true);
					gsm.setBattle(false);
				}
			}

		}

		if(gsm.isConsumiu()){
			ataqueInimigo();
			gsm.setConsumiu(false);
			verificaMorte();
		}

	}

	public void ataqueInimigo(){

		if(inimigo.getHpAtual() > 0)
			PlayState.jogador.setHpAtual(PlayState.jogador.getHpAtual() - inimigo.getCp());

		else {
			PlayState.jogador.setLutou(true);
			if(PlayState.jogador.itens.size() < 5){
				PlayState.jogador.itens.add(new Item(rand.nextInt(6)));
				
				gsm.setBattle(false);
			}
			else gsm.setBattle(false);
			
		}

	}

	public void ataque(){

		if(PlayState.jogador.getHpAtual() > 0)
			inimigo.setHpAtual(inimigo.getHpAtual() - PlayState.jogador.getCp());

	}

	public boolean verificaMorte(){

		if(PlayState.jogador.getHpAtual() <= 0)
			return true;
		else return false;

	}

}
