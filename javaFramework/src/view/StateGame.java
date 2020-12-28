package view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controller.InputManager;
import de.tud.gdi1.dropofwater.ui.Launch;
import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.action.basicactions.ChangeStateInitAction;
import eea.engine.action.basicactions.DestroyEntityAction;
import eea.engine.action.basicactions.MoveDownAction;
import eea.engine.action.basicactions.QuitAction;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.ANDEvent;
import eea.engine.event.basicevents.KeyPressedEvent;
import eea.engine.event.basicevents.LeavingScreenEvent;
import eea.engine.event.basicevents.LoopEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;
import model.BasicTerrain;
import model.Coord;
import model.Lander;
import model.Terrain;

public class StateGame  extends BasicGameState {
	
	private int state_ID; 						
	private StateBasedEntityManager entity_Manager; 	
	
	public StateGame( int idNum ) {
	       state_ID = idNum;
	       entity_Manager = StateBasedEntityManager.getInstance();
	}
	

	/**
	 *  Wird am Start dieser Klasse ausgeführt um das Menü aufzubauen.
	 */
	@Override
	public void init(GameContainer game_cont, StateBasedGame sb_Game) throws SlickException {
		
		// Die Erstellung der Enttäten:
		// String main_Game = "Lander Game";
    	// Entity main_Game_Entity = new Entity(main_Game);
    	
    	// Erstelle das Ausloese-Event und die zugehoerige Action
    	
    	// Bei Drücken der ESC-Taste zurueck ins Hauptmenue wechseln
    	Entity esc_Listener = new Entity("ESC_Listener");
    	KeyPressedEvent esc_pressed = new KeyPressedEvent(Input.KEY_ESCAPE);
    	esc_pressed.addAction(new ChangeStateAction(Launch.MAINMENU_STATE));
    	esc_Listener.addComponent(esc_pressed);    	
    	entity_Manager.addEntity(state_ID, esc_Listener);
    	
    	
    	// Der Lander wird erstellt
    	Lander lando = new Lander("Test_Lander", new Coord(250, 250));
    	lando.setPosition(new Vector2f(250, 250));
    	lando.addComponent(new ImageRenderComponent(new Image("/assets/lander.png"))); // Bildkomponente
    	
    	// Die Inputs für den Lander werden alle in InputManager verarbeitet
    	@SuppressWarnings("unused")
		Entity inpMan = new InputManager("inputMan", entity_Manager, state_ID, lando);
    	
    	//	Landschaft wird erstellt und geladen:
    	Terrain ter = new BasicTerrain();
    	
    	
    	// Hintergrund laden
    	Entity background = new Entity("background");	// Entitaet fuer Hintergrund
    	background.setPosition(new Vector2f(400,300));	// Startposition des Hintergrunds
    	background.addComponent(new ImageRenderComponent(new Image("/assets/background.png"))); // Bildkomponente

    	
    	// Fuege die Entitys zum StateBasedEntityManager hinzu
    	entity_Manager.addEntity(this.state_ID, background);
    	entity_Manager.addEntity(this.state_ID, lando);
    	//StateBasedEntityManager.getInstance().addEntity(state_ID, background);
	}
	
	

    /**
     * Wird vor dem Frame ausgefuehrt.
     */
    @Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		// StatedBasedEntityManager soll alle Entities aktualisieren
    	entity_Manager.updateEntities(container, game, delta);
	}
    
    /**
     * Wird mit dem Frame ausgefuehrt.
     */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// StatedBasedEntityManager soll alle Entities rendern
		entity_Manager.renderEntities(container, game, g);
	}

	/**
	 * Liefert die state ID der Klasse.
	 */
	@Override
	public int getID() {
		return state_ID;
	}
}
