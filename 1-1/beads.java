/*
ID: dennis.8
LANG: JAVA
TASK: beads
*/
import java.io.*;
import java.util.*;

class beads {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("beads.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
    int beadNum = Integer.parseInt(f.readLine());
    String beads = f.readLine();
    int maxBeadsTaken = 2;
    for(int i = 0; i < beadNum; i++){
        int leftTaken = takeLeftBeads(i,beads);
        int rightTaken;
        if (i == beadNum - 1){
            rightTaken = takeRightBeads(0,beads);
        } else {
            rightTaken = takeRightBeads(i+1,beads);
        }
        int total = Math.min(leftTaken + rightTaken, beadNum);
        if(total > maxBeadsTaken){
            maxBeadsTaken = total;
        }
    }
    out.print(maxBeadsTaken+"\n");
    out.close();
  }

  public static int takeLeftBeads(int begin, String beads){
        int left = begin;
        boolean canTake = true;
        int beadsTaken = 1;
        char colorTaken = beads.charAt(left);
        left = left == 0 ? beads.length() - 1 : left - 1;
        while(canTake && beadsTaken < beads.length()){
            char bead = beads.charAt(left);
            if(bead != 'w' && colorTaken != 'w' && bead != colorTaken){
                canTake = false;
            } else {
                if(colorTaken == 'w'){
                    colorTaken = bead;
                }
                beadsTaken++;
                left = left == 0 ? beads.length() - 1 : left - 1;
            }
        }
        return beadsTaken;
  }

  public static int takeRightBeads(int begin, String beads){
        int right = begin;
        boolean canTake = true;
        int beadsTaken = 1;
        char colorTaken = beads.charAt(right);
        right = right == beads.length()-1 ? 0: right + 1;
        while(canTake && beadsTaken < beads.length()){
            char bead = beads.charAt(right);
            if(bead != 'w' && colorTaken != 'w' && bead != colorTaken){
                canTake = false;
            } else {
                if(colorTaken == 'w'){
                    colorTaken = bead;
                }
                beadsTaken++;
                right = right == beads.length()-1 ? 0: right + 1;
            }
        }
        return beadsTaken;
  }
}
