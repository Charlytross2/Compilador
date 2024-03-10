package program;

import java.util.ArrayList;
import java.util.List;

public class Core {

    private final List<String> sources;
    private final List<Boolean> responses;


    public Core(List<String> sources) {
        this.sources = sources;
        this.responses = new ArrayList<>();
    }

    public List<Boolean> resolveSources() throws Exception {

        sources.forEach(source -> {
            int estado = 1;
            int columnaError = -1;
            boolean error = false;

            for (int i = 0; i <= source.length() - 1; i++) {
                char c = source.charAt(i);

                if (c != '0' && c != '1') {
                    error = true;
                    columnaError = i;
                    break;
                }

                switch (estado) {
                    case 1: // A
                        if (c == '0') estado = 2; // B
                        else if (c == '1') estado = 3; // C
                        break;
                    case 2: // B
                        if (c == '0') estado = 2; // B
                        else if (c == '1') estado = 3; // C
                        break;
                    case 3: // C
                        if (c == '0') estado = 2; // B
                        else if (c == '1') estado = 4; // D
                        break;
                    case 4: // D
                        if (c == '0') estado = 5; // E
                        else if (c == '1') estado = 4; // D
                        break;
                    case 5: // E
                        if (c == '0') estado = 5; // E
                        else if (c == '1') estado = 3; // C
                        break;
                }
            }
            if ((estado == 4 || estado == 5) && !error) {
                this.responses.add(true);
            } else {
                this.responses.add(false);
                if (error && columnaError >= 0)
                    System.out.printf("Caracter invalido en la columna %d", columnaError);
            }
        });

        return this.responses;
    }

}
