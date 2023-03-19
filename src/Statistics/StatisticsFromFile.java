package Statistics;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class StatisticsFromFile {
    private File directory=null;

    String[][] tableData;
    private int amountOfRows;
    private int amountOfColumns;

    private double averageScore = 0;
    private int highestTile = 0;
    private ArrayList<Integer> allHighestTiles;
    private int mostFrequentTile = 0;
    private int frequency;


    public StatisticsFromFile() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.showOpenDialog(frame);
        directory = fileChooser.getSelectedFile();
        if (directory == null) {
            directory = new File("files/scorefiler_algorithm.txt");
        }
        allHighestTiles = new ArrayList<>();
        getInformation();

        System.out.println(Arrays.deepToString(tableData));
        String[] names = {"Type of Data", "Value"};
        setInformationInArray();
        JTable table = new JTable(tableData, names);

        frame.add(table);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



    }

    public static void main(String[] args) throws IOException {
        StatisticsFromFile stats = new StatisticsFromFile();
    }


    private void getInformation() {
        averageScore = 0;
        String row;
        this.amountOfRows = 0;
        highestTile = 0;
        amountOfColumns = 0;
        mostFrequentTile = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(getDirectory()));
            row = bufferedReader.readLine();
            while (row != null) {
                String[] rowSplit = row.split(" ");
                amountOfColumns = rowSplit.length;
                if (Integer.parseInt(rowSplit[1]) > highestTile )
                    highestTile = Integer.parseInt(rowSplit[1]);
                averageScore += Double.parseDouble(rowSplit[0]);
                allHighestTiles.add(Integer.parseInt(rowSplit[1]));
                row = bufferedReader.readLine();
                amountOfRows++;
            }
        } catch (IOException e) {
            System.out.println("Fel: " + e.getMessage());
        }
        int counter = 0;
        System.out.println(allHighestTiles);
        try {
            for (int i = 0; i < allHighestTiles.size(); i++) {

                frequency = Collections.frequency(allHighestTiles, allHighestTiles.get(i));
                if (frequency == counter && allHighestTiles.get(i) > mostFrequentTile) {
                    mostFrequentTile = allHighestTiles.get(i);
                }
                else if (frequency > counter) {
                    mostFrequentTile = allHighestTiles.get(i);
                    counter = frequency;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Fel Format i filen, se till att högsta rutan sparas som andra ordet på varje rad");
        }
        averageScore = (double) Math.round((averageScore/amountOfRows) * 100)/100;
    }

    private File getDirectory() {
        return directory;
    }
    private void setInformationInArray() {
        try {
            tableData = new String[amountOfColumns+1][2];
            tableData[0][0] = "Average score";
            tableData[0][1] = String.valueOf(averageScore);
            tableData[1][0] = "Highest tile";
            tableData[1][1] = String.valueOf(highestTile);
            tableData[2][0] = "Mode value";
            tableData[2][1] = String.valueOf(mostFrequentTile + " (" + frequency + ")");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Filen är tom: " + e.getMessage());
        }
    }
}


