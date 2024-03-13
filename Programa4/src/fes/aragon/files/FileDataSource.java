package fes.aragon.files;

import fes.aragon.core.Tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FileDataSource {
    private static final String SOURCE_FILENAME = "fuente.txt";
    private static final String MATRIX_FILENAME = "matrix.txt";
    private static final List<String> sources = new ArrayList<>();
    private static int[][] matrix = null;

    public static int[][] getMatrixFromFile() throws Exception {
        List<String> lines = Files.readAllLines(getPath(FileDataSource.MATRIX_FILENAME));

        String[] size = getSplitLine(lines.get(0));
        if (size.length != 2) {
            throw new IllegalArgumentException("Invalid array size");
        }
        String rows = size[0], cols = size[1];
        if (!isNumber(rows) && !isNumber(cols)) {
            throw new IllegalArgumentException("The arguments must be numbers");
        }
        matrix = new int[Integer.parseInt(rows)][Integer.parseInt(cols) + 1];

        AtomicInteger row = new AtomicInteger(0);
        lines.subList(1, lines.size()).forEach(line -> {
            String[] parts = getSplitLine(line);
            int currentRow = row.getAndIncrement();
            for (int col = 0; col < parts.length; col++) {
                matrix[currentRow][col] = Integer.parseInt(parts[col]);
            }
        });

        return matrix;
    }

    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static List<String> getSourceFromFile() throws IOException {

        Path path = getPath(FileDataSource.SOURCE_FILENAME);
        sources.addAll(Files.readAllLines(path));

        if (sources.isEmpty()) {
            throw new IOException("Source Empty");
        }

        return sources;
    }

    private static String[] getSplitLine(String line) {
        return line.split(" ");
    }

    private static Path getPath(String filePath) {
        return Paths.get(filePath);
    }

}
