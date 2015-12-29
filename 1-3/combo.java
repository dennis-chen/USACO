/*
ID: dennis.8
LANG: JAVA
TASK: combo
*/
import java.io.*;
import java.util.*;

class combo {

  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("combo.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
    int dialSize = Integer.parseInt(f.readLine());
    int[] fCombo = new int[3];
    int[] mCombo = new int[3];
    StringTokenizer st = new StringTokenizer(f.readLine());
    StringTokenizer st1 = new StringTokenizer(f.readLine());
    for(int i = 0; i < 3; i++){
        fCombo[i] = Integer.parseInt(st.nextToken());
        mCombo[i] = Integer.parseInt(st1.nextToken());
    }
    boolean[][][] comboTried = new boolean[101][101][101];
    int numCombos = 0;
    for(int i = fCombo[0]-2; i < fCombo[0]+3; i++){
    for(int j = fCombo[1]-2; j < fCombo[1]+3; j++){
    for(int k = fCombo[2]-2; k < fCombo[2]+3; k++){
        int iW = wrapNum(i,dialSize);
        int jW = wrapNum(j,dialSize);
        int kW = wrapNum(k,dialSize);
        //System.out.println(iW+" "+jW+" "+kW);
        if(!comboTried[iW][jW][kW]){
            numCombos++;
            comboTried[iW][jW][kW] = true;
        }
    }}}
    for(int i = mCombo[0]-2; i < mCombo[0]+3; i++){
    for(int j = mCombo[1]-2; j < mCombo[1]+3; j++){
    for(int k = mCombo[2]-2; k < mCombo[2]+3; k++){
        int iW = wrapNum(i,dialSize);
        int jW = wrapNum(j,dialSize);
        int kW = wrapNum(k,dialSize);
        if(!comboTried[iW][jW][kW]){
            numCombos++;
            comboTried[iW][jW][kW] = true;
        }
    }}}
    //System.out.println(numCombos);
    out.println(numCombos);
    out.close();
  }

  public static int wrapNum(int a, int dialSize){
      if(dialSize == 1){
          return 1;
      }
      if(a < 1){
          return a + dialSize;
      }
      else if(a > dialSize){
          int mod = a % dialSize;
          return mod == 0? dialSize : mod;
      }
      else{
          return a;
      }
  }

}
