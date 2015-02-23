package algorithms;

import java.awt.*;
import java.util.*;

import problems.*;

public class DepthFirst {

	Stack<Point> Frontier;
	int[][] maze;
	Point startPoint;
	Point endPoint;
	public int expanded = 0;
	public int pathCost = 0;
	ArrayList<Point> path;
	public ArrayList<Point> finalPath;
	ArrayList<Point> looked;

	public DepthFirst(int[][] m){
		maze = m;
		for(int i = 0; i< maze.length;i++){
			for(int j = 0; j < maze[0].length; j++){
				if(maze[i][j] == P1.MK){
					startPoint = new Point(i, j);
				} else if(maze[i][j] == P1.FK){
					System.out.println("here");
					endPoint = new Point(i,j);
				}
			}
		}
		//		System.out.println("maze[0].length: " + maze[0].length);
	}

	public void look(){
		path = new ArrayList<Point>();
		Frontier = new Stack<Point>();
		looked = new ArrayList<Point>();

		Frontier.push(startPoint);
		while(Frontier.size() > 0)
			iterate();
	}

	@SuppressWarnings("unchecked")
	private void iterate(){
		Point p = (Point) Frontier.pop();
		path.add(p);
		looked.add(p);
		if(finalPath != null){
			return;
		}
		expanded++;

		if(p.equals(endPoint)){
			finalPath = (ArrayList<Point>) path.clone();
			pathCost = path.size();
		}

		addPoints(p);
		path.remove(p);
	}

	private void addPoints(Point p){
		if(insideMazeAndValid(p.x+1, p.y)){
			Frontier.push(new Point(p.x+1, p.y));
			iterate();
		}
		if(insideMazeAndValid(p.x-1, p.y)){
			Frontier.push(new Point(p.x-1, p.y));
			iterate();
		}
		if(insideMazeAndValid(p.x, p.y+1)){
			Frontier.push(new Point(p.x, p.y+1));
			iterate();
		}
		if(insideMazeAndValid(p.x, p.y-1)){
			Frontier.push(new Point(p.x, p.y-1));
			iterate();
		}
		return;
	}
	
	private boolean insideMazeAndValid(int x, int y){
		Point thePoint = new Point(x,y);
		//inside maze
		boolean b = x < maze.length && x >= 0 && y < maze[0].length && y >= 0;
		if(!b){
			return b;
		}
		// can move there
		b = b && (maze[x][y] == P1.SK || maze[x][y] == P1.FK);
		if(!b){
			return b;
		}
		//dont add if already visited
		for(Point p: looked){
			if(p.equals(thePoint)){
				return false;
			}
		}
		return true;
	}
}
