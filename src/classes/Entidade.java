package classes;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import tileMap.Tile;
import tileMap.TileMap;

public class Entidade {

	private int mundo;
	
	// Dimens�es
	protected int width;
	protected int height;
	protected int cwidth;
	protected int cheight;

	// Posi��o
	protected int x;
	protected int y;
	protected int xdest;
	protected int ydest;
	protected int rowTile;
	protected int colTile;

	// Movimento
	protected boolean moving;
	protected boolean esquerda;
	protected boolean direita;
	protected boolean cima;
	protected boolean baixo;
	protected int velMovimento;

	// Tilemap
	protected TileMap tileMap;
	protected int tileSize;
	protected int xmap;
	protected int ymap;

	// Anima��o
	protected Animation animation;
	protected int currentAnimation;

	public Entidade(TileMap tm){
		tileMap = tm;
		tileSize = tileMap.getTileSize();
		animation = new Animation();
	}

	public int getx(){ return x; }
	public int gety(){ return y; }
	public int getRow(){ return rowTile; }
	public int getCol(){ return colTile; }

	public void setPosition(int i1, int i2){
		x = i1;
		y = i2;
		xdest = x;
		ydest = y;
	}

	public void setPosicaoMapa(){
		xmap = tileMap.getx();
		ymap = tileMap.gety();
	}

	public void setTilePosition(int i1, int i2){
		y = i1 * tileSize + tileSize / 2;
		x = i2 * tileSize + tileSize / 2;
		xdest = x;
		ydest = y;
	}

	public void setEsquerda(){
		if(moving) return;
		esquerda = true;
		moving = validarProximaPosicao();
	}

	public void setDireita(){
		if(moving) return;
		direita = true;
		moving = validarProximaPosicao();
	}

	public void setCima(){
		if(moving) return;
		cima = true;
		moving = validarProximaPosicao();
	}

	public void setBaixo(){
		if(moving) return;
		baixo = true;
		moving = validarProximaPosicao();
	}

	public Rectangle getRectangle(){ return new Rectangle(x, y, cwidth, cheight); }

	public boolean intersects(Entidade o){ return getRectangle().intersects(o.getRectangle()); }

	public boolean validarProximaPosicao(){
		if(moving) return true;

		rowTile = y / tileSize;
		colTile = x / tileSize;

		if(esquerda){
			if(colTile == 0 || tileMap.getType(rowTile, colTile - 1) == Tile.BLOCKED)
				return false;
			else if (colTile == 0 || tileMap.getType(rowTile, colTile - 1) == Tile.PASSAR)
				mundo++;
			else xdest = x - tileSize;
		}

		if(direita){
			if(colTile ==  tileMap.getNumCols() || tileMap.getType(rowTile, colTile + 1) == Tile.BLOCKED)
				return false;
			else if(colTile ==  tileMap.getNumCols() || tileMap.getType(rowTile, colTile + 1) == Tile.PASSAR)
				mundo++;
			else xdest = x + tileSize;
		}

		if(cima){
			if(colTile == 0 || tileMap.getType(rowTile - 1, colTile) == Tile.BLOCKED)
				return false;
			else if(colTile == 0 || tileMap.getType(rowTile - 1, colTile) == Tile.PASSAR)
				mundo++;
			else ydest = y - tileSize;
		}

		if(baixo){
			if(colTile == tileMap.getNumRows() - 1 || tileMap.getType(rowTile + 1, colTile) == Tile.BLOCKED)
				return false;
			else if(colTile == tileMap.getNumRows() - 1 || tileMap.getType(rowTile + 1, colTile) == Tile.PASSAR)
				mundo++;
			else ydest = y + tileSize;
		}

		return true;
	}

	// Calcula o destino
	public void getProximaPosicao(){
		if(esquerda && x > xdest) x -= velMovimento;
		else esquerda = false;
		if(esquerda && x < xdest) x = xdest;

		if(direita && x < xdest) x += velMovimento;
		else direita = false;
		if(direita && x > xdest) x = xdest;

		if(cima && y > ydest) y -= velMovimento;
		else cima = false;
		if(cima && y < ydest) y = ydest;

		if(baixo && y < ydest)
			y += velMovimento;
		else baixo = false;
		if(baixo && y > ydest) y = ydest;
	}

	public void update(){
		if(moving) getProximaPosicao();

		if(x == xdest && y == ydest){
			esquerda = direita = cima = baixo = false;
			moving = false;
			rowTile = y / tileSize;
			colTile = x / tileSize;
		}

		animation.update();
	}

	public void draw(Graphics2D g){
		setPosicaoMapa();
		g.drawImage(
				animation.getImage(),
				x + xmap - width / 2,
				y + ymap - height / 2,
				null);
	}
	
	public void setVelMovimento(int i){
		velMovimento = i;
	}

	public int getMundo() {
		return mundo;
	}

	public void setMundo(int mundo) {
		this.mundo = mundo;
	}
	
	public void setMoving(boolean b){
		moving = b;
	}
	
}
