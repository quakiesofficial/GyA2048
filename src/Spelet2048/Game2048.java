package Spelet2048;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Game2048 {
    static JFrame frame = new JFrame();
    static JPanel[] rutorna = new JPanel[25];
    public static void main(String[] args) {

        frame.setLayout(new GridLayout(4,4));
    frame.setSize(new Dimension(600,600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        for(int i=0;i<16;i++){
            JPanel p = new JPanel();
            p.setBorder(new LineBorder(Color.BLACK));
            frame.add(p);
            rutorna[i]=p;
        }

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        Timer timer = new Timer(1, e -> generateBox());
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                generateBox();
                //timer.start();
            }
        });
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT){generateBox();}
                if (key == KeyEvent.VK_RIGHT){generateBox();}
                if (key == KeyEvent.VK_UP){generateBox();}
                if (key == KeyEvent.VK_DOWN){generateBox();}
            }
        });



    }
    public void moveRight(){
        for(int i=0;i<16;i++){
            if ((rutorna[i].getBackground())!=((new JPanel()).getBackground())){
                rutorna[i].setBackground((new JPanel()).getBackground());
            }
            }
        }

    public static void generateBox(){
        int slump = ((int)(Math.random()*16));
        rutorna[slump].setBackground(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
        rutorna[slump].repaint();
        rutorna[slump].revalidate();
    }
}
