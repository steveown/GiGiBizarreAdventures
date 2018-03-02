package main;

import java.awt.Image;

import javax.swing.JFrame;

import manager.Content;

public class Main {
	
	public static void main(String[] args) {

		Image i;
		i = Content.loadGif("/menu/icone.png");
            
		JFrame janela = new JFrame("Gigi Bizarre Adventures");
		janela.setIconImage(i);
		janela.add(new GamePanel());
		janela.setResizable(false);
		janela.pack();
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	
}
