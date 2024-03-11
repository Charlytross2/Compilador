package fes.aragon.program;

import java.util.ArrayList;
import java.util.List;

public class Core {
    private int index = 0;
    private final int error = -1;
    private final int accepted = 1;
    private String source;
    private List<String> sources;
    private List<Integer> results;

    public Core(List<String> sources) {
        this.sources = sources;
        this.results = new ArrayList<>();
        this.sources.forEach(src -> {
            this.source = src;
            results.add(statusA());
            this.index = 0;
        });
    }

    public int getAccepted() {
        return accepted;
    }

    public List<Integer> getResults() {
        return results;
    }

    private char nextCharacter() {
        char caracter = ' ';
        if (index < source.length()) {
            caracter = source.charAt(index);
            index++;
        }
        return caracter;
    }

    private int statusA() {
        char c = nextCharacter();
        return switch (c) {
            case '0' -> statusB();
            case '1' -> statusC();
            default -> error;
        };
    }

    private int statusB() {
        char c = nextCharacter();
        return switch (c) {
            case '0' -> statusB();
            case '1' -> statusC();
            default -> error;
        };
    }

    private int statusC() {
        char c = nextCharacter();
        return switch (c) {
            case '0' -> statusB();
            case '1' -> statusD();
            default -> error;
        };
    }

    private int statusD() {
        char c = nextCharacter();
        return switch (c) {
            case '0' -> statusE();
            case '1' -> statusD();
            case ' ' -> accepted;
            default -> error;
        };
    }

    private int statusE() {
        char c = nextCharacter();
        return switch (c) {
            case '0' -> statusE();
            case '1' -> statusC();
            case ' ' -> accepted;
            default -> error;
        };
    }
}
