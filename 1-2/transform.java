/*
ID: dennis.8
LANG: JAVA
TASK: transform
*/
import java.io.*;
import java.util.*;

class transform {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("transform.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
    int size = Integer.parseInt(f.readLine());
    char[][] orig = new char[size][size];
    char[][] trans = new char[size][size];
    for(int i = 0; i < size; i++){
        String line = f.readLine();
        for(int j = 0; j < size; j++){
            orig[i][j] = line.charAt(j);
        }
    }
    for(int i = 0; i < size; i++){
        String line = f.readLine();
        for(int j = 0; j < size; j++){
            trans[i][j] = line.charAt(j);
        }
    }
    char[][][] transformations = new char[8][size][size];
    transformations[0] = rotate90(orig);
    transformations[1] = rotate90(transformations[0]);
    transformations[2] = rotate90(transformations[1]);
    transformations[3] = flip(orig);
    transformations[4] = flip(transformations[0]);
    transformations[5] = flip(transformations[1]);
    transformations[6] = flip(transformations[2]);
    transformations[7] = orig;
    boolean eqFound = false;
    for(int i = 0; i < 8; i++){
        boolean eq = isEqual(transformations[i], trans);
        if(eq){
            eqFound = true;
            if(i < 4){
                out.println((i+1)+"");
            } else if (i < 7){
                out.println("5");
            } else {
                out.println("6");
            }
            break;
        }
    }
    if(!eqFound){
        out.println("7");
    }
    out.close();
  }

  public static void printArr(char[][] a){
      for(int i = 0; i < a.length; i++){
          for(int j = 0; j < a.length; j++){
              System.out.print(a[i][j] + " ");
          }
          System.out.println("");
      }
  }

  public static char[][] rotate90(char[][] a){
      char[][] res = new char[a.length][a.length];
      for(int i = 0; i < a.length; i++){
          for(int j = 0; j < a.length; j++){
              res[i][j] = '0';
          }
      }
      int layers = (a.length+1)/2;
      for(int i = 0; i < layers; i++){
          //rotate each layer by 90
          int layerWidth = a.length - (2*i);
          int min = i; //inclusive
          int max = i+layerWidth-1; 
          for(int j = min; j < max+1; j++){
              res[j][max] = a[min][j];
              res[max][max-(j-min)] = a[j][max];
              res[j][min] = a[max][j];
              res[min][max-(j-min)] = a[j][min];
          }
      }
      return res;
  }

  public static char[][] flip(char[][] a){
      char[][] res = new char[a.length][a.length];
      for(int i = 0; i < a.length; i++){
          for(int j = 0; j < a.length; j++){
              res[i][a.length-1-j] = a[i][j];
          }
      }
      return res;
  }

  public static boolean isEqual(char[][] a, char[][] b){
      for(int i = 0; i < a.length; i++){
          for(int j = 0; j < a.length; j++){
              if(a[i][j] != b[i][j]){
                  return false;
              }
          }
      }
      return true;
  }

}
