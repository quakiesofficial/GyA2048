package Spelet2048;

import javax.swing.*;
import java.awt.*;

public class Box2048 extends JPanel {
    private JLabel label = new JLabel();

    public Box2048(){
        setLayout(new GridBagLayout());
        label.setFont(new Font(null,Font.BOLD,60));

        add(label);

    }
    private int valueOfTile(){
        int returnvalue = 0;
        Color c=this.getBackground();
        if (c==Color.red)returnvalue=1;
        if (c==Color.ORANGE)returnvalue=2;
        if (c==Color.YELLOW)returnvalue=3;
        if (c==Color.MAGENTA)returnvalue=4;
        if (c==Color.blue)returnvalue=5;
        if (c==Color.PINK)returnvalue=6;
        if (c==Color.cyan)returnvalue=7;
        if (c==Color.GREEN)returnvalue=8;
        if (c==Color.darkGray)returnvalue=9;
        if (c==Color.lightGray)returnvalue=10;
        if (c==Color.black)returnvalue=11;
        return returnvalue;
    }

    public void Combine(){
        //lyckas inte få switch case att funka här, skulle nog vara bättre
        setBackground(getcolor(valueOfTile()));
        recheck();
    }
    private Color getcolor(int number){
        Color[] array = new Color[11];
        array[0]= Color.red;
        array[1]= Color.ORANGE;
        array[2]= Color.yellow;
        array[3]= Color.MAGENTA;
        array[4]= Color.blue;
        array[5]= Color.PINK;
        array[6]= Color.cyan;
        array[7]= Color.GREEN;
        array[8]= Color.lightGray;
        array[9]= Color.darkGray;
        array[10]= Color.BLACK;


        return array[number];
    }


    public void generateNew() {
        if ((int)(Math.random() * 10) < 9) {
            setBackground(Color.RED);
        }else setBackground(Color.ORANGE);
        recheck();

    }
    public void recheck(){
        label.setText(String.valueOf((int) Math.pow(2,valueOfTile())));
        if (((int) Math.pow(2,valueOfTile())==1))label.setText("");
        label.revalidate();
        repaint();
    }
}
