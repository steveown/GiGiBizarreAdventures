package classes;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gameState.NewGameState;
import itens.Armamento;
import itens.Item;
import manager.Content;
import tileMap.TileMap;

public class Jogador extends Entidade {

	// Gameplay
	private String nick;
	private String classe;
	private int hp;
	private int hpAtual;
	private int cp;
	private long ticks;
	public ArrayList<Item> itens; 
	private Armamento armamento;
	private boolean montaria;
	private boolean lutou;
	
	// Sprites
	private BufferedImage[][] todosSprite;
	private BufferedImage[] baixoSprites;
	private BufferedImage[] cimaSprites;
	private BufferedImage[] esquerdaSprites;
	private BufferedImage[] direitaSprites;
	
	private BufferedImage[][] todosSpritesMontaria;
	private BufferedImage[] baixoSpritesMontaria;
	private BufferedImage[] cimaSpritesMontaria;
	private BufferedImage[] esquerdaSpritesMontaria;
	private BufferedImage[] direitaSpritesMontaria;
	
	// Anima��o
	private final int BAIXO = 0;
	private final int ESQUERDA = 1;
	private final int DIREITA = 2;
	private final int CIMA = 3;
	
	// Classes
	private final int HUMANO = 0;
	private final int MARCIANO = 1;
	private final int VENUSIANO = 2;
	private final int VAMPIRO = 3;
	private final int ELFO = 4;
	private final int ZUMBI = 5;

	public Jogador(TileMap tm){
		super(tm);
		
		lutou = false;
		
		int escolhaClasse = NewGameState.staticOption;
		
		width = 95;
		height = 60;
		cwidth = 91;
		cheight = 56;
		
		setMundo(0);
		
		velMovimento = 10;
		
		if(escolhaClasse == this.HUMANO){
			setAtributosHumano();
			todosSprite = Content.HUMANO;
			baixoSprites = todosSprite[0];
			direitaSprites = todosSprite[1];
			esquerdaSprites = todosSprite[2];
			cimaSprites = todosSprite[3];
			todosSpritesMontaria = Content.HUMANOMONTARIA;
			esquerdaSpritesMontaria = todosSpritesMontaria[0];
			baixoSpritesMontaria = todosSpritesMontaria[1];
			direitaSpritesMontaria = todosSpritesMontaria[2];
			cimaSpritesMontaria = todosSpritesMontaria[3];
		}
		
		else if(escolhaClasse == this.MARCIANO){
			setAtributosMarciano();
			todosSprite = Content.MARCIANO;
			baixoSprites = todosSprite[0];
			direitaSprites = todosSprite[1];
			esquerdaSprites = todosSprite[2];
			cimaSprites = todosSprite[3];
			todosSpritesMontaria = Content.MARCIANOMONTARIA;
			esquerdaSpritesMontaria = todosSpritesMontaria[0];
			baixoSpritesMontaria = todosSpritesMontaria[1];
			direitaSpritesMontaria = todosSpritesMontaria[2];
			cimaSpritesMontaria = todosSpritesMontaria[3];
		}
		else if(escolhaClasse == this.VENUSIANO){
			setAtributosVenusiano();
			todosSprite = Content.VENUSIANO;
			baixoSprites = todosSprite[0];
			direitaSprites = todosSprite[1];
			esquerdaSprites = todosSprite[2];
			cimaSprites = todosSprite[3];
			todosSpritesMontaria = Content.VENUSIANOMONTARIA;
			esquerdaSpritesMontaria = todosSpritesMontaria[0];
			baixoSpritesMontaria = todosSpritesMontaria[1];
			direitaSpritesMontaria = todosSpritesMontaria[2];
			cimaSpritesMontaria = todosSpritesMontaria[3];

		}
		else if(escolhaClasse == this.VAMPIRO){
			setAtributosVampiro();
			todosSprite = Content.VAMPIRO;
			baixoSprites = todosSprite[0];
			direitaSprites = todosSprite[1];
			esquerdaSprites = todosSprite[2];
			cimaSprites = todosSprite[3];
		}
		else if(escolhaClasse == this.ELFO){
			setAtributosElfo();
			todosSprite = Content.ELFO;
			baixoSprites = todosSprite[0];
			direitaSprites = todosSprite[1];
			esquerdaSprites = todosSprite[2];
			cimaSprites = todosSprite[3];
			todosSpritesMontaria = Content.ELFOMONTARIA;
			esquerdaSpritesMontaria = todosSpritesMontaria[0];
			baixoSpritesMontaria = todosSpritesMontaria[1];
			direitaSpritesMontaria = todosSpritesMontaria[2];
			cimaSpritesMontaria = todosSpritesMontaria[3];
		}
		else if(escolhaClasse == this.ZUMBI){
			setAtributosZumbi();
			todosSprite = Content.ZUMBI;
			baixoSprites = todosSprite[0];
			direitaSprites = todosSprite[1];
			esquerdaSprites = todosSprite[2];
			cimaSprites = todosSprite[3];
			todosSpritesMontaria = Content.ZUMBIMONTARIA;
			esquerdaSpritesMontaria = todosSpritesMontaria[0];
			baixoSpritesMontaria = todosSpritesMontaria[1];
			direitaSpritesMontaria = todosSpritesMontaria[2];
			cimaSpritesMontaria = todosSpritesMontaria[3];
		}
		
		itens = new ArrayList<>();
		
		hpAtual = hp;

		animation.setFrames(baixoSprites);
		animation.setDelay(5);
	}
	
	private void setAnimamacao(int i, BufferedImage[] bi, int d){
		currentAnimation = i;
		animation.setFrames(bi);
		animation.setDelay(d);
	}
	
	public long getTicks(){ return ticks; }
	
	public void setBaixo(){
		super.setBaixo();
	}
	public void setEsquerda(){
		super.setEsquerda();
	}
	public void setDireita(){
		super.setDireita();
	}
	public void setCima(){
		super.setCima();
	}
	
	public void setMontaria(boolean b){ montaria = b; }
	public boolean isMontaria(){ return montaria; }
	
	public void update(){
		
		ticks++;
		
		// Set animation
		if(baixo)
			if(montaria)
				setAnimamacao(BAIXO, baixoSpritesMontaria, 10);
			else
				setAnimamacao(BAIXO, baixoSprites, 5);
		
		if(esquerda)
			if(montaria)
				setAnimamacao(ESQUERDA, esquerdaSpritesMontaria, 10);
			else
				setAnimamacao(ESQUERDA, esquerdaSprites, 5);
		
		if(direita)
			if(montaria)
				setAnimamacao(DIREITA, direitaSpritesMontaria, 10);
			else
				setAnimamacao(DIREITA, direitaSprites, 5);
		
		if(cima)
			if(montaria)
				setAnimamacao(CIMA, cimaSpritesMontaria, 10);
			else
				setAnimamacao(CIMA, cimaSprites, 5);
		
		super.update();
	}
	
	public void draw(Graphics2D g){
		super.draw(g);
	}
	
	public void setAtributosHumano(){
		setClasse(Humano.getClasse());
		setHp(Humano.getHp());
		setCp(Humano.getCp());
	}
	
	public void setAtributosMarciano(){
		setClasse(Marciano.getClasse());
		setHp(Marciano.getHp());
		setCp(Marciano.getCp());
	}
	
	public void setAtributosVenusiano(){
		setClasse(Venusiano.getClasse());
		setHp(Venusiano.getHp());
		setCp(Venusiano.getCp());
	}
	
	public void setAtributosVampiro(){
		setClasse(Vampiro.getClasse());
		setHp(Vampiro.getHp());
		setCp(Vampiro.getCp());
	}
	
	public void setAtributosElfo(){
		setClasse(Elfo.getClasse());
		setHp(Elfo.getHp());
		setCp(Elfo.getCp());
	}
	
	public void setAtributosZumbi(){
		setClasse(Zumbi.getClasse());
		setHp(Zumbi.getHp());
		setCp(Zumbi.getCp());
	}
	
	public BufferedImage getSprite(){
		return baixoSprites[0]; 
	}
	public int getHpAtual() {
		return hpAtual;
	}
	public void setHpAtual(int hpAtual) {
		this.hpAtual = hpAtual;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getCp() {
		return cp;
	}
	public void setCp(int cp) {
		this.cp = cp;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	public void setArmamento(Armamento a){
		armamento = a;
	}
	
	public Armamento getArmamento(){ return armamento; }

	public boolean isLutou() {
		return lutou;
	}

	public void setLutou(boolean lutou) {
		this.lutou = lutou;
	}

}
