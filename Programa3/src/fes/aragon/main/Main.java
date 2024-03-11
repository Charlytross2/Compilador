package fes.aragon.main;

import fes.aragon.files.FontFileReader;
import fes.aragon.program.Core;

import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        try {
            List<String> sources = FontFileReader.getSourceFromFile();
            Core core = new Core(sources);
            List<Integer> results = core.getResults();
            IntStream.range(0, results.size()).forEach(index -> {
                String verification = results.get(index).equals(core.getAccepted()) ? "Valid" : "Invalid";
                System.out.println(sources.get(index) + "-> is " + verification);
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}