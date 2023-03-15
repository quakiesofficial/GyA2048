package Statistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StatisticsFromFile {

    public static void main(String[] args) throws IOException {
        StatisticsFromFile stats = new StatisticsFromFile();
        stats.getAverageScore("files/scorefiler_algorithm.txt");
    }

    private double getAverageScore(String directory) {
        double result;
        String row = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(directory));
            while (row != null) {
                row = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Fel: " + e.getMessage());
        }
        return 0;
    }
}
