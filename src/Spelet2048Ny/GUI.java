package Spelet2048Ny;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;


public class GUI {
    JFrame frame;
    Board2048 board = new Board2048(4);
    private final JPanel boardPanel;

    RandomMoves randomInputs = new RandomMoves(GUI.this);


    public GUI() throws IOException {
        frame = new JFrame("\uD83C\uDF46 \uD83C\uDF46 \uD83C\uDF46 2048 \uD83C\uDF46 \uD83C\uDF46 \uD83C\uDF46");
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                directionsInput(e.getKeyCode());
            }
        });







        //Fixa ButtonGroup om det finns flera knappar med olika algoritmer i varje knapp
        JRadioButton randomButton = new JRadioButton("Random");
        randomButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (randomButton.isSelected())
                    randomInputs.startTimer();
                else
                    randomInputs.stopTimer();
            }
        });

        boardPanel = new JPanel(new GridLayout(board.getBoardSize(), board.getBoard()[0].length));
        updateBoard();
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(randomButton, BorderLayout.EAST);
        randomButton.setFocusable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500,500));

        URL url = new URL("https://i.imgur.com/VjMRtzC.png");
        Image image = ImageIO.read(url);
        frame.setIconImage(new ImageIcon(image).getImage());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    private static Color getColorForValue(int value) {
        switch (value) {
            case 0:
                return new Color(255,255,255);
            case 2:
                return new Color(238, 228, 218);
            case 4:
                return new Color(237, 224, 200);
            case 8:
                return new Color(242, 177, 121);
            case 16:
                return new Color(245, 149, 99);
            case 32:
                return new Color(246, 124, 95);
            case 64:
                return new Color(246, 94, 59);
            case 128:
                return new Color(237, 207, 114);
            case 256:
                return new Color(237, 204, 97);
            case 512:
                return new Color(237, 200, 80);
            case 1024:
                return new Color(237, 197, 63);
            case 2048:
                return new Color(237, 194, 46);
            default:
                return new Color(205, 193, 180);
        }
    }
    public void directionsInput(int directions) {
        board.move(directions);

        if (board.isGameLost()) {
            förlustRuta();
            randomInputs.stopTimer();
        }

        updateBoard();
        boardPanel.repaint();
    }


    public void förlustRuta() {
        JDialog förlust = new JDialog(frame);
        förlust.setLayout(new FlowLayout());
        förlust.add(new JLabel("Du Förlora!"));
        förlust.pack();
        förlust.setVisible(true);
        förlust.setLocationRelativeTo(frame);
    }

    public static void main(String[] args) throws IOException {
        new GUI();
    }

    public void updateBoard() {
        boardPanel.removeAll();
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                int value = board.getBoard()[i][j];
                JLabel label = new JLabel(value == 0 ? "" : Integer.toString(value));;
                label.setBorder(new LineBorder(Color.BLACK,1));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                label.setOpaque(true);
                label.setBackground(getColorForValue(board.getBoard()[i][j]));
                boardPanel.add(label);
            }
        }
        boardPanel.revalidate();
    }
}
