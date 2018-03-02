package classes;

import java.awt.image.BufferedImage;

import manager.Content;

public class Inimigo {

	// Gameplay
	private String classe;
	private int hp;
	private int hpAtual;
	private int cp;
	private BufferedImage sprite;

	// Dimens�es
	protected int width;
	protected int height;
	protected int cwidth;
	protected int cheight;
	
	// Sprite
	BufferedImage baixoSprite;

	// Inimigos
	private final int POLITIKOAK = 0;
	private final int DRAGONITE = 1;
	private final int TERRIVELBESTA = 2;
	private final int TERRIVELBESTAV2 = 3;

	public Inimigo(int i){
	
		if(i == POLITIKOAK){
			classe = Politikoak.getClasse();
			hp = Politikoak.getHp();
			cp = Politikoak.getCp();
			sprite = Content.POLITIKOAK[0][0];
		}
		else if(i == DRAGONITE){
			classe = Dragonite.getClasse();
			hp = Dragonite.getHp();
			cp = Dragonite.getCp();
			sprite = Content.DRAGONITE[0][0];
		}
		else if(i == TERRIVELBESTA){
			classe = TerrivelBestaVorazdeTraal.getClasse();
			hp = TerrivelBestaVorazdeTraal.getHp();
			cp = TerrivelBestaVorazdeTraal.getCp();
			sprite = Content.BESTATRAAL[0][0];
		}
		
		else if(i == TERRIVELBESTAV2){
			classe = TerrivelBestaVorazdeTraalv2.getClasse();
			hp = TerrivelBestaVorazdeTraalv2.getHp();
			cp = TerrivelBestaVorazdeTraalv2.getCp();
			sprite = Content.BESTATRAALV2[0][0];
		}
		
		hpAtual = hp;
	
	}
	
	public BufferedImage getSprite(){ return sprite; }

	public String getClasse() {
		return classe;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getHpAtual() {
		return hpAtual;
	}

	public void setHpAtual(int hpAtual) {
		this.hpAtual = hpAtual;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}
	
}
