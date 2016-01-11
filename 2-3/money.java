/*
ID: dennis.8
LANG: JAVA
TASK: money
*/
import java.io.*;
import java.util.*;


class money {

    public static PrintWriter out;
    public static long[] ways;
    public static int[] coins;

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("money.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
        //read input
        StringTokenizer st = new StringTokenizer(f.readLine());
        int V = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        coins = new int[V];
        ways = new long[N+1];
        st = new StringTokenizer(f.readLine());
        for(int i = 0; i < V; i++){
            if(!st.hasMoreTokens()){
                st = new StringTokenizer(f.readLine());
            }
            coins[i] = Integer.parseInt(st.nextToken());
        }
        countWays();
        System.out.println(ways[N]);
        out.println(ways[N]);
        out.close();
    }

    public static void countWays(){
        //1 way to make zero using any money system
        ways[0] = 1;
        for(int j = 0; j < coins.length; j++){
            for(int i = 1; i < ways.length; i++){
                int coin = coins[j];
                if(i >= coin){
                    ways[i] += ways[i-coin];
                }
            }
        }
        return;
    }

}
