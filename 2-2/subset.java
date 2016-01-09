/*
ID: dennis.8
LANG: JAVA
TASK: subset
*/
import java.io.*;
import java.util.*;


class subset {

    public static PrintWriter out;
    public static int N;
    public static long[][] numWays;

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("subset.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
        //read input
        N = Integer.parseInt(f.readLine());
        int totalSum = (N*(N+1))/2;
        if(totalSum%2 == 0){
            int targetSum = totalSum/2;
            //initialize numWays
            numWays = new long[N+1][targetSum+1];
            for(int row = 0; row <= N; row++){
                numWays[row][0] = 1;
            }
            for(int row = 1; row <= N; row++){
                for(int col = 1; col <= targetSum; col++){
                    numWays[row][col] = numWays[row-1][col];
                    if(col >= row){
                        numWays[row][col] += numWays[row-1][col-row];
                    }
                }
            }
            System.out.println(numWays[N][targetSum]/2);
            out.println(numWays[N][targetSum]/2);
        } else {
            System.out.println("0");
            out.println("0");
        }
        //System.out.println(Arrays.deepToString(numWays));
        out.close();
    }

}
