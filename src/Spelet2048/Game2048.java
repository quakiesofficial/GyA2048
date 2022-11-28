package Spelet2048;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

public class Game2048 {
    static JFrame frame = new JFrame();
    static Box2048[] rutorna = new Box2048[16];
    static boolean[] upptagna = new boolean[16];
    static Color basecolor = (new JPanel().getBackground());
    public static void main(String[] args) {

        frame.setLayout(new GridLayout(4,4));
    frame.setSize(new Dimension(600,600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        for(int i=0;i<16;i++){
            Box2048 p = new Box2048();
            p.setBorder(new LineBorder(Color.BLACK));
            frame.add(p);
            rutorna[i]=p;
        }

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        //startar med 2 boxar från början
        generateBox();
        generateBox();

        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                generateBox();
            }
        });
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT){moveLeft();}
                if (key == KeyEvent.VK_RIGHT){moveRight();}
                if (key == KeyEvent.VK_UP){moveUp();}
                if (key == KeyEvent.VK_DOWN){generateBox();}
            }
        });



    }
    static boolean handeNgt = false;
    public static void moveRight(){
        handeNgt = false;
        for(int i=15;i>=0;i--){
            int extrakoll = i;
            if (upptagna[i]){
                Color farg=rutorna[i].getBackground();
                for (int ii =0;ii<(3-(i-(i/4*4)));ii++){

                    extrakoll+=1;
                        if (!upptagna[extrakoll]) {

                            rutorna[extrakoll].setBackground(farg);
                            upptagna[extrakoll]=true;
                            rutorna[extrakoll - 1].setBackground(basecolor);
                            upptagna[extrakoll-1] = false;
                            rutorna[extrakoll].repaint();
                            handeNgt=true;
                        }
                        else if (rutorna[extrakoll-1].getBackground()==rutorna[extrakoll].getBackground()){
                            rutorna[extrakoll].Combine();
                            rutorna[extrakoll - 1].setBackground(basecolor);
                            upptagna[extrakoll-1] = false;
                            handeNgt=true;
                        }



                }
            }
            }
        //Skapar bara box om klicket gjorde något
        if (handeNgt)generateBox();
        }
    public static void moveLeft(){
        handeNgt = false;
        for(int i=0;i<=15;i++){
            int extrakoll = i;
            if (upptagna[i]){
                Color farg=rutorna[i].getBackground();
                for (int ii =0;ii<(i-(i/4*4));ii++) {


                    extrakoll -= 1;

//(i/4*4)
                    if (!upptagna[extrakoll]) {

                        rutorna[extrakoll].setBackground(farg);
                        upptagna[extrakoll] = true;
                        rutorna[extrakoll + 1].setBackground(basecolor);
                        upptagna[extrakoll + 1] = false;
                        rutorna[extrakoll].repaint();
                        handeNgt = true;
                    } else if (rutorna[extrakoll + 1].getBackground() == rutorna[extrakoll].getBackground()) {
                        rutorna[extrakoll].Combine();
                        rutorna[extrakoll + 1].setBackground(basecolor);
                        upptagna[extrakoll + 1] = false;
                        handeNgt = true;
                    }
                }





                }

        }
        //Skapar bara box om klicket gjorde något
        if (handeNgt)generateBox();
    }
    public static void moveUp(){
        handeNgt = false;
        System.out.println("upp finkar 1");
        for(int i=0;i<=15;i++){
            System.out.println("upp finkar 2");
            int extrakoll = i;
            if (upptagna[i]){
                Color farg=rutorna[i].getBackground();
                for (int ii =0;ii<(i/4*4);ii+=4) {
                    System.out.println("upp finkar 3");

                    extrakoll -= 4;

//(i/4*4)
                    if (!upptagna[extrakoll]) {

                        rutorna[extrakoll].setBackground(farg);
                        upptagna[extrakoll] = true;
                        rutorna[extrakoll + 4].setBackground(basecolor);
                        upptagna[extrakoll + 4] = false;
                        rutorna[extrakoll].repaint();
                        handeNgt = true;
                    } else if (rutorna[extrakoll + 4].getBackground() == rutorna[extrakoll].getBackground()) {
                        rutorna[extrakoll].Combine();
                        rutorna[extrakoll + 4].setBackground(basecolor);
                        upptagna[extrakoll + 4] = false;
                        handeNgt = true;
                    }
                }





            }

        }
        //Skapar bara box om klicket gjorde något
        if (handeNgt)generateBox();
    }
    public static void generateBox() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 16; i++) {
            if (!upptagna[i]) list.add(i);
        }
        if (list.size() > 0) {
            int slump = list.get((int) (Math.random() * list.size()));

            upptagna[slump] = true;
            rutorna[slump].setBackground(Color.RED);
            rutorna[slump].repaint();
        }
    }
    }
