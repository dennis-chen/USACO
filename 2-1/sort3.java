/*
ID: dennis.8
LANG: JAVA
TASK: sort3
*/
import java.io.*;
import java.util.*;


class sort3 {

    public static PrintWriter out;
    public static int[][] sectionCounts;
    public static int swaps = 0;

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
        //read input
        int numRecords = Integer.parseInt(f.readLine());
        int[] records = new int[numRecords];
        int[] numCounts = new int[4];
        for(int i = 0; i < numRecords; i++){
            int record = Integer.parseInt(f.readLine());
            records[i] = record;
            numCounts[record]++;
        }
        //count occurences of each num in each section
        sectionCounts = new int[4][4];
        int index = 0;
        for(int section = 1; section <= 3; section++){
            int sectionSize = numCounts[section];
            while(sectionSize > 0){
                int val = records[index];
                sectionCounts[section][val]++;
                index++;
                sectionSize--;
            }
        }
        System.out.println(Arrays.deepToString(sectionCounts));
        //swap pairs
        int[][] pairs = new int[][]{{1,2,3},{2,3,1}};
        for(int pair = 0; pair < 3; pair++){
            int a = pairs[0][pair];
            int b = pairs[1][pair];
            int swap = Math.min(sectionCounts[a][b],sectionCounts[b][a]);
            sectionCounts[a][b] -= swap;
            sectionCounts[b][a] -= swap;
            swaps+= swap;
        }
        swapTrios();
        System.out.println(swaps);
        out.println(swaps);
        out.close();
    }

    public static void swapTrios(){
        for(int i = 1; i <= 3; i++){
            for(int j = 1; j <= 3; j++){
                if(i!=j){
                    if(sectionCounts[i][j] != 0){
                        swaps += 2*sectionCounts[i][j];
                        return;
                    }
                }
            }
        }
    }

}
