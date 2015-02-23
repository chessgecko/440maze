package algorithms;

import java.awt.Point;

public class MyPoint extends Point{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MyPoint prev;
	public MyPoint(int x, int y, MyPoint p){
		super(x,y);
		prev = p;
	}

}
