package controller;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

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
import eea.engine.event.OREvent;
import eea.engine.event.basicevents.KeyPressedEvent;
import eea.engine.event.basicevents.LeavingScreenEvent;
import eea.engine.event.basicevents.LoopEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;
import model.Coord;
import model.Lander;

/**
 * Translates input signal into the proper functions and their parameters.
 *
 */
public class InputManager extends Entity {
	
	private int state_ID;
	private StateBasedEntityManager entMan;
	private Lander lander;

	public InputManager(String entity_ID, StateBasedEntityManager new_entMan, int new_state_ID, Lander land) {
		super(entity_ID);
		state_ID = new_state_ID;
		entMan = new_entMan;
		lander = land;
		
		addLanderControlls(lander);
		//entMan.addEntity(state_ID, lander); //Can we need to register the Entitys before adding components?
		
		System.out.println("Input Manager Constructor call finished");
	}
	
	
	
	private void addLanderControlls(Lander lander) {
		
		LoopEvent input_loop = new LoopEvent();
		Input input = new Input( 800 ); // int shouldbe height of the screen
		
    	Action input_action = new Action() {
    		
    		@Override
    		public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
    			
    			if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W) ) {
    				System.out.println("Main thruster activated");
    				lander.mainThrust();
    			}
    			
    			if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S) ) {
    				lander.downThrust();
    			}
    			
    			if ( ( input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A) ) &&
    				!( input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D) ) ) {
    				lander.turnLeft();
    			}
  
    			if ( ( input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D) ) &&
        			!( input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A) ) ) {
        			lander.turnRight();
        		}
    		} // end of update
    	};
    			
    	input_loop.addAction(input_action);
    	lander.addComponent(input_loop);
	} // end of addLanderControlls
	
	
	
	private void addLanderMovement(Lander lander) {
		
		LoopEvent movement_loop = new LoopEvent();
		
    	Action movement_action = new Action() {
    		
    		@Override
    		public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
    			if (lander.velocity.x < 0 ) {
    				
    			}
    			
    			

    		} // end of update
    	};
    			
    	movement_loop.addAction(movement_action);
    	lander.addComponent(movement_loop);
	} // end of addLanderControlls

	
	/*
	private void addWaterDropMechanic() {
		
		Entity mouse_Clicked_Listener = new Entity("Mouse_Clicked_Listener");
		MouseClickedEvent mouse_Clicked = new MouseClickedEvent();	
		
		mouse_Clicked.addAction(new Action() {
			
			@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
				
				System.out.println("update of Mouseclicked Action called");
				
				// Wassertropfen wird erzeugt
				Entity drop = new Entity("drop of water");
				drop.setPosition(new Vector2f(gc.getInput().getMouseX(),gc.getInput().getMouseY()));
				
				try {
					// Bild laden und zuweisen
					drop.addComponent(new ImageRenderComponent(new Image("assets/drop.png")));
				} catch (SlickException e) {
					System.err.println("Cannot find file assets/drop.png!");
					e.printStackTrace();
				}
				
				// Wassertropfen faellt nach unten
				LoopEvent loop = new LoopEvent();
		    	loop.addAction(new MoveDownAction(0.5f));
		    	drop.addComponent(loop);
		    	
		    	// Wenn der Bildschirm verlassen wird, dann ...
		    	LeavingScreenEvent lse = new LeavingScreenEvent();
		    	
		    	// ... zerstoere den Wassertropfen
		    	lse.addAction(new DestroyEntityAction());
		    	// ... und wechsle ins Hauptmenue
		    	lse.addAction(new ChangeStateAction(Launch.MAINMENU_STATE));
		    	
		    	drop.addComponent(lse);
		    	entMan.addEntity(state_ID, drop);
			}    		
		});
	
	mouse_Clicked_Listener.addComponent(mouse_Clicked);
	entMan.addEntity(state_ID, mouse_Clicked_Listener); 
	
	System.out.println("Waterdrops Called");
		
	} // end of WaterDrops  // */

} //end of class
