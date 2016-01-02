/*
ID: dennis.8
LANG: JAVA
TASK: castle
*/
import java.io.*;
import java.util.*;


class castle {

    public static int[][] castle;
    public static int[][] components;
    public static ArrayList<Integer> componentSizes = new ArrayList<Integer>();
    public static PrintWriter out;

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("castle.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
        StringTokenizer st;
        st = new StringTokenizer(f.readLine());
        int width = Integer.parseInt(st.nextToken()); 
        int height = Integer.parseInt(st.nextToken()); 
        castle = new int[height][width];
        components = new int[height][width];
        for(int i = 0; i < height; i++){
            st = new StringTokenizer(f.readLine());
            for(int j = 0; j < width; j++){
                castle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        componentSizes.add(0); //add null value to component sizes so components 
        //can be used to directly index into componentSizes
        int roomNum = countAndLabelComponents();
        out.println(roomNum);
        out.println(Collections.max(componentSizes));
        int maxConnect = 0;
        int maxX = 0;
        int maxY = 0;
        String maxDir = "";
        for(int col = width-1; col >= 0; col--){
            for(int row = 0; row < height; row++){
                int wallNum = castle[row][col];
                int componentNum = components[row][col];
                int componentSize = componentSizes.get(componentNum);
                if((wallNum & 0x1) != 0){ //west wall
                    int otherNum = getComponentNum(row,col-1);
                    if(componentNum != otherNum){
                        int otherSize = componentSizes.get(otherNum);
                        if(componentSize+otherSize >= maxConnect){
                            maxConnect = componentSize+otherSize;
                            maxX = col-1;
                            maxY = row;
                            maxDir = "E";
                        }
                    }
                }
                if((wallNum & 0x4) != 0){ //east wall
                    int otherNum = getComponentNum(row,col+1);
                    if(componentNum != otherNum){
                        int otherSize = componentSizes.get(otherNum);
                        if(componentSize+otherSize >= maxConnect){
                            maxConnect = componentSize+otherSize;
                            maxX = col;
                            maxY = row;
                            maxDir = "E";
                        }
                    }
                }
                if((wallNum & 0x2) != 0){ //north wall
                    int otherNum = getComponentNum(row-1,col);
                    if(componentNum != otherNum){
                        int otherSize = componentSizes.get(otherNum);
                        if(componentSize+otherSize >= maxConnect){
                            maxConnect = componentSize+otherSize;
                            maxX = col;
                            maxY = row;
                            maxDir = "N";
                        }
                    }
                }
                if((wallNum & 0x8) != 0){ //south wall
                    int otherNum = getComponentNum(row+1,col);
                    if(componentNum != otherNum){
                        int otherSize = componentSizes.get(otherNum);
                        if(componentSize+otherSize >= maxConnect){
                            maxConnect = componentSize+otherSize;
                            maxX = col;
                            maxY = row-1;
                            maxDir = "N";
                        }
                    }
                }
            }
        }
        out.println(maxConnect);
        out.println((maxY+1)+" "+(maxX+1)+" "+maxDir);
        out.close();
    }

    public static int getComponentNum(int row, int col){
        //returns 0 if indices are not valid
        if(row < 0 || col < 0 || row >= components.length ||
                col >= components[0].length){
            return 0;
        }
        return components[row][col];
    }

    public static int countAndLabelComponents(){
        int componentCount = 0;
        for(int row = 0; row < components.length; row++){
            for(int col = 0; col < components[0].length; col++){
                if(components[row][col] == 0){ //if node unvisited
                    componentCount++;
                    int size = floodFill(row,col,componentCount);
                    componentSizes.add(size);
                }
            }
        }
        return componentCount;
    }

    public static int floodFill(int i, int j, int componentNum){
        if(components[i][j] == componentNum){
            return 0;
        }
        //floodfill and return component size
        components[i][j] = componentNum;
        int size = 1;
        int wallNum = castle[i][j];
        if((wallNum & 0x1) == 0){ //no west wall
            size += floodFill(i,j-1,componentNum);
        }
        if((wallNum & 0x2) == 0){ //no north wall
            size += floodFill(i-1,j,componentNum);
        }
        if((wallNum & 0x4) == 0){ //no east wall
            size += floodFill(i,j+1,componentNum);
        }
        if((wallNum & 0x8) == 0){ //no south wall
            size += floodFill(i+1,j,componentNum);
        }
        return size;
    }

}
