package hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import classes.Inimigo;
import classes.Jogador;
import manager.Content;

public class HudBattle {
	
	private BufferedImage hud;
	private BufferedImage hudbar;
	private BufferedImage hudInimigo;
	private BufferedImage hudBarInimigo;
	private BufferedImage spriteJogador;
	private BufferedImage spriteInimigo;
	
	private Inimigo inimigo;
	private float pixelPorHpInimigo;
	private Jogador jogador;
	private float pixelPorHp;
	private String classe;
	private Font font;
	private float tamanhoBarraHp;
	private float tamanhoBarraHpInimigo;
	
	FontMetrics fm;
	String s;
	
	public HudBattle(Jogador j, Inimigo i){
		
		jogador = j;
		inimigo = i;
		pixelPorHpInimigo = 170 / (float)inimigo.getHp();
		
		spriteInimigo = inimigo.getSprite();
		s = inimigo.getClasse();
		spriteJogador = jogador.getSprite();
		hud = Content.HUD[0][0];
		hudbar = Content.HUDBAR[0][0];
		hudInimigo = Content.HUDINIMIGO[0][0];
		hudBarInimigo = Content.HUDBARINIMIGO[0][0];
		
		classe = jogador.getClasse();

		font =new Font("SF Pixelate", Font.PLAIN, 15);
	}
	
	public void update(){
		
		pixelPorHp = 170 / (float)jogador.getHp();
		tamanhoBarraHp = jogador.getHpAtual() * pixelPorHp;
		tamanhoBarraHpInimigo = inimigo.getHpAtual() * pixelPorHpInimigo;
	
	}
	
	public void draw(Graphics2D g){
		
		g.setColor(Color.red);
		
		// Draw hud
		g.drawImage(hudbar, 70, 380, null);
		g.fillRect(135, 411, (int) tamanhoBarraHp, 15);
		g.drawImage(hud, 70, 380, null);
		g.drawImage(spriteJogador, 87, 390, 40, 45, null);

		// Draw hud inimigo
		g.drawImage(hudBarInimigo, 500, 50, null);
		g.fillRect(518 + (170 - (int)tamanhoBarraHpInimigo), 81, (int)tamanhoBarraHpInimigo, 15);
		g.drawImage(hudInimigo, 500, 50, null);
		g.drawImage(spriteInimigo, 693, 60, 40, 45, null);
		
		g.setFont(font);
		fm = g.getFontMetrics();
		g.setColor(Color.WHITE);
		g.drawString(classe, 135, 403);
		g.drawString(s, 683 - fm.stringWidth(s), 73);
		
	}
	
}
