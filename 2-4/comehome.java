/*
ID: dennis.8
LANG: JAVA
TASK: comehome
*/
import java.io.*;
import java.util.*;

class comehome {

    public static PrintWriter out;
    public static int[][] weights = new int[52][52];
    public static int[][] dist = new int[52][52];

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("comehome.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
        for(int[] t: weights){
            Arrays.fill(t,Integer.MAX_VALUE);
        }
        for(int i = 0; i < 52; i++){
            weights[i][i] = 0;
        }
        //read input
        StringTokenizer st;
        int paths = Integer.parseInt(f.readLine());
        for(int i = 0; i < paths; i++){
            st = new StringTokenizer(f.readLine());
            int from = toNum(st.nextToken());
            int to = toNum(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            weights[from][to] = Math.min(weights[from][to],weight);
            weights[to][from] = Math.min(weights[to][from],weight);
        }
        //System.out.println(Arrays.deepToString(weights));
        findShortest();
        int shortest = Integer.MAX_VALUE;
        int cow = -1;
        for(int i = 26; i < 51; i++){
            if(dist[i][51] < shortest){
                shortest = dist[i][51];
                cow = i;
            }
        }
        String res = toString(cow) + " " + shortest;
        System.out.println(res);
        out.println(res);
        out.close();
    }

    public static void findShortest(){
        for(int i = 0; i < 52; i++){
            for(int j = 0; j < 52; j++){
                dist[i][j] = weights[i][j];
            }
        }
        for(int k = 0; k < 52; k++){
            for(int i = 0; i < 52; i++){
                for( int j = 0; j < 52; j++){
                    int newDist = sum(dist[i][k],dist[k][j]);
                    if(newDist < dist[i][j]){
                        dist[i][j] = newDist;
                    }
                }
            }
        }
    }

    public static int sum(int a, int b){
        return (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE) ?
            Integer.MAX_VALUE : a+b;
    }

    public static int toNum(String s){
        char c = s.charAt(0);
        int res = Character.isUpperCase(c) ? 
            26 + Character.getNumericValue(c)-10 : 
            Character.getNumericValue(c)-10;
        return res;
    }

    public static String toString(int t){
        char c;
        if(t > 25){ //uppercase
            c = (char) (t+39);
        } else { //lowercase
            c = (char) (t+97);
        }
        return c + "";
    }

}
