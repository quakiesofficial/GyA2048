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
    AlgoritmMall algoritmMall = new AlgoritmMall(GUI.this);
    CornerAlgoritm cornerAlgoritm = new CornerAlgoritm(GUI.this);

    private JDialog lost;
    private ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton randomButton = new JRadioButton("Random");
        JRadioButton algoritmButton = new JRadioButton("Algoritmall");
        JRadioButton cornerAlgoritmButton = new JRadioButton("Corner Algoritm");
        JRadioButton manualButton = new JRadioButton("Maunual");


    public GUI() throws IOException {
        frame = new JFrame("\uD83C\uDF46 \uD83C\uDF46 \uD83C\uDF46 2048 \uD83C\uDF46 \uD83C\uDF46 \uD83C\uDF46");
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
        cornerAlgoritmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (cornerAlgoritmButton.isSelected()) {
                    StopAllTimers();
                    cornerAlgoritm.startTimer();
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
        algoritmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (algoritmButton.isSelected()) {
                    StopAllTimers();
                    algoritmMall.startTimer();
                }
            }
        });
        buttonGroup.add(randomButton);
        buttonGroup.add(algoritmButton);
        buttonGroup.add(manualButton);
        buttonGroup.add(cornerAlgoritmButton);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
        buttonPanel.add(randomButton);
        buttonPanel.add(algoritmButton);
        buttonPanel.add(cornerAlgoritmButton);
        buttonPanel.add(manualButton);
        boardPanel = new JPanel(new GridLayout(board.getBoardSize(), board.getBoard()[0].length));
        updateBoard();
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.EAST);
        randomButton.setFocusable(false);
        algoritmButton.setFocusable(false);
        cornerAlgoritmButton.setFocusable(false);
        manualButton.setFocusable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500,500));

        URL url = new URL("https://i.imgur.com/VjMRtzC.png");
        Image image = ImageIO.read(url);
        frame.setIconImage(new ImageIcon(image).getImage());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void StopAllTimers() {
        cornerAlgoritm.stopTimer();
        algoritmMall.stopTimer();
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
        boolean anythingHappend=board.move(directions);

        if (board.isGameLost()) {
            boolean hasDepth=false;
            //lostDialog();
            String directory = "";
            if (randomButton.isSelected())
                directory = "files/scorefiler_random.txt";
            else if (cornerAlgoritmButton.isSelected())
                directory = "files/scorefiler_corner.txt";
            else if (algoritmButton.isSelected()) {
                directory = "files/scorefiler_algorithm.txt";
                hasDepth=true;
            }
            if (amountOfTimesRan <= runAmountToStopAt) {
                newPrintInFile(directory, hasDepth);
                System.out.println(board.getScore());
                board = new Board2048(board.getBoardSize());
                amountOfTimesRan++;

            } else
                StopAllTimers();
        }

        updateBoard();
        boardPanel.repaint();
        return anythingHappend;
    }
    private void newPrintInFile(String directory, boolean hasDepth) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter((directory), true));
            bufferedWriter.write(board.getScore() + " ");
            bufferedWriter.write(board.getHighestTile() + "");
            if (hasDepth)
                bufferedWriter.write(" " + algoritmMall.getDepth());
            if (amountOfTimesRan <= 99) {
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Fel: " + e.getMessage());
        }
    }
    private void scorefileUpdate() {
        try {
            FileWriter fileWriterRandom = new FileWriter("files/scorefiler_random");
            FileWriter fileWriterCorner = new FileWriter("files/scorefiler_corner",true);
            FileWriter fileWriterAlgorithm = new FileWriter("files/scorefiler_algorithm",true);
            PrintWriter printRandom = new PrintWriter(fileWriterRandom);
            FileReader fileReaderRandom = new FileReader("files/scorefiler_random");
            BufferedReader bufferedReaderRandom = new BufferedReader(fileReaderRandom);

            String numberOfRuns = bufferedReaderRandom.readLine();
            int numbrOfRunsint = Integer.parseInt(numberOfRuns);
            String totalScore = bufferedReaderRandom.readLine();
            int totalScoreint = Integer.parseInt(totalScore);
            totalScore += board.getScore();
            System.out.println(numberOfRuns);
            System.out.println(numbrOfRunsint);
            System.out.println(totalScore);
            System.out.println(totalScoreint);

            //int avrageScore = totalScore/numberOfRuns;

            printRandom.println(numbrOfRunsint + "");
            //printRandom.println(totalScore);
            //printRandom.println(avrageScore);


            fileWriterRandom.close();
            fileWriterCorner.close();
            fileWriterAlgorithm.close();
        } catch (IOException e) {
            System.out.println("fel");
        }
    }


    public void lostDialog() {
        JDialog lostDialog = new JDialog(frame);
        lostDialog.setLayout(new FlowLayout());
        lostDialog.add(new JLabel("Du Förlora!"));
        lostDialog.pack();
        lostDialog.setVisible(true);
        lostDialog.setLocationRelativeTo(frame);
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
                label.setFont(new Font("Verdana", Font.BOLD, 24));
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
