import javax.swing.JFrame;
import java.awt.Color;

public class Main
{
    public static void main(String[] args)
    {

        JFrame f=new JFrame();      //main frame of our game
        f.setTitle("Snake Game");   //set title for our game
        f.setBounds(10, 10, 905, 700);      //set boundary for our main frame
        f.setResizable(false);      //no one resize my frame
        f.setVisible(true);         //visible frame on screen
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setBackground(Color.DARK_GRAY);       //set our main frame as a dark gray color

        Gameplay gameplay=new Gameplay();       //created object of our Gameplay class
        f.add(gameplay);                        //add objecct into frame



    }
}