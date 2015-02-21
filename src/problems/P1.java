package problems;

import java.io.*;
import java.util.*;

public class P1 {

	final static int SK = 0; //space key
	final static int WK = 1; //wall key
	final static int FK = 2; //food key
	final static int MK = 3; //my key
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		BufferedReader f = new BufferedReader(new FileReader("maze.txt"));
		ArrayList<String> lines = new ArrayList<String>();

		for(int i = 0; true; i++){
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
	}

}
