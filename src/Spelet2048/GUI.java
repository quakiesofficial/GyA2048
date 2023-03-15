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
    AverageAlgoritm averageAlgoritm = new AverageAlgoritm(GUI.this);
    CornerAlgoritm cornerAlgoritm = new CornerAlgoritm(GUI.this);
    PrioriteringsAlgoritm prioriteringsAlgoritm = new PrioriteringsAlgoritm(GUI.this);
    String directory = "";
    private boolean hasDepth=false;
    PessimismAlgoritm pessimismAlgoritm = new PessimismAlgoritm(GUI.this);

    private JDialog lost;
    private ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton randomButton = new JRadioButton("Random");
        JRadioButton algoritmButton = new JRadioButton("AverageAlgorithm");
        JRadioButton cornerAlgoritmButton = new JRadioButton("Corner Algoritm");
        JRadioButton manualButton = new JRadioButton("Manual");
        JRadioButton pessimistButton = new JRadioButton("Pessimist algoritm");
        JRadioButton prioriteringButton = new JRadioButton("Prioritering algorithm");


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
        prioriteringButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (prioriteringButton.isSelected()) {
                    StopAllTimers();
                    prioriteringsAlgoritm.stopTimer();
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
                    averageAlgoritm.startTimer();
                }
            }
        });
        pessimistButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (pessimistButton.isSelected()) {
                    StopAllTimers();
                    pessimismAlgoritm.startTimer();
                }
            }
        });
        buttonGroup.add(randomButton);
        buttonGroup.add(algoritmButton);
        buttonGroup.add(cornerAlgoritmButton);
        buttonGroup.add(manualButton);
        buttonGroup.add(pessimistButton);
        buttonGroup.add(prioriteringButton);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
        buttonPanel.add(randomButton);
        buttonPanel.add(algoritmButton);
        buttonPanel.add(cornerAlgoritmButton);
        buttonPanel.add(pessimistButton);
        buttonPanel.add(prioriteringButton);
        buttonPanel.add(manualButton);
        boardPanel = new JPanel(new GridLayout(board.getBoardSize(), board.getBoard()[0].length));
        updateBoard();
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.EAST);
        randomButton.setFocusable(false);
        algoritmButton.setFocusable(false);
        cornerAlgoritmButton.setFocusable(false);
        manualButton.setFocusable(false);
        pessimistButton.setFocusable(false);
        prioriteringButton.setFocusable(false);
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
        averageAlgoritm.stopTimer();
        randomInputs.stopTimer();
        prioriteringsAlgoritm.stopTimer();
        pessimismAlgoritm.stopTimer();
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
            //lostDialog();
            directory = "files/scorefiler_manuell.txt";
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
            updatescorefile();
            StopAllTimers();
        }

        updateBoard();
        boardPanel.repaint();
        return anythingHappend;
    }

    private void updatescorefile() {
        String file = directory;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            int scoreTal = Integer.parseInt(br.readLine());
            System.out.println(scoreTal);
            scoreTal+=board.getScore();
            System.out.println(scoreTal);
            int numberOfTries = Integer.parseInt(br.readLine());
            System.out.println(numberOfTries);
            numberOfTries++;
            System.out.println(numberOfTries);




            Writer wr = new FileWriter(file);
            BufferedWriter bw= new BufferedWriter(wr);
            bw.write(String.valueOf(scoreTal));
            bw.newLine();
            bw.write(String.valueOf(numberOfTries));
            bw.newLine();

            if (hasDepth==true && directory=="files/scorefiler_algorithm.txt"){
                for (int i = 0; i < averageAlgoritm.getDepth(); i++) {
                    bw.newLine();
                }
                bw.write("1");
            }


            bw.close();
            wr.close();
            br.close();
            fr.close();
        }catch (IOException e){
            System.out.println("fucked");
        }
    }

    private void newPrintInFile(String directory, boolean hasDepth) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter((directory), true));
            bufferedWriter.write(board.getScore() + " ");
            bufferedWriter.write(board.getHighestTile() + "");
            if (amountOfTimesRan <= 99)
                bufferedWriter.newLine();
            if (hasDepth)
                bufferedWriter.write(" " + averageAlgoritm.getDepth());

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Fel: " + e.getMessage());
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
