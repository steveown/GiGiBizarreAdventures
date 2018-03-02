package hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import classes.Jogador;
import manager.Content;

public class Hud {
	
	private BufferedImage hud;
	private BufferedImage hudbar;
	private BufferedImage spriteJogador;
	
	private Jogador jogador;
	private String classe;
	private float pixelPorHp;
	private float tamanhoBarraHp;
	
	private Font font;
	
	public Hud(Jogador j){
		
		jogador = j;
		pixelPorHp = 170 / (float)jogador.getHp();
		
		spriteJogador = jogador.getSprite();
		hud = Content.HUD[0][0];
		hudbar = Content.HUDBAR[0][0];
		
		classe = jogador.getClasse();

		font =new Font("SF Pixelate", Font.PLAIN, 15);
	}
	
	public void update(){
		
		tamanhoBarraHp = jogador.getHpAtual() * pixelPorHp;
		
	}
	
	public void draw(Graphics2D g){
		
		// Draw hud
		g.drawImage(hudbar, 0, 0, null);
		//tamanho da barra: 170
		g.fillRect(65, 31, (int) tamanhoBarraHp, 15);
		g.drawImage(hud, 0, 0, null);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(classe, 65, 23);
		g.drawImage(spriteJogador, 17, 10, 40, 45, null);
		g.setColor(Color.red);
		
		g.drawImage(jogador.getArmamento().getSprite(), 740, 540, 60, 60, null);

	}
	
}
