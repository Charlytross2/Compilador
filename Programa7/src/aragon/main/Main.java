package aragon.main;

import fes.aragon.core.Core;
import fes.aragon.files.FileDataSource;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            int[][] matrix = FileDataSource.getMatrixFromFile();
            String[] language = FileDataSource.getLanguage();
            List<String> sources = FileDataSource.getSourceFromFile();
            Core core = new Core(matrix, language);

            System.out.println(Arrays.toString(language));

            for (String source : sources) {
                core.setToken(source);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}