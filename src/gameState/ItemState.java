package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import manager.Content;
import manager.GameStateManager;
import manager.Keys;

public class ItemState extends GameState {

	private int currentItem;

	private FontMetrics fm;

	private BufferedImage quadrado;

	public ItemState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {

		quadrado = Content.QUADRADOITEM[0][0];

	}

	public void update() {

		handleInput();

	}

	public void draw(Graphics2D g) {

		g.drawImage(quadrado, 200, 50, 400, 400, null);

		g.setColor(Color.BLACK);
		g.setFont(new Font("SF Pixelate", Font.PLAIN, 25));
		fm = g.getFontMetrics();

		try{
			String s = PlayState.jogador.itens.get(currentItem).getNome();
			g.drawString(s, 225 + ((350 - fm.stringWidth(s)) / 2), 130);

			g.drawImage(PlayState.jogador.itens.get(currentItem).getSprite(), 225 + ((350 - PlayState.jogador.itens.get(currentItem).getSprite().getWidth()) / 2), 200, null);

			//DESCRIÇÃO
			String[] s2 = PlayState.jogador.itens.get(currentItem).getDescricao();

			g.setFont(new Font("SF Pixelate", Font.PLAIN, 15));
			fm = g.getFontMetrics();
			g.drawString("Item " + (currentItem + 1), 225 + ((350 - fm.stringWidth("Item " + currentItem)) / 2), 160);

			int y=300;

			for(String linha: s2)
			{
				g.drawString(linha, 225 + ((350 - fm.stringWidth(linha)) / 2), y);

				y+=19;
			}

		}
		catch(Exception e){
			g.setFont(new Font("SF Pixelate", Font.PLAIN, 30));
			g.drawString("Sem Itens", 225 + ((350 - fm.stringWidth("Sem Itens")) / 2), 230);
		}
	}

	public void handleInput() {
		if(Keys.isPressed(Keys.ESC))
			gsm.setItem(false);

		if(Keys.isPressed(Keys.DIREITA) && currentItem == PlayState.jogador.itens.size() - 1)
			currentItem = 0;
		else if(Keys.isPressed(Keys.DIREITA) && currentItem != PlayState.jogador.itens.size() - 1)
			currentItem++;

		if(Keys.isPressed(Keys.ESQUERDA) && currentItem == 0)
			currentItem = PlayState.jogador.itens.size() - 1;
		else if(Keys.isPressed(Keys.ESQUERDA) && currentItem != 0)
			currentItem--;

		if(Keys.isPressed(Keys.Z)){
			consumirItem();
			if(gsm.isConsumiu()){
				PlayState.jogador.itens.remove(currentItem);
				currentItem = 0;
			}
			gsm.setItem(false);
		}

	}

	public void consumirItem(){

		try{
			int hpAtual = PlayState.jogador.getHpAtual();
			int cpAtual = PlayState.jogador.getCp();

			if(hpAtual <= PlayState.jogador.getHp()){
				if(hpAtual + PlayState.jogador.itens.get(currentItem).getHp() <= PlayState.jogador.getHp())
					PlayState.jogador.setHpAtual(hpAtual + PlayState.jogador.itens.get(currentItem).getHp());
				else {
					hpAtual += PlayState.jogador.itens.get(currentItem).getHp();
					PlayState.jogador.setHpAtual(hpAtual - (hpAtual - PlayState.jogador.getHp()));
				}

				gsm.setConsumiu(true);

			}

			PlayState.jogador.setCp(cpAtual + PlayState.jogador.itens.get(currentItem).getCp());
		}
		
		catch(Exception e){}
	
	}

}
