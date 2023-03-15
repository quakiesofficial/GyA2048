package Statistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StatisticsFromFile {

    public static void main(String[] args) throws IOException {
        StatisticsFromFile stats = new StatisticsFromFile();
        System.out.println(stats.getAverageScore("files/scorefiler_algorithm.txt"));
    }

    private double getAverageScore(String directory) {
        double result = 0;
        String row;
        int amountOfRows = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(directory));
            row = bufferedReader.readLine();
            while (row != null) {
                String[] rowSplit = row.split(" ");
                result += Double.parseDouble(rowSplit[0]);
                row = bufferedReader.readLine();
                amountOfRows++;
            }
        } catch (IOException e) {
            System.out.println("Fel: " + e.getMessage());
        }
        return result/amountOfRows;
    }
}
