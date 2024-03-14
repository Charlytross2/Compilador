package aragon.core;

public class Core {

    private final int[][] matrix;
    private final char[] language;
    private String token;

    public static final int ACCEPTED_STATE = 1;
    private int index = 0;
    private int state = 0;
    private int column = 0;

    public Core(int[][] matrix, char[] language) {
        this.language = language;
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
            boolean characterFound = false;
            c = this.nextCharacter();
            int isFound = verifyInLanguage(c);

            if (Tools.isNumber(c) && isFound < 0) {
                c = 'D';
            }
            else if (Tools.isLetter(c) && c != 'D' && c != 'L' && isFound < 0) {
                c = 'L';
            }

            isFound = verifyInLanguage(c);

            if (isFound >= 0) {
                characterFound = true;
                column = isFound;
            } else {
                if (Tools.endString(c)) {
                    column = matrix[0].length - 1;
                    characterFound = true;
                } else {
                    throw new Exception("The character " + c + " isnt part of the language");
                }
            }

            if (characterFound) {
                state = matrix[state][column];
            } else {
                throw new Exception("Invalid Word");
            }

        } while (!Tools.endString(c));

        return state;
    }

    private int verifyInLanguage(char c) {
        for (int i = 0; i < language.length; i++) {
            if (language[i] == c) {
                return i;
            }
        }
        return -1;
    }

    private void clearValues() {
        index = 0;
        state = 0;
        column = 0;
    }

}
