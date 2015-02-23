package problems;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import algorithms.AST;
import algorithms.BreadthFirst;

public class P1 {

	public final static int SK = 0; //space key
	public final static int WK = 1; //wall key
	public final static int FK = 2; //food key
	public final static int MK = 3; //my key
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new FileReader("maze.txt"));
		ArrayList<String> lines = new ArrayList<String>();

		while(true){
			lines.add(f.readLine());
			if(lines.get(lines.size()-1).equals("end")){
				break;
			}
		}
		lines.remove(lines.size()-1);
		
		int[][] maze = new int[lines.size()][lines.get(0).length()];
		
		for(int j = 0; j<lines.size(); j++){
			for(int i = 0; i<lines.get(0).length(); i++){
				char c = lines.get(j).charAt(i);
				if(c == '%'){
					maze[j][i] = WK;
				} else if (c == 'P'){
					maze[j][i] = MK;
				} else if (c == '.'){
					maze[j][i] = FK;
				} else {
					maze[j][i] = SK;
				}
			}
		}
		
//		DepthFirst myD = new DepthFirst(maze);
//		myD.look();
//		System.out.println("Depth pathcost: " +(myD.pathCost-2));
//		System.out.println("Depth frontiers expanded: " + (myD.expanded-2));
//		
//		BreadthFirst myB = new BreadthFirst(maze);
//		myB.look();
//		System.out.println("Breadth pathcost: " +(myB.pathCost - 2));
//		System.out.println("Breadth frontiers expanded: " + (myB.expanded-2));
		
//		ASF myASF = new ASF(maze);
//		myASF.look();
//		System.out.println("AS pathcost: " +myASF.pathCost);
//		System.out.println("AS frontiers expanded: " + myASF.expanded);
//		
//		
//		Greedy myG = new Greedy(maze);
//		myG.look();
//		System.out.println("Greedy pathcost: " +myG.pathCost);
//		System.out.println("Greedy frontiers expanded: " + myG.expanded);
		
		AST myG = new AST(maze);
		myG.look();
		System.out.println("Greedy pathcost: " +myG.pathCost);
		System.out.println("Greedy frontiers expanded: " + myG.expanded);
		
		for(int i = 0; i<myG.finalPath.size(); i++){
			System.out.print("(" + myG.finalPath.get(i).x + "," + myG.finalPath.get(i).y + ")$");
		}
		
	}

}
