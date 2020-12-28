package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The BasicTerrain is modelled by connected points.
 * This is easy to implement, but result in a somewhat angular and rough look.
 */
public class BasicTerrain extends Terrain {
	
	ArrayList<Coord> points;

	Coord calcPoint(double x) {
		Coord leftPoint = null;
		Coord rightPoint = null;
		
		//check for out of bounds left
		if ( x < points.get(0).x ) {
			//TODO out of bounds Error
		}
		
		// What two points are relevant for this coordinate?
		Iterator<Coord> it = points.iterator();
	    while ( true ) {
	    	
	    	Coord curCoord = it.next();
	    	if (curCoord.x > x) { //found the position
	    		rightPoint = curCoord;
	    		break;
	    	}
	    	
	    	leftPoint = curCoord;
	    	
	    	if ( ! it.hasNext() ) {
	    		// TODO out of bounds Error
	    	}
	    }
		
		// Calculate Terrain hight at toChecks x position
	    Coord vector = new Coord( rightPoint.x - leftPoint.x, rightPoint.y - leftPoint.y );
	    double vectorSize = (x - leftPoint.x) / vector.x;
	    Coord calculatedPoint = new Coord( leftPoint.x + vectorSize * vector.x, leftPoint.y + vectorSize * vector.y );
	    
	    return calculatedPoint;
	}
	
	
	/**
	 * @Override
	 * 
	 * @param toCheck
	 * @return
	 */
	public boolean collision(Coord toCheck) {
		Coord terrainAtCheckPoint = calcPoint(toCheck.x);
		
		if (terrainAtCheckPoint.y <= toCheck.y) {
			return true;
		} else {
			return false;
		}
	}
	
	
	Coord[] getApproxPoints(long start, long end) {
		
		ArrayList<Coord> graphPoints = new  ArrayList<Coord>();
		int endOfPoints = (int) Math.ceil(end);
		
		//All points in an even distance
		for (int i = (int) Math.ceil(start); i < Math.ceil(end) ; i++) {
			Coord newPoint = calcPoint(i);
		}
			
		graphPoints.addAll(points);
		
		return graphPoints.toArray( new Coord[graphPoints.size()] );
	}

}
