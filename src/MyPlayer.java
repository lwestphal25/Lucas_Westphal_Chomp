import java.awt.*;
import java.util.ArrayList;
import java.util.*;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;
    public int boardCount = 0;

    public ArrayList<int[]> allBoards = new ArrayList<int[]>();
    public ArrayList<int[]> lifeBoards = new ArrayList<int[]>();
    public ArrayList<int[]> deathBoards = new ArrayList<int[]>();


    public MyPlayer() {
        //int count = 1;
//a resulting board takes the column values for the current board, and, if c is changed alters only c. if b is changed alters b and c, if a is changed alters all 3
        /*for (int a=1;a<=3;a++){
            for (int b=0;b<=3;b++){
                for (int c=0;c<=3;c++){
                    if (c<=b && b<= a){
                        System.out.println("");
                        System.out.println("board "+count+": ("+a+","+b+","+c+")");
                        System.out.println("board "+count+" has these resulting boards:");
                        count++;
                        for (int x=1;x<a;x++){
                            if (x<=b && x<= c){
                                System.out.println("("+x+", "+x+", "+x+")");
                            }else{
                                System.out.println("("+x+", "+b+", "+c+")");
                            }
                        }
                        for (int y=0;y<b;y++){
                            if (y<= c){
                                System.out.println("("+a+", "+y+", "+y+")");
                            }else{
                                System.out.println("("+a+", "+y+", "+c+")");
                            }
                        }
                        for (int z=0;z<c;z++){
                            System.out.println("("+a+", "+b+", "+z+")");
                        }


                        }

                    }
                }
            }

         */

        for (int col1=1;col1<=3;col1++){
            for (int col2=0;col2<=3;col2++){
                for (int col3=0;col3<=3;col3++){
                    if (col3<=col2 && col2<=col1){
                        allBoards.add(new int[]{col1, col2, col3});
                        System.out.println(col1+","+col2+","+col3);
                    }

                }
            }
        }

       // for (int[] board : allBoards){
          //  System.out.println(board[0]+","+board[1]+","+board[2]+","+isLifeBoard(board));
        //}

        deathBoards.add(allBoards.get(0));
        for (int i = 1; i<allBoards.size();i++){
            organizeBoard(allBoards.get(i));
        }
        System.out.println("Life Boards");
        for (int[] board : lifeBoards){
            System.out.println(board[0]+","+board[1]+","+board[2]);
        }
        System.out.println("Death Boards");
        for (int[] board : deathBoards){
            System.out.println(board[0]+","+board[1]+","+board[2]);
        }


        /***
         * This code will run just once, when the game opens.
         * Add your code here.
         */
    }

    public ArrayList<int[]> resultingBoards(int[] board){
        int c1 = board[0];
        int c2 = board[1];
        int c3 = board[2];
       ArrayList<int[]> filler = new ArrayList<int[]>();

        for (int i=c3-1; i>=0;i--){
            filler.add(new int[]{c1, c2, i});
        }
        for (int i=c2-1;i>=0;i--){
            if (i <= c3){
                filler.add(new int[]{c1, i, i});
            }else{
                filler.add(new int[]{c1, i, c3});
            }
        }
        for (int i=c1-1;i>=0;i--){
            if (i <= c2 && i <= c3){
                filler.add(new int[]{i, i, i});
            }else{
                filler.add(new int[]{i, c2, c3});
            }
        }


       return filler;
    }

    public void organizeBoard(int[] board){
        System.out.println("iterating");

        boolean hasOnlyLifeBoards = true;
        ArrayList<int[]> resultingBoards = resultingBoards(board);
        for (int a = 0;a<resultingBoards.size();a++){
            for (int b = 0;b<deathBoards.size();b++){
                if (resultingBoards.get(a)[0] == deathBoards.get(b)[0] && resultingBoards.get(a)[1] == deathBoards.get(b)[1] && resultingBoards.get(a)[2] == deathBoards.get(b)[2]){
                    lifeBoards.add(board);
                }
            }
        }

        for (int a = 0;a<resultingBoards.size();a++){
            for (int b = 0;b<lifeBoards.size();b++){
                if (resultingBoards.get(a)[0] == lifeBoards.get(b)[0] && resultingBoards.get(a)[1] == lifeBoards.get(b)[1] && resultingBoards.get(a)[2] == lifeBoards.get(b)[2]){
                }else{
                    hasOnlyLifeBoards = false;
                }
            }
        }

        if (hasOnlyLifeBoards){
            deathBoards.add(board);
        }


    }

    public boolean isLifeBoard(int[] board){
        int c1 = board[0];
        int c2 = board[1];
        int c3 = board[2];

       if (c1<=1 && c2<=0 && c3<=0){
           return false;

       }

       for (int i=c3-1; i>=0;i--){
           if (!isLifeBoard(new int[]{c1, c2, i})){
               return true;
           }
       }
       for (int i=c2-1;i>=0;i--){
           if (i <= c3){
               if (!isLifeBoard(new int[]{c1, i, i})){
                   return true;
               }
           }else{
               if(!isLifeBoard(new int[]{c1, i, c3})){
                   return true;
               }
           }
       }
        for (int i=c1-1;i>=0;i--){
            if (i <= c2 && i <= c3){
                if (!isLifeBoard(new int[]{i, i, i})){
                    return true;
                }
            }else{
                if(!isLifeBoard(new int[]{i, c2, c3})){
                    return true;
                }
            }
        }

        return false;

    }

    public Point move(Chip[][] pBoard) {

        System.out.println("MyPlayer Move");

        gameBoard = pBoard;
        int column = 0;
        int row = 0;

        row = 1;
        column = 1;

        /***
         * This code will run each time the "MyPlayer" button is pressed.
         * Add your code to return the row and the column of the chip you want to take.
         * You'll be returning a data type called Point which consists of two integers.
         */

        Point myMove = new Point(row, column);
        return myMove;
    }

}
