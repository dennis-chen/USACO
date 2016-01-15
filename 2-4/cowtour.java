/*
ID: dennis.8
LANG: JAVA
TASK: cowtour
*/
import java.io.*;
import java.util.*;


class cowtour {

    public static PrintWriter out;
    public static int pastures;
    public static int[] labels; // component labels for each pasture
    public static double diam = 0;
    public static ArrayList<Double> maxD = new ArrayList<Double>(); // max diameters for each component
    public static double[][] weights; //edge weights
    public static double[][] distances;
    public static int[][] locations;
    public static double minDiameter = Double.MAX_VALUE;

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("cowtour.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
        StringTokenizer st;
        //read input
        pastures = Integer.parseInt(f.readLine());
        weights = new double[pastures][pastures];
        distances = new double[pastures][pastures];
        locations = new int[pastures][2];
        labels = new int[pastures];
        for(int i = 0; i < pastures; i++){
            st = new StringTokenizer(f.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int[] coord = new int[]{x,y};
            locations[i] = coord;
        }
        for(int i = 0; i < pastures; i++){
            String row = f.readLine();
            for(int j = 0; j < row.length(); j++){
                char c = row.charAt(j);
                if(c == '1'){
                    weights[i][j] = findDist(i,j);
                } else {
                    weights[i][j] = Double.MAX_VALUE;
                }
            }
        }
        findShortest();
        findComponents();
        tryPairs(0);
        String res = String.format("%.6f", minDiameter);
        System.out.println(res);
        out.println(res);
        out.close();
    }

    public static double findDist(int a, int b){
        int[] aCoord = locations[a];
        int[] bCoord = locations[b];
        int deltaX = aCoord[0] - bCoord[0];
        int deltaY = aCoord[1] - bCoord[1];
        return Math.sqrt(deltaX*deltaX + deltaY*deltaY);
    }

    public static void tryPairs(int first){
        if(first == labels.length-2){
            int second = labels.length-1;
            if(labels[first] != labels[second]){
                tryEdge(first, second);
            }
            return;
        }
        for(int second = first+1; second < labels.length; second++){
            if(labels[first] != labels[second]){
                tryEdge(first, second);
            }
        }
        tryPairs(first+1);
    }

    public static void tryEdge(int a, int b){
        //try adding edge between two pastures and calculate diameter.
        double aDiam = maxD.get(labels[a]);
        double bDiam = maxD.get(labels[b]);
        double aMax = 0;
        double bMax = 0;
        double abDist = findDist(a,b);
        for(double d: distances[a]){
            //find point that's furthest from A
            if(d != Double.MAX_VALUE){
                aMax = Math.max(aMax,d);
            }
        }
        for(double d: distances[b]){
            //find point that's furthest from A
            if(d != Double.MAX_VALUE){
                bMax = Math.max(bMax,d);
            }
        }
        double newDiam = aMax+bMax+abDist;
        double finalDiam = Math.max(aDiam, Math.max(bDiam, newDiam));
        minDiameter = Math.min(finalDiam,minDiameter);
    }

    public static void findShortest(){
        //floyd warshall to find shortest dist from each node
        //to ever other node
        for(int i = 0; i < pastures; i++){
            for(int j = 0; j < pastures; j++){
                distances[i][j] = weights[i][j];
            }
        }
        for(int k = 0; k < pastures; k++){
            for(int i = 0; i < pastures; i++){
                for( int j = 0; j < pastures; j++){
                    double newWeight = distances[i][k] + distances[k][j];
                        //(distances[i][k] == Double.MAX_VALUE) ||
                        //(distances[k][j] == Double.MAX_VALUE) ? 
                        //Double.MAX_VALUE : distances[i][k] + distances[k][j];
                    if(newWeight < distances[i][j] && i != j){
                        distances[i][j] = newWeight;
                    }
                }
            }
        }
    }

    public static void findComponents(){
        Arrays.fill(labels,-1);
        int componentNum = 0;
        for(int i = 0; i < pastures; i++){
            if(labels[i] == -1){ //no component num assigned yet
                diam = 0;
                floodFill(i, componentNum);
                maxD.add(diam);
                componentNum++;
            }
        }
    }

    public static void floodFill(int pasture, int label){
        if(labels[pasture] != -1){
            return;
        }
        labels[pasture] = label;
        for(double d: distances[pasture]){
            if(d != Double.MAX_VALUE){
                diam = Math.max(diam,d);
            }
        }
        for(int col = 0; col < weights[0].length; col++){
            if(weights[pasture][col] != Double.MAX_VALUE){
                floodFill(col, label);
            }
        }
    }

}
