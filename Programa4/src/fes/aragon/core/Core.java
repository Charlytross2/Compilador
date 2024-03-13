package fes.aragon.core;

public class Core {
    public static final int ACCEPTED_STATE = 1;
    private int index = 0;
    private int error = -1;
    private int state = 0;
    private int input = 0;
    private String token;
    private int[][] matrix = null;

    public Core(int[][] matrix) {
        this.matrix = matrix;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private char nextCharacter() throws Exception {
        char c = ' ';

        if (index < token.length()) {
            c = token.charAt(index);
            if (Tools.endString(c)) {
                throw new Exception(token + "-> Invalid:Space between words in the column: " + (index + 1));
            }
            index++;
        }
        return c;
    }

    public int init() throws Exception {
        char c = ' ';
        clearValues();
        do {
            c = this.nextCharacter();
            if (Tools.isLetter(c)) {
                input = 0;
            } else if (Tools.isNumber(c)) {
                input = 1;
            } else if (Tools.endString(c)) {
                input = 2;
            } else {
                error = 0;
            }

            if (error != 0) {
                state = matrix[state][input];
            } else {
                throw new Exception("Invalid Word");
            }
        } while (!Tools.endString(c));

        return state;
    }

    private void clearValues() {
        index = 0;
        state = 0;
        input = 0;
    }
}
