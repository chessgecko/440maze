package algorithms;

import java.awt.Point;
import java.util.ArrayList;

import problems.P1;

public class MyFPoint extends Point{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int[][] myMaze;
	int[][] myMaze2;
	int foodLeft;
	ArrayList<Point> thePoints;
	public int heuristic;
	
	//for the search
	public ArrayList<Point> toPath;
	public Point ePoint;
	public boolean found;
	
	public MyFPoint(int x, int y, int[][] myM, int fl, ArrayList<Point> ps){
		super(x,y);
		thePoints = ps;
		foodLeft = fl;
		myMaze = myM;
		myMaze2 = new int[myMaze.length][myMaze[0].length];
		for(int i = 0; i<myMaze.length; i++)
			myMaze2[i] = myMaze[i].clone();
		myMaze2[x][y] = P1.MK;
		
		calcHeuristic3();
		if(toPath == null && foodLeft == 0){
			heuristic = 0;
		}

	}
	
	@SuppressWarnings("unchecked")
	public boolean calcHeuristic3(){
		
		BreadthFirst myB = new BreadthFirst(myMaze2);
		myB.look();
		if(myB.finalPath == null && foodLeft == 0){
			return true;
		} else if(myB.finalPath == null){
			found = false;
			return false;
		}
		ePoint = (Point) myB.finalPath.get(myB.finalPath.size()-1).clone();
		toPath = (ArrayList<Point>) myB.finalPath.clone();
		myMaze2[ePoint.x][ePoint.y] = P1.WK;
		found = true;
		heuristic = toPath.size()-1;
		myB = null;
		return true;
	}
	
}
