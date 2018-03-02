package manager;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Content {

	public static Image DADO1 = loadGif("/dadoAnimacao/dado1.gif");
	public static Image DADO2 = loadGif("/dadoAnimacao/dado2.gif");
	public static Image DADO3 = loadGif("/dadoAnimacao/dado3.gif");
	public static Image DADO4 = loadGif("/dadoAnimacao/dado4.gif");
	public static Image DADO5 = loadGif("/dadoAnimacao/dado5.gif");
	public static Image DADO6 = loadGif("/dadoAnimacao/dado6.gif");
	public static Image GIGINASESTRELAS = loadGif("/menu/gigigif.gif");
	public static Image BACKGROUNDMENU = loadGif("/menu/background_menu.gif");
	public static Image BACKGROUNDBATALHA = loadGif("/menu/tela_completa.gif");
	public static Image BACKGROUNBATALHAPOLUX = loadGif("/menu/tela_completa_polux.gif");
	public static BufferedImage[][] LOGO = load("/menu/gigi2.png", 2000, 1260);
	public static BufferedImage[][] QUADRADOITEM = load("/hudImage/quadrado.png", 426, 320);
	public static BufferedImage[][] BACKGROUNDNEWGAME = load("/menu/background_new_game.png", 800, 600);
	public static BufferedImage[][] HUMANO = load("/sprites/humano.png", 60, 95);
	public static BufferedImage[][] ZUMBI = load("/sprites/zombie.png", 60, 95);
	public static BufferedImage[][] MARCIANO = load("/sprites/marciano.png", 60, 95);
	public static BufferedImage[][] VENUSIANO = load("/sprites/venusiano.png", 60, 95);
	public static BufferedImage[][] ELFO = load("/sprites/elfo.png", 60, 95);
	public static BufferedImage[][] VAMPIRO = load("/sprites/vampiro.png", 60, 95);
	public static BufferedImage[][] POLITIKOAK = load("/spriteInimigos/politico.png", 60, 110);
	public static BufferedImage[][] DRAGONITE = load("/spriteInimigos/dragonite.png", 115, 110);
	public static BufferedImage[][] BESTATRAAL = load("/spriteInimigos/mike_fase_1.png", 180, 192);
	public static BufferedImage[][] BESTATRAALV2 = load("/spriteInimigos/mike_fase_2.png", 124, 84);
	public static BufferedImage[][] font = load("/hudImage/font.gif", 24, 24);
	public static BufferedImage[][] ZOMBIEHAND = load("/menu/icon_hand.png", 70, 43);
	public static BufferedImage[][] HUD = load("/hudImage/hud.png", 250, 63);
	public static BufferedImage[][] HUDBAR = load("/hudImage/hudBar.png", 250, 63);
	public static BufferedImage[][] HUDINIMIGO = load("/hudImage/hud2.png", 250, 63);
	public static BufferedImage[][] HUDBARINIMIGO = load("/hudImage/hudbar2.png", 250, 63);
	public static BufferedImage[][] BACONLUNAR = load("/spriteItens/baconlunar.png", 60, 23);
	public static BufferedImage[][] BACON = load("/spriteItens/bacon.png", 60, 23);
	public static BufferedImage[][] BARRAENERGIA = load("/spriteItens/barrinha.png", 60, 22);
	public static BufferedImage[][] BRIGADEIRO = load("/spriteItens/brigadeiro.png", 63, 60);
	public static BufferedImage[][] DINAMITE = load("/spriteItens/dinamite.png", 49, 60);
	public static BufferedImage[][] DINAMITEPANGA = load("/spriteItens/dinamite_panga.png", 54, 60);
	public static BufferedImage[][] HUMANOMONTARIA = load("/montarias/mont3.png", 110, 132);
	public static BufferedImage[][] ELFOMONTARIA = load("/montarias/mont4.png", 110, 132);
	public static BufferedImage[][] MARCIANOMONTARIA = load("/montarias/mont2.png", 110, 132);
	public static BufferedImage[][] VENUSIANOMONTARIA = load("/montarias/mont1.png", 110, 132);
	public static BufferedImage[][] ZUMBIMONTARIA = load("/montarias/mont5.png", 110, 132);
	public static BufferedImage[][] ESPADAFLAMEJANTE = load("/spriteItens/espadaflame.png", 67, 141);
	public static BufferedImage[][] ADAGADEPRATA = load("/spriteItens/itens.png", 63, 60);
	public static BufferedImage[][] KATANA = load("/spriteItens/katana.png", 63, 96);
	public static BufferedImage[][] MULHERMARAVILHA = load("/spriteItens/mulhermara.png", 67, 49);
	public static BufferedImage[][] TUNICA = load("/spriteItens/tunica.png", 67, 49);
	public static BufferedImage[][] ARMALASER = load("/spriteItens/Armalaser.png", 60, 45);
	
	public static Image loadGif(String s){
		try{
			return new ImageIcon(Content.class.getResource(s)).getImage();
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Erro: Falha da leitura gr�fica (GIF).");
		}
		return null;
	}
	
	private static BufferedImage[][] load(String s, int w, int h) {
		BufferedImage[][] ret;
		try{
			BufferedImage spritesheet = ImageIO.read(Content.class.getResourceAsStream(s));
			int widht = spritesheet.getWidth() / w;
			int height = spritesheet.getHeight() / h;
			ret = new BufferedImage[height][widht];
			for(int i = 0; i < height; i++)
				for(int j = 0; j < widht; j++)
					ret[i][j] = spritesheet.getSubimage(j * w, i * h, w, h);
			return ret;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error: falha na leitura grafica (PNG).");
			System.exit(0);
		}
		return null;
	}

	public static void drawString(Graphics2D g, String s, int x, int y){
		s = s.toUpperCase();;
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if(c == 47) c = 36; // barra
			if(c == 58) c = 37; // ponto e virgula
			if(c == 32) c = 38; // espa�o
			if(c >= 65 && c <= 90) c -= 65; // letras
			if(c >= 48 && c <= 57) c -= 22; //numeros
			int row = c / font[0].length;
			int col = c % font[0].length;
			g.drawImage(font[row][col], x + 22 * i, y, null);
		}
	}
	
}
