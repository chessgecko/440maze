package algorithms;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import problems.P1;

public class ASF {
	ArrayList<MyPoint> Frontier;
	int[][] maze;
	Point startPoint;
	Point endPoint;
	public int expanded = 0;
	public int pathCost = 0;
	ArrayList<Point>[][] path;
	public ArrayList<Point> finalPath;
	ArrayList<Point> looked;

	public ASF(int[][] m){
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

		Frontier = new ArrayList<MyPoint>();

		Frontier.add(new MyPoint(startPoint.x, startPoint.y, null));
		iterate();
		//		while(Frontier.size() > 0)
		//			iterate();
	}

	@SuppressWarnings("unchecked")
	private void iterate(){
		if(finalPath != null){
			return;
		}
		int bestCost = Integer.MAX_VALUE;
		MyPoint bPoint = null;
		if(Frontier.size() > 1){
			for(MyPoint mPoint : Frontier){
				if(mPoint.distance(endPoint) +path[mPoint.prev.x][mPoint.prev.y].size() < bestCost){
					bestCost = (int)mPoint.distance(endPoint) +path[mPoint.prev.x][mPoint.prev.y].size();
					bPoint = mPoint;
				}
			}
		}else {
			bPoint = Frontier.get(0);
		}


		MyPoint p = bPoint;
		Frontier.remove(bPoint);
		if(p.prev == null){ 
			path[p.x][p.y] = new ArrayList<Point>();
			path[p.x][p.y].add(p);
		} else {
			path[p.x][p.y] = (ArrayList<Point>) path[p.prev.x][p.prev.y].clone();
			path[p.x][p.y].add(p);
		}

		
		expanded++;

		if(p.equals(endPoint)){
			if(finalPath == null){
				finalPath = (ArrayList<Point>) path[p.x][p.y].clone();
				pathCost = path[p.x][p.y].size()-2;
				expanded -=2;
				return;
			} else {
				if(finalPath.size() > path[p.x][p.y].size()){
					finalPath = (ArrayList<Point>) path[p.x][p.y].clone();
					pathCost = path[p.x][p.y].size() -2;
					expanded -= 2;
					return;
				}else {
					return;
				}
			}

		}

		addPoints(p);
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
		b = b && (maze[x][y] == P1.SK || maze[x][y] == P1.FK || maze[x][y] == P1.MK);
		if(!b){
			return false;
		}

		return true;
	}
}
