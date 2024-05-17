import java.awt.*;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;

    public MyPlayer() {
        int count = 1;
//a resulting board takes the column values for the current board, and, if c is changed alters only c. if b is changed alters b and c, if a is changed alters all 3
        for (int a=1;a<=3;a++){
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


        /***
         * This code will run just once, when the game opens.
         * Add your code here.
         */
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
