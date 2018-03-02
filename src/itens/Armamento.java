package itens;

import java.awt.image.BufferedImage;

import manager.Content;

public class Armamento {

	private String nome;
	private int hp;
	private int cp;
	private BufferedImage sprite;

	private final int ADAGA_DE_PRATA_PLUTONIANA = 0;
	private final int ARMA_LASER = 1;
	private final int KATANA = 2;
	private final int TIARA_DA_MULHER_MARAVILHA = 3;
	private final int EXTRAORDINARIA_TUNICA_DE_SONHOS_TECNICOLOR = 4;
	private final int ESPADA_FLAMEJANTE_COM_CHEIRO_DE_CHICLETE = 5;

	public Armamento(int i) {

		if (i == ADAGA_DE_PRATA_PLUTONIANA) {
			nome = "Adaga de Prata Plutoniana";
			hp = 0;
			cp = 0;
			sprite = Content.ADAGADEPRATA[0][0];
		}

		else if (i == ARMA_LASER) {
			nome = "Arma Laser";
			hp = 0;
			cp = 5;
			sprite = Content.ARMALASER[0][0];
		}
		
		else if (i == KATANA) {
			nome = "Katana";
			hp = 0;
			cp = 30;
			sprite = Content.KATANA[0][0];
		}
		
		else if (i == EXTRAORDINARIA_TUNICA_DE_SONHOS_TECNICOLOR) {
			nome = "Extraordinaria Tunida de Sonhos Tecnicolor";
			hp = 1000;
			cp = 0;
			sprite = Content.TUNICA[0][0];
		}
		
		else if (i == TIARA_DA_MULHER_MARAVILHA) {
			nome = "Tiara da Mulher Maravilha";
			hp = 30;
			cp = 10;
			sprite = Content.MULHERMARAVILHA[0][0];
		}
		
		else if (i == ESPADA_FLAMEJANTE_COM_CHEIRO_DE_CHICLETE) {
			nome = "Espada Flamejante com cheiro de chiclete";
			hp = 0;
			cp = 8;
			sprite = Content.ESPADAFLAMEJANTE[0][0];
		}

		
	}

	public String getNome() {
		return nome;
	}

	public int getHp() {
		return hp;
	}

	public int getCp() {
		return cp;
	}

	public BufferedImage getSprite() {
		return sprite;
	}
}
