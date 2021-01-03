package model;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;

public class ScreenLeaveAction implements Action {

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2, Component arg3) {
		Entity owner = arg3.getOwnerEntity();
	    if(owner.getPosition().x < 0) {
	    	owner.setPosition(new Vector2f(1200, owner.getPosition().y));
	    }
	    else if(owner.getPosition().x > 1200) {
	    	owner.setPosition(new Vector2f(0, owner.getPosition().y));
	    }
	    System.out.println("Leeeave");
		
	}

}