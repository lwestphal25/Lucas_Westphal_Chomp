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


    public MyPlayer () {

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

        deathBoards.add(allBoards.get(0));
        for (int i = 1; i<allBoards.size();i++){
            organizeBoard(allBoards.get(i));
        }

        System.out.println("Life Boards");
        System.out.println(lifeBoards.size());
        for (int[] lifeBoard : lifeBoards){
            System.out.println(lifeBoard[0]+","+lifeBoard[1]+","+lifeBoard[2]);
            int[] nextBoard = nextBoard(lifeBoard);
            System.out.println("Next board is: " + nextBoard[0]+","+nextBoard[1]+","+nextBoard[2]);
            System.out.println("So press the chip at location: " + nextMove(lifeBoard, nextBoard)[0]+","+nextMove(lifeBoard, nextBoard)[1]);
        }
        System.out.println("Death Boards");
        for (int[] deathBoard : deathBoards){
            System.out.println(deathBoard[0]+","+deathBoard[1]+","+deathBoard[2]);
           int[] nextBoard = nextBoard(deathBoard);
            System.out.println("Next board is: " + nextBoard[0]+","+nextBoard[1]+","+nextBoard[2]);
            System.out.println("So press the chip at location: " + nextMove(deathBoard, nextBoard)[0]+","+nextMove(deathBoard, nextBoard)[1]);
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

       //for (int f = board.length-1;f>=0;f--){
          // for ()
      // }

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
        for (int i=c1-1;i>=1;i--){
            if (i <= c2){
                if (i <= c3){
                    filler.add(new int[]{i, i, i});
                }else{
                    filler.add(new int[]{i, i, c3});
                }

            }else{
                filler.add(new int[]{i, c2, c3});
            }
        }


       return filler;
    }

    public boolean organizeBoard(int[] board){
        boolean isLifeBoard = false;

        ArrayList<int[]> resultingBoards = resultingBoards(board);
        //if board is 210

        for (int a = 0;a<resultingBoards.size();a++){
            for (int b = 0;b<deathBoards.size();b++){

                if (resultingBoards.get(a)[0] == deathBoards.get(b)[0] && resultingBoards.get(a)[1] == deathBoards.get(b)[1] && resultingBoards.get(a)[2] == deathBoards.get(b)[2]){
                    isLifeBoard = true;
                }
            }
        }
        int count = 0;

        for (int a = 0;a<resultingBoards.size();a++){
            for (int b = 0;b<lifeBoards.size();b++){
                if (resultingBoards.get(a)[0] == lifeBoards.get(b)[0] && resultingBoards.get(a)[1] == lifeBoards.get(b)[1] && resultingBoards.get(a)[2] == lifeBoards.get(b)[2]){
                    count++;
                }
            }
        }

        if (count == resultingBoards.size()){
            deathBoards.add(board);
            return false;
        }
        if (isLifeBoard){
            lifeBoards.add(board);
            return true;
        }
        return false;


    }


    public int[] nextBoard(int[] board){
        ArrayList<int[]> resultingBoards = resultingBoards(board);
        for (int[] a : resultingBoards){
            for (int[] b : deathBoards){
                if (a[0]==b[0] && a[1]==b[1]&&a[2]==b[2]){
                    return a;
                }
            }
        }
        if(board[0] == 1 && board[1]==0 && board[2]==0){
            return new int[]{0,0,0};
        }else{
            int randomBoard = (int)(Math.random()*resultingBoards.size());
            return resultingBoards.get(randomBoard);
        }



        /*if (lifeBoards.contains(board)){
            for (int[] i : resultingBoards){
                System.out.println(Arrays.toString(i));

                if (deathBoards.contains(i)){
                    return i;
                }
            }

        }
        if(board[0] == 1 && board[1]==0 && board[2]==0){
            return new int[]{0,0,0};
        }else{
            int randomBoard = (int)(Math.random()*resultingBoards.size());
            return resultingBoards.get(randomBoard);
        }

         */






    }

    public int[] nextMove(int[] board, int[] nextBoard){
        int[] differences = new int[board.length];
        for (int i=0;i<board.length;i++){
            differences[i] = board[i]-nextBoard[i];
            if (differences[i]>0){
                return new int[]{i, nextBoard[i]};
            }
        }
        return new int[]{0,0};

    }

    public Point move(Chip[][] pBoard) {

         int[] board = new int[10];

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

        for (int c = 0; c<pBoard.length;c++){
            int count = 0;
            for (int r = 0; r<pBoard[0].length;r++){
                if (pBoard[r][c].isAlive){
                    count++;
                }
            }
            board[c] = count;

        }
        System.out.println("board:");
        for (int i = 0; i< board.length;i++){
            System.out.println(board[i]);
        }

        row = nextMove(board, nextBoard(board))[1];
        column = nextMove(board, nextBoard(board))[0];

        Point myMove = new Point(row, column);
        return myMove;
    }



}
