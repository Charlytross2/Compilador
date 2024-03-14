package fes.aragon.main;

import fes.aragon.core.Core;
import fes.aragon.files.FileDataSource;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            int[][] matrix = FileDataSource.getMatrixFromFile();
            char[] language = FileDataSource.getLanguage();
            List<String> sources = FileDataSource.getSourceFromFile();
            Core core = new Core(matrix, language);

            for (String source : sources) {
                core.setToken(source);
                int result = core.init();
                String response = result == Core.ACCEPTED_STATE ? " is Valid" : " is Invalid";
                System.out.println(source + response);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}