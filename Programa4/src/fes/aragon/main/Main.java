package fes.aragon.main;

import fes.aragon.core.Core;
import fes.aragon.files.FileDataSource;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            int[][] matrix = FileDataSource.getMatrixFromFile();
            List<String> sources = FileDataSource.getSourceFromFile();
            Core core = new Core(matrix);
            for (String source : sources) {
                core.setToken(source);
                int result = core.init();
                if (result == Core.ACCEPTED_STATE) {
                    System.out.println(source + " is valid");
                } else {
                    System.out.println(source + " is Invalid");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}