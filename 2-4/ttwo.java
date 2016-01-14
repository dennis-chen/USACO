/*
ID: dennis.8
LANG: JAVA
TASK: ttwo
*/
import java.io.*;
import java.util.*;


class ttwo {

    public static PrintWriter out;
    public static boolean[][] field = new boolean[10][10];
    //farmer x, farmer y, farmer dir, cow x, cow y, cow dir
    public static boolean[][][][][][] seen = new boolean[10][10][4][10][10][4];

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("ttwo.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
        //read input
        M farmer = null;
        M cow = null;
        for(int i = 0; i < 10; i++){
            String row = f.readLine();
            for(int j = 0; j < row.length(); j++){
                char c = row.charAt(j);
                switch (c){
                    case '.':
                        field[i][j] = true;
                        break;
                    case 'F':
                        field[i][j] = true;
                        farmer = new M(i,j,Dir.NORTH);
                        break;
                    case 'C':
                        field[i][j] = true;
                        cow = new M(i,j,Dir.NORTH);
                        break;
                }
            }
        }
        boolean cycle = false;
        int min = 0;
        while(!farmer.sameLocation(cow) && !cycle){
            farmer.move();
            cow.move();
            min++;
            if(seen[farmer.row][farmer.col][farmer.d.ordinal()]
                    [cow.row][cow.col][cow.d.ordinal()]){
                cycle = true;
                min = 0;
            } else {
                seen[farmer.row][farmer.col][farmer.d.ordinal()]
                    [cow.row][cow.col][cow.d.ordinal()] = true;
                
            }
        }
        System.out.println(min);
        out.println(min);
        out.close();
    }

    static enum Dir {
        //code from
        //http://stackoverflow.com/questions/17664445/java-operator-for-enum
        NORTH, EAST, SOUTH, WEST {
            @Override
            public Dir next(){
                return values()[0];
            };
        };

        public Dir next(){
            return values()[ordinal() + 1];
        }
    }

    static class M {
        public int row;
        public int col;
        public Dir d;
        public M(int row, int col, Dir d){
            this.row = row;
            this.col = col;
            this.d = d;
        }
        public String toString(){
            return row+","+col+","+d;
        }
        public boolean sameLocation(M other){
            return this.row == other.row && this.col == other.col;
        }
        public void move(){
            int newR = -1; int newC = -1;
            switch(this.d){
                case NORTH:
                    newR = this.row-1;
                    newC = this.col;
                    break;
                case EAST:
                    newR = this.row;
                    newC = this.col+1;
                    break;
                case SOUTH:
                    newR = this.row+1;
                    newC = this.col;
                    break;
                case WEST:
                    newR = this.row;
                    newC = this.col-1;
                    break;
            }
            if(isValid(newR,newC)){
                this.forward(newR,newC);
            } else {
                this.turn();
            }
        }
        private boolean isValid(int row, int col){
            return (row >= 0 && col >= 0 && row < 10 && col < 10 
                    && field[row][col]);
        }
        private void turn(){
            this.d = this.d.next();
        }
        private void forward(int newR, int newC){
            this.row = newR;
            this.col = newC;
        }
    }

}
