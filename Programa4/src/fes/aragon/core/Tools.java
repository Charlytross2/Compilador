package fes.aragon.core;

public class Tools {
    public static boolean isLetter(char c) {
        return (c >= 97 && c <= 122) || (c >= 66 && c <= 90);
    }

    public static boolean isNumber(char c) {
        return (c >= 48 && c <= 57);
    }

    public static boolean endString(char c) {
        return (c==32);
    }

}
