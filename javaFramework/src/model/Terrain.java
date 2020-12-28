package model;

/**
 * Functions used to simulate an uneven ground.
 * They are mainly used for collision point calculation.
 */
public abstract class Terrain {
	
	abstract boolean collision(Coord toCheck); //used to detect collisions with the terrain/function.
	
	abstract Coord[] getApproxPoints(long start, long end); //Points used to display the terrain/function.
}
