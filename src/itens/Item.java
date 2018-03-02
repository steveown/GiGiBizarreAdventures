package itens;

import java.awt.image.BufferedImage;
import java.util.Random;

import manager.Content;

public class Item {
	
	private String nome;
	private String[] descricao = new String[6];
	private int hp;
	private int cp;
	private BufferedImage sprite;
	
	private final int BACONLUNAR = 0;
	private final int BACONTERRAQUEO = 1;
	private final int BARRAENERGIA = 2;
	private final int BRIGADEIRO = 3;
	private final int DINAMITE = 4;
	private final int DRINK = 5;
	
	public Item(int i){
	
		Random rand = new Random();
		
		if(i == BACONLUNAR){
			nome = "Bacon Lunar";
			descricao[0] ="Não tão bom quanto o terráqueo,";
			descricao[1] ="só tem esse nome por questões";
			descricao[2] ="de marca registrada.";
			descricao[3] ="Restaura 4 de HP.";
			descricao[4] ="";
			descricao[5] ="";			
			hp = 4;
			cp = 0;
			sprite = Content.BACONLUNAR[0][0];
		}
		
		else if(i == BACONTERRAQUEO){
			nome = "Bacon Terraqueo";
			descricao[0] ="Feito com porcos mortos a meia";
			descricao[1] ="noite de dias primos.";
			descricao[2] ="Restaura 10 de HP";
			descricao[3] ="";
			descricao[4] ="";
			descricao[5] ="";
			hp = 10;
			cp = 0;
			sprite = Content.BACON[0][0];
		}
		
		else if(i == BARRAENERGIA){
			nome = "Barras de energia Poluxianas";
			descricao[0] ="Restaura 7 de CP.";
			descricao[1] ="";
			descricao[2] ="";
			descricao[3] ="";
			descricao[4] ="";
			descricao[5] ="";
			hp = 0;
			cp = 7;
			sprite = Content.BARRAENERGIA[0][0];
		}
		
		else if(i == BRIGADEIRO){
			nome = "Brigadeiro Magico";
			descricao[0] ="Brigadeiro feito por elfos.";
			descricao[1] ="Restaura 30 de HP";
			descricao[2] ="";
			descricao[3] ="";
			descricao[4] ="";
			descricao[5] ="";
			hp = 30;
			cp = 0;
			sprite = Content.BRIGADEIRO[0][0];
		}
		
		else if(i == DINAMITE){
			nome = "Dinamite Pangalática";
			descricao[0] ="O efeito de beber uma dessas é";
			descricao[1] ="como ter  seu cérebro esmagado";
			descricao[2] ="por uma fatia de limão colocada";
			descricao[3] ="nem volta de uma grande barra";
			descricao[4] ="de ouro.";
			descricao[5] ="Restaura 30 de HP e 17 de CP.";
			hp = 30;
			cp = 17;
			sprite = Content.DINAMITEPANGA[0][0];
		}
		
		else if(i == DRINK){
			nome = "Drink Galático";
			descricao[0] ="Drink adorado pelos marcianos,";
			descricao[1] ="pode ter sido infectado  por um";
			descricao[2] ="lote de Pataxaram infectado.";
			descricao[3] ="Restaura 4 de CP ou causa";
			descricao[4] ="50 de dano.";
			descricao[5] ="";
			
			int a = rand.nextInt(2);
			
			if(a == 1){
				hp = 0;
				cp = 4;
			}
			
			else{
				hp = -40;
				cp = 0;
			}
			
			sprite = Content.DINAMITE[0][0];
			
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

	public String[] getDescricao() {
		return descricao;
	}
	
}
