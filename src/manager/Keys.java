package manager;

import java.awt.event.KeyEvent;

public class Keys {

	public static final int NUM_KEYS = 6;
	
	public static boolean keyState[] = new boolean[NUM_KEYS];
	public static boolean prevKeyState[] = new boolean[NUM_KEYS];
	
	public static final int CIMA = 0;
	public static final int ESQUERDA = 1;
	public static final int BAIXO = 2;
	public static final int DIREITA = 3;
	public static final int Z = 4;
	public static final int ESC = 5;
	
	public static void keySet(int i, boolean b){
		if(i == KeyEvent.VK_UP) keyState[CIMA] = b;
		else if(i == KeyEvent.VK_LEFT) keyState[ESQUERDA] = b;
		else if(i == KeyEvent.VK_DOWN) keyState[BAIXO] = b;
		else if(i == KeyEvent.VK_RIGHT) keyState[DIREITA] = b;
		else if(i == KeyEvent.VK_Z) keyState[Z] = b;
		else if(i == KeyEvent.VK_ESCAPE) keyState[ESC] = b;
	}
	
	public static void update(){
		for(int i = 0; i < NUM_KEYS; i++){
			prevKeyState[i] = keyState[i];
		}
		
	}
	
	public static boolean isPressed(int i){
		return keyState[i] && !prevKeyState[i];
	}
	
	public static boolean isDown(int i){
		return keyState[i];
	}
	
	
	public static boolean anyKeyDown(){
		for(int i = 0; i < NUM_KEYS; i++)
			if(keyState[i]) return true;
		return false;
	}
	
	public static boolean anyKeyPress(){
		for(int i = 0; i < NUM_KEYS; i++)
			if(keyState[i] && !prevKeyState[i]) return true;
		return false;
	}
	
}
