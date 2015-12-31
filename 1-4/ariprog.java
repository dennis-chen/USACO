/*
ID: dennis.8
LANG: JAVA
TASK: ariprog
*/
import java.io.*;
import java.util.*;

class ariprog {

  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("ariprog.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
    int length = Integer.parseInt(f.readLine());
    int bisquareMax = Integer.parseInt(f.readLine());

    //generate bisquare lookup table
    //LESSON LEARNED: a lookup table is 5x faster than a hashset in java.
    boolean[] bisquares = new boolean[125000+1];
    ArrayList<Integer> sortedBisquares = new ArrayList<Integer>();
    for(int i = 0; i <= bisquareMax; i++){
        for(int j = 0; j <= bisquareMax; j++){
            if(!bisquares[i*i+j*j]){
                sortedBisquares.add(i*i+j*j);
            }
            bisquares[i*i+j*j] = true;
        }
    }

    Collections.sort(sortedBisquares);
    int maxBisquare = sortedBisquares.get(sortedBisquares.size() -1);

    boolean valsFound = false;
    int maxInterval = (2*bisquareMax*bisquareMax)/(length-2);
    for(int interval = 1; interval < maxInterval; interval++){
        for(Integer t: sortedBisquares){
            if(t+((length-1)*interval) <= maxBisquare){
                boolean isValid = true;
                int i = 1;
                while(i < length && isValid){
                    isValid = bisquares[t+i*interval];
                    i++;
                }
                if(isValid){
                    out.println(t+" "+interval);
                    valsFound = true;
                }
            }
        }
    }

    if(!valsFound){
        out.println("NONE");
    }
    
    out.close();
  }
}
