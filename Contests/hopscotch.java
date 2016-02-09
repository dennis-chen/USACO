/*
ID: dennis.8
LANG: JAVA
TASK: hopscotch
*/
import java.io.*;
import java.util.*;

class hopscotch {

  static int modVal = 1000000007;
  static int R;
  static int C;
  static int K;
  static int[][] tiles;
  static long[][] ways;

  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("hopscotch.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hopscotch.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    tiles = new int[R][C];
    ways = new long[R][C];
    for(int i = 0; i < R; i++){
    	st = new StringTokenizer(f.readLine());
	for(int j = 0; j < C; j++){
	    tiles[i][j] = Integer.parseInt(st.nextToken());
	}
    }
    ways[0][0] = 1;
    countWays();
    System.out.println(ways[R-1][C-1]);
    out.println(ways[R-1][C-1]);
    out.close();
  }

  public static void countWays(){
    //0 ways to get to anything in row or col 0
    for(int row = 1; row < R; row++){
        for(int col = 1; col < C; col++){
            long numWays = 0;
            for(int i = 0; i < row; i++){
                for(int j = 0; j < col; j++){
                    if(tiles[i][j] != tiles[row][col]){
                        numWays += ways[i][j];
                    }
                }
            }
            ways[row][col] = numWays % modVal;
        }
    }
  }

}
