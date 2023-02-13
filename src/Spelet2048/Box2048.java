package Spelet2048;

import javax.swing.*;
import java.awt.*;

public class Box2048 extends JPanel {
    JLabel label = new JLabel("2");
    public void Box(int position){

        add(label);

    }
    public void Combine(){
        //lyckas inte få switch case att funka här, skulle nog vara bättre
        Color c=this.getBackground();
        // behövs 10 unika färger (2 4 8 16 32 64 128 256 512 1024 2048)
        if (c==Color.red)this.setBackground(Color.ORANGE);
        if (c==Color.ORANGE)this.setBackground(Color.YELLOW);
        if (c==Color.YELLOW)this.setBackground(Color.MAGENTA);
        if (c==Color.MAGENTA)this.setBackground(Color.blue);
        if (c==Color.blue)this.setBackground(Color.PINK);
        if (c==Color.PINK)this.setBackground(Color.cyan);
        if (c==Color.cyan)this.setBackground(Color.darkGray);
        if (c==Color.darkGray)this.setBackground(Color.lightGray);
        if (c==Color.lightGray)this.setBackground(Color.black);
        if (c==Color.black)this.setBackground(Color.GREEN);

    }

}
