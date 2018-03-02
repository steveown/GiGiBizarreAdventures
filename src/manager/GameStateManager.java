package manager;

import java.awt.Graphics2D;

import gameState.BattleState;
import gameState.DadoState;
import gameState.GameOverState;
import gameState.GameState;
import gameState.IntroState;
import gameState.ItemState;
import gameState.MenuState;
import gameState.NewGameState;
import gameState.PassarState;
import gameState.PauseState;
import gameState.PlayState;

public class GameStateManager {

	private boolean passar;
	private boolean morreu;
	private boolean paused;
	private boolean battle;
	private boolean item;
	private boolean dado;
	private PassarState passarState;
	private PauseState pauseState;
	private BattleState battleState;
	private ItemState itemState;
	private DadoState dadoState;
	
	private boolean consumiu;
	
	private GameState[] gameStates;
	private int currentState;
	private int previousState;
	
	public static final int NUM_STATES = 5;
	public static final int INTRO = 0;
	public static final int MENU = 1;
	public static final int NEWGAME = 2;
	public static final int PLAY = 3;
	public static final int GAMEOVER = 4;
	
	public GameStateManager(){
		
		passar = false;
		passarState = new PassarState(this);
		morreu = false;
		dadoState = new DadoState(this);
		item = false;
		itemState = new ItemState(this);
		consumiu = false;
		battle = false;
		battleState = new BattleState(this);
		paused = false;
		pauseState = new PauseState(this);
		gameStates = new GameState[NUM_STATES];
		
		setState(INTRO);
	}
	
	public void setState(int i){
            
		previousState = currentState;
		unloadState(previousState);
		currentState = i;
		
		if(i == INTRO){
			gameStates[i] = new IntroState(this);
			gameStates[i].init();
		}
		else if(i == MENU){
			gameStates[i] = new MenuState(this);
			gameStates[i].init();
		}
		else if (i == NEWGAME){
			gameStates[i] = new NewGameState(this);
			gameStates[i].init();
		}
		else if(i == PLAY) {
            gameStates[i] = new PlayState(this);
            gameStates[i].init();
        }
		else if(i == GAMEOVER){
			gameStates[i] = new GameOverState(this);
			gameStates[i].init();
		}
		
	}
	
	public void unloadState(int i){
		gameStates[i] = null;
	}
	
	public void setPaused(boolean b){
		paused = b;
		if(b) pauseState.init();
	}
	
	public void setBattle(boolean b){
		battle = b;
		if(b) battleState.init();
	}
	
	public void setItem(boolean b){
		item = b;
		if(b) itemState.init();
	}
	
	public void setConsumiu(boolean b){
		consumiu = b;
	}
	
	public boolean isConsumiu() {
		return consumiu;
	}
	
	public void setMorreu(boolean b){
		morreu = b;
	}
	
	public boolean getMorreu(){ return morreu; }
	
	public void setDado(boolean b){
		dado = b;
		System.out.println(dado);
		if(b) dadoState.init();
	}
	
	public void setPassar(boolean b){
		passar = b;
		if(b) passarState.init();
	}
	
	public boolean isDado(){ return dado; }

	public void update(){
		if(paused) pauseState.update();
		else if(item) itemState.update();
		else if(battle) battleState.update();
		else if(dado) dadoState.update();
		else if(passar) passarState.update();
		else if(gameStates[currentState] != null)
			gameStates[currentState].update();
	}
	
	public void draw(Graphics2D g) {
        if (paused) pauseState.draw(g);
        else if (battle) battleState.draw(g);
        else if(passar) passarState.draw(g);
        else if (gameStates[currentState] != null)
            gameStates[currentState].draw(g);
        if(item) itemState.draw(g);
        if (dado) dadoState.draw(g);

    }

	
}
