package model;

import org.newdawn.slick.geom.Vector2f;

import eea.engine.entity.Entity;


// https://gamedev.stackexchange.com/questions/24267/rotation-of-images-in-slick2d
// http://slick.ninjacave.com/javadoc/org/newdawn/slick/geom/Vector2f.html

// ggf https://gamedev.stackexchange.com/questions/63790/slick2d-entities-and-rendering
// TODO use Vector 2f instead of homemade stuff ?

/**
 * The player controlled Lander
 * Input is triggered via InputHandler
 */
public class Lander extends Entity {
	
	private final double rotationSpeed = 0.05;
	private final double mainThrusterSpeed = 0.25;
	private final double downThrusterSpeed = 0.25;
	
	public Coord pos; 								// Coordinates of mass center. Not Collision box
	public Vector2f velocity = new Vector2f(0,0);			// Velocity Vektor.
	
	private float rotationInDegrees = 0;			// current Rotation of the Lander
	public Vector2f rotationVector = new Vector2f(1,0);	// current Rotation of the Lander, updated according to Degrees
	
	public static final String img = "assets/lander.png";
	
	
	public Lander(String entityID, Coord new_pos) {
		super(entityID);
		pos = new_pos;
	}
	
	public void mainThrust() {
		velocity = velocity.add(rotationVector.scale( (float) mainThrusterSpeed ));
		//System.out.println("Mainthrust");
	}

	public void turnLeft() {
		rotationInDegrees += rotationSpeed;
		if (rotationInDegrees >= 360) {rotationInDegrees = 0;}
		
		this.setRotation(rotationInDegrees);
		updateRotVector();
		//System.out.println("Turn left");
	}

	public void turnRight() {
		rotationInDegrees -= rotationSpeed;
		if (rotationInDegrees < 0) {rotationInDegrees = 359;}
		
		this.setRotation(rotationInDegrees);
		updateRotVector();
		//System.out.println("Turn right");
	}

	public void downThrust() {
		velocity = velocity.sub(rotationVector.scale( (float) downThrusterSpeed) );
		//System.out.println("Downhrust");
	}
	
	private void updateRotVector() {
		double rads = Math.toRadians( rotationInDegrees );
		rotationVector.x = (float) Math.cos(rads);
		rotationVector.x = (float) Math.sin(rads);
		System.out.println("updated Rotation to: " + rads);
	}

}
