package model;

import eea.engine.entity.Entity;

/**
 * This class saves all Information about the vicinity/map of a game.
 * That means the terrain as well as wind speed, special objects, target location
 * The terrain is simuated by a funtion, 
 */
public class Vicinity {
	
	long wind = 0; //negative means from left to right, positive means from right to left
	Entity[] ppi ; //points of interest, like objects, decorations, power-ups
	Terrain terrain;
}
