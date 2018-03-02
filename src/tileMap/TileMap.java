package tileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileMap {

	// Posi��es
	private int x;
	private int y;
	private int xdest;
	private int ydest;
	private int speed;
	private boolean moving;

	// Limites
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;

	// Mapa
	private int[][] mapa;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;

	// Tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	private Tile[][] tiles;

	// Drawing
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;

	public TileMap(int tileSize){
		this.tileSize = tileSize;
		numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
		numColsToDraw = GamePanel.WIDTH / tileSize + 2;
		speed = 50;
	}

	public void loadTiles(String s){
		try{
			tileset = ImageIO.read(getClass().getResourceAsStream(s));
			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Tile[6][numTilesAcross];

			BufferedImage subimage;
			for(int col = 0; col < numTilesAcross; col++){
				subimage = tileset.getSubimage(col * tileSize,
						0, tileSize, tileSize);
				tiles[0][col] = new Tile(subimage, Tile.NORMAL);

				subimage = tileset.getSubimage(col * tileSize,
						tileSize, tileSize, tileSize);
				tiles[1][col] = new Tile(subimage, Tile.BLOCKED);

				subimage = tileset.getSubimage(col * tileSize,
						tileSize * 2, tileSize, tileSize);
				tiles[2][col] = new Tile(subimage, Tile.BLOCKED);

				subimage = tileset.getSubimage(col * tileSize,
						tileSize * 3, tileSize, tileSize);
				tiles[3][col] = new Tile(subimage, Tile.PASSAR);
				
				subimage = tileset.getSubimage(col * tileSize,
						tileSize * 4, tileSize, tileSize);
				tiles[4][col] = new Tile(subimage, Tile.PASSAR);
				
				subimage = tileset.getSubimage(col * tileSize,
						tileSize * 5, tileSize, tileSize);
				tiles[5][col] = new Tile(subimage, Tile.PASSAR);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	public void loadMap(String s){
		try{

			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			mapa = new int [numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;

			xmin = GamePanel.WIDTH - width;
			xmin = -width;
			xmax = 0;
			ymin = GamePanel.HEIGHT - height;
			ymin = -height;
			ymax = 0;

			String delims = "\\s+";

			for(int row = 0; row < numRows; row++){
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for(int col = 0; col < numCols; col++)
					mapa[row][col] = Integer.parseInt(tokens[col]);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	public int getTileSize(){ return tileSize; }
	public int getx(){ return x; }
	public int gety(){ return y; };
	public int getWidth(){ return width; }
	public int getHeight(){ return height; }
	public int getNumRows(){ return numRows; }
	public int getNumCols(){ return numCols; }
	public int getType(int row, int col){
		int rc = mapa[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		return tiles[r][c].getType();
	}
	public int getIndex(int row, int col){
		return mapa[row][col];
	}
	public boolean isMoving(){ return moving; }
	public void setTile(int row, int col, int index){
		mapa[row][col] = index;
	}
	public void replace(int i1, int i2){
		for(int row = 0; row < numRows; row++)
			for(int col = 0; col < numCols; col++)
				if(mapa[row][col] == i1) mapa[row][col] = i2;
	}
	public void setPosition(int x, int y){
		xdest = x;
		ydest = y;
	}
	public void setPositionImmediately(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void fixBounds(){
		if(x < xmin) x = xmin;
		if(y < ymin) y = ymin;
		if(x > xmax) x = xmax;
		if(y > ymax) y = ymax;
	}

	public void update(){
		if(x < xdest){
			x += speed;
			if(x > xdest)
				x = xdest;
		}
		if(x > xdest) {
			x -= speed;
			if(x < xdest)
				x = xdest;
		}
		if(y < ydest) {
			y += speed;
			if(y > ydest)
				y = ydest;
		}
		if(y > ydest) {
			y -= speed;
			if(y < ydest)
				y = ydest;
		}

		fixBounds();
		colOffset = -this.x / tileSize;
		rowOffset = -this.y / tileSize;

		if(x != xdest || y != ydest) moving = true;
		else moving = false;

	}

	public void draw(Graphics2D g){

		for(int row = rowOffset; row < rowOffset + numRowsToDraw; row++){

			if(row >= numRows) break;
			
			for(int col = colOffset; col < colOffset + numColsToDraw; col++){

				if(col >= numCols) break;
				if(mapa[row][col] == 0) continue;

				int rc = mapa[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;
				
				g.drawImage(tiles[r][c].getImage(),
						x + col * tileSize,
						y + row * tileSize,
						null);
				
			}

		}

	}

}
