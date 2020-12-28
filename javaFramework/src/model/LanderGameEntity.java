package model;

import java.awt.image.BufferedImage;

import org.newdawn.slick.Image;

import eea.engine.component.render.ImageRenderComponent;

public abstract class LanderGameEntity {
	
	public Coord pos; 								// Coordinates of mass center. Not Collision box
	public Coord rotationVector = new Coord(0,0);  // current Rotation of the Lander
	public static final String img = "assets/";
	
	//drop.addComponent(new ImageRenderComponent(new Image("assets/drop.png")));
}
