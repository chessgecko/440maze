package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import problems.P1;

public class BreadthFirst {
	Queue<MyPoint> Frontier;
	int[][] maze;
	Point startPoint;
	Point endPoint;
	public int expanded = 0;
	public int pathCost = 0;
	ArrayList<Point>[][] path;
	public ArrayList<Point> finalPath;
	ArrayList<Point> looked;

	public BreadthFirst(int[][] m){
		maze = m;
		for(int i = 0; i< maze.length;i++){
			for(int j = 0; j < maze[0].length; j++){
				if(maze[i][j] == P1.MK){
					startPoint = new Point(i, j);
				} else if(maze[i][j] == P1.FK){
					endPoint = new Point(i,j);
				}
			}
		}
		//		System.out.println("maze[0].length: " + maze[0].length);
	}

	@SuppressWarnings("unchecked")
	public void look(){
		path = new ArrayList[maze.length][maze[0].length];
		
		Frontier = new LinkedList<MyPoint>();

		Frontier.add(new MyPoint(startPoint.x, startPoint.y, null));
		iterate();
//		while(Frontier.size() > 0)
//			iterate();
	}

	@SuppressWarnings("unchecked")
	private void iterate(){
		MyPoint p =(MyPoint) Frontier.remove();
		if(p.prev == null){ 
			path[p.x][p.y] = new ArrayList<Point>();
			path[p.x][p.y].add(p);
		} else {
			path[p.x][p.y] = (ArrayList<Point>) path[p.prev.x][p.prev.y].clone();
			path[p.x][p.y].add(p);
		}
		
		if(finalPath != null){
			return;
		}
		expanded++;

		if(maze[p.x][p.y] == P1.FK){
			if(finalPath == null){
				finalPath = (ArrayList<Point>) path[p.x][p.y].clone();
				pathCost = path[p.x][p.y].size();
				return;
			} else {
				if(finalPath.size() > path[p.x][p.y].size()){
					finalPath = (ArrayList<Point>) path[p.x][p.y].clone();
					pathCost = path[p.x][p.y].size();
					return;
				}else {
					return;
				}
			}
			
		}

		addPoints(p);
		if(Frontier.size() == 0){
			return;
		}
		iterate();
	}

	private void addPoints(MyPoint p){
		if(insideMazeAndValid(p.x+1, p.y, p)){
			Frontier.add(new MyPoint(p.x+1, p.y, p));
		}
		if(insideMazeAndValid(p.x-1, p.y, p)){
			Frontier.add(new MyPoint(p.x-1, p.y, p));
		}
		if(insideMazeAndValid(p.x, p.y+1, p)){
			Frontier.add(new MyPoint(p.x, p.y+1, p));
		}
		if(insideMazeAndValid(p.x, p.y-1, p)){
			Frontier.add(new MyPoint(p.x, p.y-1, p));
		}
		return;
	}
	
	private boolean insideMazeAndValid(int x, int y, Point From){
		Point thePoint = new Point(x,y);
		//inside maze
		boolean b = x < maze.length && x >= 0 && y < maze[0].length && y >= 0;
		if(!b){
			return false;
		}
		// can move there
		b = b && (maze[x][y] == P1.SK || maze[x][y] == P1.FK);
		if(!b){
			return false;
		}
		
		if(path[thePoint.x][thePoint.y] == null){
			return true;
		} else {
			if(path[thePoint.x][thePoint.y].size() <= path[thePoint.x][thePoint.y].size()){
				return false;
			} else {
				return true;
			}
		}
	}
}
