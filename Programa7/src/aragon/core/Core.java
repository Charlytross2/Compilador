package aragon.core;

public class Core {

    private int[][] matrix;
    private String[] language;
    private String token;
    public Core(int[][] matrix, String[] language) {
        this.language = language;
        this.matrix = matrix;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
