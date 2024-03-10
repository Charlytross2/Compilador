package main;

import files.FontFileReader;
import program.Core;

import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> sources = FontFileReader.getSourceFromFile();
            Core core = new Core(sources);
            List<Boolean> results = core.resolveSources();

            IntStream.range(0, results.size()).forEach(index -> {
                String verification = results.get(index) ? "Valid" : "Invalid";
                System.out.println("Source:" + sources.get(index) + " is " + verification);
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}