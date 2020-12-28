package model;

/**
 * Coord is a shortform for coordinates.
 * Coords are mostly used as positions inside the game, simplifying the x and y values.
 * They can be used as a vector or frame start/end too.
 */
public class Coord {
	public double x;
	public double y;
	
	public Coord(double d, double newY ) {
		x = d;
		y = newY;
	}
	
	/**
	 * returns this Coord as an easily readable String
	 */
	public String toString() {
		return "(" + x + "," + y +")";
	}
	
	/**
	 * 2D substraction.
	 * @return new Value of this Coord added with the input Coord.
	 */
	public Coord sub(Coord toSubstract) {
		return new Coord(x - toSubstract.x,   y - toSubstract.y);
	}

	/**
	 * 2D addition.
	 * @return new Value of this Coord substracted by the input Coord.
	 */
	public Coord add(Coord toAdd) {
		return new Coord(x + toAdd.x,   y + toAdd.y);
	}
	
	/**
	 * 2D scalar multiplication.
	 * @return new Value of this Coord scaled with the input.
	 */
	public Coord scale(double skalar) {
		return new Coord(x * skalar, y * skalar);
	}
	
}
