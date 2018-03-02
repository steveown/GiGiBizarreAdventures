package gameState;

import java.awt.Graphics2D;
import java.util.Random;

import classes.Jogador;
import hud.Hud;
import itens.Armamento;
import itens.Item;
import main.GamePanel;
import manager.GameStateManager;
import manager.Keys;
import tileMap.TileMap;

public class PlayState extends GameState {

	// Jogador
	protected static Jogador jogador;

	// Batalha
	Random rand = new Random();

	// Tilemap
	private TileMap tilemap;

	// Hud
	private Hud hud;

	// Camera position
	private int xsector;
	private int ysector;
	private int sectorSizeX;
	private int sectorSizeY;

	public int mundo;

	public PlayState(GameStateManager gsm) {
		super(gsm);
	}

	public void init() {

		tilemap = new TileMap(60);
		tilemap.loadTiles("/tileSet/tileSetTerra.png");
		tilemap.loadMap("/Map/mapaTerra.map");

		jogador = new Jogador(tilemap);

		// Initialize player
		jogador.setTilePosition(2, 2);

		// Set up camera position
		sectorSizeX = GamePanel.WIDTH;
		sectorSizeY = GamePanel.HEIGHT;
		xsector = jogador.getx() / sectorSizeX;
		ysector = jogador.gety() / sectorSizeY;
		tilemap.setPositionImmediately(-xsector * sectorSizeX, -ysector * sectorSizeY);

		// Load hud
		hud = new Hud(jogador);

		jogador.itens.add(new Item(rand.nextInt(6)));
		jogador.itens.add(new Item(rand.nextInt(6)));
		jogador.itens.add(new Item(rand.nextInt(6)));

		jogador.setArmamento(new Armamento(rand.nextInt(6)));

		mundo = 1;

	}

	public void update() {
		handleInput();

		// Update camera
		xsector = jogador.getx() / sectorSizeX;
		ysector = jogador.gety() / sectorSizeY;

		tilemap.setPosition(-xsector * sectorSizeX, -ysector * sectorSizeY);
		tilemap.update();

		if(tilemap.isMoving()) return;

		jogador.update();

		hud.update();

		System.out.println(jogador.getx());
		System.out.println(jogador.gety());

		if(jogador.getHpAtual() > 0)
			gsm.setMorreu(false);

		if(gsm.getMorreu()){
			gsm.setDado(true);
		}

		if(jogador.isMontaria())
			jogador.setVelMovimento(15);

		if(jogador.getMundo() == 1){
			if(mundo == jogador.getMundo()){
				gsm.setPassar(true);
				jogador.setLutou(false);
				mundo++;
				tilemap.loadTiles("/tileSet/tileSetVenus.png");
				tilemap.loadMap("/Map/mapaVenus.map");
				jogador.setTilePosition(3, 3);
				xsector = jogador.getx() / sectorSizeX;
				ysector = jogador.gety() / sectorSizeY;
				tilemap.setPositionImmediately(-xsector * sectorSizeX, -ysector * sectorSizeY);
				jogador.setMoving(false);
			}
		}

		else if(jogador.getMundo() == 2){
			if(mundo == jogador.getMundo()){
				gsm.setPassar(true);
				jogador.setLutou(false);
				mundo++;
				tilemap.loadTiles("/tileSet/tileSetPolux.png");
				tilemap.loadMap("/Map/mapaPolux.map");
				jogador.setTilePosition(2, 2);
				xsector = jogador.getx() / sectorSizeX;
				ysector = jogador.gety() / sectorSizeY;
				tilemap.setPositionImmediately(-xsector * sectorSizeX, -ysector * sectorSizeY);
			}
		}

		if((jogador.getx() == 2190 && jogador.gety() == 510 && !jogador.isLutou()) && mundo == 1 || ((jogador.getx() == 1890 && jogador.gety() == 2910 && !jogador.isLutou() && mundo == 2))){
			gsm.setBattle(true);
		}
		if((jogador.getx() == 2190 && jogador.gety() == 510 && !jogador.isLutou()) && mundo == 3)
			gsm.setBattle(true);
	}

	public void draw(Graphics2D g) {

		// Draw tilemap
		tilemap.draw(g);

		// Draw player
		jogador.draw(g);

		// Draw hud
		hud.draw(g);


	}

	public void handleInput() {

		if(Keys.isPressed(Keys.ESC))
			gsm.setPaused(true);
		if(Keys.isDown(Keys.ESQUERDA)) jogador.setEsquerda();
		if(Keys.isDown(Keys.BAIXO)) jogador.setBaixo();
		if(Keys.isDown(Keys.DIREITA)) jogador.setDireita();
		if(Keys.isDown(Keys.CIMA)) jogador.setCima();
		if(Keys.anyKeyDown() && !(Keys.isDown(Keys.Z) || Keys.isDown(Keys.ESC))){
			if(tilemap.isMoving()){}

			else{
				if(rand.nextInt(100) < 2)
					gsm.setBattle(true);
				if(jogador.getClasse() != "Vampiro")
					if(rand.nextInt(3) < 2)
						jogador.setMontaria(true);
				if(rand.nextInt(500) < 2){

					jogador.setArmamento(new Armamento(rand.nextInt(6)));
					
				}

			}

		}

	}

	public int getMundo() {
		return mundo;
	}

}
