package view;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controller.InputManager;
import de.tud.gdi1.dropofwater.ui.Launch;
import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateInitAction;
import eea.engine.action.basicactions.QuitAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;

/**
 *	Diese Klasse enthält das gesammte Hauptmenü.
 *	Hier werden die Settings für das Spiel eingestellt und das Spiel gestartet.
 */
public class StateMainMenu extends BasicGameState { //TODO what's delta from update method?
	
	private StateBasedEntityManager entity_Manager; 	// entityManager der States und Entities der library verarbeitet
	private int state_ID = 0; // notwendig für BasicGameState

	//Werte für Labels
	private int labels_start = 180;
	private int labels_abstand = 100;
	
	// Der Konstruktor für die state_ID, meldet diese Klasse im manager an.
	StateMainMenu( int new_state_ID ) {
		state_ID = new_state_ID;
	    entity_Manager = StateBasedEntityManager.getInstance();
	}
	
	/**
	 *  Wird am Start dieser Klasse ausgeführt um das menü aufzubauen
	 */
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
		//	Die erstellung der Enttäten, hauptsächlich die Knöpfe/Regler:
    	Entity new_Game_Entity = new Entity("Neues Spiel starten");
    	Entity quit_Entity = new Entity("Beenden");
    	
    	// Setze Position und Bild für die Knöpfe
    	new_Game_Entity.setPosition(new Vector2f(218, 190));
    	new_Game_Entity.setScale(0.28f);
    	new_Game_Entity.addComponent(new ImageRenderComponent(new Image("assets/entry.png")));
    	
    	quit_Entity.setPosition(new Vector2f(218, 290));
    	quit_Entity.setScale(0.28f);
    	quit_Entity.addComponent(new ImageRenderComponent(new Image("assets/entry.png")));
    	
    	// Erstelle das Ausloese-Event und die zugehoerige Action
    	
    	ANDEvent events_start = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
    	Action start_Game_Action = new ChangeStateInitAction(Launch.GAMEPLAY_STATE);
    	events_start.addAction(start_Game_Action);
    	new_Game_Entity.addComponent(events_start);
    	
    	ANDEvent mainEvents_q = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
    	Action quit_Action = new QuitAction();
    	mainEvents_q.addAction(quit_Action);
    	quit_Entity.addComponent(mainEvents_q);
    	
    	// Hintergrund laden
    	Entity background = new Entity("background");	// Entitaet fuer Hintergrund
    	background.setPosition(new Vector2f(400,300));	// Startposition des Hintergrunds
    	background.addComponent(new ImageRenderComponent(new Image("/assets/background.png"))); // Bildkomponente
    	
    	// Fuege die Entities zum StateBasedEntityManager hinzu
    	entity_Manager.addEntity(this.state_ID, background);
    	entity_Manager.addEntity(this.state_ID, new_Game_Entity);
    	entity_Manager.addEntity(this.state_ID, quit_Entity);
	}



	/**
	 * 	Wird ausgeführt um die graphische Darstellung neu zu berechnen.
	 */
	@Override
	public void render(GameContainer game_container, StateBasedGame state_based_Game, Graphics g)
			throws SlickException {

		entity_Manager.renderEntities(game_container, state_based_Game, g);
		
		int counter = 0; //zählt die Anzahl der labels für deren richtige Position.
		
		g.drawString("Neues Spiel", 110, labels_start + labels_abstand*counter); counter++;
		g.drawString("Beenden", 110, labels_start + labels_abstand*counter); counter++;
		g.drawString("Ein weiteres Label", 110, labels_start + labels_abstand*counter); counter++;
	}



	@Override
	public void update(GameContainer game_container, StateBasedGame state_based_Game, int delta)
			throws SlickException {
		entity_Manager.updateEntities(game_container, state_based_Game, delta);
	}
	
	
	
	@Override
	public int getID() {
		return state_ID;
	}
}
