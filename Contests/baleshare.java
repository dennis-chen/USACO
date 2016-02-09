/*
ID: dennis.8
LANG: JAVA
TASK: baleshare
*/
import java.io.*;
import java.util.*;

class baleshare {

  static int[] bales;
  static int[] sums;
  static int total;
  static boolean[][][] shares;

  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("baleshare.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("baleshare.out")));
    int baleNum = Integer.parseInt(f.readLine());
    bales = new int[baleNum];
    sums = new int[baleNum];
    int sum = 0;
    for(int i = 0; i < baleNum; i++){
        int b = Integer.parseInt(f.readLine());
        sum += b;
        bales[i] = b;
        sums[i] = sum;
    }
    total = sums[sums.length-1];
    shares = new boolean[baleNum+1][total+1][total+1];
    shares[0][0][0] = true;
    dp();
    int minJ = Integer.MAX_VALUE;
    for(int k = 0; k <= total; k++){
        for(int l = 0; l <= total; l++){
            if(shares[shares.length-1][k][l]){
                int j = total - k - l;
                if(j >= k && j >= l){
                    minJ = Math.min(j,minJ);
                }
            }
        }
    }
    System.out.println(minJ);
    out.println(minJ);
    out.close();
  }

  public static void dp(){
      for(int i = 1; i < shares.length; i++){
          for(int k = 0; k <= total; k++){
              for(int l = 0; l <= total; l++){
                  int j = sums[i-1] - k - l;
                  int b = bales[i-1];
                      boolean minusK = k-b >= 0 ? shares[i-1][k-b][l] : false;
                      boolean minusL = l-b >= 0 ? shares[i-1][k][l-b] : false;
                      shares[i][k][l] = minusK ||
                                        minusL ||
                                        shares[i-1][k][l];
              }
          }
      }
  }

}
