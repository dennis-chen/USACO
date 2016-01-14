/*
ID: dennis.8
LANG: JAVA
TASK: maze1
*/
import java.io.*;
import java.util.*;


class maze1 {

    public static PrintWriter out;
    public static char[][] maze;
    public static int[][] costs;
    public static boolean[][] visited;
    public static boolean[][] added;
    public static int maxDist;
    public static int W;
    public static int w;
    public static int H;
    public static int h;

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("maze1.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
        //read input
        StringTokenizer st = new StringTokenizer(f.readLine());
        W = Integer.parseInt(st.nextToken());
        w = 2*W+1;
        H = Integer.parseInt(st.nextToken());
        h = 2*H+1;
        maze = new char[h][w];
        costs = new int[h][w];
        visited = new boolean[h][w];
        for(int i = 0; i < h; i++){
            String row = f.readLine();
            for(int j = 0; j < row.length(); j++){
                maze[i][j] = row.charAt(j);
            }
        }
        ArrayList<int[]> ent = findEntrances();
        BFS(ent);
        System.out.println(maxDist/2);
        out.println(maxDist/2);
        out.close();
    }

    public static void BFS(ArrayList<int[]> ent){
        int dist = 1;
        while(ent.size() > 0){
            ArrayList<int[]> neighbors = new ArrayList<int[]>();
            added = new boolean[h][w];
            for(int[] t: ent){
                costs[t[0]][t[1]] = dist;
                maxDist = dist;
                visited[t[0]][t[1]] = true;
                neighbors.addAll(getValidNeighbors(t));
            }
            ent = neighbors;
            dist++;
        }
    }

    public static ArrayList<int[]> getValidNeighbors(int[] c){
        ArrayList<int[]> res = new ArrayList<int[]>();
        int[][] dirs = new int[][]{{1,0,-1,0},
                                    {0,1,0,-1}};
        for(int i = 0; i < dirs[0].length; i++){
            int newR = c[0] + dirs[0][i];
            int newC = c[1] + dirs[1][i];
            if(newR >= 0 && newC >= 0 && newR < maze.length &&
            newC < maze[0].length && !visited[newR][newC]
            && !added[newR][newC] && (maze[newR][newC] == ' ')){
                res.add(new int[]{newR,newC});
                added[newR][newC] = true;
            }
        }
        return res;
    }

    public static ArrayList<int[]> findEntrances(){
        ArrayList<int[]> res = new ArrayList<int[]>();
        //top row and bottom rows
        for(int i = 0; i < maze[0].length; i++){
            for(int j: new int[]{0,maze.length-1}){
                char c = maze[j][i];
                if(c == ' '){
                    int[] temp = new int[]{j,i};
                    res.add(temp);
                }
            }
        }
        //left and right sides (ignoring first and last elems)
        for(int i = 1; i < maze.length-1; i++){
            for(int j: new int[]{0,maze[0].length-1}){
                char c = maze[i][j];
                if(c == ' '){
                    int[] temp = new int[]{i,j};
                    res.add(temp);
                }
            }
        }
        return res;
    } 

}
