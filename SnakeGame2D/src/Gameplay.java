import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.util.Random;



////becaause Jpanel is component and we want Gamelay as a compoonent only after that we add into main frame
//
public class Gameplay extends JPanel implements KeyListener, ActionListener
{
    private int[] snakexlength= new int[750];       //snake x and y axis moving area
    private int[] snakeylength=new int [750];


    //for  knowinig direction of snake
    private boolean left=false;
    private boolean right=false;
    private boolean up=false;
    private boolean down=false;



    //take variable for snake image
    private ImageIcon rightmouth;;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon snakeimage;


    //array  bound for food or enemy
    private int[] enemyxpos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};

    private int[] enemyypos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575, 600,625};


    private ImageIcon enemyimage;
    private Random random=new Random();          //ramdomize our  enemy;



    //checking index of randomaize enemy
    private int xpos=random.nextInt(34);    //length of enemyxpos array
    private int ypos=random.nextInt(23);    //length of enemeyypos array



    private Timer timer;            //for timing
    private int  delay=90;         //for controling snake speed or moment



    private int lengthofsnake=3;    //initial length of snake is 3
    private int moves=0;            //initially moves is  0
    private int scores=0;             //for printing score



    private ImageIcon titleImage;



    public Gameplay()                   //create constructor
    {

        addKeyListener(this);       //iske andar  uss class ka object pass karna hai jisme keylistner impleent kiya hai  if same to  put here this
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);

        timer=new Timer(delay, this);       //timer class ka bject iniital kiya and pass delay (its speed) annd this
        timer.start();                             //when game will be start , timer also start

    }




    public void paint(Graphics g)    //JPanel par issi ki help se ham draw ya paint karenge
    {
        if(moves==0)                //check for  initial  position
        {
            snakexlength[0]=100;    //for drawing snake head on initial position
            snakexlength[1]=75;     //for body
            snakexlength[2]=50;

            snakeylength[0]=100;    //for drawing snake head on initial position
            snakeylength[1]=100;    //for body
            snakeylength[2]=100;
        }


        //Border of title image
        g.setColor(Color.WHITE);
        g.drawRect(24,10, 851, 55);                       //draw a rectanlgle for title


        ImageIcon titleImage =new ImageIcon("snaketitle.jpg");      //create image object and as should be as same as image file  name
        titleImage.paintIcon(this,  g, 25, 11);                    //draw snake title in frame



        //Border of gameplay
        g.setColor(Color.WHITE);
        g.drawRect(24,74,851, 577);         //draw a rectanlgle for playarea
        g.setColor(Color.BLACK);                             //select black color
        g.fillRect(25, 75, 850,  575);     // fill gamearea react with  black color;



        //draw the score
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN,14));     //choose font style and size
        g.drawString("Scores: "+scores, 780,30);        //draw score string in  corner
        g.drawString("Length : "+ lengthofsnake, 780, 50);





        rightmouth =new ImageIcon("rightmouth.png");
        rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);    //x , y pos is  intital  [0]

        for(int a=0; a<lengthofsnake;a++)
        {
            if(a==0 && right)   //f a =0 so  this  means its head and direction is right
            {
                rightmouth =new ImageIcon("rightmouth.png");
                rightmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);

            }
            if(a==0 && left)   //f a =0 so  this  means its head and direction is left
            {
                leftmouth =new ImageIcon("leftmouth.png");
                leftmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);

            }
            if(a==0 && up)   //f a =0 so  this  means its head and direction is up
            {
                upmouth =new ImageIcon("upmouth.png");
                upmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);

            }
            if(a==0 && down)   //f a =0 so  this  means its head and direction is down
            {
                downmouth =new ImageIcon("downmouth.png");
                downmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);

            }
            if(a!=0)            //if a not equal zero that means it is snake body so we wil print  snake body
            {
                snakeimage =new ImageIcon("snakeimage.png");
                snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);

            }
        }

        enemyimage=new ImageIcon("enemy.png");
        enemyimage.paintIcon(this,  g, enemyxpos[xpos], enemyypos[ypos]);

        //snake head  collide with enemy
        if(enemyxpos[xpos]==snakexlength[0] && enemyypos[ypos]==snakeylength[0] )       //if snake head colide with enemy
        {
            lengthofsnake++;                    //increase length
            scores++;
            xpos=random.nextInt(34);     // again regenrate enemy x and y position
            ypos=random.nextInt(23);

        }


        //check if collide so gameover
        for(int b=1; b<lengthofsnake;b++)
        {
            if(snakexlength[b]==snakexlength[0] && snakeylength[b]==snakeylength[0])    //if head colide with its own body then
            {                                                                           //we do all direction false and snake stuck

                right=false;
                left=false;
                up=false;
                down=false;

                g.setColor(Color.WHITE);            //select font color //print gameover message on screen
                g.setFont(new Font("arial", Font.BOLD, 50));    //select font  type and size
                g.drawString("Game  Over", 300,300);            //print game ver message


                g.setFont(new Font("arial", Font.BOLD, 20));    //select font  type and size
                g.drawString("Press space  to RESTART", 350,340);




            }
        }

        g.dispose();




    }



    @Override
    public void actionPerformed(ActionEvent e)      //Action formed method  ActionListner se aa raha hai
    {
        if(right)           //it check  snake  direction and moving right
        {
            for(int i=lengthofsnake-1;i>=0;i--)
            {
                snakeylength[i+1]=snakeylength[i];
            }
            for(int  i=lengthofsnake; i>=0;  i--)
            {
                if(i==0)        //it means its snake head
                {
                    snakexlength[i]=snakexlength[i]+25;

                }
                else
                {
                    snakexlength[i]=snakexlength[i-1];
                }
                if(snakexlength[i]>850)
                {
                    snakexlength[i]=25;
                }
            }

            repaint();
        }



        if(left)           //it check  snake  direction and  moving  left
        {
            for(int i=lengthofsnake-1;i>=0;i--)
            {
                snakeylength[i+1]=snakeylength[i];
            }
            for(int  i=lengthofsnake; i>=0;  i--)
            {
                if(i==0)        //it means its snake head
                {
                    snakexlength[i]=snakexlength[i]-25;

                }
                else
                {
                    snakexlength[i]=snakexlength[i-1];
                }
                if(snakexlength[i]<25)
                {
                    snakexlength[i]=850;
                }
            }

            repaint();
        }



        if(up)           //it check  snake  direction and moving up
        {
            for(int i=lengthofsnake-1;i>=0;i--)
            {
                snakexlength[i+1]=snakexlength[i];
            }
            for(int  i=lengthofsnake; i>=0;  i--)
            {
                if(i==0)        //it means its snake head
                {
                    snakeylength[i]=snakeylength[i]-25;

                }
                else
                {
                    snakeylength[i]=snakeylength[i-1];
                }
                if(snakeylength[i]<75)
                {
                    snakeylength[i]=625;
                }
            }

            repaint();
        }



        if(down)           //it check  snake  direction and moving down
        {
            for(int i=lengthofsnake-1;i>=0;i--)
            {
                snakexlength[i+1]=snakexlength[i];
            }
            for(int  i=lengthofsnake; i>=0;  i--)
            {
                if(i==0)        //it means its snake head
                {
                    snakeylength[i]=snakeylength[i]+25;

                }
                else
                {
                    snakeylength[i]=snakeylength[i-1];
                }
                if(snakeylength[i]>625)
                {
                    snakeylength[i]=75;
                }
            }

            repaint();
        }




    }




    @Override
    public void keyTyped(KeyEvent e)
    {
    }




    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            moves++;            //when user press right ki snake move forward and only right direction is true;
            if(!left)
            {
                right=true;
            }
            else
            {
                right=false;
                left=true;
            }
            up=false;
            down=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            moves++;            //when user press right ki snake move forward and only right direction is true;
            if(!right)
            {
                left=true;
            }
            else
            {
                left=false;
                right=true;
            }
            up=false;
            down=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP)
        {
            moves++;            //when user press right ki snake move forward and only right direction is true;
            if(!down)
            {
                up=true;
            }
            else
            {
                up=false;
                down=true;
            }
            left=false;
            right=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN)
        {
            moves++;            //when user press right ki snake move forward and only right direction is true;
            if(!up)
            {
                down=true;
            }
            else
            {
                down=false;
                up=true;
            }
            left=false;
            right=false;
        }


        if(e.getKeyCode()==KeyEvent.VK_SPACE)       //for restart
        {
            scores=0;
            moves=0;
            lengthofsnake=3;
            repaint();


        }

    }





    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
