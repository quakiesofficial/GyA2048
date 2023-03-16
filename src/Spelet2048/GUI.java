package Spelet2048;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;


public class GUI {
    JFrame frame;
    Board2048 board = new Board2048(4);
    private final JPanel boardPanel;
    private int amountOfTimesRan;
    private int runAmountToStopAt;

    RandomMoves randomInputs = new RandomMoves(GUI.this);
    SmartAlgorithm smartAlgorithm = new SmartAlgorithm(GUI.this);
    CornerAlgorithm cornerAlgorithm = new CornerAlgorithm(GUI.this);
    PrioritizationAlgorithm prioritizationAlgorithm = new PrioritizationAlgorithm(GUI.this);
    PessimisticAlgorithm pessimisticAlgorithm = new PessimisticAlgorithm(GUI.this);
    String directory = "";
    private boolean hasDepth=false;

    private ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton smartButton = new JRadioButton("Smart Algorithm");
        JRadioButton pessimisticButton = new JRadioButton("Pessimistic Algorithm");
        JRadioButton prioritizationButton = new JRadioButton("Prioritization Algorithm");
        JRadioButton cornerAlgoritmButton = new JRadioButton("Corner Algoritm");
        JRadioButton randomButton = new JRadioButton("Random");
        JRadioButton manualButton = new JRadioButton("Manual");


    public GUI() throws IOException {
        //\uD83C\uDF46 \uD83C\uDF46 \uD83C\uDF46
        frame = new JFrame("2048");
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                directionsInput(e.getKeyCode());
            }
        });
        amountOfTimesRan = 1;
        runAmountToStopAt = 100;





        //Fixa ButtonGroup om det finns flera knappar med olika algoritmer i varje knapp
        randomButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (randomButton.isSelected()) {
                    StopAllTimers();
                    randomInputs.startTimer();
                }
            }
        });
        prioritizationButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (prioritizationButton.isSelected()) {
                    StopAllTimers();
                    prioritizationAlgorithm.startTimer();
                }
            }
        });
        cornerAlgoritmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (cornerAlgoritmButton.isSelected()) {
                    StopAllTimers();
                    cornerAlgorithm.startTimer();
                }
            }
        });
        manualButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (manualButton.isSelected()) {
                    StopAllTimers();
                }
            }
        });
        smartButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (smartButton.isSelected()) {
                    StopAllTimers();
                    smartAlgorithm.startTimer();
                }
            }
        });
        pessimisticButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (pessimisticButton.isSelected()) {
                    StopAllTimers();
                    pessimisticAlgorithm.startTimer();
                }
            }
        });
        buttonGroup.add(smartButton);
        buttonGroup.add(pessimisticButton);
        buttonGroup.add(prioritizationButton);
        buttonGroup.add(cornerAlgoritmButton);
        buttonGroup.add(randomButton);
        buttonGroup.add(manualButton);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
        buttonPanel.add(smartButton);
        buttonPanel.add(pessimisticButton);
        buttonPanel.add(prioritizationButton);
        buttonPanel.add(cornerAlgoritmButton);
        buttonPanel.add(randomButton);
        buttonPanel.add(manualButton);
        boardPanel = new JPanel(new GridLayout(board.getBoardSize(), board.getBoard()[0].length));
        updateBoard();
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.EAST);
        smartButton.setFocusable(false);
        pessimisticButton.setFocusable(false);
        prioritizationButton.setFocusable(false);
        cornerAlgoritmButton.setFocusable(false);
        randomButton.setFocusable(false);
        manualButton.setFocusable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600,500));

        URL url = new URL("https://i.imgur.com/VjMRtzC.png");
        Image image = ImageIO.read(url);
        frame.setIconImage(new ImageIcon(image).getImage());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void StopAllTimers() {
        smartAlgorithm.stopTimer();
        pessimisticAlgorithm.stopTimer();
        prioritizationAlgorithm.stopTimer();
        cornerAlgorithm.stopTimer();
        randomInputs.stopTimer();
    }

    private static Color getColorForValue(int value) {
        switch (value) {
            case 0:
                return new Color(255,255,255);
            case 2:
                return new Color(238, 228, 218);
            case 4:
                return new Color(206, 143, 49);
            case 8:
                return new Color(207, 151, 104);
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
    public boolean directionsInput(int directions) {
        boolean anythingHappened=board.move(directions);

        if (board.isGameLost()) {
            directory = "files/scorefiler_manuell.txt";
            if (randomButton.isSelected())
                directory = "files/scorefiler_random.txt";
            else if (cornerAlgoritmButton.isSelected())
                directory = "files/scorefiler_corner.txt";
            else if (smartButton.isSelected()) {
                directory = "files/scorefiler_algorithm.txt";
                hasDepth=true;
            }
            if (amountOfTimesRan <= runAmountToStopAt) {
                PrintInFile(directory);
                board = new Board2048(board.getBoardSize());
                amountOfTimesRan++;
            } else
                StopAllTimers();
        }

        updateBoard();
        return anythingHappened;
    }


    private void PrintInFile(String directory) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter((directory), true));
            bufferedWriter.write(board.getScore() + " ");
            bufferedWriter.write(board.getHighestTile() + "");
            if (amountOfTimesRan <= 99)
                bufferedWriter.newLine();
            if (hasDepth)
                bufferedWriter.write(" " + smartAlgorithm.getDepth());

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Fel: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        new GUI();
    }

    public void updateBoard() {
        boardPanel.removeAll();
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                int value = board.getBoard()[i][j];
                JLabel label = new JLabel(value == 0 ? "" : Integer.toString(value));
                label.setFont(new Font("Verdana", Font.BOLD, 28));
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
