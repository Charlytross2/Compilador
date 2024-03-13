package fes.aragon.main;

import fes.aragon.files.FontFileReader;
import fes.aragon.program.Core;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> sources = FontFileReader.getSourceFromFile();
            Core core = new Core(sources);
            List<Map<String, Boolean>> results = core.resolveSources();

            IntStream.range(0, results.size()).forEach(index -> {
                Object[] keys = results.get(index).keySet().toArray();
                Object[] values = results.get(index).values().toArray();

                System.out.println(sources.get(index) + ":");
                System.out.println(keys[0] + "->" + values[0]);
                System.out.println(keys[1] + "->" + values[1]);
                System.out.println();
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}