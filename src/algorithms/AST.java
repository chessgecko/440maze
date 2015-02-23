package algorithms;

import java.awt.Point;
import java.util.ArrayList;

import problems.P1;

public class AST {
	ArrayList<MyFPoint> Frontier;
	int[][] maze;
	Point startPoint;
	public int expanded = 0;
	public int pathCost = 0;
	ArrayList<Point>[][] path;
	public ArrayList<Point> finalPath;
	ArrayList<Point> looked;

	int startingFood = 0;
	int emptySize = 0;

	public AST(int[][] m){
		maze = m;
		for(int i = 0; i< maze.length;i++){
			for(int j = 0; j < maze[0].length; j++){
				if(maze[i][j] == P1.MK){
					startPoint = new Point(i, j);
					maze[i][j] = P1.SK;
				} else if(maze[i][j] == P1.FK){
					startingFood++;
				} 
				if(maze[i][j] != P1.WK){
					emptySize++;
				}
			}
		}
		//		System.out.println("maze[0].length: " + maze[0].length);
	}

	public void look(){
		Frontier = new ArrayList<MyFPoint>();
		ArrayList<Point> f = new ArrayList<Point>();
		f.add(startPoint);
		Frontier.add(new MyFPoint(startPoint.x, startPoint.y, maze.clone(), startingFood, f));
		int i = 0;
		while(true){
			int bestCost = Integer.MAX_VALUE;
			MyFPoint bPoint = null;
			//System.out.println();
			if(Frontier.size() > 1){
				for(MyFPoint mPoint : Frontier){
					int val = (mPoint.heuristic)+mPoint.foodLeft+ mPoint.thePoints.size();
					if(val < bestCost){
						bestCost = val;
						bPoint = mPoint;
					}
				}
			}else if(Frontier.size() == 1){
				bPoint = Frontier.get(0);
			} else {
				break;
			}
			
//			printMaze(bPoint.myMaze);
			
			MyFPoint p = bPoint;
			Frontier.remove(bPoint);
			expanded++;

			if(p.foodLeft == 0){
				finalPath = p.thePoints;
				pathCost = p.thePoints.size()-2;
				expanded -=2;
				break;
			}
			i++;
			if(i%20 == 3){
				System.out.println("frontier size: " + Frontier.size() + " food left: " + bPoint.foodLeft);
			}

			addPoints(p);
		}
	}

	private void addPoints(MyFPoint p){		
		checkBreadth(p);
		return;
	}
	
	@SuppressWarnings("unchecked")
	private void checkBreadth(MyFPoint p){
		boolean j = p.found;
		while(j){
			int[][] newMaze = new int[maze.length][maze[0].length];
			for(int i = 0; i< maze.length; i++){
				newMaze[i] = p.myMaze[i].clone();
			}
			
			newMaze[p.ePoint.x][p.ePoint.y] = P1.SK;
			
			ArrayList<Point> d = (ArrayList<Point>) p.thePoints.clone();
			d.remove(d.size()-1);
			d.addAll(p.toPath);
			MyFPoint temp = new MyFPoint(p.ePoint.x, p.ePoint.y, newMaze, p.foodLeft-1, d);
			Frontier.add(temp);
			
			j = p.calcHeuristic3();
		}
	}
}
